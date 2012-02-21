/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.DBConnect;
import db.SQLInstruct;
import etoile.javaapi.*;
import etoile.javaapi.question.MultipleChoiceQuestion;
import etoile.javaapi.question.OneChoiceQuestion;
import etoile.javaapi.question.OpenQuestion;
import etoile.javaapi.question.Question;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Ruben
 */
public class UserService implements Serializable{

    DBConnect db;
    Student current_student;

    public UserService(DBConnect db, Student current_student) {
        this.db = db;
        this.current_student = current_student;
    }

    public void updateCourses(int student_id) throws SQLException {
        String sqlStatement = SQLInstruct.getCourses(student_id);
        ResultSet rSet = db.queryDB(sqlStatement);

        while (rSet.next()) {
            Course c=new Course(rSet.getInt(1), rSet.getString(2));
            current_student.addCourse(c);
            updateDisciplines(c);
        }

    }

    public void addStudent(Student student) throws SQLException {
        String sqlStatement = SQLInstruct.addStudent(student.getUsername(), student.getPassword(), student.getFirstname(), student.getLastname(), student.getEmail());
        db.updateDB(sqlStatement);
    }

    public void updateDisciplines(Course course) throws SQLException {
        String sqlStatement = SQLInstruct.getDisciplines(course.getId());
        ResultSet rSet = db.queryDB(sqlStatement);

        while (rSet.next()) {
            Discipline d = new Discipline(rSet.getInt(1), rSet.getString(2));
            course.addDiscipline(d);
            updateModules(d);
        }
    }

    public void updateModules(Discipline discipline) throws SQLException {
        String sqlStatement = SQLInstruct.getModules(discipline.getId());
        ResultSet rSet = db.queryDB(sqlStatement);

        while (rSet.next()) {
            discipline.addModule(new Module(rSet.getInt(1), rSet.getString(2)));
        }
    }

    public void updateTests(Module module) throws SQLException {
        
        module.setTests(new LinkedList<Test>()); //to restart tests No repeats
        
        String sqlStatement = SQLInstruct.getTests(module.getId());
        ResultSet rSet = db.queryDB(sqlStatement);

        while (rSet.next()) {


            module.addTest(new Test(rSet.getInt(1), rSet.getString(2), rSet.getString(3), rSet.getString(4), rSet.getDate(5), rSet.getDate(6)));
        }
    }

    public void updateQuestions(Test test) throws SQLException {
        getOpenQuestions(test);
        getOneChoiceQuestions(test);
        getMultipleChoiceQuestions(test);

    }

    private void getOpenQuestions(Test test) throws SQLException {
        //OPENQUESTIONS
        String sqlStatement = SQLInstruct.getOpenQuestions(test.getId());
        ResultSet rSet = db.queryDB(sqlStatement);
        while (rSet.next()) {
            Question q = new OpenQuestion(rSet.getInt(1), rSet.getString(2));
            test.addQuestion(q);
            String sqlStatement_correct = SQLInstruct.getOpenQuestionAnswer(rSet.getInt(1), current_student.getId());
            ResultSet rSet_answer = db.queryDB(sqlStatement_correct);
            if (rSet_answer.next()) {
                q.setUserAnswer(rSet_answer.getString(1));
            }

        }
    }

    private void getOneChoiceQuestions(Test test) throws SQLException {
        String sqlStatement = SQLInstruct.getOneChoiceQuestions(test.getId());
        ResultSet rSet = db.queryDB(sqlStatement);
        while (rSet.next()) {
            OneChoiceQuestion op = new OneChoiceQuestion(rSet.getInt(1), rSet.getString(2));
            test.addQuestion(op);

            //Hypothesis
            String sqlStatement_hypothesis = SQLInstruct.getOneChoiceHypothesis(rSet.getInt(1));
            ResultSet rSet_hypothesis = db.queryDB(sqlStatement_hypothesis);
            while (rSet_hypothesis.next()) {
                op.addPossibleAnswser(rSet_hypothesis.getString(2));
                if (rSet_hypothesis.getInt(3) == 1) {
                    op.setCorrectAnswer(rSet_hypothesis.getString(2));
                }
            }


            String sqlStatement_correct = SQLInstruct.getOneChoiceAnswer(rSet.getInt(1), current_student.getId());
            ResultSet rSet_answer = db.queryDB(sqlStatement_correct);
            if (rSet_answer.next()) {
                op.setUserAnswer(rSet_answer.getString(1));
            }

        }
    }

    private void getMultipleChoiceQuestions(Test test) throws SQLException {
        String sqlStatement = SQLInstruct.getMultipleChoiceQuestions(test.getId());
        ResultSet rSet = db.queryDB(sqlStatement);
        while (rSet.next()) {
            MultipleChoiceQuestion mp = new MultipleChoiceQuestion(rSet.getInt(1), rSet.getString(2));
            test.addQuestion(mp);

            //Hypothesis
            String sqlStatement_hypothesis = SQLInstruct.getMultipleChoiceHypothesis(rSet.getInt(1));
            ResultSet rSet_hypothesis = db.queryDB(sqlStatement_hypothesis);
            LinkedList<String> correct = new LinkedList<String>();
            while (rSet_hypothesis.next()) {
                mp.addPossibleAnswser(rSet_hypothesis.getString(2));
                if (rSet_hypothesis.getInt(3) == 1) {
                    correct.add(rSet_hypothesis.getString(2));
                }
            }
            mp.setCorrectAnswers(correct);
            String sqlStatement_correct = SQLInstruct.getMultipleChoiceAnswer(rSet.getInt(1), current_student.getId());
            ResultSet rSet_answer = db.queryDB(sqlStatement_correct);
            LinkedList<String> answers = new LinkedList<String>();
            while(rSet_answer.next()){
            answers.add(rSet_answer.getString(1));
            }
            mp.setAnswers(answers);
        }
    }
    
     public Discipline getDiscipline(String name){
       
        for(Course c : current_student.getCourses()){
            for(Discipline d: c.getDisciplines()){
                if(d.getName().equals(name))
                    return d;
            }
        }
    return null;
    }

    public Module getModule(Object value) {
        //TODO Cant exist modules with the same name
        for(Course c: current_student.getCourses()){
            for(Discipline d: c.getDisciplines()){
               for(Module m: d.getModules())
                   if(m.getName().equals(value))
                       return m;
            }
        }
        return null;
    }

   
}
