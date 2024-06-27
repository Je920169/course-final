package com.example.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.dto.ChooseRecordsDto;
import com.example.demo.model.po.ChooseRecords;
import com.example.demo.model.po.Courses;
import com.example.demo.model.po.Students;


@Repository
public class ChooseRecordsDaoImpl implements ChooseRecordsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//Courses的Impl
    
	@Override
	public List<Courses> findAllCourses() {
        String sql = "SELECT c.*, t.name as teacherName FROM courses c JOIN teachers t ON c.teacherId = t.id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Courses.class));
    }
  
	
	@Override
	public int addCourses(Courses courses) {
		  String sql = "INSERT INTO courses (id, subject, teacherid, coursetype, place, time, quota, credits, remark) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        return jdbcTemplate.update(sql, courses.getId(), courses.getSubject(), courses.getTeacherId(), courses.getCourseType(), courses.getPlace(), courses.getTime(), courses.getQuota(), courses.getCredits(), courses.getRemark());
	}


	@Override
	public int updateCourses(Integer id, Courses courses) {
		 String sql = "UPDATE courses SET subject = ?, teacher_id = ?, course_type = ?, place = ?, time = ?, quota = ?, credits = ?, remark = ? WHERE id = ?";
	        return jdbcTemplate.update(sql, courses.getSubject(), courses.getTeacherId(), courses.getCourseType(), courses.getPlace(), courses.getTime(), courses.getQuota(), courses.getCredits(), courses.getRemark(), id);
	}



	@Override
	public int deleteCourses(Integer id) {
		 String sql = "DELETE FROM courses WHERE id = ?";
	        return jdbcTemplate.update(sql, id);
	    }



	@Override
	public Courses getCoursesById(Integer id) {
		 String sql = "SELECT * FROM courses WHERE id = ?";
	        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Courses.class), id);
	    }



	//ChooseRecords的Impl

	@Override
	public int addChooseRecord(ChooseRecords chooseRecord) {
		String sql = "INSERT INTO choose_records (studentid, teacherid, courseid, credits, choosetime, action) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, chooseRecord.getStudentid(), chooseRecord.getTeacherId(), chooseRecord.getCourseid(), chooseRecord.getCredits(), chooseRecord.getChoosetime(), chooseRecord.getAction());
    }

	
	

	@Override
	public int updateChooseRecord(Integer id, ChooseRecords chooseRecord) {
        String sql = "UPDATE choose_records SET studentid = ?, teacherid = ? courseid = ?, credits = ?, choosetime = ?, action = ?,  WHERE id = ?";
        return jdbcTemplate.update(sql, chooseRecord.getStudentid(), chooseRecord.getTeacherId(), chooseRecord.getCourseid(), chooseRecord.getCredits(), chooseRecord.getChoosetime(), chooseRecord.getAction(), id);
    }

	@Override
	public int deleteChooseRecord(Integer id) {
		  String sql = "DELETE FROM choose_records WHERE id = ?";
	        return jdbcTemplate.update(sql, id);
	    }

	@Override
	public ChooseRecords getChooseRecordById(Integer id) {
		  String sql = "SELECT * FROM choose_records WHERE id = ?";
	        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ChooseRecords.class), id);
	    }

	@Override
	public List<ChooseRecordsDto> findAllChooseRecords() {
	    String sql = "SELECT "
	    		+ "c.id, c.studentid, c.teacherid, c.courseid, c.credits, c.choosetime, c.action, "
	    		+ " u.id as course_id, u.subject, u.teacherid as course_teacherid, u.coursetype, u.place, u.time, u.quota, u.credits as test, u.remark, "
	    		+ "   s.id, s.name "
	    		+ "FROM "
	    		+ "choose_records c "
	    		+ "left join courses u on c.courseid = u.id "
	    		+ "left join students s on c.studentid = s.id";
        
	    RowMapper<ChooseRecordsDto> mapper = new RowMapper<ChooseRecordsDto>() {
			
			@Override
			public ChooseRecordsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id = rs.getInt("id");
				Integer studentid = rs.getInt("studentid");
				Integer courseid = rs.getInt("courseid");
				Integer credits = rs.getInt("credits");
				Timestamp choosetime = rs.getTimestamp("choosetime");
				String action = rs.getString("action");
				String subject = rs.getString("subject");
				Integer teacherid = rs.getInt("teacherid");
				String coursetype = rs.getString("coursetype");
				String place = rs.getString("place");
				String time = rs.getString("time");
				Integer quota = rs.getInt("quota");
				Integer test = rs.getInt("test");
				String remark = rs.getString("remark");
				String name = rs.getString("name");
				
				Courses courses = new Courses(courseid, subject, teacherid, coursetype, place, time, quota, credits, remark);
				Students students = new Students();
				ChooseRecordsDto dto = new ChooseRecordsDto();
				dto.setId(id);
				dto.setStudentid(studentid);
				dto.setTeacherId(teacherid);
				dto.setCourseid(courseid);
				dto.setCredits(credits);
				dto.setChoosetime(choosetime);
				dto.setAction(action);
				dto.setCourses(courses);
				dto.setStudents(students);
				return dto;
			}
		};
	    	return jdbcTemplate.query(sql, mapper);	
	}



	@Override
	public Courses findCoursesById(Integer id) {
		 String sql = "SELECT * FROM courses where id = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Courses.class), id);
	}



	@Override
	public Courses findCoursesBySubject(String subject) {
		String sql = "SELECT * FROM courses where subject = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Courses.class), subject);
	}



	@Override
	public Courses findCoursesByTeacherId(Integer teacherId) {
		String sql = "SELECT * FROM courses where teacherId = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Courses.class), teacherId);
	}



	@Override
	public Courses findCoursesByCourseType(String courseType) {
		String sql = "SELECT * FROM courses where courseType = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Courses.class), courseType);
	}



	@Override
	public Courses findCoursesByCredits(Integer credits) {
		String sql = "SELECT * FROM courses where credits = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Courses.class), credits);
	}



	@Override
	public Courses findCoursesByTime(String time) {
		String sql = "SELECT * FROM courses where time = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Courses.class), time);
	}



	@Override
	public List<ChooseRecords> getChooseRecordByStudentId(Integer studentId) {
	    String sql = "SELECT * FROM choose_records WHERE studentid = ?";
	    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ChooseRecords.class), studentId);
	}



	@Override
	public List<ChooseRecords> getChooseRecordByTeacherId(Integer teacherId) {
		 String sql = "SELECT * FROM choose_records WHERE teacherid = ?";
	        return jdbcTemplate.query(sql, new Object[]{teacherId}, new BeanPropertyRowMapper<>(ChooseRecords.class));
	    }
	}
	    		
	

