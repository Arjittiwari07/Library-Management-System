package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Transaction;
import util.DBConnnection;

public class TransactionDAO {
    // Attribute to hold the database connection
    private Connection connection;

    // Constructor to initialize the database connection
    public TransactionDAO() {
        this.connection = DBConnnection.getConnectionFromConfig();
    }
    // Method to issue a book to a student and record the transaction in the database
    public boolean issueBook(Transaction transaction){
        try {
            connection.setAutoCommit(false); // Start transaction
            // Insert the transaction record
            String sql = "INSERT INTO transactions (student_id, book_id, issue_date, due_date) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, transaction.getStudentId());
                pstmt.setInt(2, transaction.getBookId());
                pstmt.setDate(3, Date.valueOf(transaction.getIssueDate()));
                pstmt.setDate(4, Date.valueOf(transaction.getDueDate()));
                pstmt.executeUpdate();
            }

            // Update the available copies of the book
            sql = "UPDATE books SET available_copies = available_copies - 1 WHERE book_id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, transaction.getBookId());
                pstmt.executeUpdate();
            }

            connection.commit(); // Commit transaction
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback(); // Rollback transaction on error
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            return false;
        } finally {
            try {
                connection.setAutoCommit(true); // Restore auto-commit mode
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    public Transaction getTransactionByBookIdAndStudentId(int bookId, int studentId){
        String sql = "SELECT * FROM transactions WHERE book_id = ? AND student_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            pstmt.setInt(2, studentId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Transaction(
                    rs.getInt("transaction_id"),
                    rs.getInt("book_id"),
                    rs.getInt("student_id"),
                    rs.getDate("issue_date").toLocalDate(),
                    rs.getDate("due_date").toLocalDate(),
                    rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null,
                    rs.getString("status"),
                    rs.getDouble("fine_paid")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // Method to return a book and update the transaction record in the database
    public boolean returnBook(int TransactionId){
        try {
            connection.setAutoCommit(false); // Start transaction
            // Update the transaction record with the return date and status
            String sql = "UPDATE transactions SET return_date = CURRENT_DATE(), status = 'COMPLETED' WHERE transaction_id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, TransactionId);
                pstmt.executeUpdate();
            }

            // Calculate the fine for the transaction and update the fine_paid field
            double fine = calculateFine(TransactionId);
            sql = "UPDATE transactions SET fine_paid = ? WHERE transaction_id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setDouble(1, fine);
                pstmt.setInt(2, TransactionId);
                pstmt.executeUpdate();
            }

            // Get the book ID associated with the transaction
            int bookId = getTransactionById(TransactionId).getBookId();

            // Update the available copies of the book
            sql = "UPDATE books SET available_copies = available_copies + 1 WHERE book_id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, bookId);
                pstmt.executeUpdate();
            }

            connection.commit(); // Commit transaction
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback(); // Rollback transaction on error
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            return false;
        } finally {
            try {
                connection.setAutoCommit(true); // Restore auto-commit mode
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    // Method to retrieve a transaction by its ID
    public Transaction getTransactionById(int transactionId){
        String sql = "SELECT * FROM transactions WHERE transaction_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, transactionId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Transaction(
                    rs.getInt("transaction_id"),
                    rs.getInt("book_id"),
                    rs.getInt("student_id"),
                    rs.getDate("issue_date").toLocalDate(),
                    rs.getDate("due_date").toLocalDate(),
                    rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null,
                    rs.getString("status"),
                    rs.getDouble("fine_paid")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // Method to retrieve all transactions from the database
    public ArrayList<Transaction> getAllTransactions(){
        String sql = "SELECT * FROM transactions";
        ArrayList<Transaction> transactions = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Transaction transaction = new Transaction(
                    rs.getInt("transaction_id"),
                    rs.getInt("student_id"),
                    rs.getInt("book_id"),
                    rs.getDate("issue_date").toLocalDate(),
                    rs.getDate("due_date").toLocalDate(),
                    rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null,
                    rs.getString("status"),
                    rs.getDouble("fine_paid")
                );
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
    // Method to retrieve all transactions for a specific student by their ID
    public ArrayList<Transaction> getTransactionsByStudentId(int studentId){
        String sql = "SELECT * FROM transactions WHERE student_id = ?";
        ArrayList<Transaction> transactions = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Transaction transaction = new Transaction(
                    rs.getInt("transaction_id"),
                    rs.getInt("book_id"),
                    rs.getInt("student_id"),
                    rs.getDate("issue_date").toLocalDate(),
                    rs.getDate("due_date").toLocalDate(),
                    rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null,
                    rs.getString("status"),
                    rs.getDouble("fine_paid")
                );
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
    // Method to Check how many books are currently issued by a student
    public int getCurrentIssuedBooksCount(int studentId){
        String sql = "SELECT COUNT(*) AS count FROM transactions WHERE student_id = ? AND return_date IS NULL";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    // Method to calculate the fine for a transaction based on the return date and due date
    private double calculateFine(int transactionId){
        Transaction transaction = getTransactionById(transactionId);
        if (transaction != null && transaction.getReturnDate() != null) {
            long daysLate = java.time.temporal.ChronoUnit.DAYS.between(transaction.getDueDate(), transaction.getReturnDate());
            if (daysLate > 0) {
                return daysLate * 50.0; // Assuming a fine of 50Rs. per day late
            }
        }
        return 0.0;
    }
}
