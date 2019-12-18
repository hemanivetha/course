package com.hcl.course.service;

import com.hcl.course.dto.EnrollCourse;
import com.hcl.course.exception.CourseNotFoundException;

public interface MyEnrolledCourseService {

	public String enrollCourse(EnrollCourse enrollCourse) throws CourseNotFoundException;

}
