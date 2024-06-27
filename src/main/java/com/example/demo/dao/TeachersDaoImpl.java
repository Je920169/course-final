package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.po.Teachers;

@Repository
public class TeachersDaoImpl implements TeachersDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public int addTeacher(Teachers teacher) {
		  String sql = "INSERT INTO teachers (id, name, email, password) VALUES (?, ?, ?, ?)";
	      return jdbcTemplate.update(sql, teacher.getName(), teacher.getEmail(), teacher.getPassword());
	    }

	@Override
	public int updateTeacher(Integer id, Teachers teacher) {
		  String sql = "UPDATE teachers SET name = ?, email = ?, password = ? WHERE id = ?";
	      return jdbcTemplate.update(sql, teacher.getId(), teacher.getName(), teacher.getEmail(), teacher.getPassword(), id);
	    }

	@Override
	public int deleteTeacher(Integer id) {
		 String sql = "DELETE FROM teachers WHERE id = ?";
	     return jdbcTemplate.update(sql, id);
	    }

	@Override
	public Teachers getTeacherById(Integer id) {
		 String sql = "SELECT * FROM teachers WHERE id = ?";
	     return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Teachers.class), id);
	    }

	@Override
	public List<Teachers> findAllTeachers() {
		  String sql = "SELECT * FROM teachers";
	      return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Teachers.class));
	    }

	@Override
	public Teachers findTeacherByEmail(String email) {
		  String sql = "SELECT * FROM teachers WHERE email = ?";
	        try {
	            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Teachers.class), email);
	        } catch (Exception e) {
	            return null;
	        }
	}

	@Override
	public String findTeacherNameById(Integer id) {
		 String sql = "SELECT name FROM teachers WHERE id = ?";
	        try {
	            return jdbcTemplate.queryForObject(sql, String.class, id);
	        } catch (Exception e) {
	            return null;
	        }
	    }
	
}