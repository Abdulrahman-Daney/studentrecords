package com.daney.studentrecords.service;

import com.daney.studentrecords.model.Student;
import com.daney.studentrecords.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    // ─── HAS-A relationship: this service uses a repository ───
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
        // Business rule: check if GPA is valid (between 0 and 4)
        if (student.getGpa() < 0 || student.getGpa() > 4) {
            throw new IllegalArgumentException("GPA must be between 0 and 4");
        }
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student updateStudent(int id, Student student) {
        return studentRepository.update(id, student);
    }

    @Override
    public boolean deleteStudent(int id) {
        return studentRepository.deleteById(id);
    }
}