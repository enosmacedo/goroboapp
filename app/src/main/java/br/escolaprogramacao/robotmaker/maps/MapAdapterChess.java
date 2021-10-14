package br.escolaprogramacao.robotmaker.maps;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;

public class MapAdapterChess extends BaseAdapter {

    private Activity activity;
    private String[] strings;
    private int width_grid;
    private int height_grid;
    private final int num_lines = 8;
    private final int num_clumns = 8;

    public MapAdapterChess(String[] strings, Activity activity, int width_grid, int height_grid) {
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
        MapItemViewChess customView ;
        if (convertView == null) {
            MapItemViewChess.Type tipo = MapItemViewChess.Type.ESRELA;
            if (strings[position].equals("E")) {
                tipo = MapItemViewChess.Type.ESRELA;
            } else if (strings[position].equals("Q")) {
                tipo = MapItemViewChess.Type.QUADRADO;
            } else if (strings[position].equals("C")) {
                tipo = MapItemViewChess.Type.CIRCULO;
            } else if (strings[position].equals("T")) {
                tipo = MapItemViewChess.Type.TRIANGULO;
            }

            customView = new MapItemViewChess(activity, tipo, 0);
        } else {
            customView = (MapItemViewChess) convertView;
        }

        customView.setLayoutParams(new FrameLayout.LayoutParams(this.width_grid /num_lines,this.height_grid /num_clumns));
        customView.display(strings[position]);
        return customView;
    }
}
