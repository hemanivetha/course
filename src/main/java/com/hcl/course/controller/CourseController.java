package com.hcl.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.course.entity.Course;
import com.hcl.course.exception.CourseNotFoundException;
import com.hcl.course.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	CourseService courseService;

	@GetMapping("/allCourse")
	public ResponseEntity<List<Course>> listAllCourse() throws CourseNotFoundException {
		List<Course> course = courseService.findAllCourse();
		return new ResponseEntity<>(course, HttpStatus.OK);
	}

	@PostMapping("/findCourseByCourseId/{courseId}")
	public ResponseEntity<Course> findCourseByCourseId(@PathVariable("courseId") Integer courseId)
			throws CourseNotFoundException {
		if (courseId != null) {
			Course course = courseService.findCourseByCourseId(courseId);
			if (course != null) {
				return new ResponseEntity<>(course, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	@PostMapping("/findCourseByCourseName/{courseName}")
	public ResponseEntity<List<Course>> findCourseByCourseName(@PathVariable("courseName") String courseName)
			throws CourseNotFoundException {
		if (courseName != null) {
			List<Course> course = courseService.findCourseByCourseName(courseName);
			if (course != null) {
				return new ResponseEntity<>(course, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

}
