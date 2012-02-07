/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javaapi.question;


/**
 *
 * @author Rui
 */
public abstract class Question {
    
    public abstract QuestionType getQuestionType();
    
    public abstract String getText();
    
    public abstract int getId();
       
    public abstract boolean setAnswer(String userAnswer);
}
