📚 Library Management System (Version 2 – JDBC + MySQL)

A professional Library Management System built using Core Java, JDBC, and MySQL, following a layered architecture with DAO classes, custom exceptions, input validation, and transaction management.

This is Version 2 of the project. It upgrades the original file-handling version to a database-driven application using JDBC and MySQL.

🚀 Project Overview

The application allows administrators and students to manage library operations through a console-based interface. All data is stored in a MySQL database using JDBC.

✨ Features

Admin

- Secure admin registration and login.
- Add, update, delete, and search books.
- View all books and student records.
- Manage book inventory.

Student

- Student registration and login.
- Search books by title, author, category, or publisher.
- Issue and return books.
- Maximum 3 books can be issued at a time.
- View issued books and transaction history.

Library Rules

- Automatic due date generation.
- Fine calculation for late returns using "LocalDate".
- Book availability updated automatically.
- Input validation for username, password, phone number, semester, and course.

🛠️ Tech Stack

- Java 17+
- JDBC
- MySQL 8
- VS Code
- MySQL Connector/J

📂 Project Structure

- "app" – Main application.
- "model" – Book, Student, Admin, Transaction classes.
- "dao" – Database access layer.
- "service" – Library business logic.
- "util" – Database connection and utility methods.
- "exception" – Custom exception classes.

🗄️ Database

Database: "librarydb"

Main tables:

- "books"
- "students"
- "admins"
- "transactions"

⚙️ Key Concepts Used

- Object-Oriented Programming (OOP)
- Encapsulation and Abstraction
- DAO Design Pattern
- JDBC ("Connection", "PreparedStatement", "ResultSet")
- MySQL CRUD Operations
- Custom Exceptions
- Input Validation
- Date & Time API ("LocalDate")

📈 Future Versions

Version 3

- Password hashing ("java.security" / BCrypt)
- JUnit 5 testing
- Improved validation and security

Final Version

- Spring Boot
- Spring Security authentication
- REST APIs
- Hibernate / JPA
- Swagger API documentation

👨‍💻 Author

Arjit Tiwari

B.Tech Information Technology, Cluster Innovation Centre, University of Delhi.

---

⭐ This project demonstrates Java OOP, JDBC, MySQL database connectivity, and industry-style layered architecture for a real-world library management system.