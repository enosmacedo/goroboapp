package br.escolaprogramacao.robotmaker;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import br.escolaprogramacao.robotmaker.bluetooth.BluetoothManager;
import br.escolaprogramacao.robotmaker.bluetooth.interfaces.HungriaBluetoothInterface;
import br.escolaprogramacao.robotmaker.maps.MapAdapter;
import br.escolaprogramacao.robotmaker.maps.MapItemView;


public class AtHungriaActivity extends AppCompatActivity {

    float margem_superior = 150;
    float margem_inferior = 150;
    float margem_direita = 100;
    float margem_esquerda = 100;
    public Button teste;

    private MenuItem miSettings ;
    private MenuItem miSave ;
    private MenuItem miLoad ;
    private MenuItem miReset;
    private MenuItem miRun ;
    private MenuItem miDebug ;
    private MenuItem miBluetooth ;

    private HungriaBluetoothInterface bluetooth_interface;

    private GridView gridView;
    private static final String[] numbers = new String[]{
            "C", "T", "Q", "C", "Q", "C", "Q", "Q",
            "Q", "T", "E", "E", "C", "E", "Q", "T",
            "E", "Q", "C", "T", "T", "T", "E", "C",
            "C", "T", "T", "E", "Q", "C", "T", "T",
            "E", "T", "Q", "E", "C", "E", "Q", "C",
            "T", "E", "C", "C", "T", "E", "E", "Q",
            "Q", "E", "T", "E", "Q", "C", "C", "T",
            "C", "E", "Q", "C", "T", "Q", "E", "Q"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_hungria);
        Toolbar toolbar = findViewById(R.id.toolbar_in_hungria);
        setSupportActionBar(toolbar);

        gridView = (GridView) findViewById(R.id.gv_map_hungria_activity);

        gridView.post(new Runnable() {
            @Override
            public void run() {
                int grid_height = gridView.getMeasuredHeight();
                int grid_width =  gridView.getMeasuredWidth();
                final MapAdapter adapter = new MapAdapter(numbers, AtHungriaActivity.this, grid_width, grid_height);
                gridView.setAdapter(adapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        ((MapItemView) v).display(true);
                    }
                });
            }
        });

        bluetooth_interface = new HungriaBluetoothInterface(this, gridView);
    }

    public View getViewByPosition(int pos, GridView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
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

        miSettings.setEnabled(false);
        miSave.setEnabled(false);
        miLoad.setEnabled(false);
        miReset.setEnabled(false);
        miRun.setEnabled(true);
        miDebug.setEnabled(false);
        miBluetooth.setEnabled(false);


        miRun.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (BluetoothManager.getSocket() != null && BluetoothManager.getSocket().isConnected()) {
                } else {
                    Toast.makeText(getBaseContext(), "Sem uma conexão bluetooth não é possível executar", Toast.LENGTH_SHORT).show();
                    return false;
                }

                int qnt_target = 0;
                int qnt_source = 0;
                for (int i = 0; i < 64; i++) {
                    if (((MapItemView) getViewByPosition(i , gridView)).status == 1)
                        qnt_target++;
                    if (((MapItemView) getViewByPosition(i , gridView)).status == 2)
                        qnt_source++;
                }

                if (qnt_source != 1 || qnt_source != 1) {
                    Toast.makeText(AtHungriaActivity.this,
                            "Você deve ter 1 destino e 1 origem... mas tem " + qnt_target + " destinos e " + qnt_source + " origens",
                            Toast.LENGTH_LONG).show();
                    BluetoothManager.closeListenBluetooth();
                } else {
                    Toast.makeText(AtHungriaActivity.this, "Pronto para começar", Toast.LENGTH_LONG).show();
                    BluetoothManager.beginListenForData(bluetooth_interface);
                }

                return true;
            }
        });

        return true;

    }
}

