/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author victor
 */
public class ClassEntry {
    private String semester; // variable setup
    private String courseCode;
    private int seats;
    
    public ClassEntry(String semester, String courseCode, int seats) { // constructor
        this.semester = semester;
        this.courseCode = courseCode;
        this.seats = seats;
    }
    
    public String getSemester() // getter for semester
    {
        return this.semester;
    }
    public String getCourseCode() // getter for courseCode
    {
        return this.courseCode;
    }
    public int getClassSeats() // getter for courseDescription
    {
        return this.seats;
    }
}
