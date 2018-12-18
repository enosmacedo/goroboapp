/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.escolaprogramacao.robotmaker.graph;

/**
 *
 * @author daniel
 */
public class Node {
    private String id;
    private String title;
    private String type;
    private String set;
    private String value;

    public Node(String id, String title, String type, String set, String value) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.value = value;
        this.set = set;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }
}
