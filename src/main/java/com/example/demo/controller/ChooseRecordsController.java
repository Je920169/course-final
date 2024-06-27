package com.example.demo.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.dto.ChooseRecordsDto;
import com.example.demo.model.po.ChooseRecords;
import com.example.demo.model.po.Courses;
import com.example.demo.model.po.Students;
import com.example.demo.service.ChooseRecordsService;
import com.example.demo.service.StudentsService;
import com.example.demo.service.TeachersService;

@Controller
@RequestMapping("/index")
public class ChooseRecordsController {

    @Autowired
    private ChooseRecordsService chooseRecordsService;
    
    @Autowired
    private StudentsService studentsService;
    
    @Autowired
    private TeachersService teachersService;
    
    
    /*
    @GetMapping("/chooseRecords")
    public String chooseRecords(@ModelAttribute ChooseRecords chooseRecords, 
    							@ModelAttribute Courses courses,
    							@ModelAttribute Students students,
    							Model model) {
        List<ChooseRecordsDto> chooseRecordsDtos = chooseRecordsService.findAllChooseRecords();
        List<Courses> course = chooseRecordsService.findAllCourses();
        List<Students> student = studentsService.findAllStudents();
        model.addAttribute("chooseRecordsDtos", chooseRecordsDtos);
        model.addAttribute("course", course);
        model.addAttribute("student", student);
    	return "chooseRecords";
    }
   */
    
    
    @GetMapping
    public String chooseRecords(@ModelAttribute Students students, Model model) {
        List<Students> student = studentsService.findAllStudents();
        model.addAttribute("student", student);
    	return "index";
    }
    
    @GetMapping("/courselist")
    public String chooseRecords(@ModelAttribute Courses courses, Model model) {
        List<Courses> course = chooseRecordsService.findAllCourses();    
        model.addAttribute("course", course);
    	return "courselist";
    }
    
    
   
    
    @GetMapping("/search")
    public String getCourses(@RequestParam(name = "courseid", required = false) Integer courseId,  
    		 			  	 @RequestParam(name = "subject", required = false) String subject,							
    		 			  	 @RequestParam(name = "teacherid", required = false) Integer teacherId,
    		 			  	 @RequestParam(name = "coursetype", required = false) String courseType,
    		 			  	 @RequestParam(name = "credits", required = false) Integer credits,
    		 			  	 @RequestParam(name = "time", required = false) String time,
    						 Model model) {
    	List<Courses> result = new ArrayList<>();
    	
    	 try {
             if (courseId != null) {
                 Courses course = chooseRecordsService.findCoursesById(courseId);
                 if (course != null) {
                     result.add(course);
                 }
             } else if (subject != null && !subject.isEmpty()) {
                 Courses course = chooseRecordsService.findCoursesBySubject(subject);
                 if (course != null) {
                     result.add(course);
                 }
             } else if (teacherId != null) {
                 Courses course = chooseRecordsService.findCoursesByTeacherId(teacherId);
                 if (course != null) {
                     result.add(course);
                 }
             } else if (courseType != null && !courseType.isEmpty()) {
                 Courses course = chooseRecordsService.findCoursesByCourseType(courseType);
                 if (course != null) {
                     result.add(course);
                 }
             } else if (credits != null) {
                 Courses course = chooseRecordsService.findCoursesByCredits(credits);
                 if (course != null) {
                     result.add(course);
                 }
             } else if (time != null && !time.isEmpty()) {
                 Courses course = chooseRecordsService.findCoursesByTime(time);
                 if (course != null) {
                     result.add(course);
                 }
             }
         } catch (Exception e) {
             model.addAttribute("errorMessage", "查詢課程時出現錯誤：" + e.getMessage());
         }
    	
        model.addAttribute("course", result);
    	return "courselist";
   }
    

    /*
    @GetMapping("/{id}")
    public String getChooseRecordById(@PathVariable("id") Integer id, Model model) {
        ChooseRecords chooseRecord = chooseRecordsService.getChooseRecordById(id);
        model.addAttribute("chooseRecord", chooseRecord);
       return "chooseRecords";
   }
    */

    
    @PostMapping("/chooseRecords/add")
    public String addChooseRecord(@ModelAttribute ChooseRecords chooseRecord, Model model) {
        chooseRecord.setChoosetime(new Timestamp(System.currentTimeMillis()));
        chooseRecordsService.addChooseRecord(chooseRecord);
        List<Courses> course = chooseRecordsService.findAllCourses();
        model.addAttribute("course", course);
        return "courselist";
    }

    
    @PostMapping("/courselist/add")
    public String addCourses(@ModelAttribute Courses courses, Model model) {
 	   chooseRecordsService.addCourses(courses.getId(), courses.getSubject(), courses.getTeacherId(), courses.getCourseType(), courses.getPlace(), courses.getTime(), courses.getQuota(), courses.getCredits(), courses.getRemark());
 	  List<Courses> course = chooseRecordsService.findAllCourses(); 
      model.addAttribute("course", course);
        return "courselist";
    }
    
    
   
    
    @DeleteMapping("/courselist/delete/{id}")
    public String deleteCourses(@PathVariable("id") Integer id, Model model) {
    	chooseRecordsService.deleteCourses(id);
    	 List<Courses> course = chooseRecordsService.findAllCourses(); 
        model.addAttribute("course", course);
        return "courselist";
    }
    
    /*
    @GetMapping("/curriculum")
    public String showTimetable(Model model) {
        List<Courses> courses = chooseRecordsService.findAllCourses();
        Map<String, Map<Integer, Courses>> timetable = new HashMap<>();

        for (String day : Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")) {
            timetable.put(day, new HashMap<>());
        }

        for (Courses course : courses) {
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

        model.addAttribute("timetable", timetable);
        return "curriculum";
    }
    */
    
    
}