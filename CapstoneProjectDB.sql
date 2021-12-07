DROP DATABASE IF EXISTS CapstoneProjectDB;
CREATE DATABASE CapstoneProjectDB;
USE CapstoneProjectDB;

CREATE TABLE user (
	user_Id INT PRIMARY KEY AUTO_INCREMENT,
    first_Name varchar(25),
	last_Name varchar(25),
	email varchar(50),
	password varchar(25)
    );
    
INSERT INTO user(user_Id, first_Name, last_Name, email, password) VALUES
	(1, "Jane", "Doe", "janedoe@email.com", "password"),
	(2, "John", "Doe", "johndoe@email.com", "password"),
	(3, "Hu", "Man", "human@email.com", "password");

CREATE TABLE post (
	post_Id INT PRIMARY KEY AUTO_INCREMENT,
    user_Id INT NOT NULL,
    post_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    content varchar(200),
    FOREIGN KEY fk_user_Id (user_Id) REFERENCES user(user_Id)
    );
    
    INSERT INTO post (post_Id, user_Id, post_time, content) VALUES 
    (1, 1, "2021-12-07 13:01:00", "Jane's first post on this site!"),
    (2, 1, "2021-12-07 13:03:00", "Jane's second post on this site!"),
    (3, 1, "2021-12-07 13:05:00", "Jane's third post on this site!"),
    (4, 2, "2021-12-07 13:07:00", "John's first post on this site!"),
    (5, 3, "2021-12-07 13:09:00", "Hu's first post on this site!"),
    (6, 3, "2021-12-07 13:11:00", "Hu's second post on this site!");
    
	select * from user;
    select * from post