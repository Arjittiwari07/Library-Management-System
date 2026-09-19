package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Student;
import util.DBConnnection;

public class StudentDAO {
    // Attribute to hold the database connection
    private Connection connection;
    // Constructor to initialize the database connection
    public StudentDAO() {
        this.connection = DBConnnection.getConnectionFromConfig();
    }
    // Method to register a new student in the database
    public boolean registerStudent(Student student) {
        String sql = "INSERT INTO students (name, username, password, email, course, department, institution, semester, phone_no) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getUsername());
            pstmt.setString(3, student.getPassword());
            pstmt.setString(4, student.getEmail());
            pstmt.setString(5, student.getCourse());
            pstmt.setString(6, student.getDepartment());
            pstmt.setString(7, student.getInstitution());
            pstmt.setInt(8, student.getSemester());
            pstmt.setLong(9, student.getPhoneNo());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // Method to retrieve a student by their ID
    public Student getStudentById(int studentId){
        String sql = "SELECT * FROM students WHERE student_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Student(
                    rs.getInt("student_id"),
                    rs.getString("username"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getLong("phone_no"),
                    rs.getString("course"),
                    rs.getInt("semester"),
                    rs.getString("department"),
                    rs.getString("institution"),
                    rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // Method to retrieve all students from the database
    public ArrayList<Student> getAllStudent(){
        String sql = "SELECT * FROM students";
        ArrayList<Student> students = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Student student = new Student(
                    rs.getInt("student_id"),
                    rs.getString("username"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getLong("phone_no"),
                    rs.getString("course"),
                    rs.getInt("semester"),
                    rs.getString("department"),
                    rs.getString("institution"),
                    rs.getString("password")
                );
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
    // Method to update a student's information in the database
    public boolean updateStudent(Student student){
        String sql = "UPDATE students SET name = ?, username = ?, email = ?, phone_no = ?, course = ?, semester = ?, department = ?, institution = ?, password = ? WHERE student_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getUsername());
            pstmt.setString(3, student.getEmail());
            pstmt.setLong(4, student.getPhoneNo());
            pstmt.setString(5, student.getCourse());
            pstmt.setInt(6, student.getSemester());
            pstmt.setString(7, student.getDepartment());
            pstmt.setString(8, student.getInstitution());
            pstmt.setString(9, student.getPassword());
            pstmt.setInt(10, student.getStudentId());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // Method to delete a student from the database based on their ID
    public boolean deleteStudent(int studentId){
        String sql = "DELETE FROM students WHERE student_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // Method to retrieve a student by their email
    public Student getStudentByEmail(String email){
        String sql = "SELECT * FROM students WHERE email = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Student(
                    rs.getInt("student_id"),
                    rs.getString("username"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getLong("phone_no"),
                    rs.getString("course"),
                    rs.getInt("semester"),
                    rs.getString("department"),
                    rs.getString("institution"),
                    rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // Method to retrieve a student by their username
    public Student getStudentByUsername(String username){
        String sql = "SELECT * FROM students WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Student(
                    rs.getInt("student_id"),
                    rs.getString("username"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getLong("phone_no"),
                    rs.getString("course"),
                    rs.getInt("semester"),
                    rs.getString("department"),
                    rs.getString("institution"),
                    rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
