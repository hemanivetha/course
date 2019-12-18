package com.hcl.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.course.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	Course findCourseByCourseId(Integer courseId);

}
