package br.escolaprogramacao.robotmaker;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import br.escolaprogramacao.robotmaker.bluetooth.BancoController;
import br.escolaprogramacao.robotmaker.bluetooth.ConnectionThread;

public class MainActivity extends AppCompatActivity {

//    void setup() {
//        // put your setup code here, to run once:
//        Serial.begin(9600);
//
//    }
//
//    void loop() {
//        // put your main code here, to run repeatedly:
//        if (Serial.available() > 0 )  {
//            Serial.println(Serial.readString());
//        }
//    }

    public static int ENABLE_BLUETOOTH = 1;
    public static int SELECT_PAIRED_DEVICE = 2;
    public static int SELECT_DISCOVERED_DEVICE = 3;
    public static BluetoothAdapter btAdapter;
    public static boolean pronto_para_mandar_mensagem = false;
    public static ConnectionThread connect;
    public static BancoController crud;


    Button btn_go_kit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        toolbar.setTitleTextColor(Color.BLACK);
//        toolbar.setBackgroundColor(Color.WHITE);
//        getSupportActionBar
//        toolbar.set(Color.WHITE);

        View root = findViewById(R.id.mainlayout);
        root.setBackgroundColor(Color.WHITE);

//        getSupportActionBar().setIcon(R.drawable.logo03);
//        getSupportActionBar().setLogo(R.drawable.logo03);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_go_kit1 = (Button) findViewById(R.id.btn_main_activity_go_kit1);
//        btn_go_kit1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        btn_go_kit1.setBackgroundColor(Color.WHITE);
        btn_go_kit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Kit1Activity.class);
                startActivity(i);
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_settings, menu);

        MenuItem miSettings = (MenuItem) menu.findItem(R.id.am_menu_main_settings);
        MenuItem miSave = (MenuItem) menu.findItem(R.id.am_menu_main_save_project);
        MenuItem miLoad = (MenuItem) menu.findItem(R.id.am_menu_main_load_project);
        MenuItem miReset = (MenuItem) menu.findItem(R.id.am_menu_main_reset_project);
        MenuItem miRun = (MenuItem) menu.findItem(R.id.am_menu_main_run_project);
        MenuItem miDebug = (MenuItem) menu.findItem(R.id.am_menu_main_debug_project);
        MenuItem miBluetooth = (MenuItem) menu.findItem(R.id.am_menu_main_bluetooth);

        miSave.setEnabled(false);
        miLoad.setEnabled(false);
        miReset.setEnabled(false);
        miRun.setEnabled(false);
        miDebug.setEnabled(false);
        miBluetooth.setEnabled(false);

        return true;
    }

}
