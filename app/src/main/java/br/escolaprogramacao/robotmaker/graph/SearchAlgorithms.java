/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.escolaprogramacao.robotmaker.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author daniel
 */
public class SearchAlgorithms {

    private Graph g;

    public SearchAlgorithms(Graph g) {
        this.g = g;
    }

    public boolean BFSBoolean(Node origem, Node destino) {
        final List<Node> fila = new LinkedList<Node>();
        fila.add(origem);
        while (!fila.isEmpty()) {
            Node v = fila.remove(0);
            LinkedList<Node> adjacentes = g.getFilhos(v);
            for (int i = 0; i < adjacentes.size(); i++) {
                Node w = adjacentes.get(i);
                if (w.equals(destino)) {
                    return true;
                } else if (!fila.contains(w)) {
                    fila.add(w);
                }
            }
        }
        return false;
    }

    public boolean DFSBoolean(Node origem, Node destino) {
        final Stack<Node> pilha = new Stack<>();
        final LinkedList<Node> visitados = new LinkedList<>();

        pilha.add(origem);

        while (!pilha.isEmpty()) {
            Node v = pilha.pop();
            if (v.equals(destino)) {
                return true;
            } else if (!visitados.contains(v)) {
                LinkedList<Node> adjacentes = g.getFilhos(v);
                for (int i = 0; i < adjacentes.size(); i++) {
                    Node w = adjacentes.get(i);
                    pilha.add(w);
                }
                visitados.add(v);
            }
        }
        return false;
    }


    // recursive dfs
    private void dfs_rec(boolean[] visited, Node v, Node d, List<Node> path, LinkedList<Path> res) {
        ArrayList<Node> vertexs = new ArrayList<>();
        vertexs.addAll(g.getDirectedGraph().vertexSet());
        visited[vertexs.indexOf(v)] = true;
        path.add(v);

        if (v == d) {
            ArrayList<Node> aux = new ArrayList<>(path);
            res.add(new Path(aux));
        } else {
            LinkedList<Node> adjacentes = g.getFilhos(v);
            for (int i = 0; i < adjacentes.size(); i++) {
                Node w = adjacentes.get(i);
                if (!visited[vertexs.indexOf(w)]) {
                    dfs_rec(visited, w, d, path, res);
                }
            }
        }
        path.remove(path.size() - 1);
        visited[vertexs.indexOf(v)] = false;
    }

    // Usually dfs_rec() would be sufficient. However, if we don't want to pass
    // a boolean array to our function, we can use another function dfs().
    // We only have to pass the adjacency list and the source node to dfs(),
    // as opposed to dfs_rec(), where we have to pass the boolean array
    // additionally.
    public LinkedList<Path> dfs(Node s, Node d) {
        int n = g.getDirectedGraph().vertexSet().size();
        boolean[] visited = new boolean[n];

        LinkedList<Path> res = new LinkedList<>();
        List<Node> path = new ArrayList<Node>();
        dfs_rec(visited, s, d, path, res);

        return res;
    }
    
    public static LinkedList<LinkedList<LinkedList<Node>>> generatePathNode(LinkedList<Path> caminhos) {
        LinkedList<LinkedList<LinkedList<Node>>> resposta = new LinkedList<>();
        for (Iterator<Path> it = caminhos.iterator(); it.hasNext();) {
            Path path = it.next();
            resposta.add(path.getListOrAnd());
        }
        return resposta;
    }
}
