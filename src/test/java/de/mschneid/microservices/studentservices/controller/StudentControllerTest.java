package de.mschneid.microservices.studentservices.controller;


import de.mschneid.microservices.studentservices.model.Course;
import de.mschneid.microservices.studentservices.service.StudentService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class, secure = false)
//@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    Course mockCourse = new Course("Course1", "Spring", "10 Steps",
            Arrays.asList("Learn Maven", "Import Project", "First Example",
                    "Second Example"));

    Course mockCourse2 = new Course("Course1", "Spring", "10 Steps",
            new ArrayList<>());

    String exampleCourseJson = "{\"name\":\"Spring\",\"description\":\"10 Steps\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]}";

    @Test
    public void retrieveDetailsForCourse() throws Exception {
       // Assert.assertEquals(2, 3);
        Mockito.when(studentService.retrieveCourse(Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(mockCourse);

        MockHttpServletRequestBuilder requestBuilder = get("/students/1/courses/Course1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getContentAsString());
        String expected = "{id:Course1,name:Spring}";
        Course expectedCourse = Course.builder().id("Course1").build();
        //"{id:Course1,name:Spring,description:10 Steps,steps:[Learn Maven,"Import Project","First Example","Second Example"]}

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void retrieveDetailsForCourse2() throws  Exception {
        Mockito.when(studentService.retrieveCourse(Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(mockCourse);

        mockMvc.perform(get("/students/1/courses/Course1")
        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(Matchers.is("Course1")));
    }

}
