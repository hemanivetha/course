package com.hcl.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.course.dto.Login;
import com.hcl.course.entity.MyEnrolledCourse;
import com.hcl.course.entity.User;
import com.hcl.course.exception.UserNotFoundException;
import com.hcl.course.repository.MyEnrolledCourseRepository;
import com.hcl.course.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	MyEnrolledCourseRepository myEnrolledCourseRepository;

	@Override
	public List<MyEnrolledCourse> login(Login login) throws UserNotFoundException {
		User user = userRepository.findUserByUserId(login.getUserId());
		if (user == null) {
			throw new UserNotFoundException("User not found");

		}
		if (login.getUserId() == user.getUserId() && login.getPassWord().equals(user.getPassWord())) {
			List<MyEnrolledCourse> myEnrolledCourse = myEnrolledCourseRepository
					.findMyEnrolledCourseByUserId(login.getUserId());
			return myEnrolledCourse;
		} else {
			return null;
		}
	}

	@Override
	public User findUserByUserId(Integer userId) {
		return userRepository.findUserByUserId(userId);
	}

}
