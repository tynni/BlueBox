CREATE DATABASE IF NOT EXISTS BlueBox;
USE BlueBox;

CREATE TABLE Movies (
    MovieID INT AUTO_INCREMENT PRIMARY KEY,
    Title VARCHAR(100),
    Production VARCHAR(100),
    Length INT,
    Description TEXT,
    Cast TEXT,
    RentalDate DATE,
    ExpectedReturnDate DATE
);

INSERT INTO Movies (Title, Production, Length, Description, Cast, RentalDate, ExpectedReturnDate) VALUES
('Mean Girls', 'Paramount Pictures', 97, 'A teenager navigates the social hierarchy of high school.', 'Lindsay Lohan, Rachel McAdams', '2025-04-01', '2025-04-08'),
('White Chicks', 'Revolution Studios', 109, 'FBI agents go undercover as women to solve a kidnapping case.', 'Shawn Wayans, Marlon Wayans', '2025-04-03', '2025-04-10'),
('Black Swan', 'Fox Searchlight Pictures', 108, 'A ballerina descends into madness as she prepares for the role of a lifetime.', 'Natalie Portman, Mila Kunis', '2025-04-05', '2025-04-12'),
('Get Out', 'Blumhouse Productions', 104, 'A Black man uncovers a disturbing secret when meeting his white girlfriend’s family.', 'Daniel Kaluuya, Allison Williams', '2025-04-07', '2025-04-14'),
('Baby’s Day Out', '20th Century Fox', 99, 'A baby escapes from kidnappers and explores the city on his own.', 'Joe Mantegna, Lara Flynn Boyle', '2025-04-02', '2025-04-09'),
('The Wild Robot', 'DreamWorks Animation', 95, 'A robot shipwrecked on an island learns to survive and raise a gosling.', 'Stephanie Hsu, Pedro Pascal', '2025-04-10', '2025-04-17'),
('Forrest Gump', 'Paramount Pictures', 142, 'The story of a slow-witted man who witnesses key historical moments.', 'Tom Hanks, Robin Wright', '2025-04-06', '2025-04-13'),
('Princess Mononoke', 'Studio Ghibli', 134, 'A young warrior gets caught in a battle between nature and industry.', 'Yoji Matsuda, Yuriko Ishida', '2025-04-09', '2025-04-16'),
('The Wind Rises', 'Studio Ghibli', 126, 'An engineer pursues his dream of building airplanes.', 'Hideaki Anno, Miori Takimoto', '2025-04-08', '2025-04-15'),
('Inception', 'Warner Bros.', 148, 'A thief enters dreams to plant an idea into a target’s subconscious.', 'Leonardo DiCaprio, Joseph Gordon-Levitt', '2025-04-04', '2025-04-11'),
('Us', 'Universal Pictures', 116, 'A family is attacked by their sinister doppelgängers.', 'Lupita Nyong’o, Winston Duke', '2025-04-11', '2025-04-18'),
('Avengers: Endgame', 'Marvel Studios', 181, 'The Avengers assemble one last time to undo the damage done by Thanos.', 'Robert Downey Jr., Chris Evans', '2025-04-01', '2025-04-08'),
('Saving Private Ryan', 'DreamWorks', 169, 'Soldiers go behind enemy lines to save a paratrooper.', 'Tom Hanks, Matt Damon', '2025-04-03', '2025-04-10'),
('Rush Hour', 'New Line Cinema', 98, 'A detective from Hong Kong teams up with an LAPD officer.', 'Jackie Chan, Chris Tucker', '2025-04-05', '2025-04-12'),
('The Godfather', 'Paramount Pictures', 175, 'The aging patriarch of a crime family transfers control to his reluctant son.', 'Marlon Brando, Al Pacino', '2025-04-06', '2025-04-13');

CREATE TABLE Customers (
    CustomerID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100),
    Contact VARCHAR(100)
);

