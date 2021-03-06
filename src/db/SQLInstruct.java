/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;

public class SQLInstruct implements Serializable {

    //Database Configs:
//    public static final String dbAdress = "jdbc:mysql://localhost:3306/etoilev3";
//    public static final String dbUsername = "root";
    //public static final String dbPassword = "";
//    public static final String dbPassword = "etoile";
//    public static final String dbAdress = "jdbc:mysql://localhost:3306/etoilev3";
//    public static final String dbUsername = "root";
//    public static final String dbPassword = "";
    //public static final String dbPassword = "etoile";
//   S public static final String dbAdress = "jdbc:mysql://localhost:3306/etoilepl_etoilev3";
    public static final String dbAdress = "jdbc:mysql://84.40.22.48:3306/etoilepl_etoilev3";
    public static final String dbUsername = "etoilepl_etoile";
    public static final String dbPassword = "WryDiluteQuirkyRider";
    
    // fazer no cmd ssh -L 3306:localhost:3306 -l user v3.etoilecascadesideas.eu
    public static String login(String username, String parseSHA1Password) {
        return "SELECT id,username,password,firstname,surname,email FROM student WHERE student.username='" + username
                + "' AND student.password='" + parseSHA1Password + "'";

    }
    
    public static String changePassword(String email,String password){
        return "UPDATE student SET password='"+password+"' WHERE student.email='"+email+"'";
    }
    
    public static String changePassword_logedin(String username,String password){
        return "UPDATE student SET password='"+password+"' WHERE student.username='"+username+"'";
    }
    
    public static String getNews(){
       return "SELECT id,title,news,url, professor FROM news"; 
    }

    public static String getCourses(int student_id) {
        return "SELECT course.id,course.name from student,student_course, course "
                + "WHERE student.id='" + student_id + "' AND student.id=student_course.student_id "
                + "AND student_course.course_id=course.id";
    }

    public static String getDisciplines(int course_id) {
        return "SELECT discipline.id,discipline.description, discipline.name FROM course,course_discipline,discipline WHERE "
                + "course.id='" + course_id + "' AND course_discipline.course_id = course.id AND "
                + "course_discipline.discipline_id = discipline.id";
    }

    public static String getModules(int discipline_id) {
        return "SELECT module.id, module.name FROM discipline,discipline_module,module WHERE "
                + "discipline.id='" + discipline_id + "' AND discipline.id=discipline_module.discipline_id AND "
                + "discipline_module.module_id=module.id";
    }

    public static String getTests(int module_id) {
        return "SELECT test.id, test.name, test.author,description, test.beginDate,test.endDate, test.url, test.hasURLS "
                + "FROM module,module_test,test WHERE "
                + "module.id='" + module_id + "' AND module.id=module_test.module_id AND module_test.test_id=test.id";
    }

    public static String getOpenQuestions(int test_id) {
        return "SELECT openquestion.id, openquestion.text , test_openquestion.number FROM test, test_openquestion,openquestion WHERE "
                + "test.id='" + test_id + "' AND test.id=test_openquestion.test_id AND test_openquestion.openquestion_id=openquestion.id";
    }

    public static String getOneChoiceQuestions(int test_id) {
        return "SELECT onechoicequestion.id, onechoicequestion.text, test_onechoicequestion.number FROM test, test_onechoicequestion,onechoicequestion WHERE "
                + "test.id='" + test_id + "' AND test.id=test_onechoicequestion.test_id AND test_onechoicequestion.onechoicequestion_id=onechoicequestion.id";
    }

    public static String getMultipleChoiceQuestions(int test_id) {
        return "SELECT multiplechoicequestion.id, multiplechoicequestion.text, test_multiplechoicequestion.number FROM test, test_multiplechoicequestion,multiplechoicequestion WHERE "
                + "test.id='" + test_id + "' AND test.id=test_multiplechoicequestion.test_id "
                + "AND test_multiplechoicequestion.multiplechoicequestion_id=multiplechoicequestion.id";
    }

    public static String getOneChoiceHypothesis(int question_id) {
        return "SELECT hypothesis.id,hypothesis.hypothesis,isCorrect FROM onechoicequestion,onechoicequestion_hypothesis, hypothesis WHERE "
                + "onechoicequestion.id='" + question_id + "' AND onechoicequestion_hypothesis.onechoicequestion_id = onechoicequestion.id AND "
                + "onechoicequestion_hypothesis.hypothesis_id=hypothesis.id";
    }

    public static String getMultipleChoiceHypothesis(int question_id) {
        return "SELECT hypothesis.id,hypothesis.hypothesis,isCorrect FROM multiplechoicequestion,multiplechoicequestion_hypothesis, hypothesis WHERE "
                + "multiplechoicequestion.id='" + question_id + "' AND multiplechoicequestion_hypothesis.multiplechoicequestion_id = multiplechoicequestion.id AND "
                + "multiplechoicequestion_hypothesis.hypothesis_id=hypothesis.id";

    }

