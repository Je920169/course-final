package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.dto.ChooseRecordsDto;
import com.example.demo.model.po.ChooseRecords;
import com.example.demo.model.po.Courses;
import com.example.demo.model.po.Students;
import com.example.demo.model.po.Teachers;
import com.example.demo.service.AuthService;
import com.example.demo.service.ChooseRecordsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @Autowired
    private ChooseRecordsService chooseRecordsService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new Students()); 
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") Students student, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
    	
        Students foundStudent = authService.findStudentByEmail(student.getEmail());
        if (foundStudent != null && foundStudent.getPassword().equals(student.getPassword())) {
            session.setAttribute("userType", "student");
            session.setAttribute("user", foundStudent);
            return "redirect:/index"; 
        }

        Teachers foundTeacher = authService.findTeacherByEmail(student.getEmail());
        if (foundTeacher != null && foundTeacher.getPassword().equals(student.getPassword())) {
            session.setAttribute("userType", "teacher"); 
            session.setAttribute("user", foundTeacher);
            return "redirect:/index"; 
        }
        
        model.addAttribute("error", "Invalid email or password");
        return "login";
    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate(); 
        return "redirect:/auth/login"; 
    }
    
    @GetMapping("/chooseRecords")
    public String showChooseRecords(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        if (user instanceof Students) {
            Students student = (Students) user;
            List<ChooseRecords> chooseRecords = chooseRecordsService.getChooseRecordByStudentId(student.getId());
            model.addAttribute("chooseRecordsDtos", chooseRecords);
        } else if (user instanceof Teachers) {
        	Teachers teacher = (Teachers) user;
            List<ChooseRecordsDto> chooseRecordsDtos = chooseRecordsService.findAllChooseRecords();
            model.addAttribute("chooseRecordsDtos", chooseRecordsDtos);
        }

        return "chooseRecords";
    }    
    
  
    @DeleteMapping("/chooseRecords/delete/{id}")
    public String deleteChooseRecord(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
    	chooseRecordsService.deleteChooseRecord(id);
    	HttpSession session = request.getSession();
    	 Object user = session.getAttribute("user");
    	Students student = (Students) user;
        List<ChooseRecords> chooseRecords = chooseRecordsService.getChooseRecordByStudentId(student.getId());
        model.addAttribute("chooseRecordsDtos", chooseRecords);
        return "chooseRecords";
    }
    
    @GetMapping("/curriculum")
    public String showTimetable(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        List<ChooseRecords> chooseRecords = new ArrayList<>();
        if (user instanceof Students) {
            Students student = (Students) user;
            chooseRecords = chooseRecordsService.getChooseRecordByStudentId(student.getId());
        } else if (user instanceof Teachers) {
            Teachers teacher = (Teachers) user;
            chooseRecords = chooseRecordsService.getChooseRecordByTeacherId(teacher.getId());
        }

        List<Courses> courses = chooseRecordsService.findAllCourses(); 
        Map<Integer, Courses> courseMap = courses.stream().collect(Collectors.toMap(Courses::getId, course -> course));

        Map<String, Map<Integer, Courses>> timetable = new HashMap<>();
        for (String day : Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")) {
            timetable.put(day, new HashMap<>());
        }

        for (ChooseRecords record : chooseRecords) {
            Courses course = courseMap.get(record.getCourseid());
            if (course != null) {
                String time = course.getTime();
                String[] parts = time.split(" ");
                String day = parts[0];
                String[] periods = parts[1].split("-");
                int startPeriod = Integer.parseInt(periods[0]);
                int endPeriod = Integer.parseInt(periods[1]);

                for (int i = startPeriod; i <= endPeriod; i++) {
                    timetable.get(day).put(i, course);
                }
            }
        }

        model.addAttribute("timetable", timetable);
        model.addAttribute("courseMap", courseMap);
        return "curriculum";
    }
    
}