package com.hcl.course.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.hcl.course.entity.Course;
import com.hcl.course.repository.CourseRepository;
import com.hcl.course.service.CourseService;

@RunWith(SpringJUnit4ClassRunner.class)
public class CourseServiceTest {
	
	
	@InjectMocks
	CourseService courseService;
	
	@Mock
	CourseRepository courseRepository;
	ResponseEntity<String> result = new ResponseEntity<String>(HttpStatus.FORBIDDEN);
	
	@Test
	public void findCourseByCourseIdTest() throws Exception{
		List<Course> course = new ArrayList<Course>();
		
		course=null;
		String message = "No course Available";
		
		/*
		 * Mockito.when(courseRepository.findCourseByCourseId(courseId) result =
		 * myEnrolledCourseController.enrollCourse(enrollCourse); assertEquals(message,
		 * result.getBody());
		 */
	}

}
