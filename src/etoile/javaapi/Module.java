/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javaapi;

import java.util.List;

/**
 *
 * @author Rui
 */
public class Module {
    
    public String name;
    
    public int id;
    
    public List<Test> tests;
    
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

    public List<Test> getTests() {
        return tests;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
    
    
}
