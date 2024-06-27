package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ChooseRecordsDao;
import com.example.demo.model.dto.ChooseRecordsDto;
import com.example.demo.model.po.ChooseRecords;
import com.example.demo.model.po.Courses;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ChooseRecordsService {
	
	@Autowired
    private ChooseRecordsDao chooseRecordsDao;

	public int addChooseRecord(ChooseRecords chooseRecord) {
		return chooseRecordsDao.addChooseRecord(chooseRecord);
    }
	
	public int updateChooseRecord(Integer id, ChooseRecords chooseRecord) {
		return chooseRecordsDao.updateChooseRecord(id, chooseRecord);
	}
	
	public int deleteChooseRecord(Integer id) {
		return chooseRecordsDao.deleteChooseRecord(id);
	}
   
	public ChooseRecords getChooseRecordById(Integer id) {
		return chooseRecordsDao.getChooseRecordById(id);
	}
	
	public List<ChooseRecordsDto> findAllChooseRecords() {
		return chooseRecordsDao.findAllChooseRecords();
	}
	//Courses的Impl
	public List<Courses> findAllCourses() {
		return chooseRecordsDao.findAllCourses();
	}
	
	public int addCourses(Integer id, String subject, Integer teacherId, String courseType, String place, String time, Integer quota, Integer credits, String remark) {
		Courses courses = new Courses(id, subject,  teacherId, courseType, place, time, quota, credits, remark);
		return chooseRecordsDao.addCourses(courses);
	}
	
	public Courses getCoursesById(Integer id) {
		return chooseRecordsDao.getCoursesById(id);
	}
	
	public int updateCourses(Integer id, Courses courses) {
		return chooseRecordsDao.updateCourses(id, courses);
	}
	
	public int deleteCourses(Integer id) {
		return chooseRecordsDao.deleteCourses(id);
	}
	
	public Courses findCoursesById(Integer id) {
		return chooseRecordsDao.findCoursesById(id);
	}
	
	public Courses findCoursesBySubject(String subject) {
		return chooseRecordsDao.findCoursesBySubject(subject);
	}

	public Courses findCoursesByTeacherId(Integer teacherId) {
		return chooseRecordsDao.findCoursesByTeacherId(teacherId);
	}
	
	public Courses findCoursesByCourseType(String courseType) {
		return chooseRecordsDao.findCoursesByCourseType(courseType);
	}
	
	public Courses findCoursesByCredits(Integer credits) {
		return chooseRecordsDao.findCoursesByCredits(credits);
	}
	
	public Courses findCoursesByTime(String time) {
		return chooseRecordsDao.findCoursesByTime(time);
	}
	
	public List<ChooseRecords> getChooseRecordByStudentId(Integer studentId) {
		return chooseRecordsDao.getChooseRecordByStudentId(studentId);
	}
	
	public List<ChooseRecords> getChooseRecordByTeacherId(Integer teacherId) {
	    return chooseRecordsDao.getChooseRecordByTeacherId(teacherId);
	}
}
