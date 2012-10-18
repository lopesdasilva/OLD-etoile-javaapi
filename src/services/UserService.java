/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.DBConnect;
import db.SQLInstruct;
import etoile.javaapi.*;
import etoile.javaapi.forum.Forum;
import etoile.javaapi.forum.Topic;
import etoile.javaapi.forum.TopicAnswer;
import etoile.javaapi.question.*;
import exceptions.VoteException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Ruben
 */
public class UserService implements Serializable{

    DBConnect db;
    Student current_student;

    public UserService(DBConnect db, Student current_student) {
        this.db = db;
        this.current_student = current_student;
    }
    

    
    public void updateCourses(int student_id) throws SQLException {
        String sqlStatement = SQLInstruct.getCourses(student_id);
        ResultSet rSet = db.queryDB(sqlStatement);

        while (rSet.next()) {
            Course c=new Course(rSet.getInt(1), rSet.getString(2));
            current_student.addCourse(c);
            updateDisciplines(c);
        }

    }
    


    public void addStudent(Student student) throws SQLException {
        String sqlStatement = SQLInstruct.addStudent(student.getUsername(), student.getPassword(), student.getFirstname(), student.getLastname(), student.getEmail());
        db.updateDB(sqlStatement);
        System.out.println("SELECT id FROM student WHERE student.username = "+student.getUsername()+"");
        ResultSet rSet = db.queryDB("SELECT id FROM student WHERE student.username = '"+student.getUsername()+"'");
        while(rSet.next()){
        String statmentAddCourse = SQLInstruct.registerStudentCourse(rSet.getInt(1),1);
        db.updateDB(statmentAddCourse);
        
        SendMail sm = new SendMail("smtp.gmail.com","465"); 
        String origem = "etoileplatform@gmail.com";
        String destino = student.getEmail();
        String assunto = "EtoilePlatform Registration";
        String mensagem = student.getUsername()+", Thank's for your registration.\n\n Etoile Team."; 
        sm.sendMail(origem,destino,assunto,mensagem);
        
        }
        
    }
    
    public void getNews() throws SQLException{
        ResultSet rSet = db.queryDB(SQLInstruct.getNews());
        while(rSet.next()){
           current_student.addNew(new News(rSet.getInt(1),rSet.getString(2),rSet.getString(3),rSet.getString(4),rSet.getString(5))); 
        }
    }
    

    public void updateDisciplines(Course course) throws SQLException {
        String sqlStatement = SQLInstruct.getDisciplines(course.getId());
        ResultSet rSet = db.queryDB(sqlStatement);

        while (rSet.next()) {
            Discipline d = new Discipline(rSet.getInt(1), rSet.getString(2),rSet.getString(3));
            course.addDiscipline(d);
            updateModules(d);
        }
    }

    public void updateModules(Discipline discipline) throws SQLException {
        String sqlStatement = SQLInstruct.getModules(discipline.getId());
        ResultSet rSet = db.queryDB(sqlStatement);

        while (rSet.next()) {
            discipline.addModule(new Module(rSet.getInt(1), rSet.getString(2)));
        }
    }

    public void updateTests(Module module) throws SQLException {
        
        module.setTests(new LinkedList<Test>()); //to restart tests No repeats
        
        String sqlStatement = SQLInstruct.getTests(module.getId());
        ResultSet rSet = db.queryDB(sqlStatement);

        while (rSet.next()) {


            module.addTest(new Test(rSet.getInt(1), rSet.getString(2), rSet.getString(3), rSet.getString(4), rSet.getDate(5), rSet.getDate(6), rSet.getString(7), rSet.getInt(8)));
        }
        
    }

    public void updateQuestions(Test test) throws SQLException {
        
        LinkedList<Question> openquestions = getOpenQuestions(test);
        getOpenQuestionURLs(openquestions);
        LinkedList<Question> onechoicequestions =getOneChoiceQuestions(test);
        getOneChoiceQuestionURLs(onechoicequestions);
        LinkedList<Question> multiplechoicequestions = getMultipleChoiceQuestions(test);
        getMultipleChoiceQuestionURLs(multiplechoicequestions);
        Collections.sort(test.questions);

    }

