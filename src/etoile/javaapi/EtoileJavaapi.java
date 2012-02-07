/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javaapi;

import etoile.javaapi.question.OpenQuestion;
import etoile.javaapi.question.Question;
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
        
        System.out.println("Users:");
        System.out.println("Student Username:"+st.getUsername());
        System.out.println("Student Password:"+st.getPassword());
        System.out.println("Student First Name:"+st.getFirstname());
        System.out.println("Student Last Name:"+st.getLastname());
        
        Course c=new Course("Curso de Matemática", 1);
        
        Discipline d= new Discipline("Matematica I",1);
        
        Module m= new Module("SUM", 1);
        
        Test t= new Test("Teste da Soma", 1, "Professor de Matematica");
        
        Question q= new OpenQuestion("Qual é o resultado da Soma 1+1?", 1);
        Question q2= new OpenQuestion("Qual é o resultado da Soma 2+2?", 2);
        
        t.addQuestion(q);
        t.addQuestion(q2);
        
        m.addTest(t);
        
        d.addModule(m);
        
        c.addDiscipline(d);
        
        st.addCourse(c);
        
        System.out.println("Curso: "+st.getCourses().getFirst().getName());
        System.out.println("Disciplina: "+st.getCourses().getFirst().getDisciplines().getFirst().getName());
        System.out.println("Modulo: "+st.getCourses().getFirst().getDisciplines().getFirst().getModules().getFirst().getName());
        System.out.println("Test: "+st.getCourses().getFirst().getDisciplines().getFirst().getModules().getFirst().getTests().getFirst().getName());
        System.out.println("Question: "+st.getCourses().getFirst().getDisciplines().getFirst().getModules().getFirst().getTests().getFirst().getQuestions().getFirst().getText());
        
        
    }
    
}
