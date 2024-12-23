/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author victor
 */
public class StudentEntry {

    private String studentID; // variable setup
    private String firstName;
    private String lastName;

    public StudentEntry(String studentID, String firstName, String lastName) // constructor
    {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getStudentID() // getter for studentID
    {
        return this.studentID;
    }

    public String getFirstName() // getter for FirstName
    {
        return this.firstName;
    }

    public String getLastName() // getter for LastName
    {
        return this.lastName;
    }

    public String lastFirstName() // getter for full name
    {
        return this.lastName + ", " + this.firstName;
    }

    public String fullID() // getter for full ID
    {
        return lastFirstName() + " " + this.studentID;
    }
}
