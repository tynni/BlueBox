# BlueBox: Movie Rental System

## Project Overview

This Movie Rental System is a Java-based application designed to simulate the process of renting, returning, and rating movies. Inspired by the Redbox rental experience, it offers an intuitive interface for both customers and staff. The system supports full CRUD operations, rental tracking, and rating functionality. MySQL serves as the backend database, and the GUI was developed using Java Swing after encountering issues with a Spring Boot implementation.

## Setup Instructions

1. **Clone the Repository**

   ```bash
   git clone https://github.com/your-username/movie-rental-system.git
   cd movie-rental-system
   
2. **Steps to Link Project with MySQL Connector jar**

1. File < Project Structure < Modules < Dependencies
2. Click + icon and choose "JARs or directories"
3. Select mysql-connector-..copy,jar under lib/ folder
4. Hit Apply then Ok

3. **Set Up the MySQL Database**

CREATE DATABASE movie_rental_db;
USE movie_rental_db;

4. **Configure Database Connection in Java (DBConnection.Java)**

String url = "jdbc:mysql://localhost:3306/movie_rental_db";
String user = "your_mysql_username";
String password = "your_mysql_password";

5. **Run MainWindow.java**

## Dependencies and Required Software

Java JDK 17 or later
IntelliJ IDEA (or another Java IDE)
MySQL Server (8.0+ recommended)
MySQL JDBC Connector (added to your project libraries)
