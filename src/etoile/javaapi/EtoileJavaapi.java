/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javaapi;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Rui
 */
public class EtoileJavaapi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new EtoileJavaapi().run();
    }

    private void run() {
       
        Student st=new Student("Rui","123","rui@iscte.pt");
        st.setFirstname("Rui");
        st.setLastname("Lopes da Silva");
        
        System.out.println("Testar User:");
        System.out.println("Student Username:"+st.getUsername());
        System.out.println("Student Password:"+st.getPassword());
        System.out.println("Student First Name:"+st.getFirstname());
        System.out.println("Student Last Name:"+st.getLastname());
        
       LinkedList<Course> cs = new LinkedList<Course>() {};
       cs.add(new Course("Curso de Matemática", 1));
       st.setCourses(cs);
       
       LinkedList<Discipline> ds= new LinkedList<>();
       ds.add(new Discipline("Matematica I", 1));
       ds.add(new Discipline("Matematica II", 2));
       ds.add(new Discipline("Algebra", 1));
       
       
       
           System.out.println("Testar Course:");
           for(Course c : cs){
           System.out.println("Name: "+c.getName());
           System.out.println("Id: "+c.getId());
           }
        
        
    }
    
}