INSERT INTO Customers (Name, Contact) VALUES
('Alice Kim', 'alice.kim@email.com'),
('Brian Lee', 'brian.lee@email.com'),
('Carmen Diaz', 'carmen.diaz@email.com'),
('Daniel Wu', 'daniel.wu@email.com'),
('Ella Zhang', 'ella.zhang@email.com'),
('Felix Mendoza', 'felix.m@email.com'),
('Grace Liu', 'grace.liu@email.com'),
('Hassan Ali', 'hassan.ali@email.com'),
('Isabelle Tran', 'isabelle.tran@email.com'),
('Jack Murphy', 'jack.murphy@email.com'),
('Karla Gomez', 'karla.gomez@email.com'),
('Leo Chen', 'leo.chen@email.com'),
('Maya Patel', 'maya.patel@email.com'),
('Noah Davis', 'noah.davis@email.com'),
('Olivia Knight', 'olivia.knight@email.com');

CREATE TABLE MovieRentalStaff (
    StaffID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100)
);

INSERT INTO MovieRentalStaff (Name) VALUES
('Samantha Hill'),
('George Lin'),
('Karen Okafor'),
('Marcus Reed'),
('Fatima Khan'),
('Terry Zhang'),
('Rebecca Smith'),
('Diego Morales'),
('Hannah Brown'),
('Ivan Novak'),
('Priya Singh'),
('William Grant'),
('Natalie Chen'),
('Ethan Lee'),
('Luna Park');

CREATE TABLE Status (
    MovieID INT PRIMARY KEY,
    Status ENUM('Rented', 'Returned', 'Overdue'),
    StaffID INT,
    FOREIGN KEY (MovieID) REFERENCES Movies(MovieID),
    FOREIGN KEY (StaffID) REFERENCES MovieRentalStaff(StaffID)
);

INSERT INTO Status (MovieID, Status, StaffID) VALUES
 (1,  'Rented',   5),
 (2,  'Returned', 2),
 (3,  'Returned', 3),
 (4,  'Rented',   8),
 (5,  'Overdue',  1),
 (6,  'Rented',   6),
 (7,  'Returned', 7),
 (8,  'Rented',   4),
 (9,  'Overdue',  9),
 (10, 'Returned', 10),
 (11, 'Rented',   11),
 (12, 'Returned', 12),
 (13, 'Returned', 13),
 (14, 'Overdue',  14),
 (15, 'Rented',   15);

CREATE TABLE Rents (
    RentID INT AUTO_INCREMENT PRIMARY KEY,
    MovieID INT,
    CustomerID INT,
    Duration INT, -- in days
    Price DECIMAL(5, 2),
    FOREIGN KEY (MovieID) REFERENCES Movies(MovieID),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

INSERT INTO Rents (MovieID, CustomerID, Duration, Price) VALUES
(1, 1, 7, 3.99),
(2, 2, 5, 2.99),
(3, 3, 4, 3.49),
(4, 4, 7, 4.49),
(5, 5, 10, 2.50),
(6, 6, 3, 2.75),
(7, 7, 5, 3.00),
(8, 8, 6, 3.25),
(9, 9, 7, 3.95),
(10, 10, 4, 3.80),
(11, 11, 8, 4.25),
(12, 12, 6, 3.10),
(13, 13, 5, 4.00),
(14, 14, 7, 2.99),
(15, 15, 9, 3.75);

CREATE TABLE Ratings (
    CustomerID INT,
    MovieID INT,
    Rating INT CHECK (Rating BETWEEN 1 AND 5),
    PRIMARY KEY (CustomerID, MovieID),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
    FOREIGN KEY (MovieID) REFERENCES Movies(MovieID)
);

INSERT INTO Ratings (CustomerID, MovieID, Rating) VALUES
(1, 1, 5),
(2, 2, 4),
(3, 3, 5),
(4, 4, 3),
(5, 5, 4),
(6, 6, 2),
(7, 7, 5),
(8, 8, 4),
(9, 9, 3),
(10, 10, 5),
(11, 11, 4),
(12, 12, 5),
(13, 13, 4),
(14, 14, 5),
(15, 15, 5);
