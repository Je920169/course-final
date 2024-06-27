package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.po.Courses;

public interface CoursesDao {
   int addCourses(Courses courses);
   int updateCourses(Integer id, Courses courses);
   int deleteCourses(Integer id);
   Courses getCoursesById(Integer id);
   List<Courses> findAllCourses();
}
