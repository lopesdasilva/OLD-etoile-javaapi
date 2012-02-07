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
    private LinkedList<URL> urls=new LinkedList<URL>();

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

    public LinkedList<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    @Override
    public boolean setPossibleAnswers(LinkedList<String> possibleAnswers) {
        this.possibleAnswers=possibleAnswers;
        return true;
    }

    @Override
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer=correctAnswer;
    }

    @Override
    public String getCorrectAnswer() {
       return correctAnswer;
    }

    @Override
    public boolean setAnswer(LinkedList<String> userAnswer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean setCorrectAnswers(LinkedList<String> correctAnswers) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LinkedList<String> getCorrectAnswers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
      @Override
    public LinkedList<URL> getURLS() {
        return urls;
    }

    @Override
    public boolean setURLS(LinkedList<URL> urls) {
        this.urls=urls;
        return true;
    }
    @Override
    public void addURL(URL url) {
        urls.add(url);
    }
    
}