    private LinkedList<Question> getOpenQuestions(Test test) throws SQLException {
        //OPENQUESTIONS
        String sqlStatement = SQLInstruct.getOpenQuestions(test.getId());
        ResultSet rSet = db.queryDB(sqlStatement);
        LinkedList<Question> questions = new LinkedList<Question>();
        while (rSet.next()) {
            Question q = new OpenQuestion(rSet.getInt(1), rSet.getString(2));
            q.setNumber(rSet.getInt(3));
            test.addQuestion(q);
            questions.add(q);
            String sqlStatement_correct = SQLInstruct.getOpenQuestionAnswer(rSet.getInt(1), current_student.getId());
            ResultSet rSet_answer = db.queryDB(sqlStatement_correct);
            if (rSet_answer.next()) {
                q.setUserAnswer(rSet_answer.getString(2));
                q.setAnswerId(rSet_answer.getInt(1));
            }else {
                String sqlStatementAddAnswer = SQLInstruct.insertOpenQuestionAnswer(current_student.getId(), q.getId(), "");
                db.updateDB(sqlStatementAddAnswer);
                q.setUserAnswer("");
            }

        }
        return questions;
    }

    private LinkedList<Question> getOneChoiceQuestions(Test test) throws SQLException {
        String sqlStatement = SQLInstruct.getOneChoiceQuestions(test.getId());
        ResultSet rSet = db.queryDB(sqlStatement);
        LinkedList<Question> questions = new LinkedList<Question>();
        while (rSet.next()) {
            OneChoiceQuestion op = new OneChoiceQuestion(rSet.getInt(1), rSet.getString(2));
            op.setNumber(rSet.getInt(3));
            test.addQuestion(op);
            questions.add(op);
            //Hypothesis
            String sqlStatement_hypothesis = SQLInstruct.getOneChoiceHypothesis(rSet.getInt(1));
            ResultSet rSet_hypothesis = db.queryDB(sqlStatement_hypothesis);
            while (rSet_hypothesis.next()) {
                op.addPossibleAnswser(rSet_hypothesis.getString(2));
                if (rSet_hypothesis.getInt(3) == 1) {
                    op.setCorrectAnswer(rSet_hypothesis.getString(2));
                }
            }


            String sqlStatement_useranswer = SQLInstruct.getOneChoiceAnswer(rSet.getInt(1), current_student.getId());
            ResultSet rSet_answer = db.queryDB(sqlStatement_useranswer);
            if (rSet_answer.next()) {
                op.setAnswerId(rSet_answer.getInt(1));
                op.setUserAnswer(rSet_answer.getString(2));
            }else{
                String sqlStatementAddAnswer = SQLInstruct.insertOneChoiceQuestionAnswer(current_student.getId(),op.getId());
                db.updateDB(sqlStatementAddAnswer);
                op.setUserAnswer("No Answer.");
            }

        }
        
        return questions;
    }

        public LinkedList<Question> getMultipleChoiceQuestions(Test test) throws SQLException {
        String sqlStatement = SQLInstruct.getMultipleChoiceQuestions(test.getId());
        ResultSet rSet = db.queryDB(sqlStatement);
        LinkedList<Question> questions = new LinkedList<Question>();
        while (rSet.next()) {
            MultipleChoiceQuestion mp = new MultipleChoiceQuestion(rSet.getInt(1), rSet.getString(2));
            mp.setNumber(rSet.getInt(3));
            test.addQuestion(mp);
            questions.add(mp);
            //Hypothesis
            String sqlStatement_hypothesis = SQLInstruct.getMultipleChoiceHypothesis(rSet.getInt(1));
            ResultSet rSet_hypothesis = db.queryDB(sqlStatement_hypothesis);
            LinkedList<String> correct = new LinkedList<String>();
            while (rSet_hypothesis.next()) {
                mp.addPossibleAnswser(rSet_hypothesis.getString(2));
                if (rSet_hypothesis.getInt(3) == 1) {
                    correct.add(rSet_hypothesis.getString(2));
                }
            }
            mp.setCorrectAnswers(correct);
            String sqlStatement_useranswer= SQLInstruct.getMultipleChoiceAnswer(rSet.getInt(1), current_student.getId());
            ResultSet rSet_answer = db.queryDB(sqlStatement_useranswer);
            LinkedList<String> answers = new LinkedList<String>();
            while(rSet_answer.next()){
            answers.add(rSet_answer.getString(2));

            }
            mp.setUserAnswers(answers);
        }
        
        return questions;
    }
    
