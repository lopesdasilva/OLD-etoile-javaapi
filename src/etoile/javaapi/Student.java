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
public class Student implements Serializable {
    int id;
    public String username;
    public String password; //SHA1
    
    //profile
    public String firstname;

    public LinkedList<News> getNews() {
        return news;
    }
    public String lastname;
    public String email;
    
    public LinkedList<Course> courses= new LinkedList<Course>();
    public LinkedList<News> news = new LinkedList<News>();

    public Student(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Student(String username, String password, String firstname, String lastname, String email) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }
    
    public Student(int id,String username, String password, String firstname, String lastname, String email) {
        this.id=id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public int getId() {
        return id;
    }
    
    
    public LinkedList<Course> getCourses() {
        return courses;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setCourses(LinkedList<Course> courses) {
        this.courses = courses;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addCourse(Course c) {
        courses.add(c);
    }
    
    public void addNew(News n){
        news.add(n);
    }
    
    
}
