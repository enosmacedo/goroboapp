package br.escolaprogramacao.robotmaker;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MapItemView extends FrameLayout {
    private ImageView textView;
    private Type t;
    public int status;

    public enum Type {
        ESRELA,
        QUADRADO,
        TRIANGULO,
        CIRCULO
    }

    public MapItemView(Context context, Type t, int status) {
        super(context);
        this.status = status;
        this.t = t;
        LayoutInflater.from(context).inflate(R.layout.item_map, this);
        textView = (ImageView) getRootView().findViewById(R.id.text);
    }

    public void display(String text) {
       // textView.setText(text);
        display( );
    }

    public void display( ) {
//        textView.setBackgroundResource(isSelected ? R.drawable.green_square : R.drawable.gray_square);
        if (t == Type.CIRCULO) {
            textView.setImageResource(R.drawable.circulo_vermelho);
        } else if (t == Type.ESRELA) {
            textView.setImageResource(R.drawable.estrela_verde);
        } else if (t == Type.TRIANGULO) {
            textView.setImageResource(R.drawable.triangulo_azul);
        } else if (t == Type.QUADRADO) {
            textView.setImageResource(R.drawable.quadrado_amarelo);
        }

        if (status == 0) {
            textView.setBackgroundResource(R.drawable.border_null);
            status++;
        } else if (status == 1) {
            textView.setBackgroundResource(R.drawable.border_target);
            status++;
        } else if (status == 2) {
            textView.setBackgroundResource(R.drawable.border_source);
            status = 0;
        } else if (status == 4) {
            textView.setBackgroundResource(R.drawable.border_local);
        }
    }
}
