package br.escolaprogramacao.robotmaker;

import android.content.DialogInterface;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.escolaprogramacao.robotmaker.bluetooth.BluetoothListenInterface;
import br.escolaprogramacao.robotmaker.bluetooth.BluetoothManager;

public class RobertActivity extends AppCompatActivity {
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

    private WebAppInterfaceRobert interface_android_web;
    private RobertBluetoothInterface bluetooth_interface = new RobertBluetoothInterface();

    private MenuItem miSettings;
    private MenuItem miSave;
    private MenuItem miLoad;
    private MenuItem miReset;
    private MenuItem miRun;
    private MenuItem miDebug;
    private MenuItem miBluetooth;
    private WebView webview;

    private AlertDialog alerta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robert);

        if (BluetoothManager.getSocket() != null && BluetoothManager.getSocket().isConnected()) {
        } else {
            Toast.makeText(getBaseContext(), "É necessário se conectar via BLuetooth inicialmente", Toast.LENGTH_SHORT).show();
        }

//        if (BluetoothManager.getSocket() != null && BluetoothManager.getSocket().isConnected()) {
//        } else {
//            Toast.makeText(getBaseContext(), "Sem uma conexão bluetooth não é possível ficar nesse módulo", Toast.LENGTH_SHORT).show();
////            RobertActivity.this.finish();
//        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Go Robo!");

        webview = (WebView) findViewById(R.id.wv_robert_activity_site);
        webview.loadUrl("file:///android_asset/site/index.html");
        interface_android_web = new WebAppInterfaceRobert(this, webview);

        WebSettings setting = webview.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setLoadWithOverviewMode(true);
        //setting.setSupportZoom(true);
        //setting.setUseWideViewPort(true);
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

        configurar_float_action_bar();
    }

    private void configurar_float_action_bar() {
        fab_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interface_android_web.set_qual_tipo_novo_node_java(300);
            }
        });

        fab_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interface_android_web.set_qual_tipo_novo_node_java(200);
            }
        });

        fab_arrow_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interface_android_web.set_qual_tipo_novo_node_java(1);
            }
        });

        fab_arrow_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interface_android_web.set_qual_tipo_novo_node_java(2);
            }
        });

        fab_arrow_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interface_android_web.set_qual_tipo_novo_node_java(3);
            }
        });

        fab_arrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interface_android_web.set_qual_tipo_novo_node_java(4);
            }
        });


        fab_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interface_android_web.set_qual_tipo_novo_node_java(5);
            }
        });

        fab_seta_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interface_android_web.set_qual_tipo_novo_node_java(6);
            }
        });

        fab_seta_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interface_android_web.set_qual_tipo_novo_node_java(7);
            }
        });

        fab_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interface_android_web.set_qual_tipo_novo_node_java(101);
            }
        });

        fab_seta_reta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interface_android_web.set_qual_tipo_novo_node_java(102);
            }
        });


        fab_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interface_android_web.set_qual_tipo_novo_node_java(103);
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFABOpen) {
                    showFABMenu();
                } else {
                    closeFABMenu();
                }
            }
        });

        fab_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFABSecondOpen) {
                    showFABMenuSecond();
                } else {
                    closeFABMenuSecond();
                }
            }
        });
    }

    private void showFABMenu() {
        isFABOpen = true;
        fab_arrow_down.animate().translationY(-getResources().getDimension(R.dimen.standard_primeiro));
        fab_arrow_left.animate().translationY(-getResources().getDimension(R.dimen.standard_segundo));
        fab_arrow_right.animate().translationY(-getResources().getDimension(R.dimen.standard_terceiro));
        fab_arrow_up.animate().translationY(-getResources().getDimension(R.dimen.standard_quarto));
        fab_stop.animate().translationY(-getResources().getDimension(R.dimen.standard_quinto));
        fab_seta_start.animate().translationY(-getResources().getDimension(R.dimen.standard_sexto));
        fab_seta_finish.animate().translationY(-getResources().getDimension(R.dimen.standard_setimo));
    }

    private void closeFABMenu() {
        isFABOpen = false;
        fab_arrow_down.animate().translationY(0);
        fab_arrow_left.animate().translationY(0);
        fab_arrow_right.animate().translationY(0);
        fab_arrow_up.animate().translationY(0);
        fab_stop.animate().translationY(0);
        fab_seta_start.animate().translationY(0);
        fab_seta_finish.animate().translationY(0);
    }

    private void showFABMenuSecond() {
        isFABSecondOpen = true;
        fab_link.animate().translationY(-getResources().getDimension(R.dimen.standard_primeiro));
        fab_seta_reta.animate().translationY(-getResources().getDimension(R.dimen.standard_segundo));
        fab_settings.animate().translationY(-getResources().getDimension(R.dimen.standard_terceiro));
        fab_move.animate().translationY(-getResources().getDimension(R.dimen.standard_quarto));
        fab_erase.animate().translationY(-getResources().getDimension(R.dimen.standard_quinto));
        fab_delete.animate().translationY(-getResources().getDimension(R.dimen.standard_sexto));
    }

    private void closeFABMenuSecond() {
        isFABSecondOpen = false;
        fab_link.animate().translationY(0);
        fab_settings.animate().translationY(0);
        fab_move.animate().translationY(0);
        fab_erase.animate().translationY(0);
        fab_delete.animate().translationY(0);
        fab_seta_reta.animate().translationY(0);
    }

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
        miBluetooth.setEnabled(false);
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
            exemplo_layout();
            return true;
        } else if (id == R.id.am_menu_main_save_project) {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.am_menu_main_load_project) {
            Toast.makeText(this, "Load", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.am_menu_main_reset_project) {
            delete_projetct();
            return true;
        } else if (id == R.id.am_menu_main_run_project) {
            run_project();
            return true;
        } else if (id == R.id.am_menu_main_debug_project) {
            Toast.makeText(this, "Debug", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.am_menu_main_bluetooth) {
        }

        return super.onOptionsItemSelected(item);
    }


    private void exemplo_layout() {
        //LayoutInflater é utilizado para inflar nosso layout em uma view.
        //-pegamos nossa instancia da classe
        LayoutInflater li = getLayoutInflater();

        //inflamos o layout alerta.xml na view
        View view = li.inflate(R.layout.layout_setting_robert, null);
//        final EditText tv = (EditText) view.findViewById(R.id.tv_name_lay_setting_robert);
        //definimos para o botão do layout um clickListener
        view.findViewById(R.id.bt_dimiss_lay_setting_robert).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                //exibe um Toast informativo.
                Toast.makeText(RobertActivity.this, "alerta.dismiss()", Toast.LENGTH_SHORT).show();

//                String nome = tv.getText().toString();
//                Toast.makeText(getBaseContext(), nome, Toast.LENGTH_LONG).show();
                //desfaz o alerta.
                alerta.dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Configuraçcões do Robert");
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
    }


    private void run_project() {
        if (webview != null) {
            interface_android_web.form_graph_java();
        } else {
            Toast.makeText(RobertActivity.this, "Não é possível executar", Toast.LENGTH_SHORT).show();
            return;
        }

//        if (BluetoothManager.getSocket() != null && BluetoothManager.getSocket().isConnected()) {
//        } else {
//            Toast.makeText(getBaseContext(), "Sem uma conexão bluetooth não é possível executar", Toast.LENGTH_LONG).show();
//            return;
//        }

//        Toast.makeText(RobertActivity.this, "Pronto para começar", Toast.LENGTH_LONG).show();
//        BluetoothManager.beginListenForData(bluetooth_interface);
//        BluetoothManager.getBluetoothManager(RobertActivity.this).write("asdas", RobertActivity.this);

    }

    private void delete_projetct() {
        AlertDialog.Builder builder = new AlertDialog.Builder(RobertActivity.this);
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


    public class RobertBluetoothInterface implements BluetoothListenInterface {
        @Override
        public void ouvinte(String s) {
            Toast.makeText(RobertActivity.this, s, Toast.LENGTH_LONG).show();
        }
    }

}
