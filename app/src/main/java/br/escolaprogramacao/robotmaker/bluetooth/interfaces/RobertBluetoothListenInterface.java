package br.escolaprogramacao.robotmaker.bluetooth.interfaces;

import android.app.Activity;
import android.widget.GridView;
import android.widget.Toast;

import br.escolaprogramacao.robotmaker.RobertActivity;
import br.escolaprogramacao.robotmaker.bluetooth.BluetoothListenInterface;

public class RobertBluetoothListenInterface implements BluetoothListenInterface {
    public Activity context;

    public RobertBluetoothListenInterface(Activity context) {
        this.context = context;
    }

    @Override
    public void ouvinte(String s) {
        Toast.makeText(this.context, s, Toast.LENGTH_LONG).show();
    }
}