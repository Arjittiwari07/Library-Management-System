import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Library {
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<Student> students = new ArrayList<>();
    int StudentCount = 0;
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
            if(book.BookId == Bookid){
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
            if(book.BookId == Bookid){
                book.title = newTitle;
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
            if(book.BookId == Bookid){
                book.author = newAuthor;
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
                System.out.println("Name: "+student.name+"\nRoll No.: "+student.RollNo+"\nStudent Id: "+student.studentId);
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
                if(book.isIssued){
                    System.out.println("***************************");
                    System.out.println("Book Id: "+book.BookId+"\nBook Title: "+book.title+"\nBook Author: "+book.author);
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
                if(!book.isIssued){
                    System.out.println("***************************");
                    System.out.println("Book Id: "+book.BookId+"\nBook Title: "+book.title+"\nBook Author: "+book.author);
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
                if(bookinLibrary.BookId == book.BookId && bookinLibrary.title.equalsIgnoreCase(book.title) && bookinLibrary.author.equalsIgnoreCase(book.author)){
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
                if(book.BookId == id){
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
                if(book.title.equalsIgnoreCase(title)){
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
                if(book.author.equalsIgnoreCase(Author)){
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
            if(student.issuedBook.size() < 3){
                if(books.contains(book)){
                    if(!book.isIssued){
                        System.out.println("To Confirm the Process\nEnter Your Password");
                        String password = sc.nextLine();
                        if(password.equals(student.getPassword())){
                            book.isIssued = true;
                            book.setIssuedTo(student);
                            book.setIssueDate(LocalDate.now());
                            book.setDueDate(book.getIssueDate().plusDays(7));
                            student.issuedBook.add(book);
                            System.out.println("Book Issued Successfully!");
                            System.out.println("Book Issued on "+ book.getIssueDate());
                            System.out.println("Note: Kindly Return the Book within 7 days.\nOtherwise Fine will be Imposed Rs. 10 for each subsequent day delay.");
                        } else{
                            System.out.println("Wrong Password!");
                        }
                    } else{
                        System.out.println(book.title+" is Already Issued to "+ book.getIssuedTo().name);
                    }
                } else{
                    System.out.println(book.title+" is Not present in Library.");
                }
            } else{
                System.out.println(student.name+" already have 3 Issued Books!");
            }
        } else{
            System.out.println(student.name +" is Not Registered. So, Register First to take Book.");
        }
    }
    void returnBook(Student student, Book book, Scanner sc){
        if(students.contains(student)){
            if(!student.issuedBook.isEmpty()){
                if(books.contains(book)){
                    if(book.isIssued){
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
                                    book.isIssued = false;
                                    book.setIssuedTo(null);
                                    student.issuedBook.remove(book);
                                    System.out.println("Book Returned Successfully!");
                                } else{
                                    System.out.println("Book is not returned.\nPay Fine to return book.");
                                }
                            } else{
                                book.isIssued = false;
                                book.setIssuedTo(null);
                                student.issuedBook.remove(book);
                                System.out.println("Book Returned Successfully!");
                            }
                        } else{
                            System.out.println("Wrong Password!");
                        }
                    } else{
                        System.out.println(book.title+" is not Issued to anyone.");
                    }
                } else{
                    System.out.println(book.title+" is Not Present in Library. Please Add if You want.");
                }
            } else{
                System.out.println(student.name+" has no Issued Book!");
            }
        } else{
            System.out.println(student.name +" is Not Registered. So, Register First to return Book.");
        }
    }
}