    public static String getOneChoiceCorrect(int question_id) {
        return "SELECT hypothesis.id,hypothesis.hypothesis FROM onechoicequestion,onechoicequestion_hypothesis, hypothesis WHERE "
                + "onechoicequestion.id='" + question_id + "' AND onechoicequestion_hypothesis.onechoicequestion_id = onechoicequestion.id AND "
                + "onechoicequestion_hypothesis.hypothesis_id=hypothesis.id AND hypothesis.isCorrect='1'";

    }

    public static String getMultipleChoiceCorrect(int question_id) {
        return "SELECT hypothesis.id,hypothesis.hypothesis FROM multiplechoicequestion,multiplechoicequestion_hypothesis, hypothesis WHERE "
                + "multiplechoicequestion.id='" + question_id + "' AND multiplechoicequestion_hypothesis.multiplechoicequestion_id = multiplechoicequestion.id AND "
                + "multiplechoicequestion_hypothesis.hypothesis_id=hypothesis.id AND hypothesis.isCorrect='1'";


    }

    public static String getOneChoiceAnswer(int question_id, int student_id) {
        return "SELECT onechoiceanswer.id, onechoiceanswer.text FROM student,onechoiceanswer,onechoicequestion WHERE "
                + "student.id='" + student_id + "' AND onechoicequestion.id='" + question_id + "' AND student.id=onechoiceanswer.student_id AND onechoiceanswer.onechoicequestion_id=onechoicequestion.id";
                
    }

    public static String getOpenQuestionAnswer(int question_id, int student_id) {
        return "SELECT openanswer.id, openanswer.text FROM openanswer,student,openquestion WHERE "
                + "student.id='" + student_id + "' AND openquestion.id='" + question_id + "' AND student.id=openanswer.student_id AND openanswer.openquestion_id=openquestion.id";
    }

    public static String getMultipleChoiceAnswer(int question_id, int student_id) {
        return "SELECT multiplechoiceanswer.id, multiplechoiceanswer.text FROM student,multiplechoiceanswer,multiplechoicequestion WHERE "
                + "student.id='" + student_id + "' AND  multiplechoicequestion.id = '" + question_id + "' AND student.id=multiplechoiceanswer.student_id AND multiplechoiceanswer.multiplechoicequestion_id=multiplechoicequestion.id";
               
    }

    public static String getMultipleChoiceURLs(int question_id) {
        return "SELECT url.id, url.name, url.url, url.votes, url.n_votes, url.rating FROM url,multiplechoicequestion_url,multiplechoicequestion WHERE "
                +"multiplechoicequestion.id="+question_id+" AND multiplechoicequestion.id=multiplechoicequestion_url.multiplechoicequestion_id AND multiplechoicequestion_url.url_id=url.id ORDER BY url.rating DESC";
    }
    
     public static String getOneChoiceURLs(int question_id) {
         return "SELECT url.id, url.name, url.url, url.votes, url.n_votes, url.rating FROM url,onechoicequestion_url,onechoicequestion WHERE "
                +"onechoicequestion.id="+question_id+" AND onechoicequestion.id=onechoicequestion_url.onechoicequestion_id AND onechoicequestion_url.url_id=url.id ORDER BY url.rating DESC";
     }
     
     public static String getOpenQuestionURLs(int question_id) {
         return "SELECT url.id, url.name, url.url, url.votes, url.n_votes, url.rating FROM url,openquestion_url,openquestion WHERE "
                +"openquestion.id="+question_id+" AND openquestion.id=openquestion_url.openquestion_id AND openquestion_url.url_id=url.id ORDER BY url.rating DESC";
     }
    //INSERTS
    public static String addStudent(String username, String password, String firstname, String surname, String email) {

        return "INSERT INTO student (username, password, firstname, surname, email) VALUES ('" + username + "', '" + password + "','" + firstname + "','" + surname + "','" + email + "');";
    }
    
    public static String registerStudentCourse(int student_id, int course_id){
        return "INSERT INTO student_course (student_id, course_id) VALUES( '"+student_id+"','"+course_id+"')";
    }
    
    public static String insertOpenQuestionAnswer(int student_id, int question_id, String answer){
        return "INSERT INTO openanswer ( student_id, openquestion_id, text ) VALUES ( '"+student_id+"','"+question_id+"','"+answer+"' )";
    }
    public static String insertOneChoiceQuestionAnswer(int student_id, int question_id){
        return "INSERT INTO onechoiceanswer ( student_id, onechoicequestion_id) VALUES ( '"+student_id+"','"+question_id+"')";
    }
    
    public static String insertMultipleChoiceAnswer(int question_id, int student_id, String answer) {
        return "INSERT INTO multiplechoiceanswer ( multiplechoicequestion_id, student_id, text ) VALUES ('"+question_id+"','"+student_id+"','"+answer+"' )";
    }
    
    public static String updateOpenQuetionAnswer(int answer_id, String answer){
        return "UPDATE openanswer SET openanswer.text='"+answer+"' WHERE EXISTS"
            +" (SELECT openanswer.id FROM student WHERE"
            +" openanswer.id='"+answer_id+"')";
    }
       
