package com.hcl.course.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.course.entity.Course;
import com.hcl.course.exception.CourseNotFoundException;
import com.hcl.course.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseRepository courseRepository;

	@Override
	public List<Course> findAllCourse() throws CourseNotFoundException {
		List<Course> course = courseRepository.findAll();
		if (course.isEmpty()) {
			throw new CourseNotFoundException("No course Available");
		}
		return course;
	}

	@Override
	public Course findCourseByCourseId(Integer courseId) throws CourseNotFoundException {
		Course course = courseRepository.findCourseByCourseId(courseId);
		if (course == null) {
			throw new CourseNotFoundException("Course not Found");
		}
		return course;
	}

	@Override
	public List<Course> findCourseByCourseName(String courseName) throws CourseNotFoundException {
		List<Course> courses = courseRepository.findAll();
		List<Course> courseByName = new ArrayList<>();
		for (Course course : courses) {
			courseByName.add(course);
		}
		courseByName = courseByName.stream().filter(
				coursesByName -> coursesByName.getCourseName().toLowerCase().startsWith(courseName.toLowerCase()))
				.collect(Collectors.toList());
		if (courseByName.isEmpty()) {
			throw new CourseNotFoundException("Course Not Found");
		}
		return courseByName;
	}

}
