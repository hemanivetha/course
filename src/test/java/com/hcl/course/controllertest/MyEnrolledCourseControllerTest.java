package com.hcl.course.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import com.hcl.course.controller.MyEnrolledCourseController;
import com.hcl.course.dto.EnrollCourse;
import com.hcl.course.service.MyEnrolledCourseService;

@RunWith(SpringJUnit4ClassRunner.class)
public class MyEnrolledCourseControllerTest {

	EnrollCourse enrollCourse = null;
	ResponseEntity<String> result = null;

	private MockMvc mockMvc;

	@InjectMocks
	MyEnrolledCourseController myEnrolledCourseController;

	@Mock
	MyEnrolledCourseService MyEnrolledCourseService;

	@Before
	public void setUp() {
		result = new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		mockMvc = MockMvcBuilders.standaloneSetup(myEnrolledCourseController).build();
		enrollCourse = new EnrollCourse();
	}

	@Test
	public void enrollCourseTest() throws Exception {
		enrollCourse.setUserId(1);
		enrollCourse.setCourseId(1);
		String message = "Course Successfully Enrolled";
		Mockito.when(MyEnrolledCourseService.enrollCourse(enrollCourse)).thenReturn(message);
		result = myEnrolledCourseController.enrollCourse(enrollCourse);
		assertEquals(message, result.getBody());

		enrollCourse.setUserId(null);
		enrollCourse.setCourseId(null);
		message = "Id not found";
		Mockito.when(MyEnrolledCourseService.enrollCourse(enrollCourse)).thenReturn(message);
		result = myEnrolledCourseController.enrollCourse(enrollCourse);
		assertEquals(message, result.getBody());
		
		enrollCourse.setUserId(1);
		enrollCourse.setCourseId(1);
		message = "CourseId Is already Registered";
		Mockito.when(MyEnrolledCourseService.enrollCourse(enrollCourse)).thenReturn(message);
		result = myEnrolledCourseController.enrollCourse(enrollCourse);
		assertEquals(message, result.getBody());

	}
}