/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

public class SQLInstruct {

    //Database Configs:
   
    public static final String dbAdress="jdbc:mysql://localhost:3306/etoile";
    public static final String dbUsername="root";
    public static final String dbPassword="etoile";

    public static String login(String username, String parseSHA1Password) {
       return "SELECT id,username FROM student WHERE student.username='" + username 
               + "' AND student.password='" + parseSHA1Password + "'";
           
    }
    public static String getCourses(int student_id) {
        return "SELECT course.id,course.name from student,student_course, course "
                + "WHERE student.id='"+student_id+"' AND student.id=student_course.student_id "
                + "AND student_course.student_id=course.id";     
    }
    
    public static String getDisciplines(int course_id) {  
        return "SELECT discipline.id, discipline.name FROM course,course_discipline,discipline WHERE"
            +"course.id='"+course_id+"' AND course_discipline.course_id = course.id AND"
            +"course_discipline.discipline_id = discipline.id";
    }
    
    public static String getModules(int discipline_id) {
        return "SELECT module.id, module.name FROM discipline,discipline_module,module WHERE"
            +"discipline.id='"+discipline_id+"' AND discipline.id=discipline_module.discipline_id AND"
            +"discipline_module.module_id=module.id";
    }
    
    public static String getTests(int module_id) {
        return "SELECT test.id, test.name, test.author, test.beginDate,test.endDate,description "
                + "FROM module,module_test,test WHERE "
                + "module.id='"+module_id+"' AND module.id=module_test.module_id AND module_test.test_id=test.id";      
    }
    
    public static String getOpenQuestions(int test_id){
        return "SELECT openquestion.id, openquestion.text FROM test, test_openquestion,openquestion WHERE "
        +"test.id='"+test_id+"' AND test.id=test_openquestion.test_id AND test_openquestion.openquestion_id=openquestion.id";
    }
    
    public static String getOneChoiceQuestions(int test_id){
        return "SELECT onechoicequestion.id, onechoicequestion.text FROM test, test_onechoicequestion,onechoicequestion WHERE "
        +"test.id='"+test_id+"' AND test.id=test_onechoicequestion.test_id AND test_onechoicequestion.onechoicequestion_id=onechoicequestion.id";
    }
    
    public static String getMultipleChoiceQuestions(int test_id){
        return "SELECT multiplechoicequestion.id, multiplechoicequestion.text FROM test, test_multiplechoicequestion,multiplechoicequestion WHERE "
        +"test.id='1' AND test.id=test_multiplechoicequestion.test_id " 
        +"AND test_multiplechoicequestion.multiplechoicequestion_id=multiplechoicequestion.id";
    }
    
    
    //INSERTS
    
    public static String addStudent(String username, String password, String firstname, String surname, String email){
        
         return "INSERT INTO student (username, password, firstname, surname, email) VALUES ('"+username+"', '"+password+"','"+firstname+"','"+surname+"','"+email+"')";
    }
    
    
}