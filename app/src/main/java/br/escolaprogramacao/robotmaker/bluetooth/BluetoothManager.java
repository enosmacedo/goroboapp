package br.escolaprogramacao.robotmaker.bluetooth;

import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.ParcelUuid;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import br.escolaprogramacao.robotmaker.MainActivity;
import br.escolaprogramacao.robotmaker.R;
import br.escolaprogramacao.robotmaker.RobertActivity;

import static br.escolaprogramacao.robotmaker.MainActivity.SELECT_PAIRED_DEVICE;

public  class BluetoothManager {

    private static BluetoothManager instance;
    private static BluetoothAdapter BA;
    String myUUID = "00001101-0000-1000-8000-00805F9B34FB";
    private Set<BluetoothDevice> pairedDevices;
    //private static ConnectedThread mConnectedThread;
    Handler bluetoothIn;
    private static BluetoothSocket btSocket = null;
    private StringBuilder recDataString = new StringBuilder();
    final int handlerState = 0;                        //used to identify handler message
    private static OutputStream mmOutputStream;
    private static InputStream mmInputStream;
    private static Thread workerThread;
    private static byte[] readBuffer;
    private static int readBufferPosition;
    private static int counter;
    private static volatile boolean stopWorker;

    private BluetoothManager(final Context c) {
        BA = BluetoothAdapter.getDefaultAdapter();


    }

    public void choose_paired_devices (final Context c) {
        final Dialog dialog = new Dialog(c);
        dialog.setContentView(R.layout.layout_dispositivos_pareados_bluetooth);
        dialog.setTitle("Title...");
        ListView bluetooth_devices = (ListView) dialog.findViewById(R.id.lv_layout_dispositivos_p_b);

        pairedDevices = BA.getBondedDevices();
        ArrayList list = new ArrayList();

        if (pairedDevices.size() <= 0 ) {
            Toast.makeText(c,"Não há dispositivos pareados", Toast.LENGTH_LONG).show();
        } else {
            for (BluetoothDevice bt : pairedDevices) list.add(bt.getName());

            final ArrayAdapter adapter = new ArrayAdapter(c, android.R.layout.simple_list_item_1, list);
            bluetooth_devices.setAdapter(adapter);
            bluetooth_devices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    BluetoothDevice b = (BluetoothDevice) pairedDevices.toArray()[arg2];
                    try {
                        btSocket = createBluetoothSocket(b);
                    } catch (IOException e) {
                        Toast.makeText(c, "Criação de canal de comunicação falhou", Toast.LENGTH_LONG).show();
                    }
                    try{
                        btSocket.connect();
                        mmOutputStream = btSocket.getOutputStream();
                        mmInputStream = btSocket.getInputStream();
                    } catch (IOException e) {
                        try {
                            btSocket.close();
                            Toast.makeText(c, "Conexão falhou e canal de comunicação foi fechado", Toast.LENGTH_LONG).show();
                        } catch (IOException e2) {
                            Toast.makeText(c, "Conexão falhou e canal de comunicação não foi fechado", Toast.LENGTH_LONG).show();
                        }
                        dialog.dismiss();
                    }
                    //mConnectedThread = new ConnectedThread(btSocket);
                    //mConnectedThread.start();
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
    }

    public static void write(String input, Context c) {
        if (mmOutputStream != null) {
//            mmOutputStream .write(input, c);
            try {
                mmOutputStream.write(input.getBytes());                //write bytes over BT connection via outstream
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isEnabled() {
        if (BA != null) {
            return BA.isEnabled();
        } else {
            return false;
        }
    }

    public BluetoothAdapter getBA() {
        return BA;
    }

    public static BluetoothManager getBluetoothManager(Context c) {
        if (instance == null) {
            instance = new BluetoothManager(c);
            return instance;
        } else  {
            return instance;
        }
    }

    public static BluetoothSocket getSocket() {
        if (instance != null && btSocket != null) {
            return btSocket;
        } else  {
            return null;
        }
    }


    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {

        return  device.createRfcommSocketToServiceRecord(UUID.fromString(myUUID));
        //creates secure outgoing connecetion with BT device using UUID
    }





//
//    //create new class for connect thread
//    private class ConnectedThread extends Thread {
//        private  InputStream mmInStream;
//        private  OutputStream mmOutStream;
//
//        //creation of the connect thread
//        public ConnectedThread(BluetoothSocket socket) {
//            try {
//                mmInStream = socket.getInputStream();
//                mmOutStream = socket.getOutputStream();
//            } catch (IOException e) {
//
//            }
//        }
//
//        public void run() {
//            byte[] buffer = new byte[256];
//            int bytes;
//
//            // Keep looping to listen for received messages
//            while (true) {
//                try {
//                    bytes = mmInStream.read(buffer);            //read bytes from input buffer
//                    String readMessage = new String(buffer, 0, bytes);
//                    // Send the obtained bytes to the UI Activity via handler
//                    bluetoothIn.obtainMessa ge(handlerState, bytes, -1, readMessage).sendToTarget();
//                } catch (IOException e) {
//                    break;
//                }
//            }
//        }
//
//        //write method
//        private void write(String input, Context c) {
//            byte[] msgBuffer = input.getBytes();           //converts entered String into bytes
//            try {
//                mmOutStream.write(msgBuffer);                //write bytes over BT connection via outstream
//            } catch (IOException e) {
//                //if you cannot write, close the application
//                Toast.makeText(c, "Connection Failure", Toast.LENGTH_LONG).show();
//                //finish();
//
//            }
//        }
//    }


    public void closeListenBluetooth() {
        stopWorker = true;
    }




    public static void beginListenForData(final BluetoothListenInterface func) {
        final Handler handler = new Handler();
        final byte delimiter = 10; //This is the ASCII code for a newline character

        stopWorker = false;
        readBufferPosition = 0;
        readBuffer = new byte[1024];
        workerThread = new Thread(new Runnable() {
            public void run(){
                while(!Thread.currentThread().isInterrupted() && !stopWorker) {
                    try {
                        int bytesAvailable = mmInputStream.available();
                        if(bytesAvailable > 0) {
                            byte[] packetBytes = new byte[bytesAvailable];
                            mmInputStream.read(packetBytes);
                            for(int i=0;i<bytesAvailable;i++) {
                                byte b = packetBytes[i];
                                if(b == delimiter) {
                                    byte[] encodedBytes = new byte[readBufferPosition];
                                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                    final String data = new String(encodedBytes, "US-ASCII");
                                    readBufferPosition = 0;

                                    handler.post(new Runnable() {
                                        public void run() {
                                            func.testFunkcija(data);
                                       }
                                    });
                                }
                                else {
                                    readBuffer[readBufferPosition++] = b;
                                }
                            }
                        }
                    }
                    catch (IOException ex) {
                        stopWorker = true;
                    }
                }
            }
        });

        workerThread.start();
    }



}
