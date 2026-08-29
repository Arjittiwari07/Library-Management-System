import java.util.Scanner;
import java.util.ArrayList;

public class Main{
    static Book takeInputBook(Scanner sc, Library library){
        System.out.println("Please Enter Books Details!");
        int bookId = readInt(sc, "Enter Book Id: ");
        System.out.print("Enter Book Title: ");
        String bookTitle = sc.nextLine();
        System.out.print("Enter Book Author: ");
        String bookAuthor = sc.nextLine();
        Book newBook = new Book(bookId, bookTitle, bookAuthor);
        for(Book book : library.getBooks()){
            if(book.equals(newBook)){
                newBook = book;
            }
        }
        return newBook;
    }
    static Student takeInputStudent(Scanner sc, Library library){
        System.out.print("Enter Student Name: ");
        String StudentName = sc.nextLine();
        int StudentId = readInt(sc, "Enter Student Id (Given by Library): ");
        Student NewStudent = new Student(StudentId,StudentName);
        for(Student student : library.getStudents()){
            if(student.equals(NewStudent)){
                NewStudent = student;
            }
        }
        return NewStudent;
    }
    static int readInt(Scanner sc, String msg){
        while (true) {
            System.out.print(msg);
            if(sc.hasNextInt()){
                int value = sc.nextInt();
                sc.nextLine();
                return value;
            }
            System.out.println("Invalid number.");
            sc.nextLine();
        }
    }
    public static void main(String[] args) {
        int choices;    // to continue loops
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        do{
            System.out.println("Welcome to Arjit's Library Management System!");
            System.out.println("=============== MENU ===============");
            System.out.println("1. Add Books\n2. Display Books\n3. Search Books\n4. Issue Book\n5. Return Book\n6. Register New Student\n7. Display Registered Students\n8. Delete Book\n9. Update Book\n");
            int choice = readInt(sc, "Enter Your Choice(1-9): ");     // to take choice form user
            switch (choice) {
                case 1:
                    System.out.println("Please Enter Books Details!");
                    int bookId = readInt(sc, "Enter Book Id: ");
                    System.out.print("Enter Book Title: ");
                    String bookTitle = sc.nextLine();
                    System.out.print("Enter Book Author: ");
                    String bookAuthor = sc.nextLine();
                    Book newBook = new Book(bookId, bookTitle, bookAuthor);
                    library.addBook(newBook);
                    break;
                case 2:
                    int displaychoices;
                    do{
                        System.out.println("=========== MENU ==========");
                        System.out.println("1. Display Issued Books\n2. Display Available Books");
                        int displayChoice = readInt(sc, "Enter Your Choice(1-2): ");
                        switch (displayChoice) {
                            case 1:
                                library.displayIssuedBooks();
                                break;
                            case 2:
                                library.displayAvailableBooks();
                                break;
                            default:
                                System.out.println("Wrong Value Entered!");
                                break;
                        }
                        displaychoices = readInt(sc, "Want to Contiue, Type 1: ");
                    } while(displaychoices == 1);
                    break;
                case 3:
                    int SearchChoices;
                    do{
                        System.out.println("=========== SEARCH MENU ===========");
                        System.out.println("1. Search by Book\n2. Search by Book Author\n3. Search by book Id\n4. Search by Book Title");
                        int choice1 = readInt(sc, "Please Enter Your Choice(1-4): "); 
                        switch (choice1) {
                            case 1:
                                System.out.println("Please Enter Books Details!");
                                int bookIdSearch = readInt(sc, "Enter Book Id: ");
                                System.out.print("Enter Book Title: ");
                                String bookTitleSearch = sc.nextLine();
                                System.out.print("Enter Book Author: ");
                                String bookAuthorSearch = sc.nextLine();
                                Book newBookSearch = new Book(bookIdSearch, bookTitleSearch, bookAuthorSearch);
                                if(library.searchBookByFullDetail(newBookSearch)){
                                    System.out.println("Book is Present in Library!");
                                } else{
                                    System.out.println("No Such Book Found!");
                                }
                                break;
                            case 2:
                                System.out.print("Enter Author Name: ");
                                String AuthorForSearch = sc.nextLine();
                                if(library.searchBookByAuthor(AuthorForSearch)==null){
                                    System.out.println("No Such Book Found!");
                                } else{
                                    ArrayList<Book> current = library.searchBookByAuthor(AuthorForSearch);
                                    for(Book book: current){
                                        System.out.println("****************************");
                                        System.out.println("1. Book Id: "+book.getBookId()+"\n2. Book Title: "+book.getTitle()+"\n3. Book Author: "+book.getAuthor());
                                        System.out.println("****************************");
                                    }
                                }
                                break;
                            case 3:
                                int id = readInt(sc,"Enter Book Id: ");
                                if(library.searchBookById(id)){
                                    System.out.println("Book is present in Library!");
                                } else{
                                    System.out.println("No Such Book Found!");
                                }
                                break;
                            case 4:
                                System.out.print("Enter Book Title: ");
                                String bookTitlesearch = sc.nextLine();
                                if(library.searchBookByTitle(bookTitlesearch)){
                                    System.out.println("Book is present in Library!");
                                } else{
                                    System.out.println("No Such Book Found!");
                                }
                                break;
                            default:
                                System.out.println("Wrong Value Entered!");
                                break;
                        }
                        SearchChoices = readInt(sc, "Want to Continue, Press 1: ");
                    }while(SearchChoices == 1);
                    break;
                case 4:
                    Student newStudent2 = takeInputStudent(sc, library);
                    Book newBook2 = takeInputBook(sc, library);
                    library.issueBook(newBook2, newStudent2, sc);
                    break;
                case 5:
                    Student newStudent3 = takeInputStudent(sc, library);
                    Book newBook3 = takeInputBook(sc, library);
                    library.returnBook(newStudent3, newBook3, sc);
                    break;
                case 6:
                    System.out.print("Enter Your Name: ");
                    String StudentName = sc.nextLine();
                    System.out.print("Enter Your Institution Name: ");
                    String InstitutionName = sc.nextLine();
                    int StudentRollNo = readInt(sc, "Enter Your Institutional Roll Number: ");
                    boolean UniqueRollNo = true;
                    for(Student student: library.getStudents()){
                        if(StudentRollNo == student.getRollNo()){
                            System.out.println("Student can't be registered!");
                            System.out.println("As Student with such details already exists.");
                            UniqueRollNo = false;
                            break;
                        }
                    }
                    if(UniqueRollNo){
                        library.setStudentCount(library.getStudentCount()+1);
                        Student newStudent = new Student(library.getStudentCount(),StudentName,StudentRollNo,InstitutionName);
                        library.addStudent(newStudent);
                        System.out.println("Congratulations! You are registered!");
                        System.out.println("Your Student Id is "+library.getStudentCount());
                        String password;
                        System.out.print("Enter Your Password (which You will use for issuing and returning Book): ");
                        password = sc.nextLine();
                        newStudent.setPassword(password);
                        System.out.println("Password Set Successfully! \nRegistration is Completed!");
                    }
                    break;
                case 7:
                    library.displayRegisteredStudents();
                    break;
                case 8:
                    int bookIdtoDelete = readInt(sc,"Enter Book Id: ");
                    library.deleteBook(bookIdtoDelete);
                    break;
                case 9:
                    int updatechoices;
                    do{
                        System.out.println("========= MENU =========");
                        System.out.println("1. Change Title\n2. Change Author");
                        int updatechoice = readInt(sc, "Enter A Number(1-2): ");
                        switch (updatechoice) {
                            case 1:
                                int bookIdtoUpdate = readInt(sc,"Enter Book Id: ");
                                System.out.print("Enter New Title: ");
                                String newTitle = sc.nextLine();
                                library.updateBookTitle(bookIdtoUpdate, newTitle);
                                break;
                            case 2:
                                int bookIdtoUpdate1 = readInt(sc,"Enter Book Id: ");
                                System.out.print("Enter New Author: ");
                                String newAuthor = sc.nextLine();
                                library.updateBookAuthor(bookIdtoUpdate1, newAuthor);
                                break;
                            default:
                                System.out.println("Wrong Value Entered!");
                                break;
                        }
                        updatechoices = readInt(sc, "Want to Continue, Press 1: ");
                    } while(updatechoices == 1);

                    break;
                default:
                    System.out.println("Not A Valid Number!");
                    break;
            }
            choices = readInt(sc, "Want to Continue, Press 1: ");
        }while(choices == 1);
        sc.close();
    }
}