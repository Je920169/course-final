package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.TeachersService;

@RestController
@RequestMapping("/api")
public class TeacherController {

    @Autowired
    private TeachersService teacherService;

    @GetMapping("/teacher/{id}")
    public ResponseEntity<String> getTeacherNameById(@PathVariable Integer id) {
        String teacherName = teacherService.getTeacherNameById(id);
        if (teacherName != null) {
            return ResponseEntity.ok(teacherName);
        }
        return ResponseEntity.notFound().build();
    }
}