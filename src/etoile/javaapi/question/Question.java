/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javaapi.question;

import java.io.Serializable;
import java.util.LinkedList;


/**
 *
 * @author Rui
 */
public abstract class Question implements Serializable{
    
    public abstract void addURL(URL url);
    
    public abstract LinkedList<URL> getURLS();
    
    public abstract boolean setURLS(LinkedList<URL> urls);
    
    public abstract void setCorrectAnswer(String correctAnswer);
   
    public abstract boolean setCorrectAnswers(LinkedList<String> correctAnswers);
    
    public abstract String getCorrectAnswer();
    
    public abstract QuestionType getQuestionType();
    
    public abstract String getText();
    
    public abstract int getId();
    
    public abstract String getAnswer();
    
    public abstract LinkedList<String> getAnswers();
       
    public abstract boolean setAnswer(String userAnswer);
    
    public abstract boolean setAnswers(LinkedList<String> userAnswer);
    
    public abstract LinkedList<String> getPossibleAnswers();
   
    public abstract LinkedList<String> getCorrectAnswers();
    
    public abstract boolean setPossibleAnswers(LinkedList<String> possibleAnswers);
    
    public abstract boolean addPossibleAnswser(String possibleAnswer);
    
    public abstract boolean isFirst();
    
    public abstract boolean isLast();
    
    
    
}
