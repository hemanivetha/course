package com.hcl.course.service;

import java.util.List;

import com.hcl.course.dto.Login;
import com.hcl.course.entity.MyEnrolledCourse;
import com.hcl.course.entity.User;
import com.hcl.course.exception.UserNotFoundException;

public interface UserService {

	public List<MyEnrolledCourse> login(Login login) throws UserNotFoundException;

	public User findUserByUserId(Integer userId);

}
