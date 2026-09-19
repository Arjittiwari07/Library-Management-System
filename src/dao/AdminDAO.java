package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Admin;
import util.DBConnnection;

public class AdminDAO {
    // Attribute to hold the database connection
    private Connection connection;
    // Constructor to initialize the database connection
    public AdminDAO() {
        this.connection = DBConnnection.getConnectionFromConfig();
    }
    // Method to register a new admin in the database
    public boolean registerAdmin(Admin admin){
        String sql = "INSERT INTO admins (username, password, name, email, phone_no) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, admin.getUsername());
            pstmt.setString(2, admin.getPassword());
            pstmt.setString(3, admin.getName());
            pstmt.setString(4, admin.getEmail());
            pstmt.setLong(5, admin.getPhoneNo());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // Method to retrieve an admin by its ID
    public Admin getAdminById(int adminId){
        String sql = "SELECT * FROM admins WHERE admin_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, adminId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Admin(
                        rs.getInt("admin_id"),
                        rs.getString("name"),
                        rs.getLong("phone_no"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // Method to retrieve all admins from the database
    public ArrayList<Admin> getAllAdmins(){
        ArrayList<Admin> admins = new ArrayList<>();
        String sql = "SELECT * FROM admins";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Admin admin = new Admin(
                        rs.getInt("admin_id"),
                        rs.getString("name"),
                        rs.getLong("phone_no"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password")
                    );
                    admins.add(admin);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admins;
    }
    // Method to get admin by email
    public Admin getAdminByEmail(String email){
        String sql = "SELECT * FROM admins WHERE email = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Admin(
                        rs.getInt("admin_id"),
                        rs.getString("name"),
                        rs.getLong("phone_no"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // Method to update an admin's information in the database
    public boolean updateAdmin(Admin admin){
        String sql = "UPDATE admins SET username = ?, password = ?, name = ?, email = ?, phone_no = ? WHERE admin_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, admin.getUsername());
            pstmt.setString(2, admin.getPassword());
            pstmt.setString(3, admin.getName());
            pstmt.setString(4, admin.getEmail());
            pstmt.setLong(5, admin.getPhoneNo());
            pstmt.setInt(6, admin.getAdminId());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // Method to get an admin by username (useful for login)
    public Admin getAdminByUsername(String username){
        String sql = "SELECT * FROM admins WHERE username = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Admin(
                        rs.getInt("admin_id"),
                        rs.getString("name"),
                        rs.getLong("phone_no"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // Method to delete an admin from the database
    public boolean deleteAdmin(int adminId){
        String sql = "DELETE FROM admins WHERE admin_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, adminId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
