/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javaapi;

import etoile.javaapi.question.*;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rui
 */
public class EtoileJavaapi {

    /**
     * @param args the command line arguments
     */
    //TEST
    int student_id = 1;

    public static void main(String[] args) {
        new EtoileJavaapi().run();
    }

    private void run() {
        try {
            ServiceManager manager = new ServiceManager();
            System.out.println(manager.setAuthentication("rui", "40bd001563085fc35165329ea1ff5c5ecbdbbeef"));
            
            //Student student = new Student("teste", "40bd001563085fc35165329ea1ff5c5ecbdbbeef", "Ruben", "Paixao", "r@r.r");

           manager.userService().updateCourses(student_id);

            for (Course c : manager.current_student.getCourses()) {
                System.out.println("CURSO: " + c.getName());

                manager.userService().updateDisciplines(c);
                for (Discipline d : c.getDisciplines()) {
                    System.out.println("\nDISCIPLINA: " + d.getName());
                    manager.userService().updateModules(d);

                    for (Module m : d.getModules()) {
                        System.out.println("*MODULO: " + m.getName());
                        manager.userService().updateTests(m);
                        for (Test t : m.getTests()) {
                            System.out.println("**TESTE : " + t.getName());
                            manager.userService().updateQuestions(t);
                            
                            for (Question element : t.getQuestions()) {
                                switch (element.getQuestionType()) {
                                    case MULTIPLE_CHOICE:
                                        System.out.println("Escolha Multipla");
                                        System.out.println("Pergunta " + element.getText());
                                        System.out.println("URL's: ");
                                        for(URL url: element.getURLS()){
                                            System.out.println(" "+ url.getName() +": " + url.getUrl() + "-> " + url.getVotes());
                                        }
                                        System.out.println("Hipoteses: ");
                                        for (String s : element.getPossibleAnswers()) {
                                            System.out.print("  " + s);
                                                                                        
                                        }
                                        System.out.println();
                                        System.out.println("Respostas Correctas: " );
                                        for (String s : element.getCorrectAnswers()) {
                                            System.out.print("  " + s);
                                        }
                                        System.out.println("\nRespostas do aluno: ");
                                         for (String s : element.getAnswers()) {
                                            System.out.print("  " + s);
                                        }
                                        System.out.println();
                                        break;
                                    case ONE_CHOICE:
                                        System.out.println("Uma Escolha");
                                        System.out.println("Pergunta " + element.getText());
                                        System.out.println("URL's: ");
                                        for(URL url: element.getURLS()){
                                            System.out.println(" "+ url.getName() +": " + url.getUrl()+ "-> " + url.getVotes() );
                                        }
                                        System.out.print("Hipoteses:");
                                        for (String s : element.getPossibleAnswers()) {
                                            System.out.print("  " + s);
                                        }
                                        System.out.println();
                                        System.out.println("Resposta Correcta: " + element.getCorrectAnswer());
                                        System.out.println("Resposta do Aluno: " + element.getUserAnswer());
                                        break;
                                    case OPEN:
                                        System.out.println("Pergunta Aberta");
                                        System.out.println("Pergunta " + element.getText());
                                        System.out.println("URL's: ");
                                        for(URL url: element.getURLS()){
                                            System.out.println(" "+ url.getName() +": " + url.getUrl() + "-> " + url.getVotes());
                                        }
                                        System.out.println("Resposta do Aluno: " + element.getUserAnswer());
                                        break;
                                }

                            }
                        }
                    }
                }
            }




            //manager.userService().addStudent(student);

            //        Student st=new Student("Rui","123","rui@iscte.pt");
            //        st.setFirstname("Rui");
            //        st.setLastname("Lopes da Silva");
            //        
            //        System.out.println("Users:");
            //        System.out.println("Student Username:"+st.getUsername());
            //        System.out.println("Student Password:"+st.getPassword());
            //        System.out.println("Student First Name:"+st.getFirstname());
            //        System.out.println("Student Last Name:"+st.getLastname());
            //        
            //        Course c=new Course("Curso de Matemática", 1);
            //        
            //        Discipline d= new Discipline("Matematica I",1);
            //        
            //        Module m= new Module("SUM", 1);
            //        
            //        Test t= new Test("Teste da Soma", 1, "Professor de Matematica");
            //        
            //        Question q= new OpenQuestion("Qual é o resultado da Soma 1+1?", 1);
            //        Question q2= new OpenQuestion("Qual é o resultado da Soma 2+2?", 2);
            //        //hipoteses
            //        LinkedList<String> h= new LinkedList<String>();
            //        h.add("3");
            //        h.add("5");
            //        h.add("5");
            //        Question q3= new OneChoiceQuestion("Qual o resultado de 3+3?", 3, h, "6");
            //        
            //        //Correctas
            //        LinkedList<String> co= new LinkedList<String>();
            //        co.add("5");
            //        co.add("5");
            //        Question q4= new MultipleChoiceQuestion("Qual o Resultado de 2+3?", 4, h, co);
            //        
            //        
            //        
            //        
            //        t.addQuestion(q);
            //        t.addQuestion(q2);
            //        t.addQuestion(q3);
            //        t.addQuestion(q4);
            //        
            //        m.addTest(t);
            //        
            //        d.addModule(m);
            //        
            //        c.addDiscipline(d);
            //        
            //        st.addCourse(c);
            //        
            //        System.out.println("Curso: "+st.getCourses().getFirst().getName());
            //        System.out.println("Disciplina: "+st.getCourses().getFirst().getDisciplines().getFirst().getName());
            //        System.out.println("Modulo: "+st.getCourses().getFirst().getDisciplines().getFirst().getModules().getFirst().getName());
            //        System.out.println("Test: "+st.getCourses().getFirst().getDisciplines().getFirst().getModules().getFirst().getTests().getFirst().getName());
            //        
            //        
            //        int size =st.getCourses().getFirst().getDisciplines().getFirst().getModules().getFirst().getTests().getFirst().getQuestions().size();
            //            System.out.println("Numero de perguntas: "+size);
            //        for (Question ql: st.getCourses().getFirst().getDisciplines().getFirst().getModules().getFirst().getTests().getFirst().getQuestions()){
            //            
            //            switch (ql.getQuestionType()){
            //                case MULTIPLE_CHOICE:
            //                    System.out.println("Escolha Multipla");
            //                    System.out.println("Pergunta "+ ql.getText());
            //                    System.out.println("Hipoteses: ");
            //                    for (String s: ql.getPossibleAnswers()){
            //                        System.out.print("  "+s);
            //                    }
            //                    System.out.println();
            //                    System.out.println("Respostas Correctas: ");
            //                    for (String s: ql.getCorrectAnswers()){
            //                        System.out.print("  "+s);
            //                    }
            //                     System.out.println();
            //                    break;
            //                case ONE_CHOICE:
            //                    System.out.println("Uma Escolha");
            //                    System.out.println("Pergunta "+ ql.getText());
            //                    System.out.print("Hipoteses:");
            //                    for (String s: ql.getPossibleAnswers()){
            //                        System.out.print("  "+s);
            //                    }
            //                    System.out.println();
            //                    System.out.println("Resposta Correcta: "+ql.getCorrectAnswer());
            //                    break;
            //                case OPEN:
            //                    System.out.println("Pergunta Aberta");
            //                    System.out.println("Pergunta "+ ql.getText());
            //                    break;
            //            }
            //        }
        } catch (InstantiationException ex) {
            Logger.getLogger(EtoileJavaapi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EtoileJavaapi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EtoileJavaapi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EtoileJavaapi.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
