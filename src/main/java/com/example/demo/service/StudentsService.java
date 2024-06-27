package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.StudentsDao;
import com.example.demo.model.po.Students;

@Service
public class StudentsService {
	
	@Autowired
	private StudentsDao studentsDao;
	
	public int addStudent(Students student) {
		return studentsDao.addStudent(student);	
	}
	
	public int updateStudent(Integer id, Students student) {
		return studentsDao.updateStudent(id, student);
	}
	
	public int deleteStudent(Integer id) {
		return studentsDao.deleteStudent(id);
	}
	
	public Students getStudentById(Integer id) {
		return studentsDao.getStudentById(id);
	}
	
	public List<Students> findAllStudents() {
		return studentsDao.findAllStudents();
	}
}
