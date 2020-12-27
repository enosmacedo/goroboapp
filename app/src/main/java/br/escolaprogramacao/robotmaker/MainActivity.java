package br.escolaprogramacao.robotmaker;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import br.escolaprogramacao.robotmaker.bluetooth.BluetoothManager;

public class MainActivity extends AppCompatActivity {

    public static int ENABLE_BLUETOOTH = 1;
    public static int SELECT_PAIRED_DEVICE = 2;
    public static int SELECT_DISCOVERED_DEVICE = 3;
    public static BluetoothAdapter btAdapter;

    private MenuItem miSettings;
    private MenuItem miSave;
    private MenuItem miLoad;
    private MenuItem miReset;
    private MenuItem miRun;
    private MenuItem miDebug;
    private MenuItem miBluetooth;

    private ListView lv;
    private Button btn_go_robert;
    private Button btn_at_hungria;
    private Button btn_go_marian;
    private Button btn_at_chess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar_in_hungria);
        setSupportActionBar(toolbar);

        View root = findViewById(R.id.mainlayout);
        root.setBackgroundColor(Color.WHITE);

        btn_go_robert = (Button) findViewById(R.id.btn_main_activity_go_kit1);
        btn_go_robert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RobertActivity.class);
                startActivity(i);
                if (BluetoothManager.getSocket() != null && BluetoothManager.getSocket().isConnected()) {
                } else {
                    Toast.makeText(getBaseContext(), "É necessário se conectar via BLuetooth inicialmente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_at_hungria = (Button) findViewById(R.id.btn_main_activity_in_hungria);
        btn_at_hungria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AtHungriaActivity.class);
                startActivity(i);
                if (BluetoothManager.getSocket() != null && BluetoothManager.getSocket().isConnected()) {
                } else {
                    Toast.makeText(getBaseContext(), "É necessário se conectar via BLuetooth inicialmente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_at_chess = (Button) findViewById(R.id.btn_main_activity_at_chess);
        btn_at_chess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AtChessActivity.class);
                startActivity(i);
                if (BluetoothManager.getSocket() != null && BluetoothManager.getSocket().isConnected()) {
                } else {
                    Toast.makeText(getBaseContext(), "É necessário se conectar via BLuetooth inicialmente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_go_marian = (Button) findViewById(R.id.btn_main_activity_go_marian);
        btn_go_marian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MarianActivity.class);
                startActivity(i);
//                if (BluetoothManager.getSocket() != null && BluetoothManager.getSocket().isConnected()) {
//                } else {
//                    Toast.makeText(getBaseContext(), "É necessário se conectar via BLuetooth inicialmente", Toast.LENGTH_SHORT).show();
//                }
            }
        });

    }

//    @Override
//    public boolean onMenuOpened(int featureId, Menu menu) {
//        if (BluetoothManager.getSocket() != null && BluetoothManager.getSocket().isConnected()) {
//            miBluetooth.setEnabled(false);
//        } else {
//            miBluetooth.setEnabled(true);
//        }
//
//        return super.onMenuOpened(featureId, menu);
//    }

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

        miSettings.setEnabled(false);
        miSave.setEnabled(false);
        miLoad.setEnabled(false);
        miReset.setEnabled(false);
        miRun.setEnabled(false);
        miDebug.setEnabled(false);
        miBluetooth.setEnabled(true);

        miBluetooth.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (!BluetoothManager.getBluetoothManager(MainActivity.this).isEnabled()) {
                    Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    MainActivity.this.startActivityForResult(turnOn, 0);
                    Toast.makeText(MainActivity.this, "Autorize o bluetooth e depois selecione essa opção novamente", Toast.LENGTH_LONG).show();
                    return false;
                } else if (BluetoothManager.getSocket() == null || BluetoothManager.getSocket().isConnected()) {
                    BluetoothManager.getBluetoothManager(MainActivity.this).choose_paired_devices(MainActivity.this);
                } else {
                    Toast.makeText(getBaseContext(), "Dispositivo não suporta o BLuetooth", Toast.LENGTH_SHORT).show();
                }
                return true;
            }

            ;
        });
        return true;
    }

}






