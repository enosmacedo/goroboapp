/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.escolaprogramacao.robotmaker.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.jgrapht.DirectedGraph;
import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author daniel
 */
public class Graph extends DefaultDirectedGraph<Node, Edge>{
//public class Graph extends SimpleGraph<Node, Edge>{

    private LinkedList<Integer> idNodes = new LinkedList<>();
    private LinkedList<Integer> idEdges = new LinkedList<>();
    public static int SIZE_ELEMENTS = 1000;


    public Graph(JSONObject obj) throws JSONException {
        super(Edge.class);
        for (int i = 0; i < SIZE_ELEMENTS; i++) {
            idNodes.add(i);
            idEdges.add(i);
        }
        JSONArray nodes = obj.getJSONArray("nodes");
        for (int i = 0; i < nodes.length(); i++) {
            JSONObject c = nodes.getJSONObject(i);
            String id = c.getString("id");
            String title = c.getString("title");
            String type = c.getString("type");
            String set = c.getString("set");
            String value = c.getString("value");
            Node n  = new Node(id, title, type, set, value);
            this.addVertex(n);
        }
        JSONArray edges = obj.getJSONArray("edges");
        for (int i = 0; i < edges.length(); i++) {
            JSONObject c = edges.getJSONObject(i);
            String source = c.getString("source");
            String target = c.getString("target");
            Edge edge = new Edge(source + " -- " + target);
            this.addEdge(this.getNode(source), this.getNode(target), edge);
        }
    }

    public Graph() {
        super(Edge.class);
        for (int i = 0; i < SIZE_ELEMENTS; i++) {
            idNodes.add(i);
            idEdges.add(i);
        }
    }

    public LinkedList<Node> getFilhos(Node pai) {
        LinkedList<Node> filhos = new LinkedList<>();
        Set<Edge> indicadores = super.outgoingEdgesOf(pai);
        for (Iterator<Edge> it = indicadores.iterator(); it.hasNext();) {
            Edge edge = it.next();
            filhos.add(super.getEdgeTarget(edge));
        }
        return filhos;
    }

    public LinkedList<Node> getPais(Node filho) {
        LinkedList<Node> filhos = new LinkedList<>();
        Set<Edge> indicadores = super.incomingEdgesOf(filho);
        for (Iterator<Edge> it = indicadores.iterator(); it.hasNext();) {
            Edge edge = it.next();
            filhos.add(super.getEdgeSource(edge));
        }
        return filhos;
    }


    public void print() {
        Set<Node> nodes = super.vertexSet();
        for (Iterator<Node> iterator = nodes.iterator(); iterator.hasNext();) {
            Node v = iterator.next();
            System.out.println("No: " + v);
            System.out.println("--In:");
            System.out.print("----");
            Set<Edge> edgesIn = super.incomingEdgesOf(v);
            for (Iterator<Edge> iterator1 = edgesIn.iterator(); iterator1.hasNext();) {
                Edge e = iterator1.next();
                System.out.print(e + " + ");
            }
            System.out.println("");
            System.out.println("--Out:");
            System.out.print("----");
            Set<Edge> edgesOut = super.outgoingEdgesOf(v);
            for (Iterator<Edge> iterator1 = edgesOut.iterator(); iterator1.hasNext();) {
                Edge e = iterator1.next();
                System.out.print(e + " + ");
            }
            System.out.println("");
            System.out.println("");
        }
    }

    public DirectedGraph<Node, Edge> getDirectedGraph() {
        return this;
    }
    
    public static boolean isRepeatInList(LinkedList<LinkedList<Node>> expressao, final Node no) {
        int qnt = 0;
        
        for (Iterator<LinkedList<Node>> it = expressao.iterator(); it.hasNext();) {
            LinkedList<Node> linkedList = it.next();
            for (Iterator<Node> it1 = linkedList.iterator(); it1.hasNext();) {
                Node node = it1.next();
//                if (node.getId().equals(no.getId()) && no != node) {
                if (node.getId().equals(no.getId())) {
                    qnt++;
                }
            }
        }
        if (qnt > 1) {
            return true;
        }
        return false;
    }
    
    
    public  Node getNode(String id) {
        Set<Node> nos = super.vertexSet();
        for (Iterator<Node> it = nos.iterator(); it.hasNext();) {
            Node node = it.next();
            if (node.getId().equals(id)) {
                return node;
            }
        }
        return null;
    }

    public  Node getNodeLast() {
        Set<Node> nos = super.vertexSet();
        for (Iterator<Node> it = nos.iterator(); it.hasNext();) {
            Node node = it.next();
            if (getFilhos(node).size() == 0) {
                return node;
            }
        }
        return null;
    }

    public  Node getNodeFirst() {
        Set<Node> nos = super.vertexSet();
        for (Iterator<Node> it = nos.iterator(); it.hasNext();) {
            Node node = it.next();
            if (getPais(node).size() == 0) {
                return node;
            }
        }
        return null;
    }
    

    
    @Override
    public String toString() {
        String s ="";
        Set<Node> nos = this.vertexSet();
        for (Iterator<Node> iterator = nos.iterator(); iterator.hasNext();) {
            Node next = iterator.next();
            s = s + next.getId();
        }
        return s;
    }
}
