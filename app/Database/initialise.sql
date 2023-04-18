DROP DATABASE IF EXISTS SQUIRREL; 
CREATE DATABASE SQUIRREL;
USE SQUIRREL;

DROP TABLE IF EXISTS students;
CREATE TABLE students (
    student_id int NOT NULL AUTO_INCREMENT,
    student_name varchar(127),
    phone_number varchar(31),
    student_age int,
    student_password varchar(31),
    PRIMARY KEY (student_id)
);

DROP TABLE IF EXISTS results;
CREATE TABLE results (
    result_id int NOT NULL AUTO_INCREMENT,
    student_id int,
    question_id int,
    result boolean,
    PRIMARY KEY (result_id)
);

DROP TABLE IF EXISTS modules;
CREATE TABLE modules (
    module_id int NOT NULL AUTO_INCREMENT,
    module_name varchar(63),
    module_image varchar(511),
    PRIMARY KEY (module_id)
);

DROP TABLE IF EXISTS professors;
CREATE TABLE professors (
    prof_id int NOT NULL AUTO_INCREMENT,
    prof_name varchar(127),
    prof_email varchar(127),
    prof_password varchar(31),
    prof_phone_number varchar(31),
    PRIMARY KEY (prof_id)
);

DROP TABLE IF EXISTS questions;
CREATE TABLE questions (
    question_id int NOT NULL AUTO_INCREMENT,
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
    choice_id int NOT NULL AUTO_INCREMENT,
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

insert into students (student_name, phone_number, student_age, student_password) values ('bryan', '81234567', 19, 'password');
insert into students (student_name, phone_number, student_age, student_password) values ('bryno', '81231233', 19, 'abc');
insert into results (student_id, question_id, result) values ('1','1',1);
insert into results (student_id, question_id, result) values ('1','2',1);
insert into results (student_id, question_id, result) values ('1','3',1);
insert into results (student_id, question_id, result) values ('2','1',1);
insert into results (student_id, question_id, result) values ('2','2',0);
insert into results (student_id, question_id, result) values ('2','3',0);
insert into modules (module_name, module_image) values ('IM1003 Object Oriented Programming', 'IM1003.jpg');
insert into modules (module_name, module_image) values ('IE2110 Signals and Systems', 'IE2110.jpg');
insert into modules (module_name, module_image) values ('IE3017 Computer Communications', 'IE3017.jpg');
insert into professors (prof_name, prof_email, prof_password, prof_phone_number) values ('prof A', 'a@ntu.edu.sg', 'a123xyz', '81212128');
insert into professors (prof_name, prof_email, prof_password, prof_phone_number) values ('prof B', 'b@ntu.edu.sg', 'b123xyz', '81212129');

insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('1','1','Introduction to Java', 'IM1003a.jpg', '1','What is the keyword to compile java code into java class?',1);
insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('2','1','Introduction to Java', 'IM1003a.jpg', '1','Which of these is not a primitive data type in Java?',2);
insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('3','1','Introduction to Java', 'IM1003a.jpg', '1','Which operator is equivalent to logical OR?',1);
insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('1','2','OOP Composition, Inheritence, and Polymorphism', 'IM1003b.jpg', '1','What is the keyword to define an inherited subclass in Java OOP?',4);
insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('2','2','OOP Composition, Inheritence, and Polymorphism', 'IM1003b.jpg', '1','What is the UML notation to represent composition?',1);
insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('3','2','OOP Composition, Inheritence, and Polymorphism', 'IM1003b.jpg', '1','What is substituting a subclass instance for its superclass called?',3);
insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('1','3','Graphical User Interface', 'IM1003c.jpg', '1','Which of these is not top-level containers?',2);
insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('2','3','Graphical User Interface', 'IM1003c.jpg', '1','What is the method when the mouse-button has been clicked?',2);
insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('3','3','Graphical User Interface', 'IM1003c.jpg', '1','How many zones are there in Border Layout?',4);

insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('1','1','Time-domain behaviors', 'IE2110a.jpg', '2','Determine the power level of the signal  x(t) = 3',3);
insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('2','1','Time-domain behaviors', 'IE2110a.jpg', '2','Given x[n] = 2 rect[n/2] and y[n] = x[n] * x[n], what is the peak value of y[n]?',4);
insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('3','1','Time-domain behaviors', 'IE2110a.jpg', '2','Determine the duration of the cross correlation function between the signals x(t) = rect(t/4) and y(t) = rect(t)',2);
insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('1','2','Frequency-domain behaviors', 'IE2110b.jpg', '2','The fundamental frequency of the signal x(t) = cos(2000πt) + cos(10000πt) is',1);
insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('2','2','Frequency-domain behaviors', 'IE2110b.jpg', '2','A periodic signal x(t) can be expressed as a Fourier series in 3 different forms. Given that the Fourier coefficients a1 = 2 and b1 = 0 in trigonometric form, c1 for complex exponential form is equal to',2);
insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('3','2','Frequency-domain behaviors', 'IE2110b.jpg', '2','Given a sampling frequency of fs Hz, determine the maximum bandwidth of the signal that can be sampled without causing aliasing.',3);
insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('1','3','Amplitude Modulation', 'IE2110c.jpg', '2','An amplitude modulated signal is given by x_AM (t) = 3 {1+ sin(100πt)} cos(5000 πt). What is the frequency of the carrier?',2);
insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('2','3','Amplitude Modulation', 'IE2110c.jpg', '2','An amplitude modulated signal is given by x_AM (t) = [3 + 6 cos(100πt)] cos(10000πt). What is the modulation index for x_AM (t)?',2);
insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('3','3','Amplitude Modulation', 'IE2110c.jpg', '2','An amplitude modulated signal is given by x_AM (t) = 3 {1 + cos(100πt)} cos(5000πt). If the receiver side uses envelope detector to demodulate the signal, what is the bandwidth of the envelop detector output?',4);

insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('1','1','Data Communications Fundamentals', 'IE3017a.jpg', '3','What is the measure of the loss of signal strength called?',3);
insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('2','1','Data Communications Fundamentals', 'IE3017a.jpg', '3','Which theorem dictates the maximum data rate of a transmission channel given the bandwidth, B of the transmission channel and the SNR of the channel?',2);
insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('3','1','Data Communications Fundamentals', 'IE3017a.jpg', '3','Which scramble code count the number of bipolar bits between last V and 0000?',3);
insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('1','2','Data Link Layer', 'IE3017b.jpg', '3','How does HDLC prevent occurrence of flag pattern 01111110 inside the frame?',1);
insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('2','2','Data Link Layer', 'IE3017b.jpg', '3','What is the standard generator polynomial used in IEEE 802?',4);
insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('3','2','Data Link Layer', 'IE3017b.jpg', '3','Which ARQ scheme is used in UDP?',1);
insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('1','3','Network and Transport Layer', 'IE3017c.jpg', '3','What is the IP Address prefix for loopback testing?',2);
insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('2','3','Network and Transport Layer', 'IE3017c.jpg', '3','Which of these terms allows efficient advertisement of routing information.',3);
insert into questions (question_number, level_number, topic, topic_image, module_id, question_text, question_answer) values ('3','3','Network and Transport Layer', 'IE3017c.jpg', '3','Which transport layer protocol uses three-way handshake?',1);

insert into choices (choice_text, question_id) values ('javac', 1); /**/
insert into choices (choice_text, question_id) values ('java', 1);
insert into choices (choice_text, question_id) values ('run', 1);
insert into choices (choice_text, question_id) values ('build', 1);

insert into choices (choice_text, question_id) values ('int', 2);
insert into choices (choice_text, question_id) values ('String', 2); /**/
insert into choices (choice_text, question_id) values ('byte', 2);
insert into choices (choice_text, question_id) values ('char', 2);

insert into choices (choice_text, question_id) values ('||', 3); /**/
insert into choices (choice_text, question_id) values ('|', 3); 
insert into choices (choice_text, question_id) values ('^', 3);
insert into choices (choice_text, question_id) values ('/', 3);

insert into choices (choice_text, question_id) values ('implements', 4); 
insert into choices (choice_text, question_id) values ('override', 4); 
insert into choices (choice_text, question_id) values ('super', 4);
insert into choices (choice_text, question_id) values ('extends', 4); /**/

insert into choices (choice_text, question_id) values ('diamond-head line', 5); /**/
insert into choices (choice_text, question_id) values ('hollow arrowhead', 5); 
insert into choices (choice_text, question_id) values ('italic', 5);
insert into choices (choice_text, question_id) values ('dash-arrow', 5); 

insert into choices (choice_text, question_id) values ('polymorphism', 6); 
insert into choices (choice_text, question_id) values ('encapsulation', 6); 
insert into choices (choice_text, question_id) values ('upcasting', 6); /**/
insert into choices (choice_text, question_id) values ('downcasting', 6); 

insert into choices (choice_text, question_id) values ('frame', 7); 
insert into choices (choice_text, question_id) values ('panel', 7); /**/
insert into choices (choice_text, question_id) values ('dialog', 7); 
insert into choices (choice_text, question_id) values ('applet', 7); 

insert into choices (choice_text, question_id) values ('mouseDragged', 8); 
insert into choices (choice_text, question_id) values ('mouseClicked', 8); /**/
insert into choices (choice_text, question_id) values ('mousePressed', 8); 
insert into choices (choice_text, question_id) values ('mouseEntered', 8); 

insert into choices (choice_text, question_id) values ('2', 9); 
insert into choices (choice_text, question_id) values ('3', 9); 
insert into choices (choice_text, question_id) values ('4', 9); 
insert into choices (choice_text, question_id) values ('5', 9); /**/

insert into choices (choice_text, question_id) values ('3', 10); 
insert into choices (choice_text, question_id) values ('-3', 10); 
insert into choices (choice_text, question_id) values ('9', 10); /**/
insert into choices (choice_text, question_id) values ('infinite', 10); 

insert into choices (choice_text, question_id) values ('0', 11); 
insert into choices (choice_text, question_id) values ('2', 11); 
insert into choices (choice_text, question_id) values ('4', 11); 
insert into choices (choice_text, question_id) values ('none of the above', 11); /**/

insert into choices (choice_text, question_id) values ('4s', 12); 
insert into choices (choice_text, question_id) values ('5s', 12); /**/
insert into choices (choice_text, question_id) values ('8s', 12); 
insert into choices (choice_text, question_id) values ('none of the above', 12); 

insert into choices (choice_text, question_id) values ('1 kHz', 13); /**/
insert into choices (choice_text, question_id) values ('2 kHz', 13); 
insert into choices (choice_text, question_id) values ('4 kHz', 13); 
insert into choices (choice_text, question_id) values ('10 kHz', 13); 

insert into choices (choice_text, question_id) values ('0', 14); 
insert into choices (choice_text, question_id) values ('1', 14); /**/
insert into choices (choice_text, question_id) values ('j', 14); 
insert into choices (choice_text, question_id) values ('1+j', 14); 

insert into choices (choice_text, question_id) values ('0.1 fs Hz', 15); 
insert into choices (choice_text, question_id) values ('0.37 fs Hz', 15); 
insert into choices (choice_text, question_id) values ('0.5 fs Hz', 15); /**/
insert into choices (choice_text, question_id) values ('fs Hz', 15); 

insert into choices (choice_text, question_id) values ('1000 Hz', 16); 
insert into choices (choice_text, question_id) values ('2500 Hz', 16); /**/
insert into choices (choice_text, question_id) values ('5000 Hz', 16); 
insert into choices (choice_text, question_id) values ('15000 Hz', 16); 

insert into choices (choice_text, question_id) values ('3', 17); 
insert into choices (choice_text, question_id) values ('2', 17); /**/
insert into choices (choice_text, question_id) values ('1', 17); 
insert into choices (choice_text, question_id) values ('0.5', 17); 

insert into choices (choice_text, question_id) values ('10000 Hz', 18); 
insert into choices (choice_text, question_id) values ('5000 Hz', 18); 
insert into choices (choice_text, question_id) values ('100 Hz', 18); 
insert into choices (choice_text, question_id) values ('50 Hz', 18); /**/

insert into choices (choice_text, question_id) values ('modulation rate', 19); 
insert into choices (choice_text, question_id) values ('channel capacity', 19); 
insert into choices (choice_text, question_id) values ('channel attenuation', 19); /**/
insert into choices (choice_text, question_id) values ('noise power', 19); 

insert into choices (choice_text, question_id) values ('Nyquist theorem', 20); 
insert into choices (choice_text, question_id) values ('Shannon theorem', 20); /**/
insert into choices (choice_text, question_id) values ('Baud theorem', 20); 
insert into choices (choice_text, question_id) values ('Bochner theorem', 20); 

insert into choices (choice_text, question_id) values ('NRZ', 21); 
insert into choices (choice_text, question_id) values ('AMI', 21); 
insert into choices (choice_text, question_id) values ('HDB3', 21); /**/
insert into choices (choice_text, question_id) values ('B8ZS', 21); 

insert into choices (choice_text, question_id) values ('bit stuffing', 22); /**/
insert into choices (choice_text, question_id) values ('full duplex', 22); 
insert into choices (choice_text, question_id) values ('flow control', 22); 
insert into choices (choice_text, question_id) values ('error control', 22); 

insert into choices (choice_text, question_id) values ('CRC 8', 23); 
insert into choices (choice_text, question_id) values ('CRC 16', 23); 
insert into choices (choice_text, question_id) values ('CCITT 16', 23); 
insert into choices (choice_text, question_id) values ('CCITT 32', 23); /**/

insert into choices (choice_text, question_id) values ('Stop and Wait', 24); /**/
insert into choices (choice_text, question_id) values ('Go back N', 24); 
insert into choices (choice_text, question_id) values ('selective repeat', 24); 
insert into choices (choice_text, question_id) values ('out of sequence', 24); 

insert into choices (choice_text, question_id) values ('63', 25); 
insert into choices (choice_text, question_id) values ('127', 25); /**/
insert into choices (choice_text, question_id) values ('255', 25); 
insert into choices (choice_text, question_id) values ('65535', 25); 

insert into choices (choice_text, question_id) values ('subnetting', 26); 
insert into choices (choice_text, question_id) values ('classful addressing', 26); 
insert into choices (choice_text, question_id) values ('route aggregation', 26); /**/
insert into choices (choice_text, question_id) values ('DHCP', 26); 

insert into choices (choice_text, question_id) values ('TCP', 27); /**/
insert into choices (choice_text, question_id) values ('IP', 27); 
insert into choices (choice_text, question_id) values ('UDP', 27); 
insert into choices (choice_text, question_id) values ('HTTP', 27); 

select * from students;
select * from results;
select * from modules;
select * from professors;
select * from questions;
select * from choices;