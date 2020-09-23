package com.LukaAbey.SuperSecureAPI.Student;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

	private static final List<Student> STUDENTS = Arrays.asList(new Student(1, "Johnny"), new Student(2, "Jim"));

	@GetMapping(path = "/{studentID}")
	public Student getStudent(@PathVariable("studentID") Integer studentID) {
		return STUDENTS.stream().filter(student -> studentID.equals(student.getStudentID())).findFirst()
				.orElseThrow(() -> new IllegalStateException("Does not exist"));
	}
}
