/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javaapi.forum;

import java.sql.SQLData;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author rubenpaixao
 */
public class Topic {
    int id;
    String title;
    SQLData date ;
    String username;
    int n_answers;
    LinkedList<TopicAnswer> answers = new LinkedList<TopicAnswer>();

    public Topic(int id, String username, String title, int n_answers) {
        this.id = id;
        this.title = title;
        this.username=username;
        this.n_answers=n_answers;
        //this.date = date;
    }

    public SQLData getDate() {
        return date;
    }

    public void setDate(SQLData date) {
        this.date = date;
    }

    public int getN_answers() {
        return n_answers;
    }

    public void setN_answers(int n_answers) {
        this.n_answers = n_answers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    

    public LinkedList<TopicAnswer> getAnswers() {
        return answers;
    }

    public void setAnswer(LinkedList<TopicAnswer> answers) {
        this.answers = answers;
    }

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public void addAnswer(TopicAnswer answer){
        answers.add(answer);
    }
    
    
    
    
    
}
