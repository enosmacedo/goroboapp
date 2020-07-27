package br.escolaprogramacao.robotmaker;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;


public class EditNodeDialog extends Dialog {

    private Button btnCancelar;
    private Button btnSalvar;

    private RadioButton rbtnTempo;
    private RadioButton rbtnDistancia;
    private EditText etValor;
    private EditText etTitulo;

    private TextView tvCabecalho;
    private String id;
    private String title;
    private String type;
    private String value;
    private String set;

    private WebAppInterfaceRobert interface_android_web;
    private Context mContext;

    public EditNodeDialog(@NonNull Context context, String id, String title, String type,
                          String set, String value, WebAppInterfaceRobert interface_android_web) {
        super(context);
        this.mContext = context;
        this.interface_android_web = interface_android_web;
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
        setContentView(R.layout.activity_edit_node_dialog);

        rbtnTempo = (RadioButton) findViewById(R.id.rb_edit_node_arrow_dialog_velocidade);
        rbtnDistancia = (RadioButton) findViewById(R.id.rb_edit_node_arrow_dialog_tempo);
        btnSalvar = (Button) findViewById(R.id.btn_edit_node_arrow_dialog_salvar);
        btnCancelar = (Button) findViewById(R.id.btn_edit_node_arrow_dialog_cancel);
        tvCabecalho = (TextView) findViewById(R.id.tv_edit_node_arrow_dialog_cabecalho);
        etValor = (EditText) findViewById(R.id.et_edit_node_arrow_dialog_valor);
        etTitulo = (EditText) findViewById(R.id.et_edit_node_arrow_dialog_titulo);

        etValor.setText(value, TextView.BufferType.EDITABLE);
        etTitulo.setText(title, TextView.BufferType.EDITABLE);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbtnDistancia.isChecked()) {
                   set = "1";
                } else if (rbtnTempo.isChecked()) {
                   set = "2";
                }

                value = etValor.getEditableText().toString();
                title = etTitulo.getEditableText().toString();

                interface_android_web.update_node(id, title, type, set, value);
                EditNodeDialog.this.dismiss();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditNodeDialog.this.cancel();
            }
        });

        tvCabecalho.setText("Id: " + id + " - Titulo: " + title + " - Type: " + type + " - Set: " +set + " -Value: " + value) ;

        if (set.equals("1")) {
            rbtnDistancia.setChecked(true);
        } else if (set.equals("2")) {
            rbtnTempo.setChecked(true);
        }

    }
}
