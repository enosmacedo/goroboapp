package br.escolaprogramacao.robotmaker;

import android.content.Context;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;

import br.escolaprogramacao.robotmaker.bluetooth.BluetoothManager;
import br.escolaprogramacao.robotmaker.graph.Graph;
import br.escolaprogramacao.robotmaker.graph.Node;
import br.escolaprogramacao.robotmaker.graph.Path;
import br.escolaprogramacao.robotmaker.graph.SearchAlgorithms;

public class WebAppInterfaceKit1 {
    Context mContext;
    WebView webview;
    public String data;
    private int qual_tipo_novo_no = 2;

    WebAppInterfaceKit1(Context ctx, WebView webview) {
        this.mContext = ctx;
        this.webview = webview;
    }

    public void set_qual_tipo_novo_node_java(int tipo_no) {
        String a = "";
        if (tipo_no == 1 || tipo_no == 2 || tipo_no == 3 || tipo_no == 4 || tipo_no == 5) {
            a += "configurar_status_teclado(true, false, false, false, false, false, false, " + tipo_no + ");";
        } else if (tipo_no == 101) {
            a += " configurar_status_teclado(false, true, false, false, false, false, false, " + tipo_no + ");";
            webview.loadUrl("javascript:configurar_curva_aresta(true)");
        } else if (tipo_no == 102) {
            a += "configurar_status_teclado(false, true, false, false, false, false, false, " + tipo_no + ");";
            webview.loadUrl("javascript:configurar_curva_aresta(false)");
        } else if (tipo_no == 103) {
            a += "configurar_status_teclado(false, false, false, true, true, false, false, " + tipo_no + ");";
        } else if (tipo_no == 200) {
            a += "configurar_status_teclado(false, false, true, false, false, false, false, " + tipo_no + ");";
        } else if (tipo_no == 0) {
            a += "configurar_status_teclado(false, false, false, false, false, false, false, " + tipo_no + ");";
        } else if (tipo_no == 300) {
            a += "configurar_status_teclado(false, false, false, false, false, true, true, " + tipo_no + ");";
        }

        a = "javascript:" + a;
        webview.loadUrl(a);


    }

    @JavascriptInterface
    public void print(String a) {
        Toast.makeText(mContext, "Print: " + a, Toast.LENGTH_SHORT).show();
    }

    public void update_node(String id, String title, String type, String set, String value) {
        final String a = "javascript:edit_node_js('" + id + "', '" + title + "', '" + type + "', '" + set + "', '" + value + "');";
        webview.post(new Runnable() {
            @Override
            public void run() {
                webview.loadUrl(a);
            }
        });

    }

    @JavascriptInterface
    public String edit_edge_java(String id, String title, String type, String set, String value) {
        Toast.makeText(mContext, "edit_node: " + set, Toast.LENGTH_SHORT).show();
        EditNodeDialog dialog = new EditNodeDialog(mContext, id, title, type, set, value, this);


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        int dialogWindowHeight = (int) (lp.height * 0.7f);
        dialog.getWindow().setAttributes(lp);

        dialog.show();
        return "as";
    }

    @JavascriptInterface
    public String edit_node_java(String id, String title, String type, String set, String value) {
        Toast.makeText(mContext, "edit_node: " + set, Toast.LENGTH_SHORT).show();
        EditNodeDialog dialog = new EditNodeDialog(mContext, id, title, type, set, value, this);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        int dialogWindowHeight = (int) (lp.height * 0.7f);
        dialog.getWindow().setAttributes(lp);

        dialog.show();
        return "as";
    }


    public void form_graph_java() {
        webview.loadUrl("javascript:AndroidInterface.run(form_graph_js());");
    }

    @JavascriptInterface
    public JSONObject run(String data) {
        try {
            JSONObject obj = new JSONObject(data);
            Graph g = new Graph(obj);
            SearchAlgorithms se = new SearchAlgorithms(g);
            LinkedList<Path> caminhos = se.dfs(g.getNodeFirst(), g.getNodeLast());
            String res = "";
            for (int i = 0; i < caminhos.size(); i++) {
                Path unico_caminho = caminhos.get(i);
                ArrayList<Node> unico_caminho_nos = unico_caminho.getPathNode();
                for (int j = 0; j < unico_caminho_nos.size(); j++) {
                    Node n = unico_caminho_nos.get(j);
                    if (n.getType().equals("1")) { //baixo
                        if (n.getSet().equals("1")) {
                            res = res + "A" + n.getValue();
                        } else {
                            res = res + "B" + n.getValue();
                        }
                    } else if (n.getType().equals("2")) { //cima
                        if (n.getSet().equals("1")) {
                            res = res + "C" + n.getValue();
                        } else {
                            res = res + "D" + n.getValue();
                        }
                    } else if (n.getType().equals("3")) { //direita
                        if (n.getSet().equals("1")) {
                            res = res + "E" + n.getValue();
                        } else {
                            res = res + "F" + n.getValue();
                        }
                    } else if (n.getType().equals("4")) { //esquerda
                        if (n.getSet().equals("1")) { //baixo
                            res = res + "G" + n.getValue();
                        } else {
                            res = res + "H" + n.getValue();
                        }
                    } else if (n.getType().equals("5")) { //stop
                        if (n.getSet().equals("1")) {
                            res = res + "I" + n.getValue();
                        } else {
                            res = res + "J" + n.getValue();
                        }
                    } else if (n.getType().equals("6")) { //start
                        if (n.getSet().equals("1")) {
                            res = res + "L" + n.getValue();
                        } else {
                            res = res + "M" + n.getValue();
                        }
                    } else if (n.getType().equals("5")) { //chegada
                        if (n.getSet().equals("1")) {
                            res = res + "N" + n.getValue();
                        } else {
                            res = res + "O" + n.getValue();
                        }
                    }
                }

            }
            BluetoothManager.getBluetoothManager(mContext).write(res, mContext);
            return obj;
        } catch (Throwable t) {
            Toast.makeText(mContext, "Erro: " + data, Toast.LENGTH_SHORT).show();
        }

        return null;
    }


    public void get_graph_js(String data) {
        Toast.makeText(mContext, "Cheguei 2", Toast.LENGTH_LONG).show();
        this.data = data;
    }

    public void delete_graph_js() {
        webview.loadUrl("javascript:delete_graph_js();");
    }


}