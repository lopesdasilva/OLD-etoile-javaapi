/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javaapi;

import etoile.javaapi.question.Question;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Rui
 */
public class Test {
    
    public String name;
    
    public int id;
    
    //TODO: Replace String with Teacher Class
    public String author;
    
    public Date beginDate;
    
    public Date endDate;
    
    public List<Question> questions;

    public Test(String name, int id, String author) {
        this.name = name;
        this.id = id;
        this.author = author;
    }

    public Test(String name, int id, String author, Date beginDate, Date endDate) {
        this.name = name;
        this.id = id;
        this.author = author;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }
    
    public String getAuthor() {
        return author;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    
    
}
