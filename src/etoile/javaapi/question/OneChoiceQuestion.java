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
public class OneChoiceQuestion extends Question{

    public String text;
    
    public int id;
    
    public LinkedList<String> possibleAnswers= new LinkedList<String>();
    
    public String userAnswer;
    
    public String correctAnswer;
    
    public QuestionType questionType = QuestionType.ONE_CHOICE;

    public OneChoiceQuestion(String text, int id, LinkedList<String> possibleAnswers, String correctAnswer) {
        this.text = text;
        this.id = id;
        this.possibleAnswers = possibleAnswers;
        this.correctAnswer = correctAnswer;
    }
    
    public boolean addPossibleAnswser(String possibleAnswer){
        possibleAnswers.add(possibleAnswer);
        return true;
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
    public boolean setAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
        return true;
    }

    @Override
    public QuestionType getQuestionType() {
      return questionType;
    }
    
}
