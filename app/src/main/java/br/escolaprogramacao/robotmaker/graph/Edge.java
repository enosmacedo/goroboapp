/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.escolaprogramacao.robotmaker.graph;

import org.jgrapht.graph.DefaultEdge;

/**
 *
 * @author daniel
 */
public class Edge {
    private String id;

    public Edge(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}
