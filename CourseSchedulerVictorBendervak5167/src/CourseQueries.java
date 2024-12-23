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
public class CourseQueries {
    private static Connection connection;
    private static PreparedStatement addCourse;
    private static PreparedStatement getAllCourseCodes;
    private static ResultSet resultSet;

    public static void addCourse(String courseCode, String description) {
        connection = DBConnection.getConnection();
        try {
            addCourse = connection.prepareStatement("insert into app.course (courseCode, description) values (?, ?)");
            addCourse.setString(1, courseCode);
            addCourse.setString(2, description);
            addCourse.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static ArrayList<String> getAllCourseCodes() {
        connection = DBConnection.getConnection();
        ArrayList<String> courseCodes = new ArrayList<String>();
        try {
            getAllCourseCodes = connection.prepareStatement("select courseCode from app.course order by courseCode");
            resultSet = getAllCourseCodes.executeQuery();

            while (resultSet.next()) {
                courseCodes.add(resultSet.getString(1));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return courseCodes;
    }
}
