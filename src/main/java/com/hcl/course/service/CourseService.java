package com.hcl.course.service;

import java.util.List;

import com.hcl.course.entity.Course;
import com.hcl.course.exception.CourseNotFoundException;

public interface CourseService {

	List<Course> findAllCourse() throws CourseNotFoundException;

	public Course findCourseByCourseId(Integer courseId) throws CourseNotFoundException;

	public List<Course> findCourseByCourseName(String courseName) throws CourseNotFoundException;

}
