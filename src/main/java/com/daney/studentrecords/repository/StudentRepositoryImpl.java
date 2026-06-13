package com.daney.studentrecords.repository;

import com.daney.studentrecords.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    // ─── In-memory storage using a HashMap ───
    // Key   = student ID (Integer)
    // Value = Student object
    private final Map<Integer, Student> students = new HashMap<>();

    @Override
    public Student save(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    @Override
    public Student findById(int id) {
        return students.get(id);  // returns null if not found
    }

    @Override
    public Student update(int id, Student student) {
        if (students.containsKey(id)) {
            student.setId(id);              // make sure ID stays the same
            students.put(id, student);      // overwrite the existing one
            return student;
        }
        return null;  // student with that ID doesn't exist
    }

    @Override
    public boolean deleteById(int id) {
        if (students.containsKey(id)) {
            students.remove(id);
            return true;
        }
        return false;
    }
}