package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.po.Students;

@Repository
public class StudentsDaoImpl implements StudentsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public int addStudent(Students student) {
		String sql = "INSERT INTO students (id, name, email, password, grade, major) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, student.getId(), student.getName(), student.getEmail(), student.getPassword(), student.getGrade(), student.getMajor());
    }

	@Override
	public int updateStudent(Integer id, Students student) {
		  String sql = "UPDATE students SET name = ?, email = ?, password = ?, grade = ?, major = ? WHERE id = ?";
	        return jdbcTemplate.update(sql, student.getName(), student.getEmail(), student.getPassword(), student.getGrade(), student.getMajor(), id);
	    }

	@Override
	public int deleteStudent(Integer id) {
		  String sql = "DELETE FROM students WHERE id = ?";
	        return jdbcTemplate.update(sql, id);
	    }

	@Override
	public Students getStudentById(Integer id) {
		 String sql = "SELECT * FROM students WHERE id = ?";
	        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Students.class), id);
	    }

	@Override
	public List<Students> findAllStudents() {
		 String sql = "SELECT * FROM students";
	        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Students.class));
	    }

	@Override
	public Students findStudentByEmail(String email) {
		 String sql = "SELECT * FROM students WHERE email = ?";
	        try {
	            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Students.class), email);
	        } catch (Exception e) {
	            return null;
	        }
	}
    
}