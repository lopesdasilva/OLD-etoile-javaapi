/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.DBConnect;
import db.SQLInstruct;
import etoile.javaapi.Course;
import etoile.javaapi.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Ruben
 */
public class UserService {
    DBConnect db ;
    Student current_student;
    public UserService(DBConnect db,Student current_student) {
       this.db=db;
       this.current_student=current_student;
    }

    public void getCourses(int student_id) throws SQLException {
        String sqlStatement = SQLInstruct.getCourses(student_id);
        System.out.println(sqlStatement);
        ResultSet rSet = db.queryDB(sqlStatement);
        
        while(rSet.next()){
        current_student.addCourse(new Course(rSet.getInt(1),rSet.getString(2)));
        }
        
    }
    
    public void addStudent(Student student) throws SQLException{
        String sqlStatement = SQLInstruct.addStudent(student.getUsername(),student.getPassword(),student.getFirstname(),student.getLastname(),student.getEmail());
        db.updateDB(sqlStatement);
    }
    
}
