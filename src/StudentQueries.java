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
import java.sql.SQLException;
import java.util.ArrayList;


public class StudentQueries {
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement studentVar;
    private static ResultSet resultSet;
    
    public static void addStudent(StudentEntry student)
    {
        connection = DBConnection.getConnection();
        try
        {
            studentVar = connection.prepareStatement("insert into app.student (studentid, firstname, lastname) values (?,?,?)");
            
            studentVar.setString(1, student.getStudentID());
            studentVar.setString(2, student.getFirstName());
            studentVar.setString(3, student.getLastName());
            
            studentVar.executeUpdate();
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    
        public static ArrayList<StudentEntry> getAllStudents() {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<StudentEntry>();
        try
        {
            
            studentVar = connection.prepareStatement("select studentid, firstname, lastname from app.student ");
            
            resultSet = studentVar.executeQuery();
            
            while(resultSet.next())
            {
                students.add(new StudentEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
                
                
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        //Testing
//        for (CourseEntry course: courses) {
//            System.out.println(course.getCourseCode() + "\t" + course.getCourseDescription() + "\t" + course.getSemester() + "\t" + course.getSeats());
//        }
        
        return students;
    }
        
        
    public static void dropStudent(String studentID) {
        connection = DBConnection.getConnection();
        
        
        
        try
        {
            
            studentVar = connection.prepareStatement("delete from app.student where studentid=?");
            studentVar.setString(1, studentID);
            
            studentVar.executeUpdate();
            
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        //Testing
//        for (CourseEntry course: courses) {
//            System.out.println(course.getCourseCode() + "\t" + course.getCourseDescription() + "\t" + course.getSemester() + "\t" + course.getSeats());
//        }
        
        
    }
}
