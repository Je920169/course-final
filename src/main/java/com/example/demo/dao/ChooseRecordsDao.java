package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.dto.ChooseRecordsDto;
import com.example.demo.model.po.ChooseRecords;
import com.example.demo.model.po.Courses;

public interface ChooseRecordsDao extends CoursesDao {
    int addChooseRecord(ChooseRecords chooseRecord);
    int updateChooseRecord(Integer id, ChooseRecords chooseRecord);
    int deleteChooseRecord(Integer id);
    String getCourseNameById(Integer id);
    ChooseRecords getChooseRecordById(Integer id);
    List<ChooseRecords> getChooseRecordByStudentId(Integer studentId);
    List<ChooseRecords> getChooseRecordByTeacherId(Integer teacherId);
    List<ChooseRecordsDto> findAllChooseRecords();
    Courses findCoursesById(Integer id);
    Courses findCoursesBySubject(String subject);
    Courses findCoursesByTeacherId(Integer teacherId);
    Courses findCoursesByCourseType(String courseType);
    Courses findCoursesByCredits(Integer credits);
    Courses findCoursesByTime(String time);
}