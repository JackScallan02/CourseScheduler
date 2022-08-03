/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jack
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;



public class ScheduleQueries {
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement mySchedule;
    private static ResultSet resultSet;
    
    
    public static void addScheduleEntry(ScheduleEntry schedule) {
        connection = DBConnection.getConnection();
        try
        {
            mySchedule = connection.prepareStatement("insert into app.schedule (semester, studentid, coursecode, status, timestamp) values (?,?,?,?,?)");
            mySchedule.setString(1, schedule.getSemester());
            mySchedule.setString(2, schedule.getStudentID());
            mySchedule.setString(3, schedule.getCourseCode());
            mySchedule.setString(4, schedule.getStatus());
            mySchedule.setTimestamp(5, schedule.getTimeStamp());
            
            mySchedule.executeUpdate();
            
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID) {
        connection = DBConnection.getConnection();
        
        ArrayList<ScheduleEntry> schedule = new ArrayList<>();
        
        try
        {
            mySchedule = connection.prepareStatement("select semester, studentid, coursecode, status, timestamp from app.schedule where semester = ? and studentid = ?");
            
            mySchedule.setString(1, semester);
            mySchedule.setString(2, studentID);
            
            resultSet = mySchedule.executeQuery();

            while (resultSet.next()) {
                ScheduleEntry entry = new ScheduleEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5));
                schedule.add(entry);
            }
            

        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return schedule;
        
    }
    
    
    public static int getScheduledStudentCount(String currentSemester, String courseCode) {
        connection = DBConnection.getConnection();
        int count = 0;
        try
        {
            mySchedule = connection.prepareStatement("select count(studentID) from app.schedule where semester = ? and courseCode = ?");
            
            mySchedule.setString(1, currentSemester);
            mySchedule.setString(2, courseCode);
            
            resultSet = mySchedule.executeQuery();
            
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            
            
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        
        return count;
        
    }
    
    
    
    public static ArrayList<ScheduleEntry> getAllSchedules(String currentSemester) {
        connection = DBConnection.getConnection();
        
        ArrayList<ScheduleEntry> schedules = new ArrayList<>();
        try {
            mySchedule = connection.prepareStatement("select semester, coursecode, studentid, status, timestamp from app.schedule where semester = ?");
            mySchedule.setString(1, currentSemester);
            
            resultSet = mySchedule.executeQuery();
            
            while (resultSet.next()) {
                schedules.add(new ScheduleEntry(resultSet.getString(1), resultSet.getString(3), resultSet.getString(2), resultSet.getString(4), resultSet.getTimestamp(5)));
            }
            

        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return schedules;
    }
    
    
    
    public static ArrayList<ScheduleEntry> getScheduledStudentsByCourse(String semester, String courseCode) {
        
        //Given a course code, return all students enrolled in that course
        
        
        ArrayList<ScheduleEntry> schedulesOut = new ArrayList<>();
        
        
            
        ArrayList<ScheduleEntry> schedules = ScheduleQueries.getAllSchedules(semester);
            
            
        for (ScheduleEntry schedule: schedules) {
//            System.out.println(schedule.getCourseCode() + " = " + courseCode);
            if (schedule.getCourseCode().equals(courseCode)) {
                schedulesOut.add(schedule);
            }
        }

        
        return schedulesOut;
    }
    
    
    
    public static void deleteScheduleByStudent(String semester, String studentID) {
        connection = DBConnection.getConnection();
        
        ArrayList<ScheduleEntry> schedules = new ArrayList<>();
        try {
            mySchedule = connection.prepareStatement("delete from app.schedule where semester = ? and studentid = ?");
            mySchedule.setString(1, semester);
            mySchedule.setString(2, studentID);
            
            mySchedule.executeUpdate();
            
            
            

        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public static void deleteScheduleItemByStudent(String semester, String studentID, String courseCode) {
        connection = DBConnection.getConnection();
        
        ArrayList<ScheduleEntry> schedules = new ArrayList<>();
        try {
            mySchedule = connection.prepareStatement("delete from app.schedule where semester = ? and studentid = ? and coursecode = ?");
            mySchedule.setString(1, semester);
            mySchedule.setString(2, studentID);
            mySchedule.setString(3, courseCode);
            
            mySchedule.executeUpdate();
            
            
            

        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public static void deleteScheduleItem(String semester, String courseCode) {
        connection = DBConnection.getConnection();
        
        try {
            mySchedule = connection.prepareStatement("delete from app.schedule where semester = ? and coursecode = ?");
            mySchedule.setString(1, semester);
            mySchedule.setString(2, courseCode);
            
            mySchedule.executeUpdate();
            
            
            

        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    
    public static void setStatus(String studentID, String status, String courseCode) {
        connection = DBConnection.getConnection();
        
        try {
            
            mySchedule = connection.prepareStatement("update app.schedule set status=? where studentid=? and coursecode=?");
            mySchedule.setString(1, status); 
            mySchedule.setString(2, studentID);
            mySchedule.setString(3, courseCode);
            
            mySchedule.executeUpdate();
            
            
            

        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
