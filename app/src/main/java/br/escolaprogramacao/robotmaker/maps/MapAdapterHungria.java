package br.escolaprogramacao.robotmaker.maps;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;

public class MapAdapterHungria extends BaseAdapter {

    private Activity activity;
    private int width_grid;
    private int height_grid;
    private final int num_lines = 8;
    private final int num_clumns = 8;

    public MapAdapterHungria(Activity activity, int width_grid, int height_grid) {
        this.activity = activity;

        this.width_grid = width_grid;
        this.height_grid = height_grid;
    }

    @Override
    public int getCount() {
        return 64;
    }

    @Override
    public Object getItem(int position) {
        return "a";
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MapItemViewHungria customView ;
        if (convertView == null) {

            customView = new MapItemViewHungria(activity,  0);
        } else {
            customView = (MapItemViewHungria) convertView;
        }

        customView.setLayoutParams(new FrameLayout.LayoutParams(this.width_grid /num_lines,this.height_grid /num_clumns));
        customView.display();
        return customView;
    }
}
