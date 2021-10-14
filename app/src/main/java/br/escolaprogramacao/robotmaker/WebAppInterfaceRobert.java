package br.escolaprogramacao.robotmaker;

import android.content.Context;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;

import br.escolaprogramacao.robotmaker.bluetooth.BluetoothManager;
import br.escolaprogramacao.robotmaker.bluetooth.Commands;
import br.escolaprogramacao.robotmaker.graph.Graph;
import br.escolaprogramacao.robotmaker.graph.Node;
import br.escolaprogramacao.robotmaker.graph.Path;
import br.escolaprogramacao.robotmaker.graph.SearchAlgorithms;

public class WebAppInterfaceRobert {
    Context context;
    WebView webview;
    public String data;
    public static final int opt_add_node_down = 1;
    public static final int opt_add_node_up = 2;
    public static final int opt_add_node_right = 3;
    public static final int opt_add_node_left = 4;
    public static final int opt_add_node_stop = 5;
    public static final int opt_add_node_start = 6;
    public static final int opt_add_node_finish = 7;
    public static final int opt_add_arc_line = 101;
    public static final int opt_add_arc_curve = 102;
    public static final int opt_delete_node_arc = 300;
    public static final int opt_setting_node_arc = 103;
    public static final int opt_move_node = 200;
    public static final int opt_none = 0;


    public WebAppInterfaceRobert(Context ctx, WebView webview) {
        this.context = ctx;
        this.webview = webview;
    }

    public void set_qual_tipo_novo_node_java(int tipo_no) {
        String a = "";
        if (tipo_no == opt_add_node_down || tipo_no == opt_add_node_up || tipo_no == opt_add_node_right
                || tipo_no == opt_add_node_left || tipo_no == opt_add_node_stop || tipo_no == opt_add_node_start
                || tipo_no == opt_add_node_finish) {
            a += "configurar_status_teclado(true, false, false, false, false, false, false, " + tipo_no + ");";
        } else if (tipo_no == opt_add_arc_line) {
            a += " configurar_status_teclado(false, true, false, false, false, false, false, " + tipo_no + ");";
            webview.loadUrl("javascript:configurar_curva_aresta(true)");
        } else if (tipo_no == opt_add_arc_curve) {
            a += "configurar_status_teclado(false, true, false, false, false, false, false, " + tipo_no + ");";
            webview.loadUrl("javascript:configurar_curva_aresta(false)");
        } else if (tipo_no == opt_setting_node_arc) {
            a += "configurar_status_teclado(false, false, false, true, true, false, false, " + tipo_no + ");";
        } else if (tipo_no == opt_move_node) {
            a += "configurar_status_teclado(false, false, true, false, false, false, false, " + tipo_no + ");";
        } else if (tipo_no == opt_none) {
            a += "configurar_status_teclado(false, false, false, false, false, false, false, " + tipo_no + ");";
        } else if (tipo_no == opt_delete_node_arc) {
            a += "configurar_status_teclado(false, false, false, false, false, true, true, " + tipo_no + ");";
        }

        a = "javascript:" + a;
        webview.loadUrl(a);
    }

    @JavascriptInterface
    public void print(String a) {
        Toast.makeText(context, "Print: " + a, Toast.LENGTH_SHORT).show();
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
        Toast.makeText(context, "edit_node: " + set, Toast.LENGTH_SHORT).show();
        EditNodeDialog dialog = new EditNodeDialog(context, id, title, type, set, value, this);

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
        Toast.makeText(context, "edit_node: " + id, Toast.LENGTH_SHORT).show();
        EditNodeDialog dialog = new EditNodeDialog(context, id, title, type, set, value, this);
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
                    res = res + Commands.getInstance().COMANDO_EH_DISCRETO;

                    if (Integer.valueOf(n.getType()) == opt_add_node_down) { //baixo
                        res = res + Commands.getInstance().COMANDO_TRAS + n.getValue();
                    } else if (Integer.valueOf(n.getType()) == opt_add_node_up) { //cima
                        res = res + Commands.getInstance().COMANDO_FRENTE + n.getValue();
                    } else if (Integer.valueOf(n.getType()) == opt_add_node_right) { //direita
                        res = res + Commands.getInstance().COMANDO_DIREITA + n.getValue();
                    } else if (Integer.valueOf(n.getType()) == opt_add_node_left) { //esquerda
                        res = res + Commands.getInstance().COMANDO_ESQUERDA + n.getValue();
                    } else if (Integer.valueOf(n.getType()) == opt_add_node_stop) { //stop
                        res = res + Commands.getInstance().COMANDO_PARE + n.getValue();
                    } else if (Integer.valueOf(n.getType()) == opt_add_node_start) { //start
                        res = res + Commands.getInstance().COMANDO_START + n.getValue();
                    } else if (Integer.valueOf(n.getType()) == opt_add_node_finish) { //chegada
                        res = res + Commands.getInstance().COMANDO_END + n.getValue();
                    }
                }
            }
            Toast.makeText(this.context, res, Toast.LENGTH_SHORT).show();
            System.out.println(res);

            BluetoothManager.validate_connection(this.context, true);
            return obj;
        } catch (Throwable t) {
            Toast.makeText(context, "Erro: " + data, Toast.LENGTH_SHORT).show();
        }

        return null;
    }

    public void get_graph_js(String data) {
        Toast.makeText(context, "Cheguei 2", Toast.LENGTH_LONG).show();
        this.data = data;
    }

    public void delete_graph_js() {
        webview.loadUrl("javascript:delete_graph_js();");
    }
}