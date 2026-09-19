package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Book;
import util.DBConnnection;

public class BookDAO {
    // Attribute to hold the database connection
    private Connection connection;
    // Constructor to initialize the database connection
    public BookDAO() {
        this.connection = DBConnnection.getConnectionFromConfig();
    }
    // Method to add a new book to the database
    public boolean addBook(Book book) {
        String sql = "INSERT INTO books (title, author, category, publication_year, total_copies, available_copies, publisher) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getCategory());
            pstmt.setInt(4, book.getPublicationYear());
            pstmt.setInt(5, book.getTotalCopies());
            pstmt.setInt(6, book.getAvailableCopies());
            pstmt.setString(7, book.getPublisher());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // Method to retrieve a book by its ID
    public Book getBookById(int bookId) {
        String sql = "SELECT * FROM books WHERE book_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Book(
                    rs.getInt("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("category"),
                    rs.getString("publisher"),
                    rs.getInt("publication_year"),
                    rs.getInt("total_copies"),
                    rs.getInt("available_copies")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // Method to retrieve all books from the database
    public ArrayList<Book> getAllBooks() {
        String sql = "SELECT * FROM books";
        ArrayList<Book> books = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Book book = new Book(
                    rs.getInt("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("category"),
                    rs.getString("publisher"),
                    rs.getInt("publication_year"),
                    rs.getInt("total_copies"),
                    rs.getInt("available_copies")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    // Method to update an existing book in the database
    public boolean updateBook(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, category = ?, publisher = ?, publication_year = ?, total_copies = ?, available_copies = ? WHERE book_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getCategory());
            pstmt.setString(4, book.getPublisher());
            pstmt.setInt(5, book.getPublicationYear());
            pstmt.setInt(6, book.getTotalCopies());
            pstmt.setInt(7, book.getAvailableCopies());
            pstmt.setInt(8, book.getBookId());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // Method to delete a book from the database by its ID
    public boolean deleteBook(int bookId){
        String sql = "DELETE FROM books WHERE book_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
