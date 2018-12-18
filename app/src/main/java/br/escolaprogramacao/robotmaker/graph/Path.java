/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.escolaprogramacao.robotmaker.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
/**
 *
 * @author rafaelleandro
 */
public class Path {
    
    private ArrayList<String> path;
    private ArrayList<Edge> pathEdge;
    private ArrayList<Node> pathNode;

    public Path(){
        path = new ArrayList<String>();
        pathEdge = new ArrayList<>();
        pathNode = new ArrayList<>();
    }
  
    public Path(ArrayList<Node> pathNode){
        path = new ArrayList<String>();
        pathEdge = new ArrayList<>();
        this.pathNode = pathNode;
    }
    
    public void print() {
        for (Iterator<String> it = path.iterator(); it.hasNext();) {
            String string = it.next();
            System.out.print(string + "+");
        }
        System.out.println("----");
        for (Iterator<Edge> it = pathEdge.iterator(); it.hasNext();) {
            Edge edge = it.next();
            System.out.print(edge + "+");
        }
        System.out.println("----");
        for (Iterator<Node> it = pathNode.iterator(); it.hasNext();) {
            Node node = it.next();
            System.out.print(node + "+");
        }
        System.out.println("----");
    }
    
    public LinkedList<LinkedList<Node>> getListOrAnd() {
        LinkedList<LinkedList<Node>> nos = new LinkedList<>();
        for (Iterator<Node> it = pathNode.iterator(); it.hasNext();) {
            Node no = it.next();
            LinkedList<Node> aux = new LinkedList<>();
            aux.add(no);
            nos.add(aux);
        }
        return nos;
    }
    
    public LinkedList<Node> getListNode() {
        LinkedList<Node> nos = new LinkedList<>();
        for (Iterator<Node> it = pathNode.iterator(); it.hasNext();) {
            Node no = it.next();
            nos.add(no);
        }
        return nos;
    }
    
    @Override
    public String toString() {
        String res = "";
        for (Iterator<Node> it = pathNode.iterator(); it.hasNext();) {
            Node no = it.next();
            res = res +  "-" + no.getId();
        }
        return res;
    }
    
    public void add(String v){
        path.add(v);
    }
    
    public String get(int i){
        return path.get(i);
    }
    
    public int size(){
        return path.size();
    }
    
    public boolean contains(String s){
        return path.contains(s);
    }
    
    public void remove(String s){
        path.remove(s);
    }

    public ArrayList<String> getPath() {
        return path;
    }

    public void setPath(ArrayList<String> path) {
        this.path = path;
    }

    public ArrayList<Edge> getPathEdge() {
        return pathEdge;
    }

    public void setPathEdge(ArrayList<Edge> pathEdge) {
        this.pathEdge = pathEdge;
    }

    public ArrayList<Node> getPathNode() {
        return pathNode;
    }
    

    public void setPathNode(ArrayList<Node> pathNode) {
        this.pathNode = pathNode;
    }

}
