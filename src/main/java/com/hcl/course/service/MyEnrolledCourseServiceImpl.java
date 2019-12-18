package com.hcl.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.course.dto.EnrollCourse;
import com.hcl.course.entity.Course;
import com.hcl.course.entity.MyEnrolledCourse;
import com.hcl.course.entity.User;
import com.hcl.course.exception.CourseNotFoundException;
import com.hcl.course.repository.MyEnrolledCourseRepository;

@Service
public class MyEnrolledCourseServiceImpl implements MyEnrolledCourseService {

	@Autowired
	MyEnrolledCourseRepository myEnrolledCourseRepository;

	@Autowired
	UserService userService;

	@Autowired
	CourseService courseService;

	@Override
	public String enrollCourse(EnrollCourse enrollCourse) throws CourseNotFoundException {
		User user = userService.findUserByUserId(enrollCourse.getUserId());
		Course course = courseService.findCourseByCourseId(enrollCourse.getCourseId());
		String result = null;
		MyEnrolledCourse myEnroll = myEnrolledCourseRepository
				.findMyEnrolledCourseByUserIdAndCourseId(enrollCourse.getUserId(), enrollCourse.getCourseId());
		if (myEnroll == null) {
			if (enrollCourse.getUserId() != null && enrollCourse.getCourseId() != null) {
				MyEnrolledCourse myEnrolledCourse = new MyEnrolledCourse();
				myEnrolledCourse.setUserId(enrollCourse.getUserId());
				myEnrolledCourse.setUserName(user.getUserName());
				myEnrolledCourse.setCourseId(enrollCourse.getCourseId());
				myEnrolledCourse.setCourseName(course.getCourseName());
				myEnrolledCourseRepository.save(myEnrolledCourse);
				result = "Course Successfully Enrolled";
			} else {
				result = "Id not found";
			}
		} else {
			result = "CourseId Is already Registered";
		}
		return result;
	}


}
