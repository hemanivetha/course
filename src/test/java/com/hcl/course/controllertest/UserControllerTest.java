package com.hcl.course.controllertest;

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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.hcl.course.controller.UserController;
import com.hcl.course.dto.Login;
import com.hcl.course.entity.MyEnrolledCourse;
import com.hcl.course.entity.User;
import com.hcl.course.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {
	
	Login login=null;
	
	private MockMvc mockMvc;

	@InjectMocks
	UserController userController;

	@Mock
	UserService userService;
	

	@Before
	public void setUp() {
		mockMvc=MockMvcBuilders.standaloneSetup(userController).build();
		login = new Login();
	}
	
	@Test
	public void loginTest() throws Exception{
		List<MyEnrolledCourse> myEnrolledCourse=new ArrayList<MyEnrolledCourse>();
		MyEnrolledCourse myCourse=new MyEnrolledCourse();
		
		login.setUserId(1);
		login.setPassWord("hema");
		myCourse.setCourseId(0);
		myCourse.setCourseName(null);
		myCourse.setEnrolledCourseId(0);
		myCourse.setUserId(0);
		myCourse.setUserName(null);
		myEnrolledCourse.add(myCourse);
		Mockito.when(userService.login(login)).thenReturn(myEnrolledCourse);
		int expected=userController.loginUser(login).getStatusCodeValue();
		assertEquals(200,expected);		
	}
	
	@Test
	public void loginTestFail() throws Exception{
		List<MyEnrolledCourse> myEnrolledCourse=new ArrayList<MyEnrolledCourse>();
		
		login.setUserId(1);
		login.setPassWord("hema");
		Mockito.when(userService.login(login)).thenReturn(myEnrolledCourse);
		int expected=userController.loginUser(login).getStatusCodeValue();
		assertEquals(404,expected);		
	}
	
	@Test
	public void notLoginTestFail() throws Exception{
		List<MyEnrolledCourse> myEnrolledCourse=new ArrayList<MyEnrolledCourse>();
		
		login.setUserId(0);
		login.setPassWord(null);
		Mockito.when(userService.login(login)).thenReturn(myEnrolledCourse);
		int expected=userController.loginUser(login).getStatusCodeValue();
		assertEquals(406,expected);		
	}

	
	@Test
	public void UserByUserIdTest() throws Exception{
			User user=new User();
			
			Integer userId=null;
			Mockito.when(userService.findUserByUserId(userId)).thenReturn(user);
			HttpStatus actual=userController.findUserByUserId(userId).getStatusCode();
			assertEquals(HttpStatus.NOT_ACCEPTABLE, actual);
			
			userId=1;
			Mockito.when(userService.findUserByUserId(userId)).thenReturn(user);
			HttpStatus status=userController.findUserByUserId(userId).getStatusCode();
			assertEquals(HttpStatus.OK, status);
			
			user=null;
			Mockito.when(userService.findUserByUserId(userId)).thenReturn(user);
			HttpStatus code=userController.findUserByUserId(userId).getStatusCode();
			assertEquals(HttpStatus.NOT_FOUND, code);
	}

}
