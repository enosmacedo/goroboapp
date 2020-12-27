package br.escolaprogramacao.robotmaker.maps;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.escolaprogramacao.robotmaker.R;

public class MapItemView extends FrameLayout {
    private ImageView textView;
    private Type t;
    private Context context;
    public int status;

    public enum Type {
        ESRELA,
        QUADRADO,
        TRIANGULO,
        CIRCULO
    }

    public MapItemView(Context context, Type t, int status) {
        super(context);
        this.context = context;
        this.status = status;
        this.t = t;
        LayoutInflater.from(context).inflate(R.layout.item_map, this);
        textView = (ImageView) getRootView().findViewById(R.id.text);
    }

    public void display(String text) {
       // textView.setText(text);
        display(false );
    }

    public void display( boolean enable_choose) {
        if (enable_choose) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            List<String> list = Arrays.asList("Comum", "Destino", "Origem");
            CharSequence[] cs = list.toArray(new CharSequence[list.size()]);

            builder.setTitle("Escolha o tipo")
                    .setItems(cs, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {
                                status = 0;
                                textView.setBackgroundResource(R.drawable.border_null);
                            } else if (which == 1) {
                                status = 1;
                                textView.setBackgroundResource(R.drawable.border_target);
                            } else if (which == 2) {
                                status = 2;
                                textView.setBackgroundResource(R.drawable.border_source);
                            }
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

//        if (t == Type.CIRCULO) {
//            textView.setImageResource(R.drawable.circulo_vermelho);
//        } else if (t == Type.ESRELA) {
//            textView.setImageResource(R.drawable.estrela_verde);
//        } else if (t == Type.TRIANGULO) {
//            textView.setImageResource(R.drawable.triangulo_azul);
//        } else if (t == Type.QUADRADO) {
//            textView.setImageResource(R.drawable.quadrado_amarelo);
//        }


        if (status == 0) {
            textView.setBackgroundResource(R.drawable.border_null);
        } else if (status == 1) {
            textView.setBackgroundResource(R.drawable.border_target);
        } else if (status == 2) {
            textView.setBackgroundResource(R.drawable.border_source);
        } else if (status == 4) {
            textView.setBackgroundResource(R.drawable.border_local);
        }
    }
}
