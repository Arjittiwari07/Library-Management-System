package model;

public class Book {
    // Attributes Started
    private int BookId;
    private String title;
    private String author;
    private String category;
    private String publisher;
    private int publicationYear;
    private int totalCopies;
    private int availableCopies;
    // Attributes Ended
    // Constructor
    public Book(String title, String author, String category, String publisher, int publicationYear, int totalCopies, int availableCopies){
        this.BookId = 0;    // Kept Default for a while only
        this.title = title;
        this.author = author;
        this.category = category;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }
    public Book(int BookId, String title, String author, String category, String publisher, int publicationYear, int totalCopies, int availableCopies){
        this.BookId = BookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }  
    // Helper Fucntions Started
    public void setBookId(int bookId){
        BookId = bookId;
    }
    public int getBookId(){
        return BookId;
    }
    public void setTitle(String Title){
        this.title = Title;
    }
    public String getTitle(){
        return title;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public String getAuthor(){
        return author;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public String getCategory(){
        return category;
    }
    public void setPublisher(String publisher){
        this.publisher = publisher;
    }
    public String getPublisher(){
        return publisher;
    }
    public void setPublicationYear(int publicationYear){
        this.publicationYear = publicationYear;
    }
    public int getPublicationYear(){
        return publicationYear;
    }
    public void setTotalCopies(int totalCopies){
        this.totalCopies = totalCopies;
    }
    public int getTotalCopies(){
        return totalCopies;
    }
    public void setAvailableCopies(int availableCopies){
        this.availableCopies = availableCopies;
    }
    public int getAvailableCopies(){
        return availableCopies;
    }
    // Helper Fucntions Ended
}
