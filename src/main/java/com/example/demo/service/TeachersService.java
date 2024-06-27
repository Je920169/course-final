package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TeachersDao;
import com.example.demo.model.po.Teachers;

@Service
public class TeachersService {
	
	@Autowired
	private TeachersDao teachersDao;
	
	public int addTeacher(Teachers teacher) {
		return teachersDao.addTeacher(teacher);
	}
	
	public int updateTeacher(Integer id, Teachers teacher) {
		return teachersDao.updateTeacher(id, teacher);
	}
	
	public int deleteTeacher(Integer id) {
		return teachersDao.deleteTeacher(id);
	}
	
	public Teachers getTeacherById(Integer id) {
		return teachersDao.getTeacherById(id);
	}
	
	public List<Teachers> findAllTeachers() {
		return teachersDao.findAllTeachers();
	}
	
	 public String getTeacherNameById(Integer id) {
        return teachersDao.findTeacherNameById(id);
	}
}
