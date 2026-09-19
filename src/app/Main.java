package app;

import java.util.Scanner;
import services.Library;
import exception.*;

public class Main{
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);
        int registrationChoices = 1;
        System.out.println("Welcome to Arjit's Library Management System!");
        do{
            System.out.println("=============== MENU ===============");
            System.out.println("1. Register as Student\n2. Register as Admin\n3. Login as Student\n4. Login as Admin\n5. Exit");
            int registrationChoice = Library.readInt(sc, "Enter Your Choice(1-5): ");
            switch(registrationChoice){
                case 1:
                    boolean isStudent = true;
                    String studentName = Library.validStringInput(sc, "Enter Your Name: ");
                    System.out.print("Enter Your Username: ");
                    String usernameStudent = sc.nextLine();
                    if(library.checkDuplicateUsername(isStudent, usernameStudent)){
                        try{
                            throw new StudentAuthenticationException("Student with such Username already exists!");
                        } catch(StudentAuthenticationException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    String emailStudent = Library.validEmailInput(sc, "Enter Your Email: ");
                    if(library.checkDuplicateEmail(isStudent, emailStudent)){
                        try{
                            throw new StudentAuthenticationException("Student with such Email already exists!");
                        } catch(StudentAuthenticationException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    } 
                    long phoneStudent = Library.validPhoneNoInput(sc, "Enter Your Phone Number: ");
                    String courseStudent = Library.validStringInput(sc, "Enter Your Full Course Name: ");
                    int semesterStudent = Library.validSemesterInput(sc, "Enter Your Semester: ");
                    String departmentStudent = Library.validStringInput(sc,"Enter Your Department(Like IT, CS etc.): ");
                    String InstitutionName = Library.validStringInput(sc, "Enter Your Institution Name: ");
                    String passwordStudent = Library.validPasswordInput(sc, "Enter Your Password: ");
                    library.registerStudent(studentName, usernameStudent, emailStudent, phoneStudent, courseStudent, semesterStudent, departmentStudent, InstitutionName, passwordStudent);
                    break;
                case 2:
                    isStudent = false;
                    String adminName = Library.validStringInput(sc, "Enter Your Name: ");
                    System.out.print("Enter Your Username: ");
                    String usernameAdmin = sc.nextLine();
                    if(library.checkDuplicateUsername(isStudent, usernameAdmin)){
                        try{
                            throw new AdminAuthenticationException("Admin with such Username already exists!");
                        } catch(AdminAuthenticationException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    String emailAdmin = Library.validEmailInput(sc, "Enter Your Email: ");
                    if(library.checkDuplicateEmail(isStudent, emailAdmin)){
                        try{
                            throw new AdminAuthenticationException("Admin with such Email already exists!");
                        } catch(AdminAuthenticationException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    long phoneNoAdmin = Library.validPhoneNoInput(sc, "Enter Your Phone Number: ");
                    String passwordAdmin = Library.validPasswordInput(sc, "Enter Your Password: ");
                    library.registerAdmin(adminName, usernameAdmin, emailAdmin, phoneNoAdmin, passwordAdmin);
                    break;
                case 3:
                    System.out.print("Enter Your Username: ");
                    String usernameStudentLogin = sc.nextLine();
                    System.out.print("Enter Your Password: ");
                    String passwordStudentLogin = sc.nextLine();
                    if(library.verifyStudentLoginDetails(usernameStudentLogin, passwordStudentLogin)){
                        System.out.println("Student Login Successful!");
                        int studentMenuChoices = 1;
                        do{
                            System.out.println();
                            System.out.println("=============== MENU ===============");
                            System.out.println("1. Search A Book\n2. View All Books\n3. Issue A Book\n4. Return A Book \n5. View Own Transaction History\n6. See Own Profile\n7. Update Profile\n8. Delete Account\n9. Log Out");
                            int studentLoginChoice = Library.readInt(sc, "Enter Your Choice(1-9): ");
                            switch (studentLoginChoice) {
                                case 1:
                                    int searchBookId = Library.readInt(sc, "Enter Book Id to Search Book: ");
                                    if(library.searchBookById(searchBookId) != null){
                                        library.showBookDetails(library.searchBookById(searchBookId));
                                    } else{
                                        try{
                                            throw new BookNotFoundException("No Such Id Book is Present in the Library.");
                                        } catch(BookNotFoundException e){
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    break;
                                case 2:
                                    library.viewAllBooks();
                                    break;
                                case 3:
                                    if(library.currentIssuedBooksCount(usernameStudentLogin) >= 3){
                                        try{
                                            throw new MaximumLimitReached("You have already issued 3 books. You cannot issue more than 3 books at a time.");
                                        } catch(MaximumLimitReached e){
                                            System.out.println(e.getMessage());
                                        }
                                        break;
                                    }
                                    int bookIdToIssue = Library.readInt(sc, "Enter Book Id(You Want To Issue): ");
                                    library.issueBook(bookIdToIssue, usernameStudentLogin);
                                    break;
                                case 4:
                                    int bookIdToReturn = Library.readInt(sc, "Enter Book Id(You Want To Return): ");
                                    library.returnBook(bookIdToReturn, usernameStudentLogin);
                                    break;
                                case 5:
                                    library.viewStudentTransactionHistory(usernameStudentLogin);
                                    break;
                                case 6:
                                    library.viewStudentProfileInfo(usernameStudentLogin);
                                    break;
                                case 7:
                                    String newName = Library.validStringInput(sc, "Enter New Name: ");
                                    String newEmail = Library.validEmailInput(sc, "Enter New Email: ");
                                    long newPhoneNo = Library.validPhoneNoInput(sc, "Enter New Phone No.: ");
                                    String newCourse = Library.validStringInput(sc, "Enter New Course: ");
                                    int newSemester = Library.validSemesterInput(sc, "Enter New Semester: ");
                                    String newDepartment = Library.validStringInput(sc, "Enter New Department: ");
                                    String newInstitution = Library.validStringInput(sc, "Enter New Institution: ");
                                    String newPassword = Library.validPasswordInput(sc, "Enter New Password: ");
                                    library.editStudentProfileInfo(usernameStudentLogin, newName, newEmail, newPhoneNo, newCourse, newSemester, newDepartment, newInstitution, newPassword);
                                    break;
                                case 8:
                                    int captcha = (int)Math.random()*100000;
                                    System.out.println("Captcha Code: "+captcha);
                                    System.out.print("Enter Given Captcha: ");
                                    int userEnteredCaptcha = sc.nextInt();
                                    sc.nextLine();  // To Remove buffer
                                    if(userEnteredCaptcha == captcha){
                                        library.deleteStudentAccount(usernameStudentLogin);
                                        studentMenuChoices = 0;
                                    } else{
                                        try{
                                            throw new ValueMismatchedException("Captcha Value Mismatched!\nAccound is not Deleted.");
                                        } catch(ValueMismatchedException e){
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    break;
                                case 9:
                                    System.out.println("Logging Out...");
                                    studentMenuChoices = 0;
                                    break;    
                                default:
                                    break;
                            }
                        } while(studentMenuChoices == 1);
                    } else{
                        try{
                            throw new StudentAuthenticationException("With given details, No Student Exists!\nRegister First to Login.");
                        } catch(StudentAuthenticationException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 4:
                    System.out.print("Enter Your Username: ");
                    String usernameAdminLogin = sc.nextLine();
                    System.out.print("Enter Your Password: ");
                    String passwordAdminLogin = sc.nextLine();
                    if(library.verifyAdminLoginDetails(usernameAdminLogin, passwordAdminLogin)){
                        System.out.println("Admin Login Successful!");
                        int adminMenuChoices = 1;
                        do{
                            System.out.println();
                            System.out.println("=============== MENU ===============");
                            System.out.println("1. Add Book\n2. Update Book\n3. Delete Book\n4. View All Book\n5. View All Students\n6. View All Transactions\n7. Search A Book\n8. Search A Student\n9. Search A Transaction\n10. See Profile Info\n11. Edit Profile\n12. Delete Account\n13. Logout");
                            int adminLoginChoice = Library.readInt(sc, "Enter Your Choice(1-13): ");
                            switch(adminLoginChoice){
                                case 1:
                                    System.out.println("Provide Book Details Here: ");
                                    String addBookTitle = Library.validStringInput(sc, "Enter Book Title: ");
                                    String addBookAuthor = Library.validStringInput(sc,"Enter Book Author: ");
                                    String addBookCategory = Library.validStringInput(sc, "Enter Book Category: ");
                                    String addBookPublisher = Library.validStringInput(sc, "Enter Book Publisher: ");
                                    int addBookPublicationYear = Library.validInputYear(sc, "Enter Book Publication Year: ");
                                    int addBookTotalCopies = Library.readInt(sc, "Enter Total Copies: ");
                                    library.addBook(addBookTitle, addBookAuthor, addBookCategory, addBookPublisher, addBookPublicationYear, addBookTotalCopies);
                                    break;
                                case 2:
                                    System.out.println("Provide Updated Book Details Here: ");
                                    int bookIdToUpdate = Library.readInt(sc, "Enter Book Id to Update: ");
                                    if(library.searchBookById(bookIdToUpdate)==null){
                                        try{
                                            throw new BookNotFoundException("No such book ID exist in the Library.");
                                        } catch(BookNotFoundException e){
                                            System.out.println(e.getMessage());
                                        }
                                        break;
                                    }
                                    String updatedTitle = Library.validStringInput(sc, "Enter Book Title: ");
                                    String updatedAuthor = Library.validStringInput(sc,"Enter Book Author: ");
                                    String updatedCategory = Library.validStringInput(sc, "Enter Book Category: ");
                                    String updatedPublisher = Library.validStringInput(sc, "Enter Book Publisher: ");
                                    int updatedPublicationYear = Library.validInputYear(sc, "Enter Book Publication Year: ");
                                    int updatedTotalCopies = Library.readInt(sc, "Enter Total Copies: ");
                                    int updatedAvailableCopies = Library.readInt(sc,"Enter Available Copies: ");
                                    library.updateBook(bookIdToUpdate ,updatedTitle, updatedAuthor, updatedCategory, updatedPublisher, updatedPublicationYear, updatedTotalCopies, updatedAvailableCopies);
                                    break;
                                case 3:
                                    int bookIdToDelete = Library.readInt(sc, "Enter Book Id: ");
                                    if(library.searchBookById(bookIdToDelete)==null){
                                        try{
                                            throw new BookNotFoundException("No such book ID exist in the Library.");
                                        } catch(BookNotFoundException e){
                                            System.out.println(e.getMessage());
                                        }
                                        break;
                                    }
                                    library.deleteBook(bookIdToDelete);
                                    break;
                                case 4:
                                    library.viewAllBooks();
                                    break;
                                case 5:
                                    library.viewAllStudents();
                                    break;
                                case 6:
                                    library.viewAllTransactions();
                                    break;
                                case 7:
                                    int searchBookId = Library.readInt(sc, "Enter Book Id to Search Book: ");
                                    if(library.searchBookById(searchBookId) != null){
                                        library.showBookDetails(library.searchBookById(searchBookId));
                                    } else{
                                        try{
                                            throw new BookNotFoundException("No such book ID exist in the Library.");
                                        } catch(BookNotFoundException e){
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    break;
                                case 8:
                                    int studentIdToSearch = Library.readInt(sc, "Enter Student Id to Search Student: ");
                                    if(library.searchStudentById(studentIdToSearch) != null){
                                        library.showStudentDetails(library.searchStudentById(studentIdToSearch));
                                    } else{
                                        try{
                                            throw new StudentNotFoundException("No such student ID exist in the Library.");
                                        } catch(StudentNotFoundException e){
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    break;
                                case 9:
                                    int transactionIdToSearch = Library.readInt(sc, "Enter Transaction Id to Search Transaction: ");
                                    if(library.searchTransactionById(transactionIdToSearch) != null){
                                        library.showTransactionDetails(library.searchTransactionById(transactionIdToSearch));
                                    } else{
                                        try{
                                            throw new TransactionNotFoundException("No Such Id Transaction is Present in the Library.");
                                        } catch(TransactionNotFoundException e){
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    break;
                                case 10:
                                    library.viewAdminProfileInfo(usernameAdminLogin);
                                    break;
                                case 11:
                                    String newName = Library.validStringInput(sc, "Enter New Name: ");
                                    String newEmail = Library.validEmailInput(sc, "Enter New Email: ");
                                    long newPhoneNo = Library.validPhoneNoInput(sc, "Enter New Phone No.: ");
                                    String newPassword = Library.validPasswordInput(sc, "Enter New Password: ");
                                    library.editAdminProfileInfo(usernameAdminLogin, newName, newEmail, newPhoneNo, newPassword);
                                    break;
                                case 12:
                                    int captcha = (int)Math.random()*100000;
                                    System.out.println("Captcha Code: "+captcha);
                                    System.out.print("Enter Given Captcha: ");
                                    int userEnteredCaptcha = sc.nextInt();
                                    sc.nextLine();  // To Remove buffer
                                    if(userEnteredCaptcha == captcha){
                                        library.deleteAdminAccount(usernameAdminLogin);
                                        adminMenuChoices = 0;
                                    } else{
                                        try{
                                            throw new ValueMismatchedException("Wrong Value Entered! Account is Not Deleted.");
                                        } catch(ValueMismatchedException e){
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                    break;
                                case 13:
                                    System.out.println("Logging Out...");
                                    adminMenuChoices = 0;
                                    break;
                                default:
                                    System.out.println("Wrong Value Entered!");
                            }
                        } while(adminMenuChoices == 1);
                    } else{
                        try{
                            throw new AdminAuthenticationException("With given details, No Admin Exists!\nRegister First to Login.");
                        } catch(AdminAuthenticationException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 5:
                    System.out.println("Exiting the Application. Thank You for using Arjit's Library Management System!");
                    registrationChoices = 0;
                    break;
                default:
                    System.out.println("Wrong Value Entered!");
                    break;
            }
        } while(registrationChoices == 1);
    }
}