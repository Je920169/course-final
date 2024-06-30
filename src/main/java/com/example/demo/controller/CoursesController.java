package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.po.Courses;
import com.example.demo.service.ChooseRecordsService;

@RestController
@RequestMapping("/api/courses")
public class CoursesController {
	
	@Autowired
	private ChooseRecordsService chooseRecordsService;
	
	 @GetMapping("/{id}")
	    public Courses getCourseById(@PathVariable Integer id) {
	        return chooseRecordsService.getCoursesById(id);
	    }
	 
	 @GetMapping("/name/{id}")
	    public String getCourseNameById(@PathVariable Integer id) {
	        return chooseRecordsService.getCourseNameById(id);
	    }
}
