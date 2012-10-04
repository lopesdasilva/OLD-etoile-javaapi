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
    LinkedList<TopicAnswer> answers = new LinkedList<TopicAnswer>();

    public Topic(int id, String username, String title) {
        this.id = id;
        this.title = title;
        this.username=username;
        //this.date = date;
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
