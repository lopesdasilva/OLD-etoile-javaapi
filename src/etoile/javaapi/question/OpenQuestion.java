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
public class OpenQuestion extends Question{
    public String text;
    public int id;
    public String userAnswer;
    public QuestionType questionType = QuestionType.OPEN;
    private URL url;

    public OpenQuestion(String text, int id) {
        this.text = text;
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
    public boolean setAnswer(String userAnswser) {
        this.userAnswer=userAnswser;
        return true;
    }

    @Override
    public QuestionType getQuestionType() {
       return questionType;
    }

    /**
     * Don't use this!
     * @return 
     */
    @Override
    public LinkedList<String> getPossibleAnswers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
/**
     * Don't use this!
     * @return 
     */
    @Override
    public boolean setPossibleAnswers(LinkedList<String> possibleAnswers) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * Don't use this!
     * @return 
     */
    @Override
    public boolean addPossibleAnswser(String possibleAnswer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * Don't use this!
     * @return 
     */
    @Override
    public void setCorrectAnswer(String correctAnswer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /**
     * Don't use this!
     * @return 
     */
    @Override
    public String getCorrectAnswer() {
        throw new UnsupportedOperationException("Not supported yet.");
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
    public URL getURL() {
        return url;
    }

    @Override
    public boolean setURL(URL url) {
        this.url=url;
        return true;
    }
    
}
