package model;

import java.time.LocalDate;

public class Transaction {
    // Attributes Started Here
    private int transactionId;
    private int bookId;
    private int studentId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String status;
    private double fine_paid;
    // Attributes Ended Here
    // Constructor
    public Transaction(int bookId, int studentId, LocalDate issueDate, LocalDate dueDate){
        this.transactionId = 0;  // Kept Default for a while only
        this.bookId = bookId;
        this.studentId = studentId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = null;
        this.status = null;
        this.fine_paid = 0.0;
    }
    public Transaction(int transactionId, int bookId, int studentId, LocalDate issueDate, LocalDate dueDate, LocalDate returnDate, String status, double fine_paid){
        this.transactionId = transactionId;
        this.bookId = bookId;
        this.studentId = studentId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
        this.fine_paid = fine_paid;
    }
    // Helper Functions Started Here
    public void setTransactionId(int transactionId){
        this.transactionId = transactionId;
    }
    public int getTransactionId(){
        return transactionId;
    }
    public void setBookId(int bookId){
        this.bookId = bookId;
    }
    public int getBookId(){
        return bookId;
    }
    public void setStudentId(int studentId){
        this.studentId = studentId;
    }
    public int getStudentId(){
        return studentId;
    }
    public void setIssueDate(LocalDate issueDate){
        this.issueDate = issueDate;
    }
    public LocalDate getIssueDate(){
        return issueDate;
    }
    public void setDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }
    public LocalDate getDueDate(){
        return dueDate;
    }
    public void setReturnDate(LocalDate returnDate){
        this.returnDate = returnDate;
    }
    public LocalDate getReturnDate(){
        return returnDate;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return status;
    }
    public void setFine_paid(double fine_paid){
        this.fine_paid = fine_paid;
    }
    public double getFine_paid(){
        return fine_paid;
    }
    // Helper Functions Ended Here
}
