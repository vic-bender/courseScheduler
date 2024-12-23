/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection; // imports
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author victor
 */
public class StudentQueries {
    private static Connection connection; // variable setup
    private static PreparedStatement addStudent;
    private static PreparedStatement getAllStudents;
    private static ResultSet resultSet;

    public static void addStudent(String studentID, String firstName, String lastName) {
        connection = DBConnection.getConnection();
        try {
            addStudent = connection.prepareStatement("insert into app.student (studentID, firstName, lastName) values (?, ?, ?)");
            addStudent.setString(1, studentID);
            addStudent.setString(2, firstName);
            addStudent.setString(3, lastName);
            addStudent.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public static void dropStudent(String studentID)
    {
        connection = DBConnection.getConnection();
        try
        {
            getAllStudents = connection.prepareStatement("delete from app.student where studentid = ?");
            getAllStudents.setString(1, studentID);
            getAllStudents.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

    public static ArrayList<StudentEntry> getAllStudents()
    {
        ArrayList<StudentEntry> faculty = new ArrayList<StudentEntry>();
        connection = DBConnection.getConnection();
        try
        {
            getAllStudents = connection.prepareStatement("select studentid, firstname, lastname from app.student");
            resultSet = getAllStudents.executeQuery();
            
            while(resultSet.next())
            {
                faculty.add(new StudentEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
            }

        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            
        }
        return faculty;
    }
    
    public static StudentEntry getStudent(String studentID)
    {
        connection = DBConnection.getConnection();
        try
        {
            getAllStudents = connection.prepareStatement("select studentid, firstname, lastname from app.student where studentid = ?");
            getAllStudents.setString(1, studentID);
            resultSet = getAllStudents.executeQuery();
            
            resultSet.next();
            return new StudentEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            return null;
        }
    }
}
