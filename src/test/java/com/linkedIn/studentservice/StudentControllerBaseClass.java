package com.linkedIn.studentservice;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@WebMvcTest
public class StudentControllerBaseClass {


    // Using MockMvc to perform HTTP requests on the web in-points, inside our web environment
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;



    @BeforeEach
    void before(){

        RestAssuredMockMvc.mockMvc(mockMvc);
        // Given
        given(studentService.getStudentById(anyLong())).willReturn(
                Student.builder()
                        .id(1l)
                        .name("Mark")
                        .grade(10)
                        .build()
        );
    }
}
