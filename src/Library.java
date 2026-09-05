import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    public Library(){
        try(ObjectInputStream oisBook = new ObjectInputStream(new FileInputStream("data/Book.dat"))){
            while(true){
                Book b = (Book)oisBook.readObject();
                books.add(b);
            }
        } catch(EOFException e){
            System.out.println("Book's data has been taken successfully!");
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
        try(ObjectInputStream oisStudent = new ObjectInputStream(new FileInputStream("data/Student.dat"))){
            while(true){
                Student s = (Student)oisStudent.readObject();
                students.add(s);
            }
        } catch(EOFException e){
            System.out.println("Student's data has been taken successfully!");
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    private int StudentCount = students.size();
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
            System.out.println("Book can't be added to Library because either with such title or id one book exist in Library.");
        } else{
            books.add(book);
            System.out.println("Book successfully added to Library.");
        }
    }
    void deleteBook(int Bookid){
        boolean bookFound = false;
        for(Book book : books){
            if(book.getBookId() == Bookid){
                books.remove(book);
                bookFound= true;
                System.out.println("Book removed successfully from Library.");
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
                System.out.println("Title updated successfully!");
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
                System.out.println("Author updated successfully!");
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
        boolean isIssuedListEmpty = true;
        if(books.isEmpty()){
            System.out.println("No Books in Library to Display.");
        } else{
            System.out.println("Displaying Only Those Books which are Issued By Library.");
            for(Book book:books){
                if(book.getIsIssued()){
                    System.out.println("***************************");
                    System.out.println("Book Id: "+book.getBookId()+"\nBook Title: "+book.getTitle()+"\nBook Author: "+book.getAuthor());
                    System.out.println("***************************");
                    isIssuedListEmpty = false;
                }
            }
            if(isIssuedListEmpty){
                System.out.println("NaN");
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
    Book searchBookByFullDetail(Book book){
        if(books.isEmpty()){
            return null;
        } else{
            for(Book bookinLibrary : books){
                if(bookinLibrary.getBookId() == book.getBookId() && bookinLibrary.getTitle().equalsIgnoreCase(book.getTitle()) && bookinLibrary.getAuthor().equalsIgnoreCase(book.getAuthor())){
                    return bookinLibrary;
                }
            }
            return null;
        }
    }
    Book searchBookById(int id){
        if(books.isEmpty()){
            return null;
        } else{
            for(Book book : books){
                if(book.getBookId() == id){
                    return book;
                }
            }
            return null;
        }
    }
    Book searchBookByTitle(String title){
        if(books.isEmpty()){
            return null;
        } else{
            for(Book book : books){
                if(book.getTitle().equalsIgnoreCase(title)){
                    return book;
                }
            }
            return null;
        }
    }
    ArrayList<Book> searchBookByAuthor(String Author){
        if(books.isEmpty()){
            return null;
        } else{
            ArrayList<Book> matchingBooks = new ArrayList<>();
            for(Book book: books){
                if(book.getAuthor().equalsIgnoreCase(Author)){
                    matchingBooks.add(book);
                }
            }
            if(matchingBooks.isEmpty()){
                return null;
            }else{
                return matchingBooks;
            }
        }
    }
    void addStudent(Student student){
        students.add(student);
    }
    void issueBook(Book book, Student student, Scanner sc){
        if(student == null || book == null){
            System.out.println("Student or Book is not known to Library.");
            return;
        }
        if(student.getIssuedBooks().size() < 3){
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
            System.out.println(student.getName()+" already have 3 Issued Books!");
        }
    }
    void returnBook(Student student, Book book, Scanner sc){
        if(student == null || book == null){
            System.out.println("Student or Book is not known to Library.");
            return;
        }
        if(!student.getIssuedBooks().isEmpty()){
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
                            book.setIssueDate(null);
                            book.setDueDate(null);
                            System.out.println("Book Returned Successfully!");
                        } else{
                            System.out.println("Book is not returned.\nPay Fine to return book.");
                        }
                    } else{
                        book.setIsIssued(false);
                        book.setIssuedTo(null);
                        student.getIssuedBooks().remove(book);
                        book.setIssueDate(null);
                        book.setDueDate(null);
                        System.out.println("Book Returned Successfully!");
                    }
                } else{
                    System.out.println("Wrong Password!");
                }
            } else{
                System.out.println(book.getTitle()+" is not Issued to anyone.");
            }
        } else{
            System.out.println(student.getName()+" has no Issued Book!");
        }
    }
    void CleanUpAction() throws IOException{
        File fileBook = new File("data/Book.txt");
        PrintWriter pw = new PrintWriter(new FileWriter(fileBook));
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/Book.dat"));
        for(Book book : books){
            oos.writeObject(book);
            pw.println(book.getBookId()+"|"+book.getTitle()+"|"+book.getAuthor()+"|"+book.getIsIssued()+"|"+book.getIssuedTo()+"|"+book.getIssueDate()+"|"+book.getDueDate());
        }
        oos.close();
        pw.close();
        File fileStudent = new File("data/Student.txt");
        PrintWriter pw1 = new PrintWriter(new FileWriter(fileStudent));
        ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream("data/Student.dat"));
        for(Student student : students){
            oos1.writeObject(student);
            pw1.println(student.getStudentId()+"|"+student.getName()+"|"+student.getRollNo()+"|"+student.getInstitution()+"|"+student.getIssuedBooks()+"|"+student.getPassword());
        }
        oos1.close();
        pw1.close();
    }
}
