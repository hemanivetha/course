package com.hcl.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.course.dto.Login;
import com.hcl.course.entity.MyEnrolledCourse;
import com.hcl.course.entity.User;
import com.hcl.course.exception.UserNotFoundException;
import com.hcl.course.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/loginUser")
	public ResponseEntity<List<MyEnrolledCourse>> loginUser(@RequestBody Login login) throws UserNotFoundException {
		if (login.getUserId() != 0 && login.getPassWord() != null) {
			List<MyEnrolledCourse> myEnrolledCourse = userService.login(login);
			if (myEnrolledCourse.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<MyEnrolledCourse>>(myEnrolledCourse, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

	}

	@PostMapping("/findUserByUserId/{userId}")
	public ResponseEntity<User> findUserByUserId(@PathVariable("userId") Integer userId) {
		if (userId != null) {
			User user = userService.findUserByUserId(userId);
			if (user != null) {
				return new ResponseEntity<>(user, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

}
