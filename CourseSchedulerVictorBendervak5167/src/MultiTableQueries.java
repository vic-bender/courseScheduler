import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author victor
 */

public class MultiTableQueries {
    private static Connection connection;
    private static PreparedStatement getAllClassDescriptions;
    private static ResultSet resultSet;

    // Get all class descriptions for a semester
public static ArrayList<ClassDescription> getAllClassDescriptions(String semester) {
        connection = DBConnection.getConnection();
        ArrayList<ClassDescription> classDescriptions = new ArrayList<ClassDescription>();
        try {
            getAllClassDescriptions = connection.prepareStatement(
                "SELECT c.courseCode, co.description, c.seats " +
                "FROM app.class c " +
                "JOIN app.course co ON c.courseCode = co.courseCode " +
                "WHERE c.semester = ? " +
                "ORDER BY c.courseCode"
            );
            getAllClassDescriptions.setString(1, semester);
            resultSet = getAllClassDescriptions.executeQuery();

            while (resultSet.next()) {
                String courseCode = resultSet.getString("courseCode");
                String description = resultSet.getString("description");
                int seats = resultSet.getInt("seats");
                classDescriptions.add(new ClassDescription(courseCode, description, seats));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return classDescriptions;
    }


public static ArrayList<StudentEntry> getWaitlistedStudentsByClass(String semester, String courseCode) {
    ArrayList<StudentEntry> waitlistedStudents = new ArrayList<>();
    try {
     Connection connection = DBConnection.getConnection();
         PreparedStatement ps = connection.prepareStatement(
             "SELECT s.studentID, s.firstName, s.lastName " +
             "FROM app.schedule sc " +
             "JOIN app.student s ON sc.studentID = s.studentID " +
             "WHERE sc.semester = ? AND sc.courseCode = ? AND sc.status = 'w' " +
             "ORDER BY sc.timestamp");
         
        ps.setString(1, semester);
        ps.setString(2, courseCode);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String studentID = rs.getString("studentID");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            waitlistedStudents.add(new StudentEntry(studentID, firstName, lastName));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return waitlistedStudents;
}

public static ArrayList<StudentEntry> getScheduledStudentsByClass(String semester, String courseCode) {
    ArrayList<StudentEntry> scheduledStudents = new ArrayList<>();
    try {
        Connection connection = DBConnection.getConnection();
   
         PreparedStatement ps = connection.prepareStatement(
             "SELECT s.studentID, s.firstName, s.lastName " +
             "FROM app.schedule sc " +
             "JOIN app.student s ON sc.studentID = s.studentID " +
             "WHERE sc.semester = ? AND sc.courseCode = ? AND sc.status = 'S'");
         
        ps.setString(1, semester);
        ps.setString(2, courseCode);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String studentID = rs.getString("studentID");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            scheduledStudents.add(new StudentEntry(studentID, firstName, lastName));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return scheduledStudents;
}


}