     private void getOpenQuestionURLs(LinkedList<Question> questions) throws SQLException {
      for(Question q : questions){
          String sqlStatement = SQLInstruct.getOpenQuestionURLs(q.getId());
          ResultSet rSet = db.queryDB(sqlStatement);
          while(rSet.next()){
              String SQLstatement_checkVoted = SQLInstruct.checkVoted(rSet.getInt(1), current_student.getId());
              ResultSet rSet_checkVoted = db.queryDB(SQLstatement_checkVoted);
              if(rSet_checkVoted.next()){
              q.addURL(new URL(rSet.getInt(1),rSet.getString(2),rSet.getString(3),"noname",rSet.getInt(4), rSet.getInt(5), rSet.getInt(6), true));
              }else{
              q.addURL(new URL(rSet.getInt(1),rSet.getString(2),rSet.getString(3),"noname",rSet.getInt(4), rSet.getInt(5), rSet.getInt(6), false));
    
              }
              }
      }
    }
     
    private void getOneChoiceQuestionURLs(LinkedList<Question> questions) throws SQLException {
        for(Question q : questions){
          String sqlStatement = SQLInstruct.getOneChoiceURLs(q.getId());
          ResultSet rSet = db.queryDB(sqlStatement);
          while(rSet.next()){
              String SQLstatement_checkVoted = SQLInstruct.checkVoted(rSet.getInt(1), current_student.getId());
              ResultSet rSet_checkVoted = db.queryDB(SQLstatement_checkVoted);
              if(rSet_checkVoted.next()){
              q.addURL(new URL(rSet.getInt(1),rSet.getString(2),rSet.getString(3),"noname",rSet.getInt(4), rSet.getInt(5),rSet.getInt(6),true));
              }else{
              q.addURL(new URL(rSet.getInt(1),rSet.getString(2),rSet.getString(3),"noname",rSet.getInt(4), rSet.getInt(5),rSet.getInt(6),false));

              }
              }
      }
    } 
    
     private void getMultipleChoiceQuestionURLs(LinkedList<Question> questions) throws SQLException {
       for(Question q : questions){
          String sqlStatement = SQLInstruct.getMultipleChoiceURLs(q.getId());
          ResultSet rSet = db.queryDB(sqlStatement);
          while(rSet.next()){
              String SQLstatement_checkVoted = SQLInstruct.checkVoted(rSet.getInt(1), current_student.getId());
              ResultSet rSet_checkVoted = db.queryDB(SQLstatement_checkVoted);
              if(rSet_checkVoted.next()){
              q.addURL(new URL(rSet.getInt(1),rSet.getString(2),rSet.getString(3),"noname",rSet.getInt(4), rSet.getInt(5), rSet.getInt(6),true));
              }else{
              q.addURL(new URL(rSet.getInt(1),rSet.getString(2),rSet.getString(3),"noname",rSet.getInt(4), rSet.getInt(5), rSet.getInt(6),true));                  
              }
              }
      } 
    } 
     
     
     public Discipline getDiscipline(String name){
       
        for(Course c : current_student.getCourses()){
            for(Discipline d: c.getDisciplines()){
                if(d.getName().equals(name))
                    return d;
            }
        }
    return null;
    }

    public Module getModule(Object value) {
        //TODO Cant exist modules with the same name
        for(Course c: current_student.getCourses()){
            for(Discipline d: c.getDisciplines()){
               for(Module m: d.getModules())
                   if(m.getName().equals(value))
                       return m;
            }
        }
        return null;
    }

    

    //UPDATE QUESTIONS
    
    public void updateOpenAnswer(int question_id, String openanswer) throws SQLException{
        // answer_id este valor é obtido após ser feito um get na Question.getAnswerId();
        //openanswer = openanswer.replaceAll("'", "''");
        String sqlStatement_getQuestion = SQLInstruct.getOpenQuestionAnswer(question_id, current_student.getId());
        ResultSet rSet = db.queryDB(sqlStatement_getQuestion);
        while(rSet.next()){
        String sqlStatement = SQLInstruct.updateOpenQuetionAnswer(rSet.getInt(1), openanswer);
        db.updateDB(sqlStatement);
        }
        
    }
    
