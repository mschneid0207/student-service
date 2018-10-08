package de.mschneid.microservices.studentservices.controller;

import de.mschneid.microservices.studentservices.model.Course;
import de.mschneid.microservices.studentservices.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(path = "/students/{studentId}/courses")
    public List<Course> retrieveCoursesForStudent(@PathVariable Integer studentId) {
        return studentService.retrieveCourses(studentId);
    }

    @GetMapping(path = "/students/{studentId}/courses/{courseId}")
    public Course retrieveDetailsForCourse(@PathVariable Integer studentId, @PathVariable String courseId) {
        Course course = studentService.retrieveCourse(studentId, courseId);
        return course;
    }
}
