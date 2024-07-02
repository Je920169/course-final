use course;
drop table if exists choose_records;
drop table if exists courses;
drop table if exists students;
drop table if exists teachers;



CREATE TABLE IF NOT EXISTS students (
    id INT PRIMARY KEY,
    name VARCHAR(45),
    email VARCHAR(60),
    password VARCHAR(200),
	grade VARCHAR(45),
    major VARCHAR(45)
);

CREATE TABLE IF NOT EXISTS teachers (
    id INT PRIMARY KEY,
    name VARCHAR(45),
	email VARCHAR(60),
    password VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    subject VARCHAR(45),
    teacherid INT,
    coursetype VARCHAR(45),
    place VARCHAR(45),
    time VARCHAR(60),
    quota INT,
    credits INT,
    remark VARCHAR(200),
    FOREIGN KEY (teacherid) REFERENCES teachers(id)
);

CREATE TABLE IF NOT EXISTS choose_records (
    id INT PRIMARY KEY,
    studentid INT,
	teacherid INT,
    courseid INT,
    credits INT,
    choosetime TIMESTAMP,
    action VARCHAR(45),
    FOREIGN KEY (studentid) REFERENCES students(id),
    FOREIGN KEY (courseid) REFERENCES courses(id)
);

ALTER TABLE choose_records MODIFY COLUMN id INT AUTO_INCREMENT;

INSERT INTO students (id, name, email, password, grade, major) VALUES (1, 'Alice', 'alice@gmail.com', 'password123', 'Freshman', 'Computer Science');
INSERT INTO students (id, name, email, password, grade, major) VALUES (2, 'Dennis', 'dennis@gmail.com', 'password123', 'Sophomore', 'Mathematics');
INSERT INTO students (id, name, email, password, grade, major) VALUES (3, 'Emily', 'emily@gmail.com', 'password123', 'Junior', 'Physics');
INSERT INTO students (id, name, email, password, grade, major) VALUES (4, 'Frank', 'frank@gmail.com', 'password123', 'Senior', 'Chemistry');


INSERT INTO teachers (id, name, email, password) VALUES (1, 'Dr. Allen', 'allen@gmail.com', 'password123');
INSERT INTO teachers (id, name, email, password) VALUES (2, 'Dr. Johnson', 'johnson@gmail.com', 'password123');
INSERT INTO teachers (id, name, email, password) VALUES (3, 'Dr. Smith', 'smith@gmail.com', 'password123');
INSERT INTO teachers (id, name, email, password) VALUES (4, 'Dr. Brown', 'brown@gmail.com', 'password123');
INSERT INTO teachers (id, name, email, password) VALUES (5, 'Dr. James', 'james@gmail.com', 'password123');
INSERT INTO teachers (id, name, email, password) VALUES (6, 'Dr. Green', 'green@gmail.com', 'password123');



INSERT INTO courses (id, subject, teacherid, coursetype, place, time, quota, credits, remark) VALUES (1, 'Introduction to Computer Science', 1, '必修', 'Room 101', 'Tuesday 3-4', 40, 2, 'Required for CS majors');
INSERT INTO courses (id, subject, teacherid, coursetype, place, time, quota, credits, remark) VALUES (2, 'Calculus I', 2, '必修', 'Room 102', 'Wednesday 1-3', 30, 3, 'Required for Math majors');
INSERT INTO courses (id, subject, teacherid, coursetype, place, time, quota, credits, remark) VALUES (3, 'Introduction to Art', 3, '選修', 'Room 103', 'Monday 5-6', 20, 2, 'Elective course for all majors');
INSERT INTO courses (id, subject, teacherid, coursetype, place, time, quota, credits, remark) VALUES (4, 'Basic Cooking', 4, '選修', 'Room 104', 'Thursday 5-6', 25, 2, 'Elective course for all majors');
INSERT INTO courses (id, subject, teacherid, coursetype, place, time, quota, credits, remark) VALUES (5, 'Advanced Physics', 5, '必修', 'Room 105', 'Friday 3-4', 35, 2, 'Required for Physics majors');
INSERT INTO courses (id, subject, teacherid, coursetype, place, time, quota, credits, remark) VALUES (6, 'Organic Chemistry', 6, '必修', 'Room 106', 'Monday 6-8', 30, 3, 'Required for Chemistry majors');
INSERT INTO courses (id, subject, teacherid, coursetype, place, time, quota, credits, remark) VALUES (7, 'Java', 2, '必修', 'Room 107', 'Wednesday 6-8', 35, 3, 'Required for Information Management majors');


INSERT INTO choose_records (id, studentid, teacherid, courseid, credits, choosetime, action) VALUES (1, 1, 1, 1, 2, '2024-01-15 08:00:00', 'Enroll');
INSERT INTO choose_records (id, studentid, teacherid, courseid, credits, choosetime, action) VALUES (2, 2, 2, 2, 3, '2024-01-15 08:10:00', 'Enroll');
INSERT INTO choose_records (id, studentid, teacherid, courseid, credits, choosetime, action) VALUES (3, 1, 3, 3, 2, '2024-01-16 09:00:00', 'Enroll');
INSERT INTO choose_records (id, studentid, teacherid, courseid, credits, choosetime, action) VALUES (4, 2, 4, 4, 2, '2024-01-16 09:15:00', 'Enroll');
INSERT INTO choose_records (id, studentid, teacherid, courseid, credits, choosetime, action) VALUES (5, 3, 3, 3, 2, '2024-01-17 10:00:00', 'Enroll');
INSERT INTO choose_records (id, studentid, teacherid, courseid, credits, choosetime, action) VALUES (6, 3, 4, 4, 2, '2024-01-17 10:30:00', 'Enroll');
INSERT INTO choose_records (id, studentid, teacherid, courseid, credits, choosetime, action) VALUES (7, 4, 4, 4, 2, '2024-01-18 11:00:00', 'Enroll');
INSERT INTO choose_records (id, studentid, teacherid, courseid, credits, choosetime, action) VALUES (8, 4, 3, 3, 2, '2024-01-18 11:30:00', 'Enroll');
INSERT INTO choose_records (id, studentid, teacherid, courseid, credits, choosetime, action) VALUES (9, 3, 5, 5, 2, '2024-01-19 10:00:00', 'Enroll');
INSERT INTO choose_records (id, studentid, teacherid, courseid, credits, choosetime, action) VALUES (10, 4, 6, 6, 3, '2024-01-19 10:30:00', 'Enroll');