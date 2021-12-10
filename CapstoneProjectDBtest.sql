DROP DATABASE IF EXISTS CapstoneProjectDBtest;
CREATE DATABASE CapstoneProjectDBtest;
USE CapstoneProjectDBtest;

CREATE TABLE user (
    user_Id INT PRIMARY KEY AUTO_INCREMENT,
    first_Name varchar(25),
    last_Name varchar(25),
    email varchar(50),
    password varchar(25)
);
    
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

CREATE TABLE postHashtag (
    post_Id INT NOT NULL,
    hashtag_Id INT NOT NULL,
    PRIMARY KEY (post_Id, hashtag_Id),
    FOREIGN KEY (post_Id) REFERENCES post(post_Id),
    FOREIGN KEY (hashtag_Id) REFERENCES hashtag(hashtag_Id)
);

SELECT * FROM `user`;
SELECT * FROM `post`;
SELECT * FROM `hashtag`;
SELECT * FROM `postHashtag`;