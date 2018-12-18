package br.escolaprogramacao.robotmaker;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class EditNodeArrowDialog extends Dialog {

    Button btnCancelar;
    Button btnSalvar;

    RadioButton rbtnTempo;
    RadioButton rbtnDistancia;
    EditText etValor;

    TextView tvCabecalho;
    TextView tvUnidade;
    String id;
    String title;
    String type;
    String value;
    String set;

    DialogListener listener;

    interface DialogListener {
        void onCompleted();
        void onCanceled();
    }

    public void setDialogListener(DialogListener listener) {
        this.listener = listener;
    }

    public EditNodeArrowDialog(@NonNull Context context, String id, String title, String type, String set, String value) {
        super(context);
        this.id = id;
        this.title = title;
        this.type = type;
        this.set = set;
        this.value = value;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edit_node_arrow_dialog);

        rbtnTempo = (RadioButton) findViewById(R.id.rb_edit_node_arrow_dialog_velocidade);
        rbtnDistancia = (RadioButton) findViewById(R.id.rb_edit_node_arrow_dialog_tempo);
        btnSalvar = (Button) findViewById(R.id.btn_edit_node_arrow_dialog_salvar);
        btnCancelar = (Button) findViewById(R.id.btn_edit_node_arrow_dialog_cancel);
        tvCabecalho = (TextView) findViewById(R.id.tv_edit_node_arrow_dialog_cabecalho);
        tvUnidade = (TextView) findViewById(R.id.tv_edit_node_arrow_dialog_medida);
        etValor = (EditText) findViewById(R.id.et_edit_node_arrow_dialog_valor);

        etValor.setText(value, TextView.BufferType.EDITABLE);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String res = "" ;
                if (rbtnDistancia.isChecked()) {
                    res = res + "1-";
                } else if (rbtnTempo.isChecked()) {
                    res = res + "2-";
                }
                res = res + etValor.getEditableText().toString();

                if(listener != null)
                    listener.onCompleted();
                EditNodeArrowDialog.this.dismiss();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null)
                    listener.onCanceled();
                EditNodeArrowDialog.this.cancel();
            }
        });

        tvCabecalho.setText("Id: " + id + " - Titulo: " + title + " - Type: " + type + " - Set: " +set) ;

        if (set.equals("1")) {
            tvUnidade.setText("m");
            rbtnDistancia.setChecked(true);
        } else if (set.equals("2")) {
            tvUnidade.setText("s");
            rbtnTempo.setChecked(true);
        }

    }
}
