package services;

import dao.*;
import model.*;
import java.util.Scanner;
import java.time.*;
import java.util.ArrayList;
import exception.*;

public class Library{
    // Attributes
    private AdminDAO adminDAO;
    private StudentDAO studentDAO;
    private BookDAO bookDAO;
    private TransactionDAO transactionDAO;
    // Constructor
    public Library(){
        this.adminDAO = new AdminDAO();
        this.studentDAO = new StudentDAO();
        this.bookDAO = new BookDAO();
        this.transactionDAO = new TransactionDAO();
    }
    // Input taking helper functions for Main.java
    public static String validPasswordInput(Scanner sc, String msg){
        while(true){
            System.out.print(msg);
            String password = sc.nextLine();
            if(password.length() < 8){
                try{
                    throw new InvalidInputException("Password must be at least 8 characters long.");
                } catch(InvalidInputException e){
                    System.out.println(e.getMessage());
                }
                continue;
            }
            boolean hasUpper = false;
            boolean hasLower = false;
            boolean hasDigit = false;
            boolean hasSpecialCharacter = false;
            for(int i = 0;i<password.length();i++){
                char ch = password.charAt(i);
                if(Character.isUpperCase(ch)){
                    hasUpper = true;
                } else if(Character.isLowerCase(ch)){
                    hasLower = true;
                } else if(Character.isDigit(ch)){
                    hasDigit = true;
                } else{
                    hasSpecialCharacter = true;
                }
            }
            if(hasUpper && hasLower && hasDigit && hasSpecialCharacter){
                return password;
            } else{
                try{
                    throw new InvalidInputException("Password must contain: \n- At least one uppercase letter\n- At least one lowercase letter\n- At least one number\n- At least one special character");
                } catch(InvalidInputException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    public static String validStringInput(Scanner sc, String msg){
        while(true){
            System.out.print(msg);
            String input = sc.nextLine().trim();
            if(input.length()<2){
                try{
                    throw new InvalidInputException("Input must be at least 2 characters long.");
                } catch(InvalidInputException e){
                    System.out.println(e.getMessage());
                }
                continue;
            }
            boolean valid = true;
            for(int i = 0; i < input.length(); i++){
                char ch = input.charAt(i);
                if(!(Character.isLetter(ch) || ch==' ')){
                    valid = false;
                    break;
                }
            }
            if(!valid){
                try{
                    throw new InvalidInputException("Input must contain only letters and spaces.");
                } catch(InvalidInputException e){
                    System.out.println(e.getMessage());
                }
                continue;
            }
            return input;
        }
    }
    public static int readInt(Scanner sc, String msg){
        while (true) {
            System.out.print(msg);
            if(sc.hasNextInt()){
                int value = sc.nextInt();
                sc.nextLine();      // To remove buffer from input
                return value;
            }
            try{
                throw new InvalidInputException("Invalid number.");
            } catch(InvalidInputException e){
                System.out.println(e.getMessage());
            }
            sc.nextLine();
        }
    }
    public static String validEmailInput(Scanner sc, String msg){
        while(true){
            System.out.print(msg);
            String email = sc.nextLine().trim();
            if(email.length()<5 || !email.contains("@") || !email.contains(".")){
                try{
                    throw new InvalidInputException("Invalid Email Address!");
                } catch(InvalidInputException e){
                    System.out.println(e.getMessage());
                }
                continue;
            }
            return email;
        }
    }
    public static long validPhoneNoInput(Scanner sc, String msg){
        while(true){
            System.out.print(msg);
            String phoneNo = sc.nextLine().trim();
            if(!phoneNo.matches("\\d{8,15}")){
                try{
                    throw new InvalidInputException("Phone Number must contain only digits and be between 8 to 15 digits long.");
                } catch(InvalidInputException e){
                    System.out.println(e.getMessage());
                }
                continue;
            }
            boolean valid = true;
            for(int i = 0; i < phoneNo.length(); i++){
                char ch = phoneNo.charAt(i);
                if(!Character.isDigit(ch)){
                    valid = false;
                    break;
                }
            }
            if(!valid){
                try{
                    throw new InvalidInputException("Phone Number must contain only digits.");
                } catch(InvalidInputException e){
                    System.out.println(e.getMessage());
                }
                continue;
            }
            return Long.parseLong(phoneNo);
        }
    }
    public static int validSemesterInput(Scanner sc, String msg){
        while(true){
            System.out.print(msg);
            if(sc.hasNextInt()){
                int semester = sc.nextInt();
                sc.nextLine();      // To remove buffer from input
                if(semester < 1 || semester > 16){
                    try{
                        throw new InvalidInputException("Semester must be between 1 and 16.");
                    } catch(InvalidInputException e){
                        System.out.println(e.getMessage());
                    }
                    continue;
                }
                return semester;
            }
            try{
                throw new InvalidInputException("Invalid Number!");
            } catch(InvalidInputException e){
                System.out.println(e.getMessage());
            }
            sc.nextLine();
        }
    }
    public static int validInputYear(Scanner sc, String msg){
        while(true){
            System.out.print(msg);
            LocalDate currentDate = LocalDate.now();
            int currentYear = currentDate.getYear();
            if(sc.hasNextInt()){
                int year = sc.nextInt();
                sc.nextLine();      // To remove buffer from input
                if(year < 1900 || year > currentYear){
                    try{
                        throw new InvalidInputException("Year must be between 1900 and " + currentYear + ".");
                    } catch(InvalidInputException e){
                        System.out.println(e.getMessage());
                    }
                    continue;
                }
                return year;
            }
            try{
                throw new InvalidInputException("Invalid Number!");
            } catch(InvalidInputException e){
                System.out.println(e.getMessage());
            }
            sc.nextLine();
        }
    }
    // Main Logic Helper functions for Main.java
    public boolean checkDuplicateEmail(boolean isStudent, String email){
        if(isStudent){
            Student student = studentDAO.getStudentByEmail(email);
            return student != null;
        } else{
            Admin admin = adminDAO.getAdminByEmail(email);
            return admin != null;
        }
    }
    public boolean checkDuplicateUsername(boolean isStudent, String username){
        if(isStudent){
            Student student = studentDAO.getStudentByUsername(username);
            return student != null;
        } else{
            Admin admin = adminDAO.getAdminByUsername(username);
            return admin != null;
        }
    }
    public void registerStudent(String studentName, String username, String email, long phoneNo, String course, int semester, String department, String institution, String password){
        Student student = new Student(username, studentName, email, phoneNo, course, semester, department, institution, password);
        boolean success = studentDAO.registerStudent(student);
        if(success){
            System.out.println("Student Registered Successfully!");
        } else{
            try{
                throw new SystemErrorException("Failed to Register Student Due to System Error.\nTry Again Later.");
            } catch(SystemErrorException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public void registerAdmin(String adminName, String username, String email, long phoneNo, String password){
        Admin admin = new Admin(adminName, phoneNo, email, username, password);
        boolean success = adminDAO.registerAdmin(admin);
        if(success){
            System.out.println("Admin Registered Successfully!");
        } else{
            try{
                throw new SystemErrorException("Failed to Register Admin Due to System Error.\nTry Again Later.");
            } catch(SystemErrorException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public boolean verifyAdminLoginDetails(String username, String password){
        Admin admin = adminDAO.getAdminByUsername(username);
        if(admin != null && admin.getPassword().equals(password)){
            return true;
        }
        return false;
    }
    public boolean verifyStudentLoginDetails(String username, String password){
        Student student = studentDAO.getStudentByUsername(username);
        if(student != null && student.getPassword().equals(password)){
            return true;
        }
        return false;
    }
    public void addBook(String bookTitle, String bookAuthor, String bookCategory, String bookPublisher, int bookPublicationYear, int bookTotalCopies){
        Book book = new Book(bookTitle, bookAuthor, bookCategory, bookPublisher, bookPublicationYear, bookTotalCopies, bookTotalCopies);
        boolean success = bookDAO.addBook(book);
        if(success){
            System.out.println("Book Added Successfully!");
        } else{
            try{
                throw new SystemErrorException("Failed to Add Book Due to System Error.\nTry Again Later.");
            } catch(SystemErrorException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public void updateBook(int bookId, String bookTitle, String bookAuthor, String bookCategory, String bookPublisher, int bookPublicationYear, int bookTotalCopies,int availableCopies){
        Book book = new Book(bookId, bookTitle, bookAuthor, bookCategory, bookPublisher, bookPublicationYear, bookTotalCopies, availableCopies);
        boolean success = bookDAO.updateBook(book);
        if(success){
            System.out.println("Book Updated Successfully!");
        } else{
            try{
                throw new SystemErrorException("Failed to Update Book Due to System Error.\nTry Again Later.");
            } catch(SystemErrorException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public Book searchBookById(int BookId){
        return bookDAO.getBookById(BookId);
    }
    public Student searchStudentById(int studentId){
        return studentDAO.getStudentById(studentId);
    }
    public Transaction searchTransactionById(int transactionId){
        return transactionDAO.getTransactionById(transactionId);
    }
    public void showBookDetails(Book book){
        System.out.println(
            "Book Title: "+book.getTitle()+
            "\nBook Author: "+book.getAuthor()+
            "\nBook Category: "+book.getCategory()+
            "\nBook Publisher: "+book.getPublisher()+
            "\nBook Publication Year: "+book.getPublicationYear()+
            "\nBook Total Copies: "+book.getTotalCopies()+
            "\nBook Available Copies: "+book.getAvailableCopies()
        );
    }
    public void showTransactionDetails(Transaction transaction){
        System.out.println(
            "Transaction Id: "+transaction.getTransactionId()+
            "\nBook Id: "+transaction.getBookId()+
            "\nStudent Id: "+transaction.getStudentId()+
            "\nIssue Date: "+transaction.getIssueDate()+
            "\nReturn Date: "+transaction.getReturnDate()+
            "\nStatus: "+transaction.getStatus()
        );
    }
    public void deleteBook(int bookId){
        boolean success = bookDAO.deleteBook(bookId);
        if(success){
            System.out.println("Book Deleted Successfully!");
        } else{
            try{
                throw new SystemErrorException("Failed to Delete Book Due to System Error.\nTry Again Later.");
            } catch(SystemErrorException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public void viewAllBooks(){
        ArrayList<Book> allBooks = bookDAO.getAllBooks();
        for(Book book : allBooks){
            showBookDetails(book);
            System.out.println();
        }
    }
    public void showStudentDetails(Student student){
        System.out.println(
            "Student Id: "+student.getStudentId()+
            "\nStudent Name: "+student.getName()+
            "\nStudent Username: "+student.getUsername()+
            "\nStudent Email: "+student.getEmail()+
            "\nStudent Phone No.: "+student.getPhoneNo()+
            "\nStudent Course: "+student.getCourse()+
            "\nStudent Semester: "+student.getSemester()+
            "\nStudent Department: "+student.getDepartment()+
            "\nStudent Institution: "+student.getInstitution()
        );
    }
    public void viewAllStudents(){
        ArrayList<Student> allStudents = studentDAO.getAllStudent();
        for(Student student : allStudents){
            showStudentDetails(student);
            System.out.println();
        }
    }
    public void viewAllTransactions(){
        ArrayList<Transaction> allTransactions = transactionDAO.getAllTransactions();
        for(Transaction transaction : allTransactions){
            showTransactionDetails(transaction);
            System.out.println();
        }
    }
    public void issueBook(int bookId, String studentUsername){
        Book book = bookDAO.getBookById(bookId);
        Student student = studentDAO.getStudentByUsername(studentUsername);
        if(book == null){
            try{
                throw new BookNotFoundException("No Such Book is Present in the Library.");
            } catch(BookNotFoundException e){
                System.out.println(e.getMessage());
            }
            return;
        }
        if(student == null){
            try{
                throw new StudentNotFoundException("No Such Student is Present in the Library.");
            } catch(StudentNotFoundException e){
                System.out.println(e.getMessage());
            }
            return;
        }
        if(book.getAvailableCopies() <= 0){
            try{
                throw new BookNotAvailableException("No Available Copies of the Book are Present in the Library.");
            } catch(BookNotAvailableException e){
                System.out.println(e.getMessage());
            }
            return;
        }
        Transaction transaction = new Transaction(bookId, student.getStudentId(), LocalDate.now(), LocalDate.now().plusDays(7));
        boolean success = transactionDAO.issueBook(transaction);
        if(success){
            System.out.println("Book Issued Successfully!");
        } else{
            try{
                throw new SystemErrorException("Failed to Issue Book Due to System Error.\nTry Again Later.");
            } catch(SystemErrorException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public void returnBook(int bookId, String studentUsername){
        Book book = bookDAO.getBookById(bookId);
        Student student = studentDAO.getStudentByUsername(studentUsername);
        if(book == null){
            try{
                throw new BookNotFoundException("No Such Book is Present in the Library.");
            } catch(BookNotFoundException e){
                System.out.println(e.getMessage());
            }
            return;
        }
        if(student == null){
            try{
                throw new StudentNotFoundException("No Such Student is Present in the Library.");
            } catch(StudentNotFoundException e){
                System.out.println(e.getMessage());
            }
            return;
        }
        Transaction transaction = transactionDAO.getTransactionByBookIdAndStudentId(bookId, student.getStudentId());
        if(transaction == null){
            try{
                throw new TransactionNotFoundException("No Such Transaction Found for this Book and Student.");
            } catch(TransactionNotFoundException e){
                System.out.println(e.getMessage());
            }
            return;
        }
        double longDays = java.time.temporal.ChronoUnit.DAYS.between(transaction.getDueDate(), LocalDate.now());
        if(longDays > 0){
            double fine = longDays * 50.0; // Assuming a fine of 50Rs. per day late
            System.out.println("You have a fine of Rs. "+fine+" for returning the book late.");
            System.out.println("Please pay the fine before returning the book.");
            System.out.println("Fine Paid: Rs. "+fine);
        }
        boolean success = transactionDAO.returnBook(transaction.getTransactionId());
        if(success){
            System.out.println("Book Returned Successfully!");
        } else{
            try{
                throw new SystemErrorException("Failed to Return Book Due to System Error.\nTry Again Later.");
            } catch(SystemErrorException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public void viewAdminProfileInfo(String username){
        Admin admin = adminDAO.getAdminByUsername(username);
        System.out.println(
            "Admin Id: "+admin.getAdminId()+
            "\nAdmin Name: "+admin.getName()+
            "\nAdmin Username: "+admin.getUsername()+
            "\nAdmin Email: "+admin.getEmail()+
            "\nAdmin Phone No.: "+admin.getPhoneNo()
        );
    }
    public void viewStudentProfileInfo(String username){
        Student student = studentDAO.getStudentByUsername(username);
        System.out.println(
            "Student Id: "+student.getStudentId()+
            "\nStudent Name: "+student.getName()+
            "\nStudent Username: "+student.getUsername()+
            "\nStudent Email: "+student.getEmail()+
            "\nStudent Phone No.: "+student.getPhoneNo()+
            "\nStudent Course: "+student.getCourse()+
            "\nStudent Semester: "+student.getSemester()+
            "\nStudent Department: "+student.getDepartment()+
            "\nStudent Institution: "+student.getInstitution()
        );
    }
    public void editAdminProfileInfo(String username, String newName, String newEmail, long newPhoneNo, String newPassword){
        Admin admin = adminDAO.getAdminByUsername(username);
        admin.setName(newName);
        admin.setEmail(newEmail);
        admin.setPhoneNo(newPhoneNo);
        admin.setPassword(newPassword);
        boolean success = adminDAO.updateAdmin(admin);
        if(success){
            System.out.println("Admin Profile Updated Successfully!");
        } else{
            try{
                throw new SystemErrorException("Failed to Update Admin Profile Due to System Error.\nTry Again Later.");
            } catch(SystemErrorException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public void editStudentProfileInfo(String username, String newName, String newEmail, long newPhoneNo,String newCourse, int newSemester, String newDepartment, String newInstitution, String newPassword){
        Student student = studentDAO.getStudentByUsername(username);
        student.setName(newName);
        student.setEmail(newEmail);
        student.setPhoneNo(newPhoneNo);
        student.setCourse(newCourse);
        student.setSemester(newSemester);
        student.setDepartment(newDepartment);
        student.setInstitution(newInstitution);
        student.setPassword(newPassword);
        boolean success = studentDAO.updateStudent(student);
        if(success){
            System.out.println("Student Profile Updated Successfully!");
        } else{
            try{
                throw new SystemErrorException("Failed to Update Student Profile Due to System Error.\nTry Again Later.");
            } catch(SystemErrorException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public void deleteAdminAccount(String username){
        Admin admin = adminDAO.getAdminByUsername(username);
        boolean success = adminDAO.deleteAdmin(admin.getAdminId());
        if(success){
            System.out.println("Admin Account Deleted Successfully!");
        } else{
            try{
                throw new SystemErrorException("Failed to Delete Admin Account Due to System Error.\nTry Again Later.");
            } catch(SystemErrorException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public void deleteStudentAccount(String username){
        Student student = studentDAO.getStudentByUsername(username);
        boolean success = studentDAO.deleteStudent(student.getStudentId());
        if(success){
            System.out.println("Student Account Deleted Successfully!");
        } else{
            try{
                throw new SystemErrorException("Failed to Delete Student Account Due to System Error.\nTry Again Later.");
            } catch(SystemErrorException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public void viewStudentTransactionHistory(String username){
        Student student = studentDAO.getStudentByUsername(username);
        ArrayList<Transaction> transactions = transactionDAO.getTransactionsByStudentId(student.getStudentId());
        if(transactions.isEmpty()){
            try{
                throw new TransactionNotFoundException("No Transactions Found for this Student.");
            } catch(TransactionNotFoundException e){
                System.out.println(e.getMessage());
            }
            return;
        }
        for(Transaction transaction : transactions){
            showTransactionDetails(transaction);
            System.out.println();
        }
    }
    public int currentIssuedBooksCount(String username){
        Student student = studentDAO.getStudentByUsername(username);
        return transactionDAO.getCurrentIssuedBooksCount(student.getStudentId());
    }
}
