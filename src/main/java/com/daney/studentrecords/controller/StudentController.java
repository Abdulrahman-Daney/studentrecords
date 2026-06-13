package com.daney.studentrecords.controller;

import com.daney.studentrecords.model.Student;
import com.daney.studentrecords.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")     // Base URL for all endpoints in this class
public class StudentController {

    // ─── HAS-A: Controller uses the Service ───
    @Autowired
    private StudentService studentService;


    // ─── 1. CREATE — POST /api/students ───
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student created = studentService.createStudent(student);
        return new ResponseEntity<>(created, HttpStatus.CREATED);     // 201
    }


    // ─── 2. GET ALL — GET /api/students ───
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);          // 200
    }


    // ─── 3. GET BY ID — GET /api/students/{id} ───
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);         // 404
        }
        return new ResponseEntity<>(student, HttpStatus.OK);           // 200
    }


    // ─── 4. UPDATE — PUT /api/students/{id} ───
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable int id,
            @RequestBody Student student) {

        Student updated = studentService.updateStudent(id, student);
        if (updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);         // 404
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);           // 200
    }


    // ─── 5. DELETE — DELETE /api/students/{id} ───
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        boolean deleted = studentService.deleteStudent(id);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);         // 404
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);            // 204
    }
}