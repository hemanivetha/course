package com.hcl.course.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.hcl.course.controller.CourseController;
import com.hcl.course.entity.Course;
import com.hcl.course.service.CourseService;

@RunWith(SpringJUnit4ClassRunner.class)
public class CourseControllerTest {
	
	private MockMvc mockMvc;

	@InjectMocks
	CourseController courseController;

	@Mock
	CourseService courseService;
	
	ResponseEntity<Course> course=null;

	@Before
	public void setUp() {
		mockMvc=MockMvcBuilders.standaloneSetup(courseController).build();
	}
	
	@Test
	public void listCourseTest() throws Exception{
			mockMvc.perform(MockMvcRequestBuilders.get("/courses/allCourse").contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	public void searchCourseByCourseIdTest() throws Exception{
		Course course = new Course();
		
		Integer courseId=null;
		Mockito.when(courseService.findCourseByCourseId(courseId)).thenReturn(course);
		HttpStatus actual=courseController.findCourseByCourseId(courseId).getStatusCode();
		assertEquals(HttpStatus.NOT_ACCEPTABLE, actual);
		
		courseId=1;
		Mockito.when(courseService.findCourseByCourseId(courseId)).thenReturn(course);
		HttpStatus status=courseController.findCourseByCourseId(courseId).getStatusCode();
		assertEquals(HttpStatus.OK, status);
		
		course=null;
		Mockito.when(courseService.findCourseByCourseId(courseId)).thenReturn(course);
		HttpStatus code=courseController.findCourseByCourseId(courseId).getStatusCode();
		assertEquals(HttpStatus.NOT_FOUND, code);
		
	}
	
	@Test
	public void searchCourseByCourseNameTest() throws Exception{
		List<Course> course = new ArrayList<Course>();
		
		String courseName=null;
		Mockito.when(courseService.findCourseByCourseName(courseName)).thenReturn(course);
		HttpStatus actual=courseController.findCourseByCourseName(courseName).getStatusCode();
		assertEquals(HttpStatus.NOT_ACCEPTABLE, actual);
		
		courseName="Java";
		Mockito.when(courseService.findCourseByCourseName(courseName)).thenReturn(course);
		HttpStatus status=courseController.findCourseByCourseName(courseName).getStatusCode();
		assertEquals(HttpStatus.OK, status);
		
		course=null;
		Mockito.when(courseService.findCourseByCourseName(courseName)).thenReturn(course);
		HttpStatus code=courseController.findCourseByCourseName(courseName).getStatusCode();
		assertEquals(HttpStatus.NOT_FOUND, code);
		
		
	}
}
