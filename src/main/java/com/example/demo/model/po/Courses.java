package com.example.demo.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Courses {

		private Integer id;
	    private String subject;
	    private Integer teacherId;
	    private String courseType;
	    private String place;
	    private String time;
	    private Integer quota;
	    private Integer credits;
	    private String remark;
}