    public static String updateOneChoiceAnswer(int answer_id, String answer) {
        return "UPDATE onechoiceanswer SET onechoiceanswer.text='"+answer+"' WHERE EXISTS"
                +" (SELECT onechoiceanswer.id FROM student WHERE onechoiceanswer.id='"+answer_id+"')";
    }
    
    public static String vote(int url_id, int stars, int new_rating){
        System.out.println(new_rating);
        return "UPDATE url SET votes=(votes+'"+stars+"'),n_votes=(n_votes+1), rating=('"+new_rating+"') where id='"+url_id+"';";
    }
    
    public static String addUrl(String name, String url){
        return "INSERT INTO url (name,url) VALUES ('"+name+"','"+url+"')";
    }
    
    public static String getLastURLInserted(){
        return  "SELECT MAX(url.id) FROM url;";
    }

    public static String linkURLOpenQuestion(int question_id, int url_id) {
        return "INSERT INTO openquestion_url( openquestion_id, url_id ) VALUES('"+question_id+"','"+url_id+"');";
    }

    public static String linkURLMultipleChoiceQuestion(int question_id, int url_id) {
        return "INSERT INTO multiplechoicequestion_url( multiplechoicequestion_id, url_id ) VALUES('"+question_id+"','"+url_id+"');";
    }

    public static String linkURLOneChoiceQuestion(int question_id, int url_id) {
        return "INSERT INTO onechoicequestion_url( onechoicequestion_id, url_id ) VALUES('"+question_id+"','"+url_id+"');";
    }

    public static String getallopenanswers() {
        
        return "SELECT id, text FROM openanswer";
    }

    public static String changeNoAnswerToNull(int id) {
        //UPDATE url SET votes=(votes+'"+stars+"') where id='"+url_id+"';
        return "UPDATE openanswer SET text=('') WHERE id='"+id+"'";
    }

    public static String validEmail(String email) {
        return "SELECT email,username FROM student WHERE email='"+email+"'";
    }

    public static String setVoted(int url_id, int student_id, int stars) {
        return "INSERT INTO url_student (url_id,student_id, stars) VALUES ('"+url_id+"','"+student_id+"','"+stars+"')";
    }
    
    public static String checkVoted(int url_id, int student_id) {
        return "SELECT url_id,student_id FROM url_student WHERE url_id='"+url_id+"' AND student_id='"+student_id+"'";
    }

    public static String getURL(int id) {
        return "SELECT url.votes, url.n_votes FROM url WHERE url.id='"+id+"'";
    }
    
    
    //BEGIN FORUM
    public static String getForum(int discipline_id) {
        return "SELECT * FROM forum WHERE forum.discipline_id='"+discipline_id+"'";
    }
    

    public static String getForumTopics(int forum_id) {
       return "SELECT forumtopic.id, forumtopic.username, forumtopic.title, forumtopic.n_answers FROM forum, forum_forumtopic, forumtopic WHERE forum.id='"+forum_id+"' AND forum.id=forum_forumtopic.forum_id AND forum_forumtopic.forumtopic_id=forumtopic.id";
    }
    
    public static String getForumTopic(int topic_id){
        return "SELECT forumtopic.n_answers FROM forumtopic WHERE forumtopic.id='"+topic_id+"'";
    }
    


    public static String getTopicAnswers(int topic_id) {
        return"SELECT topicanswer.id, topicanswer.username, topicanswer.answer FROM forumtopic, forumtopic_topicanswer, topicanswer WHERE forumtopic.id='"+topic_id+"' AND forumtopic.id=forumtopic_topicanswer.forumtopic_id AND forumtopic_topicanswer.topicanswer_id = topicanswer.id";
    }
        //INSERT NEW FORUM
    public static String addForumTopic(String username, String title){
        return "INSERT INTO forumtopic (username,title) VALUES ('"+username+"','"+title+"')";
    }
    
    public static String getLastTopicInserted(){
        return  "SELECT MAX(forumtopic.id) FROM forumtopic;";
    }
    
    public static String linkTopicForum(int forum_id, int forumtopic_id) {
        return "INSERT INTO forum_forumtopic( forum_id, forumtopic_id ) VALUES('"+forum_id+"','"+forumtopic_id+"');";
    }
    
    //INSERT NEW ANSWER
    
    public static String addTopicAnswer(String username, String answer){
        return "INSERT INTO topicanswer (username,answer) VALUES ('"+username+"','"+answer+"');";
    }
    
    public static String getLastTopicAnswerInserted(){
        return  "SELECT MAX(topicanswer.id) FROM topicanswer;";
    }
    
    public static String linkAnswerTopic(int forumtopic_id, int topicanswer_id) {
        return "INSERT INTO forumtopic_topicanswer( forumtopic_id, topicanswer_id ) VALUES('"+forumtopic_id+"','"+topicanswer_id+"');";
    }
    
    
    

        //END FORUM

    public static String updateNAnswers(int topic_id) {
        return "UPDATE forumtopic SET n_answers=(n_answers+1) WHERE id='"+topic_id+"'";
    }

 
}
