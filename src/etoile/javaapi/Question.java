/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javaapi;

import java.util.List;

/**
 *
 * @author Rui
 */
public abstract class Question {
    
    public abstract String getName();
    
    public abstract String getId();
    
    public abstract List<Question> getQuestions();
    
    public abstract boolean setAnswer(Anwser userAnwser);
}