    public void updateOneChoiceAnswer(int question_id,String answer) throws SQLException{
        String sqlStatement_getQuestion = SQLInstruct.getOneChoiceAnswer(question_id, current_student.getId());
        ResultSet rSet = db.queryDB(sqlStatement_getQuestion);
        while(rSet.next()){
        String sqlStatement = SQLInstruct.updateOneChoiceAnswer(rSet.getInt(1),answer);
        db.updateDB(sqlStatement);
            System.out.println("ONECHOICEANSWER UPDATED!!!");
        }
    }
    
    public void updateMultipleChoiceAnswer(int question_id, LinkedList<String> userAnswers) throws SQLException{
            String sqlStatement_useranswer= SQLInstruct.getMultipleChoiceAnswer(question_id, current_student.getId());
            ResultSet rSet_answer = db.queryDB(sqlStatement_useranswer);
            //remover respostas anteriores
            while(rSet_answer.next()){
             String SqlStatement_removeAnswer = "DELETE FROM multiplechoiceanswer WHERE id='"+rSet_answer.getInt(1)+"'";
             db.updateDB(SqlStatement_removeAnswer);
            }
            //adicionar respostas actuais
            for(String answer: userAnswers){
                String sqlStatement = SQLInstruct.insertMultipleChoiceAnswer(question_id,current_student.getId(),answer);
                db.updateDB(sqlStatement);
            }
            
    }
    
    public void setVotes(URL url, int stars) throws SQLException, VoteException{    
        //votar
        
        
        //ver na base de dados
        try{
        if(url.isAlready_voted()){
            throw new VoteException();
        }else{
        
       
        
        String SQLStatement_getUpdatedValues = SQLInstruct.getURL(url.getId());
        ResultSet rSet_getUpdatedValues = db.queryDB(SQLStatement_getUpdatedValues);
        int new_rating=0;
        double total_stars = 0;
        double total_votes = 0;
        while(rSet_getUpdatedValues.next()){
        total_stars = rSet_getUpdatedValues.getInt(1)+stars;
        total_votes = rSet_getUpdatedValues.getInt(2)+1;
        }
            System.out.println("DEBUG");
            System.out.println(total_stars);
            System.out.println(total_votes);

        double new_rating_aux = 0;
        if(total_votes>1 && total_votes<5){
                new_rating_aux = (total_stars/total_votes)+0.4;
        }else if(total_votes==1){
             new_rating_aux = (total_stars/total_votes)-0.6;       
               
        }else if(total_votes > 4 && total_votes <=6){
                new_rating_aux=((total_stars*0.7)+(total_votes*0.3))/5;
        }else{
             new_rating_aux=total_stars/total_votes;
             System.out.println("ASDAS"+new_rating_aux);
        }
        
            System.out.println("new rating aux >= 4.5?" + new_rating_aux);
        if(new_rating_aux >= 4.5){
            new_rating= 5;
        }else{
        new_rating = (int)new_rating_aux;
        }
        
        System.out.println("NEW RATING = "+ new_rating);
        url.setAverage(new_rating);
        url.setVotes(stars);
        String sqlStatement = SQLInstruct.vote(url.getId(),stars,new_rating);
        db.updateDB(sqlStatement);
        
        String SQLStatement_voted = SQLInstruct.setVoted(url.getId(),current_student.getId(),stars);
        db.updateDB(SQLStatement_voted);
        
        url.setAlready_voted(true);

        
        }
        }catch(VoteException e){
            throw e;
        }
    }
    
