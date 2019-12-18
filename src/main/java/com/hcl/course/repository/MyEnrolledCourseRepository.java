package com.hcl.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.course.entity.MyEnrolledCourse;

public interface MyEnrolledCourseRepository extends JpaRepository<MyEnrolledCourse, Integer> {

	MyEnrolledCourse findMyEnrolledCourseByUserIdAndCourseId(Integer userId, Integer courseId);

	List<MyEnrolledCourse> findMyEnrolledCourseByUserId(int userId);

}
