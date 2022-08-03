/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jack
 */
public class StudentEntry {
    private String StudentID;
    private String FirstName;
    private String LastName;

    public StudentEntry(String StudentID, String FirstName, String LastName) {
        this.StudentID = StudentID;
        this.FirstName = FirstName;
        this.LastName = LastName;
    }

    public String getStudentID() {
        return StudentID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }
    
}