    public void addURL(String url_name, String url , QuestionType type, Question question ) throws SQLException{
        
        String sqlStatement = SQLInstruct.addUrl(url_name, url);
        db.updateDB(sqlStatement);
                String sqlGetUrl = SQLInstruct.getLastURLInserted();
        ResultSet rSet=db.queryDB(sqlGetUrl);
                if(rSet.next()){
        if (type==QuestionType.OPEN){ // adiciona À openquestion


            String sqlLinkURLOpenQuestion = SQLInstruct.linkURLOpenQuestion(question.getId(), rSet.getInt(1));
            System.out.println("OPEN: "+sqlLinkURLOpenQuestion);

            db.updateDB(sqlLinkURLOpenQuestion);
            
        }else if(type==QuestionType.ONE_CHOICE){ // adiciona À onechoicequestion

            String sqlLinkURLOneChoiceQuestion = SQLInstruct.linkURLOneChoiceQuestion(question.getId(), rSet.getInt(1));
            System.out.println("ONECHOICE: "+sqlLinkURLOneChoiceQuestion);
            db.updateDB(sqlLinkURLOneChoiceQuestion);
          
            
        }else{//adiciona à multiplechoicequestion

            String sqlLinkURLMultipleChoiceQuestion = SQLInstruct.linkURLMultipleChoiceQuestion(question.getId(), rSet.getInt(1));
            System.out.println("MULTIPLE: "+sqlLinkURLMultipleChoiceQuestion);

            db.updateDB(sqlLinkURLMultipleChoiceQuestion);
        
    }
    
                }
               
                question.addURL(new URL(rSet.getInt(1),url_name,url,"noname",0,0,0,false));
     
                
    
    }
    
    public void changeNoAnswersToNull() throws SQLException{
       String sqlStatement = SQLInstruct.getallopenanswers();
       ResultSet rSet = db.queryDB(sqlStatement);
       while(rSet.next()){
           if(rSet.getString(2).equals("No Answer.")){
               String udpateDB = SQLInstruct.changeNoAnswerToNull(rSet.getInt(1));
               db.updateDB(udpateDB);
           }
       }
    }
    
    public void getOpenQuestionTestResults(int test_id){
        
    }
    
    public void sendLinkToResetPassword(String email){
        SendMail sm = new SendMail("smtp.gmail.com","465"); 
        String origem = "etoileplatform@gmail.com";
        String destino = email;
        String assunto = "EtoilePlatform Reset Password";
        String mensagem = "Dear Student, tap this link to reset your password.\nhttp://www.google.pt" ; 
        sm.sendMail(origem,destino,assunto,mensagem); 
    }
    
