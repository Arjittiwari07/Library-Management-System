import java.time.LocalDate;

public class Book {
    int BookId;
    String title;
    String author;
    boolean isIssued;
    private Student issuedTo;
    private LocalDate issueDate;
    private LocalDate dueDate;
    Book(int BookId, String title, String author){
        this.BookId = BookId;
        this.title = title;
        this.author = author;
        this.isIssued  = false;
        issuedTo =null;
        issueDate = dueDate =null;
    }
    void setIssueDate(LocalDate localdate){
        issueDate = localdate;
    }
    LocalDate getIssueDate(){
        return issueDate;
    }
    void setDueDate(LocalDate localdate){
        dueDate = localdate;
    }
    LocalDate getDueDate(){
        return dueDate;
    }
    void setIssuedTo(Student student){
        issuedTo = student;
    }
    Student getIssuedTo(){
        return issuedTo;
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(!(obj instanceof Book))
            return false;
        Book book =(Book) obj;
        if(this.BookId == book.BookId && this.title.equalsIgnoreCase(book.title) && this.author.equalsIgnoreCase(book.author)){
            return true;
        }
        return false;
    }
}
