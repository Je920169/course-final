package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.StudentsDao;
import com.example.demo.dao.TeachersDao;
import com.example.demo.model.po.Students;
import com.example.demo.model.po.Teachers;

@Service
public class AuthService {

    @Autowired
    private StudentsDao studentsDao;

    @Autowired
    private TeachersDao teachersDao;

    public Students findStudentByEmail(String email) {
        return studentsDao.findStudentByEmail(email);
    }

    public Teachers findTeacherByEmail(String email) {
        return teachersDao.findTeacherByEmail(email);
    }

    public void addStudent(Students student) {
        studentsDao.addStudent(student);
    }

    public void addTeacher(Teachers teacher) {
        teachersDao.addTeacher(teacher);
    }
}