/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javaapi.question;


/**
 *
 * @author Rui
 */
public class MultipleChoiceQuestion extends Question{
    public String text;
    
    public int id;
    
    public Answer userAnswer;

    public MultipleChoiceQuestion(String name, int id) {
        this.text = name;
        this.id = id;
    }
    
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
      this.userAnswer=userAnwser;
      return true;
    }
    
}