    public void resetPassword(String email) throws NoSuchAlgorithmException, SQLException{
        //GERAR Nova Password
        
        String SQLString = SQLInstruct.validEmail(email);
        ResultSet rSet = db.queryDB(SQLString);
        try{
        if(rSet.next()){
        final int PASSWORD_LENGTH = 8;  
        StringBuffer sb = new StringBuffer();  
        for (int x = 0; x < PASSWORD_LENGTH; x++)  
        {  
          sb.append((char)((int)(Math.random()*26)+97));  
        }  
        
        String password = sb.toString();
        //Alterar Password na BD
         //Transformar password em sha
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(password.getBytes());
        byte[] bytes = md.digest();

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
            int parteBaixa = bytes[i] & 0xf;
            if (parteAlta == 0) {
                s.append('0');
            }
            s.append(Integer.toHexString(parteAlta | parteBaixa));
        }
        
        String password_sha=s.toString();
        System.out.println("PW" + password);
        System.out.println("SHA1:"+password_sha);
        
        
        String SQLStatement = SQLInstruct.changePassword(email,password_sha);
        System.out.println(SQLStatement);
        db.updateDB(SQLStatement);
        
        //Ir buscar o user
        
        
        SendMail sm = new SendMail("smtp.gmail.com","465"); 
        String origem = "etoileplatform@gmail.com";
        String destino = email;
        String assunto = "EtoilePlatform Reset Password";
        String mensagem = "Dear "+rSet.getString(2)+", This is your new Password: " + sb.toString() ; 
        sm.sendMail(origem,destino,assunto,mensagem);
        }else{
           throw new SQLException();

        }
        }catch(SQLException e){
           throw e;
        }
 
    
    
        
    }
    
        public void changePassword(String new_password) throws NoSuchAlgorithmException, SQLException{
          
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(new_password.getBytes());
        byte[] bytes = md.digest();

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
            int parteBaixa = bytes[i] & 0xf;
            if (parteAlta == 0) {
                s.append('0');
            }
            s.append(Integer.toHexString(parteAlta | parteBaixa));
        } 
            
        String SQLStatement = SQLInstruct.changePassword_logedin(current_student.username,s.toString());
        System.out.println(SQLStatement);
        db.updateDB(SQLStatement);
        
        //Ir buscar o user
        
        
        SendMail sm = new SendMail("smtp.gmail.com","465"); 
        String origem = "etoileplatform@gmail.com";
        String destino = current_student.email;
        String assunto = "EtoilePlatform Password Changed";
        String mensagem = "Dear "+current_student.getUsername()+", This is your new Password: " + new_password ; 
        sm.sendMail(origem,destino,assunto,mensagem);
                
        }
        
        
        //BEGIN FORUM
        
        public void updateForum(Discipline d) throws SQLException{
            //id à db buscar o id do forum
            //criar Forum f
            //updateTopics(f)
            //adicionar à Discipline
            
            String sqlStatement = SQLInstruct.getForum(d.getId());
            ResultSet rSet = db.queryDB(sqlStatement);

            while (rSet.next()) {
                Forum f = new Forum(rSet.getInt(1),rSet.getString(3));
                d.setForum(f);
                updateForumTopics(f);
             }
            
            
            
        }
        


    public void updateForumTopics(Forum f) throws SQLException {
        String SQLStatement = SQLInstruct.getForumTopics(f.getId());
        ResultSet rSet = db.queryDB(SQLStatement);
        
        while(rSet.next()){
            Topic t = new Topic(rSet.getInt(1),rSet.getString(2),rSet.getString(3), rSet.getInt(4));
            f.addTopic(t);
            //updateTopicAnswers(t);
        }
    }

    public void updateTopicAnswers(Topic t) throws SQLException {
        String SQLStatement = SQLInstruct.getTopicAnswers(t.getId());
        System.out.println(SQLStatement);
        ResultSet rSet = db.queryDB(SQLStatement);
        
        while(rSet.next()){
            TopicAnswer ta = new TopicAnswer(rSet.getInt(1),rSet.getString(2),rSet.getString(3));
            t.addAnswer(ta);
        }
        
        
        
        //E QUANDO FOR CHAMADO DUAS VEZES? acrescenta sempre mais 1 ? ver disto !
    }
    
    public void addForumTopic(Forum f, String title) throws SQLException{
        Topic t;
        
        String SQLStatement = SQLInstruct.addForumTopic(current_student.getUsername(), title);
        db.updateDB(SQLStatement);
        //Topico já adicionado
        
        String SQLStatement_getLastTopic = SQLInstruct.getLastTopicInserted();
        ResultSet rSet = db.queryDB(SQLStatement_getLastTopic);
        if(rSet.next()){
           String SQLStatement_linkTopic = SQLInstruct.linkTopicForum(f.getId(), rSet.getInt(1)); 
           db.updateDB(SQLStatement_linkTopic);
           t = new Topic(rSet.getInt(1), current_student.getUsername(), title, 0);
        }

    }
    
    public void addTopicAnswer(Topic t, String answer) throws SQLException{
        
        TopicAnswer ta;
        String SQLStatement = SQLInstruct.addTopicAnswer(current_student.getUsername(), answer);
        db.updateDB(SQLStatement);
        //Answer já adicionada
        
        String SQLStatement_getLastAnswer = SQLInstruct.getLastTopicAnswerInserted();
        ResultSet rSet = db.queryDB(SQLStatement_getLastAnswer);
        
        if(rSet.next()){
           String SQLStatement_linkAnswer = SQLInstruct.linkAnswerTopic(t.getId(), rSet.getInt(1)); 
           db.updateDB(SQLStatement_linkAnswer);
           ta = new TopicAnswer(rSet.getInt(1), current_student.getUsername(), answer);
        }
        

        //actualizar o numero de answers do topico
        String SQLStatement_UpdateNAnswers = SQLInstruct.updateNAnswers(t.getId());
        db.updateDB(SQLStatement_UpdateNAnswers);
        
        //isto tava a dar problemas tenho que ir sempre buscar `a DB antes de por na classe.
        
//        String StringSQL_getNanswers = SQLInstruct.getForumTopic(t.getId());
//        ResultSet rSet_NAnswers = db.queryDB(StringSQL_getNanswers);
//        
//        while(rSet_NAnswers.next()){
//            System.out.println("ASDHASDJKHASKDJHASKDJHASJKD"+rSet.getInt(1));
//            t.setN_answers(rSet.getInt(1));
//        }
        
        t.setN_answers(t.getN_answers()+1);
        
        
    }
    
            //END FORUM
    
   
}
