package com.example.demo.model.po;



import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChooseRecords {
	
	 	private Integer id;
	    private Integer studentid;
	    private Integer teacherId;
	    private Integer courseid;
	    private Integer credits;
	    private Timestamp choosetime;
	    private String action;
	   
}
