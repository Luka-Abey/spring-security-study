package com.LukaAbey.Student;

public class Student {
	public final Integer studentID;
	public final String studentName;

	public Integer getStudentID() {
		return studentID;
	}

	public String getStudentName() {
		return studentName;
	}

	public Student(Integer studentID, String studentName) {
		super();
		this.studentID = studentID;
		this.studentName = studentName;
	}

}
