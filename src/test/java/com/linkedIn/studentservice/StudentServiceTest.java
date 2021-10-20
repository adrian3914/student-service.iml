package com.linkedIn.studentservice;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE) // since this is an integration test
@Transactional
public class StudentServiceTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService  studentService;

    @DisplayName("Returning saved student from service layer")
    @Test
    void getStudentById_forSavedStudent_isReturned(){

        // given: we want student to be injected ti the db already
        Student savedStudent = studentRepository.save(new Student(null, "Mark"));

        // when
        Student student = studentService.getStudentById(savedStudent.getId());

        // then
        then(student.getName()).isEqualTo("Mark");
        then(student.getId()).isNotNull();
    }

    @Test
    void getStudentById_whenMissingStudent_notFoundExceptionThrown(){
        // given
        Long id = 1234l;

        // when
       Throwable throwable = catchThrowable(() -> studentService.getStudentById(id));

        // then
        BDDAssertions.then(throwable).isInstanceOf(StudentNotFoundException.class);
    }
}
