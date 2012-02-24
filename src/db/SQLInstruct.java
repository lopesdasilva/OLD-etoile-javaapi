/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;

public class SQLInstruct implements Serializable {

    //Database Configs:
    public static final String dbAdress = "jdbc:mysql://localhost:3306/etoilev3";
    public static final String dbUsername = "root";
    public static final String dbPassword = "";
    //public static final String dbPassword = "etoile";
//    public static final String dbAdress = "jdbc:mysql://localhost:3306/etoilev3";
//    public static final String dbUsername = "etoilev3";
//    public static final String dbPassword = "webetoile12";
    
    // fazer no cmd ssh -L 3306:localhost:3306 -l user v3.etoilecascadesideas.eu
    public static String login(String username, String parseSHA1Password) {
        return "SELECT id,username,password,firstname,surname,email FROM student WHERE student.username='" + username
                + "' AND student.password='" + parseSHA1Password + "'";

    }

    public static String getCourses(int student_id) {
        return "SELECT course.id,course.name from student,student_course, course "
                + "WHERE student.id='" + student_id + "' AND student.id=student_course.student_id "
                + "AND student_course.course_id=course.id";
    }

    public static String getDisciplines(int course_id) {
        return "SELECT discipline.id, discipline.name FROM course,course_discipline,discipline WHERE "
                + "course.id='" + course_id + "' AND course_discipline.course_id = course.id AND "
                + "course_discipline.discipline_id = discipline.id";
    }

    public static String getModules(int discipline_id) {
        return "SELECT module.id, module.name FROM discipline,discipline_module,module WHERE "
                + "discipline.id='" + discipline_id + "' AND discipline.id=discipline_module.discipline_id AND "
                + "discipline_module.module_id=module.id";
    }

    public static String getTests(int module_id) {
        return "SELECT test.id, test.name, test.author,description, test.beginDate,test.endDate "
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
        return "SELECT hypothesis.hypothesis, hypothesis.hypothesis FROM student, hypothesis,onechoiceanswer,onechoicequestion WHERE "
                + "student.id='" + student_id + "' AND onechoicequestion.id='" + question_id + "' AND student.id=onechoiceanswer.student_id AND onechoiceanswer.onechoicequestion_id=onechoicequestion.id AND "
                + "onechoiceanswer.hypothesis_id=hypothesis.id";
    }

    public static String getOpenQuestionAnswer(int question_id, int student_id) {
        return "SELECT openanswer.text FROM openanswer,student,openquestion WHERE "
                + "student.id='" + student_id + "' AND openquestion.id='" + question_id + "' AND student.id=openanswer.student_id AND openanswer.openquestion_id=openquestion.id";
    }

    public static String getMultipleChoiceAnswer(int question_id, int student_id) {
        return "SELECT hypothesis.hypothesis FROM student, hypothesis,multiplechoiceanswer,multiplechoicequestion WHERE "
                + "student.id='" + student_id + "' AND  multiplechoicequestion.id = '" + question_id + "' AND student.id=multiplechoiceanswer.student_id AND multiplechoiceanswer.multiplechoicequestion_id=multiplechoicequestion.id AND "
                + "multiplechoiceanswer.hypothesis_id=hypothesis.id";
    }

    public static String getMultipleChoiceURLs(int question_id) {
        return "SELECT url.id, url.name, url.url, url.votes FROM url,multiplechoicequestion_url,multiplechoicequestion WHERE "
                +"multiplechoicequestion.id="+question_id+" AND multiplechoicequestion.id=multiplechoicequestion_url.multiplechoicequestion_id AND multiplechoicequestion_url.url_id=url.id";
    }
    
     public static String getOneChoiceURLs(int question_id) {
         return "SELECT url.id, url.name, url.url, url.votes FROM url,onechoicequestion_url,onechoicequestion WHERE "
                +"onechoicequestion.id="+question_id+" AND onechoicequestion.id=onechoicequestion_url.onechoicequestion_id AND onechoicequestion_url.url_id=url.id";
     }
     
     public static String getOpenQuestionURLs(int question_id) {
         return "SELECT url.id, url.name, url.url, url.votes FROM url,openquestion_url,openquestion WHERE "
                +"openquestion.id="+question_id+" AND openquestion.id=openquestion_url.openquestion_id AND openquestion_url.url_id=url.id";
     }
    //INSERTS
    public static String addStudent(String username, String password, String firstname, String surname, String email) {

        return "INSERT INTO student (username, password, firstname, surname, email) VALUES ('" + username + "', '" + password + "','" + firstname + "','" + surname + "','" + email + "');";
    }
    
    public static String registerStudentCourse(int student_id, int course_id){
        return "INSERT INTO student_course (student_id, course_id) VALUES( '"+student_id+"','"+course_id+"')";
    }
    
   
}