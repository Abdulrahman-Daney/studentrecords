package com.daney.studentrecords.repository;

import com.daney.studentrecords.model.Student;
import java.util.List;

public interface StudentRepository {

    // Save a new student (or update if same ID exists)
    Student save(Student student);

    // Get all students
    List<Student> findAll();

    // Get one student by ID — returns null if not found
    Student findById(int id);

    // Update an existing student
    Student update(int id, Student student);

    // Delete a student by ID — returns true if deleted, false if not found
    boolean deleteById(int id);
}