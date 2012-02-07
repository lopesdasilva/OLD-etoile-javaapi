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
public  class Course {
    
    public String name;
    public int id;
    public List<Discipline> disciplines;

    public Course(String name, int id) {
        this.name = name;
        this.id = id;
    }
    
    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
     
    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    
    
 
    
}
