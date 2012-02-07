/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javaapi;

import java.util.LinkedList;


/**
 *
 * @author Rui
 */
public class Module {
    
    public String name;
    
    public int id;
    
    public LinkedList<Test> tests= new LinkedList<Test>();
    
    public Module(String name, int id) {
        this.name = name;
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LinkedList<Test> getTests() {
        return tests;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTests(LinkedList<Test> tests) {
        this.tests = tests;
    }

    void addTest(Test t) {
        tests.add(t);
    }
    
    
}
