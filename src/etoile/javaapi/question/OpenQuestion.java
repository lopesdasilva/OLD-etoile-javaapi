/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javaapi.question;

/**
 *
 * @author Rui
 */
public class OpenQuestion extends Question{
    public String text;
    public int id;
    public Answer userAnwser;

    @Override
    public String getText() {
       return text;
    }

    @Override
    public int getId() {
       return id;
    }

    @Override
    public boolean setAnswer(Answer userAnwser) {
        this.userAnwser=userAnwser;
        return true;
    }
    
}
