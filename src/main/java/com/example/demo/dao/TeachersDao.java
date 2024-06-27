package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.po.Teachers;

public interface TeachersDao {
    int addTeacher(Teachers teacher);
    int updateTeacher(Integer id, Teachers teacher);
    int deleteTeacher(Integer id);
    Teachers getTeacherById(Integer id);
    List<Teachers> findAllTeachers();
    Teachers findTeacherByEmail(String email);
    String findTeacherNameById(Integer id);
}