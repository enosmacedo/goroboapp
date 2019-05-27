package br.escolaprogramacao.robotmaker;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.escolaprogramacao.robotmaker.bluetooth.BluetoothListenInterface;
import br.escolaprogramacao.robotmaker.bluetooth.BluetoothManager;

public class InHungriaActivity extends AppCompatActivity {

    float margem_superior = 150;
    float margem_inferior = 150;
    float margem_direita = 100;
    float margem_esquerda = 100;
    public Button teste;

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

        gridView = (GridView) findViewById(R.id.gv_map_hungria_activity);


        final MapAdapter adapter = new MapAdapter(numbers, this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                ((MapItemView) v).display();
            }
        });

        MyInterfaceImpl s = new MyInterfaceImpl();
        BluetoothManager.beginListenForData(s);
    }

    public class MyInterfaceImpl implements BluetoothListenInterface {
        public void testFunkcija(String s){
            //Sesija.forceNalog(reg.getText().toString(), num);
            String[] separated = s.split("-");
            int x = Integer.valueOf(separated[0]); // this will contain "Fruit"
            String yaux = separated[1].substring(0,1);
            int y = Integer.valueOf(yaux); // this will contain " they taste good"

            ((MapItemView) getViewByPosition(x+y, gridView)).status = 4;
            ((MapItemView) getViewByPosition(x+y, gridView)).display();

            Toast.makeText(InHungriaActivity.this,s, Toast.LENGTH_LONG).show();
            Button b = teste;

        }
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

}

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_in_hungria);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        final RelativeLayout layout = (RelativeLayout) findViewById(R.id.content_in_hungria_activity);
////        ConstraintSet set = new ConstraintSet();
//
//        Display display = getWindowManager(). getDefaultDisplay();
//        Point size = new Point();
//        display. getSize(size);
//        final int tamanho_horizontal = size.x;
//        final int tamanho_vertical = size.y;
//
//
////        FloatingActionButton fab = findViewById(R.id.fab);
////        fab.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
////            }
////        });
//
//
//        for(int i = 0; i < 8; i++) {
//            for(int j = 0; j < 8; j++) {
//
//                Button btn= new Button(this);
//                String id = "i: " + i + " - j: " + j;
//                btn.setId( i * 8 + j);
//
//                final int w = (int) (((int) tamanho_horizontal - margem_direita - margem_esquerda)/8);
//                final int h = (int) (((int) tamanho_vertical - margem_inferior - margem_superior)/8);
//                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(w, h);
//                params.leftMargin = (int) margem_direita + (w * j);
//                params.topMargin = (int)  margem_inferior + ((h-32) * i) ;
//                params.width=w;
//                params.height=h;
//                layout.addView(btn, params);
//
//                btn.setOnClickListener(new View.OnClickListener()
//                {
//                    public void onClick(View view)
//                    {
//                        Toast.makeText(InHungriaActivity.this,tamanho_horizontal + " -- "+ tamanho_vertical, Toast.LENGTH_LONG).show();//your write code
//                    }
//                });
//            }
//        }
//    }
//
//    public class MyInterfaceImpl implements BluetoothListenInterface {
//        public void testFunkcija(){
//            //Sesija.forceNalog(reg.getText().toString(), num);
//            String id = "i: " + 0 + " - j: " + 0;
//            Button b = teste;
//
//        }
//    }


