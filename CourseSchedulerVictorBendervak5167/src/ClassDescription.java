/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author victor
 */
public class ClassDescription {
    private String _courseCode = null;
    private String _description = null;
    private int _seats = 0;

    public ClassDescription(String courseCode, String description, int seats) {
        _courseCode = courseCode;
        _description = description;
        _seats = seats;
    }

    public String getDescription() {
        return _description;
    }
    
    public String getCourseCode() {
        return _courseCode;
    }

    public int getSeats() {
        return _seats;
    }
}
