/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.DBConnect;
import db.SQLInstruct;
import etoile.javaapi.Student;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ruben
 */
public class UserService {
    DBConnect db ;
    public UserService(DBConnect db) {
       this.db=db;
    }

    public void getCourses(int student_id) throws SQLException {
        String sqlStatement = SQLInstruct.getCourses(student_id);
        System.out.println(sqlStatement);
        ResultSet rSet = db.queryDB(sqlStatement);
    }
    
    public void addStudent(Student student) throws SQLException{
        String sqlStatement = SQLInstruct.addStudent(student.getUsername(),student.getPassword(),student.getFirstname(),student.getLastname(),student.getEmail());
        db.updateDB(sqlStatement);
    }
    
}
