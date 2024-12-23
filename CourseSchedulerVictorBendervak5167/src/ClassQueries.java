
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author victor
 */
public class ClassQueries {
    private static Connection connection; // variable setup
    private static PreparedStatement addClass;
    private static ResultSet resultSet;
    private static PreparedStatement getAllCourseCodes;
    private static PreparedStatement getClassSeats;

    public static void addClass(String semester, String courseCode, int seats) {
        connection = DBConnection.getConnection();
        try {
            addClass = connection.prepareStatement("insert into app.class (semester, courseCode, seats) values (?, ?, ?)");
            addClass.setString(1, semester);
            addClass.setString(2, courseCode);
            addClass.setInt(3, seats);
            addClass.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static ArrayList<String> getAllCourseCodes(String semester) {
        connection = DBConnection.getConnection();
        ArrayList<String> courseCodes = new ArrayList<String>();
        try {
            getAllCourseCodes = connection.prepareStatement("select courseCode from app.class where semester = ? order by courseCode");
            getAllCourseCodes.setString(1, semester);
            resultSet = getAllCourseCodes.executeQuery();

            while (resultSet.next()) {
                courseCodes.add(resultSet.getString(1));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return courseCodes;
    }

    public static int getClassSeats(String semester, String courseCode) {
        connection = DBConnection.getConnection();
        int seats = 0;
        try {
            getClassSeats = connection.prepareStatement("select seats from app.class where semester = ? and courseCode = ?");
            getClassSeats.setString(1, semester);
            getClassSeats.setString(2, courseCode);
            resultSet = getClassSeats.executeQuery();

            if (resultSet.next()) {
                seats = resultSet.getInt(1);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return seats;
    }
    
    public static void dropClass(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        try {
            getAllCourseCodes = connection.prepareStatement("delete from app.class where semester = ? and coursecode = ?");
            getAllCourseCodes.setString(1, semester);
            getAllCourseCodes.setString(2, courseCode);
            getAllCourseCodes.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
}
