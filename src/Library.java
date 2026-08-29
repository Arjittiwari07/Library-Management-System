import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    private int StudentCount = 0;
    ArrayList<Book> getBooks(){
        return books;
    }
    ArrayList<Student> getStudents(){
        return students;
    }
    void setStudentCount(int StudentCount){
        this.StudentCount = StudentCount;
    }
    int getStudentCount(){
        return StudentCount;
    }
    void addBook(Book book){
        if(books.contains(book)){
            System.out.println("Book is Already in Library.");
        } else{
            books.add(book);
        }
    }
    void deleteBook(int Bookid){
        boolean bookFound = false;
        for(Book book : books){
            if(book.getBookId() == Bookid){
                books.remove(book);
                bookFound= true;
                break;
            }
        }
        if(!bookFound)
            System.out.println("No such ID book is present in Library.");
    }
    void updateBookTitle(int Bookid, String newTitle){
        boolean bookFound = false;
        for(Book book : books){
            if(book.getBookId() == Bookid){
                book.setTitle(newTitle);
                bookFound = true;
                break;
            }
        }
        if(!bookFound)
            System.out.println("No such ID book is present in Library.");
    }
    void updateBookAuthor(int Bookid, String newAuthor){
        boolean bookFound = false;
        for(Book book : books){
            if(book.getBookId() == Bookid){
                book.setAuthor(newAuthor);
                bookFound = true;
                break;
            }
        }
        if(!bookFound)
            System.out.println("No such ID book is present in Library.");
    }
    void displayRegisteredStudents(){
        if(students.isEmpty()){
            System.out.println("No Students Registered yet.");
        } else{
            System.out.println("Displaying Registered Students: ");
            for(Student student : students){
                System.out.println("*************************");
                System.out.println("Name: "+student.getName()+"\nRoll No.: "+student.getRollNo()+"\nStudent Id: "+student.getStudentId());
                System.out.println("*************************");
            }
        }
    }
    void displayIssuedBooks(){
        if(books.isEmpty()){
            System.out.println("No Books in Library to Display.");
        } else{
            System.out.println("Displaying Only Those Books which are Issued By Library.");
            for(Book book:books){
                if(book.getIsIssued()){
                    System.out.println("***************************");
                    System.out.println("Book Id: "+book.getBookId()+"\nBook Title: "+book.getTitle()+"\nBook Author: "+book.getAuthor());
                    System.out.println("***************************");
                }
            }
        }
    }
    void displayAvailableBooks(){
        if(books.isEmpty()){
            System.out.println("No Books in Library to Display.");
        } else{
            System.out.println("Displaying Only Those Books which are currently present in Library.");
            for(Book book:books){
                if(!book.getIsIssued()){
                    System.out.println("***************************");
                    System.out.println("Book Id: "+book.getBookId()+"\nBook Title: "+book.getTitle()+"\nBook Author: "+book.getAuthor());
                    System.out.println("***************************");
                }
            }
        }
    }
    boolean searchBookByFullDetail(Book book){
        if(books.isEmpty()){
            return false;
        } else{
            for(Book bookinLibrary : books){
                if(bookinLibrary.getBookId() == book.getBookId() && bookinLibrary.getTitle().equalsIgnoreCase(book.getTitle()) && bookinLibrary.getAuthor().equalsIgnoreCase(book.getAuthor())){
                    return true;
                }
            }
            return false;
        }
    }
    boolean searchBookById(int id){
        if(books.isEmpty()){
            return false;
        } else{
            for(Book book : books){
                if(book.getBookId() == id){
                    return true;
                }
            }
            return false;
        }
    }
    boolean searchBookByTitle(String title){
        if(books.isEmpty()){
            return false;
        } else{
            for(Book book : books){
                if(book.getTitle().equalsIgnoreCase(title)){
                    return true;
                }
            }
            return false;
        }
    }
    ArrayList<Book> searchBookByAuthor(String Author){
        if(books.isEmpty()){
            return null;
        } else{
            ArrayList<Book> mathcingBooks = new ArrayList<>();
            for(Book book: books){
                if(book.getAuthor().equalsIgnoreCase(Author)){
                    mathcingBooks.addLast(book);
                }
            }
            return mathcingBooks;
        }
    }
    void addStudent(Student student){
        students.add(student);
    }
    void issueBook(Book book, Student student, Scanner sc){
        if(students.contains(student)){
            if(student.getIssuedBooks().size() < 3){
                if(books.contains(book)){
                    if(!book.getIsIssued()){
                        System.out.println("To Confirm the Process\nEnter Your Password");
                        String password = sc.nextLine();
                        if(password.equals(student.getPassword())){
                            book.setIsIssued(true);
                            book.setIssuedTo(student);
                            book.setIssueDate(LocalDate.now());
                            book.setDueDate(book.getIssueDate().plusDays(7));
                            student.getIssuedBooks().add(book);
                            System.out.println("Book Issued Successfully!");
                            System.out.println("Book Issued on "+ book.getIssueDate());
                            System.out.println("Note: Kindly Return the Book within 7 days.\nOtherwise Fine will be Imposed Rs. 10 for each subsequent day delay.");
                        } else{
                            System.out.println("Wrong Password!");
                        }
                    } else{
                        System.out.println(book.getTitle()+" is Already Issued to "+ book.getIssuedTo().getName());
                    }
                } else{
                    System.out.println(book.getTitle()+" is Not present in Library.");
                }
            } else{
                System.out.println(student.getName()+" already have 3 Issued Books!");
            }
        } else{
            System.out.println(student.getName() +" is Not Registered. So, Register First to take Book.");
        }
    }
    void returnBook(Student student, Book book, Scanner sc){
        if(students.contains(student)){
            if(!student.getIssuedBooks().isEmpty()){
                if(books.contains(book)){
                    if(book.getIsIssued()){
                        System.out.println("To Confirm the Process\nEnter Your Password");
                        String password = sc.nextLine();
                        if(password.equals(student.getPassword())){
                            LocalDate returnDate = LocalDate.now();
                            if(returnDate.isAfter(book.getDueDate())){
                                long delayDays = ChronoUnit.DAYS.between(book.getDueDate(), returnDate);
                                long fine = 10*delayDays;
                                System.out.println("You are "+delayDays+" days Late.");
                                System.out.println("You have to pay Rs."+fine+" as Fine.");
                                int proceedToPayment = Main.readInt(sc, "Enter 1 for Payment: ");
                                if(proceedToPayment == 1){
                                    book.setIsIssued(false);
                                    book.setIssuedTo(null);
                                    student.getIssuedBooks().remove(book);
                                    System.out.println("Book Returned Successfully!");
                                } else{
                                    System.out.println("Book is not returned.\nPay Fine to return book.");
                                }
                            } else{
                                book.setIsIssued(false);
                                book.setIssuedTo(null);
                                student.getIssuedBooks().remove(book);
                                System.out.println("Book Returned Successfully!");
                            }
                        } else{
                            System.out.println("Wrong Password!");
                        }
                    } else{
                        System.out.println(book.getTitle()+" is not Issued to anyone.");
                    }
                } else{
                    System.out.println(book.getTitle()+" is Not Present in Library. Please Add if You want.");
                }
            } else{
                System.out.println(student.getName()+" has no Issued Book!");
            }
        } else{
            System.out.println(student.getName() +" is Not Registered. So, Register First to return Book.");
        }
    }
}
