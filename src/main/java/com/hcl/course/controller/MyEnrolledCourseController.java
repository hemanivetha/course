package com.hcl.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.course.dto.EnrollCourse;
import com.hcl.course.exception.CourseNotFoundException;
import com.hcl.course.service.MyEnrolledCourseService;

@RestController
@RequestMapping("/myEnrolledCourses")
public class MyEnrolledCourseController {

	@Autowired
	MyEnrolledCourseService myEnrolledCourseService;

	@PostMapping("/enrollCourse")
	public ResponseEntity<String> enrollCourse(@RequestBody EnrollCourse enrollCourse) throws CourseNotFoundException {
		if (enrollCourse.getUserId() != null && enrollCourse.getCourseId() != null) {
			String course = myEnrolledCourseService.enrollCourse(enrollCourse);
			if (course.equals("Course Successfully Enrolled")) {
				return new ResponseEntity<>(course, HttpStatus.OK);
			}
			return new ResponseEntity<>("CourseId Is already Registered",HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>("Id not found",HttpStatus.NOT_FOUND);
	}

}
