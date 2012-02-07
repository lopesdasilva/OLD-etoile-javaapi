/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javaapi.question;

import java.util.List;

/**
 *
 * @author Rui
 */
public abstract class Question {
    
    public abstract String getText();
    
    public abstract int getId();
       
    public abstract boolean setAnswer(Answer userAnwser);
}
