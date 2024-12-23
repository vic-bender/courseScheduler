/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author victor
 */

public class ScheduleQueries {
    private static Connection connection;
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getScheduleByStudent;
    private static ResultSet resultSet;

    public static void addScheduleEntry(String semester, String courseCode, String studentID, String status, java.sql.Timestamp timestamp) {
        connection = DBConnection.getConnection();
        try {
            addScheduleEntry = connection.prepareStatement("insert into app.schedule (semester, courseCode, studentID, status, timestamp) values (?, ?, ?, ?, ?)");
            addScheduleEntry.setString(1, semester);
            addScheduleEntry.setString(2, courseCode);
            addScheduleEntry.setString(3, studentID);
            addScheduleEntry.setString(4, status);
            addScheduleEntry.setTimestamp(5, timestamp);
            addScheduleEntry.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID) {
    ArrayList<ScheduleEntry> schedule = new ArrayList<>();

    connection = DBConnection.getConnection();
    try {
        getScheduleByStudent = connection.prepareStatement(
            "SELECT courseCode, status, timestamp FROM app.schedule WHERE semester = ? AND studentID = ?"
        );
        getScheduleByStudent.setString(1, semester);
        getScheduleByStudent.setString(2, studentID);
        resultSet = getScheduleByStudent.executeQuery();

        while (resultSet.next()) {
            String courseCode = resultSet.getString("courseCode");
            String status = resultSet.getString("status");
            java.sql.Timestamp timestamp = resultSet.getTimestamp("timestamp");
            ScheduleEntry entry = new ScheduleEntry(semester, courseCode, studentID, status, timestamp);

            schedule.add(entry);
        }
    } catch (SQLException sqlException) {
        sqlException.printStackTrace();
    }
    return schedule;
    
}
public static ArrayList<ScheduleEntry> getWaitlistedStudentsByCourse(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> retList = new ArrayList<ScheduleEntry>();
        try
        {
            getScheduleByStudent = connection.prepareStatement("select studentid, timestamp from app.schedule where semester = ? and courseCode = ? and status = ?");
            getScheduleByStudent.setString(1, semester);
            getScheduleByStudent.setString(2, courseCode);
            getScheduleByStudent.setString(3, "w");
            resultSet = getScheduleByStudent.executeQuery();
            
            while(resultSet.next())
            {
                retList.add(new ScheduleEntry(semester, courseCode, resultSet.getString(1), "w", resultSet.getTimestamp(2)));
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return retList;
    }
public static void dropStudentScheduleByCourse(String semester, String studentID, String courseCode)
    {
        connection = DBConnection.getConnection();
        try
        {
            getScheduleByStudent = connection.prepareStatement("DELETE FROM APP.SCHEDULE WHERE SEMESTER = ? AND STUDENTID = ? AND COURSECODE = ?");
            
            getScheduleByStudent.setString(1, semester);
            getScheduleByStudent.setString(2, studentID);
            getScheduleByStudent.setString(3, courseCode);
            getScheduleByStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
public static void dropScheduleByCourse(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        try
        {
            getScheduleByStudent = connection.prepareStatement("delete from app.schedule where semester = ? and coursecode = ?");
            getScheduleByStudent.setString(1, semester);
            getScheduleByStudent.setString(2, courseCode);
            getScheduleByStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
public static ArrayList<ScheduleEntry> getScheduledStudentsByCourse(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> retList = new ArrayList<ScheduleEntry>();
        try
        {
            getScheduleByStudent = connection.prepareStatement("select studentid, timestamp from app.schedule where semester = ? and courseCode = ? and status = ?");
            getScheduleByStudent.setString(1, semester);
            getScheduleByStudent.setString(2, courseCode);
            getScheduleByStudent.setString(3, "s");
            resultSet = getScheduleByStudent.executeQuery();
            
            while(resultSet.next())
            {
                retList.add(new ScheduleEntry(semester, courseCode, resultSet.getString(1), "s", resultSet.getTimestamp(2)));
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return retList;
    }
public static void updateScheduleEntry(String semester, ScheduleEntry entry)
    {
        connection = DBConnection.getConnection();
        try
        {
            getScheduleByStudent = connection.prepareStatement("update app.schedule set status = 's' where semester = ? and studentid = ? and coursecode = ?");
            getScheduleByStudent.setString(1, semester);
            getScheduleByStudent.setString(2, entry.getStudentID());
            getScheduleByStudent.setString(3, entry.getCourseCode());
            getScheduleByStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
