package com.example.demo.model.dto;

import java.sql.Timestamp;

import com.example.demo.model.po.Courses;
import com.example.demo.model.po.Students;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChooseRecordsDto {
	private Integer id;
    private Integer studentid;
    private Integer teacherId;
    private Integer courseid;
    private Integer credits;
    private Timestamp choosetime;
    private String action;
    
   
    private Courses courses;
    private Students students;
}
