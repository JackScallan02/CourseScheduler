
import java.sql.Timestamp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jack
 */
public class ScheduleEntry {
   private String semester;
   private String studentID;
   private String courseCode;
   private String status;
   private Timestamp timeStamp;

    public String getSemester() {
        return semester;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }
    
    

    public ScheduleEntry(String semester, String studentID, String courseCode, String status, Timestamp timeStamp) {
        this.semester = semester;
        this.studentID = studentID;
        this.courseCode = courseCode;
        this.status = status;
        this.timeStamp = timeStamp;
    }

   
   
}
