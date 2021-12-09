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
	(2, "John", "Doe", "johndoe@email.com", "password");

CREATE TABLE post (
	post_Id INT PRIMARY KEY AUTO_INCREMENT,
    	user_Id INT NOT NULL,
    	post_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	scheduled_date DATETIME,
	expiration_date DATETIME,
	expired BOOLEAN DEFAULT false,
	title VARCHAR(100),
    	content MEDIUMTEXT,
    	FOREIGN KEY fk_user_Id (user_Id) REFERENCES user(user_Id)
    );
    
CREATE TABLE hashtag (
	hashtag_Id INT PRIMARY KEY AUTO_INCREMENT,
	hashtag varchar(25)
);

INSERT INTO hashtag(hashtag) VALUES
	("cool"),
	("funny");

CREATE TABLE postHashtag (
	post_Id INT NOT NULL,
	hashtag_Id INT NOT NULL,
	PRIMARY KEY (post_Id, hashtag_Id),
	FOREIGN KEY (post_Id) REFERENCES post(post_Id),
	FOREIGN KEY (hashtag_Id) REFERENCES hashtag(hashtag_Id)
);

    
    INSERT INTO post (post_Id, user_Id, post_time, content) VALUES 
    (1, 1, "2021-12-07 13:01:00", "Jane's first post on this site!"),
    (2, 1, "2021-12-07 13:03:00", "Jane's second post on this site!"),
    (3, 1, "2021-12-07 13:05:00", "Jane's third post on this site!"),
    (4, 2, "2021-12-07 13:07:00", "John's first post on this site!");
    
INSERT INTO postHashtag (post_Id, hashtag_Id) VALUES
	(1, 1),
	(1, 2),
	(2, 2);
    
	select * from user;
    select * from post;
    SELECT * FROM hashtag;
