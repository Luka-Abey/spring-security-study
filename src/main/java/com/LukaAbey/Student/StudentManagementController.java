package com.LukaAbey.Student;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {
	private static final List<Student> STUDENTS = Arrays.asList(new Student(4, "Jo"), new Student(5, "Harry"));

	@GetMapping
	public List<Student> getAllStudents() {
		return STUDENTS;
	}

	@PostMapping
	public void registerNewStudent(@RequestBody Student student) {
		System.out.println(student);
	}

	@PutMapping(path = "{studentID}")
	public void updateStudent(@PathVariable("studentID") Integer studentID, @RequestBody Student student) {
		System.out.println(String.format("the id is %s and student is %s", studentID, student));
	}

	@DeleteMapping(path = "{studentID")
	public void deleteStudent(@PathVariable("studentID") Integer studentID) {
		System.out.println(studentID);
	}

}
