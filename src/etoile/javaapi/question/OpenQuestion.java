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
    public String userAnswer;
    public QuestionType questionType = QuestionType.OPEN;

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
    
}
