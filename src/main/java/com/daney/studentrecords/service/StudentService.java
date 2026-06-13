package com.daney.studentrecords.service;

import com.daney.studentrecords.model.Student;
import java.util.List;

public interface StudentService {

    Student createStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(int id);

    Student updateStudent(int id, Student student);

    boolean deleteStudent(int id);
}