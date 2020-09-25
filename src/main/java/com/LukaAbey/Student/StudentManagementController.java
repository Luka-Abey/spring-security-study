package com.LukaAbey.Student;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {
	private static final List<Student> STUDENTS = Arrays.asList(new Student(4, "Jo"), new Student(5, "Harry"));

	public List<Student> getAllStudents() {
		return STUDENTS;
	}

	public void registerNewStudent(Student student) {
		System.out.println(student);
	}

	public void updateStudent(Integer studentID, Student student) {
		System.out.println(String.format("the id is %s and student is %s", studentID, student));
	}

	public void deleteStudent(Integer studentID) {
		System.out.println(studentID);
	}

}
