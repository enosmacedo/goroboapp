package br.escolaprogramacao.robotmaker;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import br.escolaprogramacao.robotmaker.bluetooth.ConnectionThread;
import br.escolaprogramacao.robotmaker.bluetooth.PairedDevices;

import static br.escolaprogramacao.robotmaker.MainActivity.SELECT_PAIRED_DEVICE;

public class Kit1Activity extends AppCompatActivity {
    boolean isFABOpen;
    boolean isFABSecondOpen;
    private FloatingActionButton fab;
    private FloatingActionButton fab_second;
    private FloatingActionButton fab_arrow_down;
    private FloatingActionButton fab_arrow_left;
    private FloatingActionButton fab_erase;
    private FloatingActionButton fab_delete;
    private FloatingActionButton fab_arrow_right;
    private FloatingActionButton fab_arrow_up;
    private FloatingActionButton fab_stop;
    private FloatingActionButton fab_link;
    private FloatingActionButton fab_settings;
    private FloatingActionButton fab_move;
    private FloatingActionButton fab_seta_reta;
    private FloatingActionButton fab_seta_start;
    private FloatingActionButton fab_seta_finish;
    public static WebAppInterfaceKit1 interface_android_web;

    MenuItem miSettings;
    MenuItem miSave ;
    MenuItem miLoad ;
    MenuItem miReset;
    MenuItem miRun ;
    MenuItem miDebug ;
    MenuItem miBluetooth ;
    WebView webview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kit1);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Go Robo!");
//        toolbar.setTitleTextColor(Color.BLACK);
//        toolbar.setBackgroundColor(Color.WHITE);



        webview = (WebView) findViewById(R.id.wv_kit1_activity_site);
        webview.loadUrl("file:///android_asset/site/index.html");

        interface_android_web = new WebAppInterfaceKit1(this, webview);

//        webview.setVerticalScrollBarEnabled(true);
//        webview.setHorizontalScrollBarEnabled(true);
//        webview.setScrollContainer(true);
        WebSettings setting = webview.getSettings();
        setting.setJavaScriptEnabled(true);
//        setting.setBuiltInZoomControls(true);
//        setting.setDisplayZoomControls(false);
        setting.setLoadWithOverviewMode(true);
        //setting.setSupportZoom(true);
//        setting.setUseWideViewPort(true);
        //setting.setLoadWithOverviewMode(true);
        webview.addJavascriptInterface(interface_android_web, "AndroidInterface");


        fab = (FloatingActionButton) findViewById(R.id.fab_kit1_activity_main);
        fab_second = (FloatingActionButton) findViewById(R.id.fab_kit1_activity_second);

        fab_seta_start = (FloatingActionButton) findViewById(R.id.fab_kit1_activity_start);
        fab_seta_finish = (FloatingActionButton) findViewById(R.id.fab_kit1_activity_finish);
        fab_seta_reta = (FloatingActionButton) findViewById(R.id.fab_kit1_activity_seta_reta);
        fab_erase = (FloatingActionButton) findViewById(R.id.fab_kit1_activity_erase);
        fab_delete = (FloatingActionButton) findViewById(R.id.fab_kit1_activity_delete);
        fab_arrow_down = (FloatingActionButton) findViewById(R.id.fab_kit1_activity_arrow_down);
        fab_arrow_left = (FloatingActionButton) findViewById(R.id.fab_kit1_activity_arrow_left);
        fab_arrow_right = (FloatingActionButton) findViewById(R.id.fab_kit1_activity_arrow_right);
        fab_arrow_up = (FloatingActionButton) findViewById(R.id.fab_kit1_activity_arrow_up);
        fab_stop = (FloatingActionButton) findViewById(R.id.fab_kit1_activity_stop);
        fab_link = (FloatingActionButton) findViewById(R.id.fab_kit1_activity_arrow_link);
        fab_settings = (FloatingActionButton) findViewById(R.id.fab_kit1_activity_settings);
        fab_move = (FloatingActionButton) findViewById(R.id.fab_kit1_activity_move);

        configurar_float_action_bar ();

        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mReceiver, filter);
//
//        mWebView.post(new Runnable() {
//            @Override
//            public void run() {
//                mWebView.loadUrl(...).
//            }
//        });

