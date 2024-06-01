# Flight Ticket Booking Application

This is a console-based flight ticket booking application written in Java. The application supports both user and admin functionalities, allowing users to search for flights and book tickets, and admins to add new flights and view bookings.


## Features

### User Features
- **Login**: Users can log in with their credentials.
- **Signup**: New users can sign up for an account.
- **Search Flights**: Users can search for flights using a keyword.
- **Book Tickets**: Users can book tickets for available flights.
- **Logout**: Users can log out from the application.

### Admin Features
- **Login**: Admins can log in with their admin credentials.
- **Add Flights**: Admins can add new flights to the system.
- **View Bookings**: Admins can view all flight bookings.
- **Logout**: Admins can log out from the application.

## Setup

### Prerequisites
- Java Development Kit (JDK) 21 or higher
- Maven
- MySQL database

### Database Setup

ERD:
![flight_booking](https://github.com/sandeepshekhawat/FlightX/assets/82081109/d79f95da-839b-4f11-8f18-432aaa0c93ec)

1. Create a MySQL database named `flight_booking`.
2. Create the necessary tables using the following SQL scripts:
    ```sql
    CREATE TABLE Users (
        id INT AUTO_INCREMENT PRIMARY KEY,
        username VARCHAR(50) NOT NULL,
        password VARCHAR(50) NOT NULL,
        role ENUM('user', 'admin') NOT NULL
    );

    CREATE TABLE Flights (
        id INT AUTO_INCREMENT PRIMARY KEY,
        flight_name VARCHAR(100) NOT NULL,
        flight_number VARCHAR(100) NOT NULL,
        date DATE NOT NULL,
        seats_available INT NOT NULL DEFAULT 60
    );

    CREATE TABLE Bookings (
        id INT AUTO_INCREMENT PRIMARY KEY,
        user_id INT,
        flight_id INT,
        date DATE NOT NULL,
        seat_count INT NOT NULL,
        FOREIGN KEY (user_id) REFERENCES Users(id),
        FOREIGN KEY (flight_id) REFERENCES Flights(id)
    );
    ```

### Project Setup
1. Clone the repository:
    ```bash
    git clone git@github.com:sandeepshekhawat/FlightX.git
    ```
2. Update the `DBConnection` class with your MySQL database credentials.
    ```java
    package org.sandeep.dao;

    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;

    public class DBConnection {
        public static Connection getConnection() throws SQLException {
            String url = "jdbc:mysql://localhost:3306/flight_booking";
            String username = "your-username";
            String password = "your-password";
            return DriverManager.getConnection(url, username, password);
        }
    }
    ```
3. Build the project using Maven:
    ```bash
    mvn clean install
    ```

4. Run the application:
    ```bash
    mvn exec:java -Dexec.mainClass="org.sandeep.Main"
    ```

## Usage
1. Start the application and choose between User Login, User Signup, Admin Login, or Exit.
2. Follow the on-screen instructions to navigate through the application's features.

