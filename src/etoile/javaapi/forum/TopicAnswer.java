/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javaapi.forum;

import java.util.Date;

/**
 *
 * @author rubenpaixao
 */
public class TopicAnswer {
    int id;
    String username;
    String answer;
    Date date;

    public TopicAnswer(int id, String username, String answer) {
        this.id = id;
        this.username = username;
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
    
}
