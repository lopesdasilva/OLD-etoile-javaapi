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
    
    public LinkedList<String> possibleAnswers= new LinkedList<String>();
    
    public LinkedList<String> correctAnswers= new LinkedList<String>();
    
    public QuestionType questionType= QuestionType.MULTIPLE_CHOICE;

    public MultipleChoiceQuestion(String name, int id, LinkedList<String> possibleAnswers, LinkedList<String> correctAnswers) {
        this.text = name;
        this.id = id;
        this.possibleAnswers=possibleAnswers;
        this.correctAnswers=correctAnswers;
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
  
    @Override
    public boolean setAnswer(LinkedList<String> userAnswer) {
        this.userAnswer=userAnswer;
      return true;
    }

    @Override
    public QuestionType getQuestionType() {
        return questionType;
    }

    @Override
    public LinkedList<String> getPossibleAnswers() {
      return possibleAnswers;
    }

    @Override
    public boolean setPossibleAnswers(LinkedList<String> possibleAnswers) {
        this.possibleAnswers=possibleAnswers;
        return true;
    }

    @Override
    public boolean addPossibleAnswser(String possibleAnswer) {
        possibleAnswers.add(possibleAnswer);
        return true;
    }

    @Override
    public void setCorrectAnswer(String correctAnswer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getCorrectAnswer() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean setCorrectAnswers(LinkedList<String> correctAnswers) {
       this.correctAnswers=correctAnswers;
       return true;
    }

    @Override
    public LinkedList<String> getCorrectAnswers() {
       return correctAnswers;
    }
    
}
