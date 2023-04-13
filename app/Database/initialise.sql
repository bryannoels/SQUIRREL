DROP DATABASE IF EXISTS SQUIRREL; 
CREATE DATABASE SQUIRREL;
USE SQUIRREL;

DROP TABLE IF EXISTS students;
CREATE TABLE students (
    student_id int NOT NULL,
    student_name varchar(127),
    phone_number varchar(31),
    student_age int,
    student_password varchar(31),
    PRIMARY KEY (student_id)
);

DROP TABLE IF EXISTS results;
CREATE TABLE results (
    result_id int NOT NULL,
    student_id int,
    question_id int,
    result boolean,
    PRIMARY KEY (result_id)
);

DROP TABLE IF EXISTS modules;
CREATE TABLE modules (
    module_id int NOT NULL,
    module_name varchar(63),
    module_image varchar(511),
    PRIMARY KEY (module_id)
);

DROP TABLE IF EXISTS professors;
CREATE TABLE professors (
    prof_id int NOT NULL,
    prof_name varchar(127),
    prof_email varchar(127),
    prof_password varchar(31),
    prof_phone_number varchar(31),
    PRIMARY KEY (prof_id)
);

DROP TABLE IF EXISTS questions;
CREATE TABLE questions (
    question_id int NOT NULL,
    question_number int,
    level_number int,
    topic varchar(127),
    topic_image varchar(511),
    module_id int,
    question_text varchar(255),
    question_answer int,
    PRIMARY KEY (question_id)
);

DROP TABLE IF EXISTS choices;
CREATE TABLE choices (
    choice_id int NOT NULL,
    choice_text varchar(255),
    question_id int,
    PRIMARY KEY (choice_id)
);

SHOW TABLES;  
DESCRIBE students;
DESCRIBE results;
DESCRIBE modules;
DESCRIBE professors;
DESCRIBE questions;
DESCRIBE choices;


