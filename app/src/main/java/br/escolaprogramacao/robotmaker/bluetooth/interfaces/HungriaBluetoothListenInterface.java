package br.escolaprogramacao.robotmaker.bluetooth.interfaces;

import android.app.Activity;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import br.escolaprogramacao.robotmaker.maps.MapItemViewHungria;
import br.escolaprogramacao.robotmaker.bluetooth.BluetoothListenInterface;

public class HungriaBluetoothListenInterface implements BluetoothListenInterface {

    public Activity context;
    private GridView gridView;

    public HungriaBluetoothListenInterface(Activity context, GridView gridView) {
        this.context = context;
        this.gridView = gridView;
    }


    public void ouvinte(String s){

        String[] separated = s.split("-");
        String yaux = separated[1].substring(0,separated[1].length()-1);

        if (separated[0].equalsIgnoreCase("A")) {
            int y = Integer.valueOf(yaux);
            if (((MapItemViewHungria) getViewByPosition(y, gridView)).status == 1) {
                Toast.makeText(context, "Concluido", Toast.LENGTH_LONG).show();
            } else if (((MapItemViewHungria) getViewByPosition(y, gridView)).status == 2) {
                Toast.makeText(context, "Voltou para origem", Toast.LENGTH_LONG).show();
            }  else {
                ((MapItemViewHungria) getViewByPosition(y, gridView)).status = 4;
                ((MapItemViewHungria) getViewByPosition(y, gridView)).display(false);
            }
            Toast.makeText(context, s, Toast.LENGTH_LONG).show();
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