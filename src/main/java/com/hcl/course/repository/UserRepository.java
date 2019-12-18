package com.hcl.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.course.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findUserByUserId(Integer userId);

}
