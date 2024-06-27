package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.po.Students;

public interface StudentsDao {
    int addStudent(Students student);
    int updateStudent(Integer id, Students student);
    int deleteStudent(Integer id);
    Students getStudentById(Integer id);
    List<Students> findAllStudents();
    Students findStudentByEmail(String email);
}