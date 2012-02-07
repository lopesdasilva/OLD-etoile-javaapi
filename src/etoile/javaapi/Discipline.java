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
public class Discipline {
    public String name;
    public int id;
    
    public List<Module> modules;

    public Discipline(String name, int id) {
        this.name = name;
        this.id = id;
    }

     
    public int getId() {
        return id;
    }

    public List<Module> getModules() {
        return modules;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
