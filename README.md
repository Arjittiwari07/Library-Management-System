📚 Library Management System

A console-based Library Management System built using Java. This project is designed to manage basic library operations such as adding books, viewing books, issuing books, and returning books.

The current version focuses on strengthening my understanding of Java, Object-Oriented Programming (OOP), encapsulation, classes, objects, and collections.

I am continuously improving this project and plan to add file handling, persistent data storage, fine calculation, transaction history, and other features in future versions.

---

🚀 Current Features

The current version of the project includes:

- 📖 Add books to the library
- 📚 Display available books
- 🔍 Search for books
- 👤 Manage library members
- 📕 Issue books to members
- 🔄 Return issued books
- 🗑️ Remove books
- 📋 Menu-driven console interface
- 🔒 Encapsulation using "private" variables and appropriate methods
- 📦 Use of Java Collections for managing data

---

🛠️ Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Java Collections Framework
- ArrayList
- VS Code

---

🧠 Java Concepts Used

This project has helped me practice several important Java concepts:

Encapsulation

Class variables have been kept private and accessed through methods where required.

class Book {
    private String title;
    private int bookId;
    private boolean available;

    // Methods to access and modify data
}

This helps protect the internal state of objects and follows good OOP practices.

Classes and Objects

The system is divided into different classes to represent real-world entities such as:

- Books
- Members
- Library
- Transactions

Collections

"ArrayList" is used to store and manage multiple objects dynamically.

Methods and Constructors

Methods are used to perform library operations, while constructors are used to initialize objects.

---

📂 Project Structure

LibraryManagementSystem/
│
├── Main.java
├── Book.java
├── Member.java
├── Library.java
├── IssueRecord.java
│
└── README.md

«The exact files/classes may change as the project continues to evolve.»

---

▶️ How to Run

1. Clone the repository

git clone <your-repository-link>

2. Open the project

Open the project in VS Code or any Java-compatible IDE.

3. Compile

javac Main.java

4. Run

java Main

---

📋 Example Menu

====== Library Management System ======

1. Add Book
2. View Books
3. Search Book
4. Add Member
5. Issue Book
6. Return Book
7. Remove Book
8. Exit

Enter your choice:

---

🔮 Future Improvements

This project is still under development. I am planning to improve it by adding the following features:

💾 File Handling

Currently, the data is handled during program execution. I plan to implement Java File Handling so that:

- Books can be saved permanently.
- Member information can be stored.
- Data can be loaded when the application starts.
- Changes are not lost when the program is closed.

🆔 Unique IDs

Add automatic generation and validation of:

- Book IDs
- Member IDs

This will help prevent duplicate records.

📅 Issue and Return Dates

Store:

- Issue date
- Expected return date
- Actual return date

💰 Fine Calculation

Implement automatic fine calculation when a book is returned after the due date.

📝 Transaction History

Maintain a record of:

- Book issued
- Member who borrowed it
- Issue date
- Return date
- Fine, if applicable

🔐 Admin/User System

Introduce different access levels such as:

- Admin – Add/remove books and manage members
- User – Search, issue and return books

🔎 Improved Search

Add more advanced searching and filtering by:

- Book title
- Author
- Book ID
- Availability

🛡️ Input Validation

Improve the system by handling:

- Invalid user input
- Duplicate IDs
- Invalid menu choices
- Attempt to issue an unavailable book
- Attempt to return a book that wasn't issued

---

📈 Project Development

This project is being developed step by step as I learn more about Java.

Current Stage

Java OOP + Collections + Basic Library Operations

Next Stage

File Handling + Persistent Data Storage

Planned Later

Validation + Transactions + Fine Calculation + Authentication + Improved Search

---

🎯 Learning Goals

Through this project, I am working on improving my understanding of:

- Java programming
- Object-Oriented Programming
- Encapsulation
- Classes and objects
- Java Collections Framework
- File Handling
- Data persistence
- Exception handling
- Software design and organization

The goal is to gradually turn this console-based application into a more complete and practical Library Management System.

---

👨‍💻 Author

Arjit Tiwari

B.Tech Information Technology
Delhi University – Cluster Innovation Centre

Currently learning Java, Data Structures & Algorithms, and Backend Development.

---

⭐ This project is actively being improved as I learn new Java concepts.