//        crud = new BancoController(getBaseContext());
//        //crud.insereDado("P1","0013A2004089E0AE","0013A2004089E0AE", TipoPeriferico.IRRIGACAO.getValue());
//        //crud.insereDado("P2","0013A20040A9CF75","0013A20040A9CF75", TipoPeriferico.SENSORIAMENTO.getValue());
    }

    private void configurar_float_action_bar () {
        fab_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interface_android_web.set_qual_tipo_novo_node_java(300);
                interface_android_web.update_status_java();
            }
        });

        fab_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interface_android_web.set_qual_tipo_novo_node_java(200);
                interface_android_web.update_status_java();
            }
        });

        fab_arrow_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interface_android_web.set_qual_tipo_novo_node_java(1);
                interface_android_web.update_status_java();
            }
        });

        fab_arrow_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interface_android_web.set_qual_tipo_novo_node_java(2);
                interface_android_web.update_status_java();
            }
        });

        fab_arrow_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interface_android_web.set_qual_tipo_novo_node_java(3);
                interface_android_web.update_status_java();
            }
        });

        fab_arrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interface_android_web.set_qual_tipo_novo_node_java(4);
                interface_android_web.update_status_java();
            }
        });


        fab_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interface_android_web.set_qual_tipo_novo_node_java(5);
                interface_android_web.update_status_java();
            }
        });

        fab_seta_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interface_android_web.set_qual_tipo_novo_node_java(6);
                interface_android_web.update_status_java();
            }
        });

        fab_seta_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interface_android_web.set_qual_tipo_novo_node_java(7);
                interface_android_web.update_status_java();
            }
        });

        fab_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interface_android_web.set_qual_tipo_novo_node_java(101);
                interface_android_web.update_status_java();
            }
        });

        fab_seta_reta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interface_android_web.set_qual_tipo_novo_node_java(102);
                interface_android_web.update_status_java();
            }
        });


        fab_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interface_android_web.set_qual_tipo_novo_node_java(103);
                interface_android_web.update_status_java();
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });

        fab_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABSecondOpen){
                    showFABMenuSecond();
                }else{
                    closeFABMenuSecond();
                }
            }
        });
    }

    private void showFABMenu(){
        isFABOpen=true;
        fab_arrow_down.animate().translationY(-getResources().getDimension(R.dimen.standard_primeiro));
        fab_arrow_left.animate().translationY(-getResources().getDimension(R.dimen.standard_segundo));
        fab_arrow_right.animate().translationY(-getResources().getDimension(R.dimen.standard_terceiro));
        fab_arrow_up.animate().translationY(-getResources().getDimension(R.dimen.standard_quarto));
        fab_stop.animate().translationY(-getResources().getDimension(R.dimen.standard_quinto));
        fab_seta_start.animate().translationY(-getResources().getDimension(R.dimen.standard_sexto));
        fab_seta_finish.animate().translationY(-getResources().getDimension(R.dimen.standard_setimo));
    }

    private void closeFABMenu(){
        isFABOpen=false;
        fab_arrow_down.animate().translationY(0);
        fab_arrow_left.animate().translationY(0);
        fab_arrow_right.animate().translationY(0);
        fab_arrow_up.animate().translationY(0);
        fab_stop.animate().translationY(0);
        fab_seta_start.animate().translationY(0);
        fab_seta_finish.animate().translationY(0);
    }

    private void showFABMenuSecond(){
        isFABSecondOpen=true;
        fab_link.animate().translationY(-getResources().getDimension(R.dimen.standard_primeiro));
        fab_seta_reta.animate().translationY(-getResources().getDimension(R.dimen.standard_segundo));
        fab_settings.animate().translationY(-getResources().getDimension(R.dimen.standard_terceiro));
        fab_move.animate().translationY(-getResources().getDimension(R.dimen.standard_quarto));
        fab_erase.animate().translationY(-getResources().getDimension(R.dimen.standard_quinto));
        fab_delete.animate().translationY(-getResources().getDimension(R.dimen.standard_sexto));
    }

    private void closeFABMenuSecond(){
        isFABSecondOpen=false;
        fab_link.animate().translationY(0);
        fab_settings.animate().translationY(0);
        fab_move.animate().translationY(0);
        fab_erase.animate().translationY(0);
        fab_delete.animate().translationY(0);
        fab_seta_reta.animate().translationY(0);
    }


    public static Handler handler_bluetooth = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            byte[] data = bundle.getByteArray("data");
            String dataString= new String(data);

            if(dataString.equals("---N"))
                //Toast.makeText(, "Ocorreu um erro durante a conexão ", Toast.LENGTH_LONG).show();
                MainActivity.pronto_para_mandar_mensagem = true;
            else if(dataString.equals("---S"))
                MainActivity.pronto_para_mandar_mensagem = false;
            //Toast.makeText(MainActivity.this,"Conectado ", Toast.LENGTH_LONG).show();
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_settings, menu);

        miSettings = (MenuItem) menu.findItem(R.id.am_menu_main_settings);
        miSave = (MenuItem) menu.findItem(R.id.am_menu_main_save_project);
        miLoad = (MenuItem) menu.findItem(R.id.am_menu_main_load_project);
        miReset = (MenuItem) menu.findItem(R.id.am_menu_main_reset_project);
        miRun = (MenuItem) menu.findItem(R.id.am_menu_main_run_project);
        miDebug = (MenuItem) menu.findItem(R.id.am_menu_main_debug_project);
        miBluetooth = (MenuItem) menu.findItem(R.id.am_menu_main_bluetooth);

        miSave.setEnabled(true);
        miLoad.setEnabled(true);
        miReset.setEnabled(true);
        miRun.setEnabled(true);
        miDebug.setEnabled(false);
        miBluetooth.setEnabled(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.am_menu_main_settings) {
            //interface_android_web.load_graph_js();
            return true;
        }else if (id == R.id.am_menu_main_save_project) {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
            return true;
        }else if (id == R.id.am_menu_main_load_project) {
            Toast.makeText(this, "Load", Toast.LENGTH_SHORT).show();
            return true;
        }else if (id == R.id.am_menu_main_reset_project) {
            delete_projetct();
            return true;
        }else if (id == R.id.am_menu_main_run_project) {
            run_project();
            return true;
        }else if (id == R.id.am_menu_main_debug_project) {
            Toast.makeText(this, "Debug", Toast.LENGTH_SHORT).show();
            return true;
        }else if (id == R.id.am_menu_main_bluetooth) {
            MainActivity.btAdapter = BluetoothAdapter.getDefaultAdapter();
            if (MainActivity.btAdapter == null) {
                Toast.makeText(Kit1Activity.this,"Que pena! Hardware Bluetooth não está funcionando ", Toast.LENGTH_SHORT).show();
            } else {
                if ( MainActivity.btAdapter.isEnabled()) {
                    searchPairedDevices(Kit1Activity.this);
                } else {
                    MainActivity.btAdapter.enable();
                }
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int bluetoothState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
                        BluetoothAdapter.ERROR);
                switch (bluetoothState) {
                    case BluetoothAdapter.STATE_ON:
                        searchPairedDevices(Kit1Activity.this);
                        break;
                }
            }
        }
    };

    private void run_project() {
        if (webview != null) {
            interface_android_web.form_graph_java();
        } else {
            Toast.makeText(Kit1Activity.this,"Não é possível executar", Toast.LENGTH_SHORT).show();
        }
    }

    private void delete_projetct() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Kit1Activity.this);
        builder.setCancelable(true);
        builder.setTitle("Deletar projeto");
        builder.setMessage("Deseja realmente deletar");
        builder.setPositiveButton("Confirmar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        interface_android_web.delete_graph_js();
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void searchPairedDevices(Context c) {
        Intent searchPairedDevicesIntent = new Intent(c, PairedDevices.class);
        startActivityForResult(searchPairedDevicesIntent, SELECT_PAIRED_DEVICE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == MainActivity.ENABLE_BLUETOOTH) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(Kit1Activity.this,"Bluetooth ativado :D", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(Kit1Activity.this,"Bluetooth não ativado ", Toast.LENGTH_SHORT).show();
            }
        } else if(requestCode == SELECT_PAIRED_DEVICE || requestCode == MainActivity.SELECT_DISCOVERED_DEVICE) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(Kit1Activity.this,"Você selecionou " + data.getStringExtra("btDevName") + "\n"
                        + data.getStringExtra("btDevAddress"), Toast.LENGTH_LONG).show();

                MainActivity.connect = new ConnectionThread(data.getStringExtra("btDevAddress"));
                MainActivity.connect.start();
//                Toast.makeText(Kit1Activity.this,"Conexão Criada ", Toast.LENGTH_LONG).show();

                miRun.setEnabled(true);
                miDebug.setEnabled(true);
            }
            else {
                Toast.makeText(Kit1Activity.this,"Nenhum dispositivo selecionado :(", Toast.LENGTH_LONG).show();
            }
        }
    }

}
