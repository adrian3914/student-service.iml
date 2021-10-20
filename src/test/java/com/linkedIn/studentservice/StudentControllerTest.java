package com.linkedIn.studentservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest // contains only beans needed for testing a Web controller
public class StudentControllerTest {

    // Using MockMvc to perform HTTP requests on the web in-points, inside our web environment
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;


    @Test
    void getStudent_forSavedStudent_isReturned() throws Exception{
        // Given
        given(studentService.getStudentById(anyLong())).willReturn(
                Student.builder()
                        .id(1l)
                        .name("Mark")
                        .grade(10)
                        .build()
        );

        // When // Then
       mockMvc.perform(get("/students/1")) // calling the controller
               .andExpect(status().isOk())
               .andExpect(jsonPath("id").value(1l))         // {"id":1, "name": "Mark", "grade": 10}
               .andExpect(jsonPath("name").value("Mark"))
               .andExpect(jsonPath("grade").value(10));
    }

    @Test
    void getStudent_forMissingStudent_status404() throws Exception {
        // given
        given(studentService.getStudentById(anyLong())).willThrow(StudentNotFoundException.class);

        // when // then
        mockMvc.perform(get("/students/1"))
                .andExpect(status().isNotFound());
    }
}
