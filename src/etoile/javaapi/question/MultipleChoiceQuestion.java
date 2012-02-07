/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etoile.javaapi.question;

import java.util.LinkedList;


/**
 *
 * @author Rui
 */
public class MultipleChoiceQuestion extends Question{
    public String text;
    
    public int id;
    
    public LinkedList<String> userAnswer=new LinkedList<String>();
    
    public QuestionType questionType= QuestionType.MULTIPLE_CHOICE;

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

    /**
     * Don't use this!
     * @param userAnswer
     * @return always false
     */
    @Override
    public boolean setAnswer(String userAnswer) {
      return false;
    }
    
    public boolean setAnswer(LinkedList<String> userAnswer) {
        this.userAnswer=userAnswer;
      return true;
    }

    @Override
    public QuestionType getQuestionType() {
        return questionType;
    }
    
}
