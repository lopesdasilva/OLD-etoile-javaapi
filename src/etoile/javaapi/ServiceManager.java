/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javaapi;

import db.DBConnect;
import db.SQLInstruct;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import services.UserService;

/**
 *
 * @author Rui
 */
public class ServiceManager implements Serializable{
    Student current_student ;
    private String username;
    private String sha1_of_password;
    private int user_id;
    DBConnect db;

    public ServiceManager() throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        db = new DBConnect(SQLInstruct.dbAdress, SQLInstruct.dbUsername, SQLInstruct.dbPassword);
        db.loadDriver();
    }

    public boolean setAuthentication(String username, String sha1_of_password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        this.username = username;
        this.sha1_of_password = sha1_of_password;


        String sqlStatement = SQLInstruct.login(username, sha1_of_password);
        ResultSet rSet = db.queryDB(sqlStatement);
        if (rSet.next()) {
            user_id = rSet.getInt(1);
            
            current_student=new Student(rSet.getInt(1),rSet.getString(2),rSet.getString(3),rSet.getString(4),rSet.getString(5),rSet.getString(6));    
            return true;
            
        }
        return false;

    }

    public Student getCurrent_student() {
        return current_student;
    }

    public UserService userService() {
        UserService us = new UserService(db,current_student);
        return us;
    }

    public void closeConnection() throws SQLException {
        db.closeDB();
    }
    
   
    
}
