package br.escolaprogramacao.robotmaker.maps;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;

public class MapAdapter extends BaseAdapter {

    private Activity activity;
    private String[] strings;
    private int width_grid;
    private int height_grid;
    private final int num_lines = 8;
    private final int num_clumns = 8;

    public MapAdapter(String[] strings, Activity activity, int width_grid, int height_grid) {
        this.strings = strings;
        this.activity = activity;

        this.width_grid = width_grid;
        this.height_grid = height_grid;
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

        customView.setLayoutParams(new FrameLayout.LayoutParams(this.width_grid /num_lines,this.height_grid /num_clumns));
        customView.display(strings[position]);
        return customView;
    }
}
