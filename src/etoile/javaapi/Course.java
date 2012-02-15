/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javaapi;

import java.io.Serializable;
import java.util.LinkedList;
/**
 *
 * @author Rui
 */
public  class Course implements Serializable{
    
    public String name;
    public int id;
    public LinkedList<Discipline> disciplines=new LinkedList<Discipline>();

    public Course(int id, String name) {
        this.name = name;
        this.id = id;
    }
    
    public void setDisciplines(LinkedList<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
     
    public LinkedList<Discipline> getDisciplines() {
        return disciplines;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean addDiscipline(Discipline d){
        disciplines.add(d);
        return true;
    }
    
 
    
}
