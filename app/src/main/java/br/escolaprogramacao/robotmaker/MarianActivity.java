package br.escolaprogramacao.robotmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import br.escolaprogramacao.robotmaker.bluetooth.BluetoothManager;
import br.escolaprogramacao.robotmaker.bluetooth.Commands;

public class MarianActivity extends AppCompatActivity {
    ImageView ibt_return_left_act_marian;
    ImageView ibt_left_act_marian;
    ImageView ibt_down_act_marian;
    ImageView ibt_up_act_marian;
    ImageView ibt_return_right_act_marian;
    ImageView ibt_right_act_marian;
    ImageView ibt_target_act_marian;
    ImageView ibt_stop_act_marian;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marian);
        context = this;

        ibt_return_left_act_marian = (ImageView) findViewById(R.id.ibt_return_left_act_marian);
        ibt_left_act_marian = (ImageView) findViewById(R.id.ibt_left_act_marian);
        ibt_down_act_marian = (ImageView) findViewById(R.id.ibt_down_act_marian);
        ibt_up_act_marian = (ImageView) findViewById(R.id.ibt_up_act_marian);
        ibt_return_right_act_marian = (ImageView) findViewById(R.id.ibt_return_right_act_marian);
        ibt_right_act_marian = (ImageView) findViewById(R.id.ibt_right_act_marian);
        ibt_target_act_marian = (ImageView) findViewById(R.id.ibt_target_act_marian);
        ibt_stop_act_marian = (ImageView) findViewById(R.id.ibt_stop_act_marian);

        setting_button();
    }

    private void setting_button() {
        ibt_return_left_act_marian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BluetoothManager.getSocket() != null && BluetoothManager.getSocket().isConnected()) {
                    Toast.makeText(context, "Comando não implementado", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Sem uma conexão bluetooth não é possível executar", Toast.LENGTH_LONG).show();
                }
            }
        });
        ibt_left_act_marian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BluetoothManager.getSocket() != null && BluetoothManager.getSocket().isConnected()) {
                    BluetoothManager.getBluetoothManager(context).write(Commands.getInstance().COMANDO_ESQUERDA, context);
                } else {
                    Toast.makeText(context, "Sem uma conexão bluetooth não é possível executar", Toast.LENGTH_LONG).show();
                }
            }
        });
        ibt_down_act_marian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BluetoothManager.getSocket() != null && BluetoothManager.getSocket().isConnected()) {
                    BluetoothManager.getBluetoothManager(context).write(Commands.getInstance().COMANDO_TRAS, context);
                } else {
                    Toast.makeText(context, "Sem uma conexão bluetooth não é possível executar", Toast.LENGTH_LONG).show();
                }
            }
        });
        ibt_up_act_marian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BluetoothManager.getSocket() != null && BluetoothManager.getSocket().isConnected()) {
                    BluetoothManager.getBluetoothManager(context).write(Commands.getInstance().COMANDO_FRENTE, context);
                } else {
                    Toast.makeText(context, "Sem uma conexão bluetooth não é possível executar", Toast.LENGTH_LONG).show();
                }
            }
        });
        ibt_return_right_act_marian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BluetoothManager.getSocket() != null && BluetoothManager.getSocket().isConnected()) {
                    Toast.makeText(context, "Comando não implementado", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Sem uma conexão bluetooth não é possível executar", Toast.LENGTH_LONG).show();
                }
            }
        });
        ibt_right_act_marian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BluetoothManager.getSocket() != null && BluetoothManager.getSocket().isConnected()) {
                    BluetoothManager.getBluetoothManager(context).write(Commands.getInstance().COMANDO_DIREITA, context);
                } else {
                    Toast.makeText(context, "Sem uma conexão bluetooth não é possível executar", Toast.LENGTH_LONG).show();
                }
            }
        });
        ibt_stop_act_marian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BluetoothManager.getSocket() != null && BluetoothManager.getSocket().isConnected()) {
                    BluetoothManager.getBluetoothManager(context).write(Commands.getInstance().COMANDO_PARE, context);
                } else {
                    Toast.makeText(context, "Sem uma conexão bluetooth não é possível executar", Toast.LENGTH_LONG).show();
                }
            }
        });
        ibt_target_act_marian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BluetoothManager.getSocket() != null && BluetoothManager.getSocket().isConnected()) {
                    BluetoothManager.getBluetoothManager(context).write(Commands.getInstance().COMANDO_PRINT, context);
                } else {
                    Toast.makeText(context, "Sem uma conexão bluetooth não é possível executar", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_marian_settings, menu);

        MenuItem miDebug = (MenuItem) menu.findItem(R.id.it_menu_marian_debug);
        MenuItem miMudarModo = (MenuItem) menu.findItem(R.id.it_menu_marian_mudar_modo);

        miDebug.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (BluetoothManager.getSocket() != null && BluetoothManager.getSocket().isConnected()) {
                    BluetoothManager.getBluetoothManager(context).write(Commands.getInstance().COMANDO_DEBUG, context);
                } else {
                    Toast.makeText(getBaseContext(), "Sem uma conexão bluetooth não é possível executar", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
            }
        });
        miMudarModo.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (BluetoothManager.getSocket() != null && BluetoothManager.getSocket().isConnected()) {
                    BluetoothManager.getBluetoothManager(context).write(Commands.getInstance().COMANDO_MUDAR_MODULO, context);
                } else {
                    Toast.makeText(getBaseContext(), "Sem uma conexão bluetooth não é possível executar", Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
            }
        });


        return true;
    }
}