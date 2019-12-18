package com.hcl.course.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mycourse")
public class MyEnrolledCourse {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer enrolledCourseId;
	private Integer userId;
	private String userName;
	private Integer courseId;
	private String courseName;

}
