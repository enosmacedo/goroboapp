package br.escolaprogramacao.robotmaker;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class MapAdapter extends BaseAdapter {

    private Activity activity;
    private String[] strings;

    public MapAdapter(String[] strings, Activity activity) {
        this.strings = strings;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Object getItem(int position) {
        return strings[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MapItemView customView ;
        if (convertView == null) {
            MapItemView.Type tipo = MapItemView.Type.ESRELA;
            if (strings[position].equals("E")) {
                tipo = MapItemView.Type.ESRELA;
            } else if (strings[position].equals("Q")) {
                tipo = MapItemView.Type.QUADRADO;
            } else if (strings[position].equals("C")) {
                tipo = MapItemView.Type.CIRCULO;
            } else if (strings[position].equals("T")) {
                tipo = MapItemView.Type.TRIANGULO;
            }

            customView = new MapItemView(activity, tipo, 0);

        } else {
            customView = (MapItemView) convertView;
        }

        customView.display(strings[position]);

        return customView;
    }
}
