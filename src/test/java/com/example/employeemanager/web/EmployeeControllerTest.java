package com.example.employeemanager.web;

import com.example.employeemanager.exception.UserNotFoundException;
import com.example.employeemanager.helper.TestHelper;
import com.example.employeemanager.model.Employee;
import com.example.employeemanager.resources.EmployeeResource;
import com.example.employeemanager.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.Serializable;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.ALL;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeResource employeeResource;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeResource).build();
    }

//    @Test
//    void getAllEmployeesSuccess() throws Exception {
//
//        when(employeeService.findAllEmployees()).thenReturn(TestHelper.getEmployees());
//
//        mockMvc.perform(get("/employee/all"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(3)))
//                // first employee
//                .andExpect(jsonPath("$[0].name").value("name1"))
//                .andExpect(jsonPath("$[0].id").value("1"))
//                .andExpect(jsonPath("$[0].email").value("email1"))
//                .andExpect(jsonPath("$[0].jobTitle").value("job1"))
//                .andExpect(jsonPath("$[0].phone").value("+123"))
//                .andExpect(jsonPath("$[0].imageURL").value("url1"))
//                .andExpect(jsonPath("$[0].personalInformation.id").value("1"))
//                .andExpect(jsonPath("$[0].personalInformation.birthDate[0]").value("2020"))
//                .andExpect(jsonPath("$[0].personalInformation.birthDate[1]").value("10"))
//                .andExpect(jsonPath("$[0].personalInformation.birthDate[2]").value("10"))
//                .andExpect(jsonPath("$[0].personalInformation.sex").value("m"))
//                .andExpect(jsonPath("$[0].personalInformation.country").value("Moldova"))
//                .andExpect(jsonPath("$[0].personalInformation.address").value("adr1"))
//                .andExpect(jsonPath("$[0].personalInformation.familyStatus").value("married"))
////           // second employee
//                .andExpect(jsonPath("$[1].name").value("name2"))
//                .andExpect(jsonPath("$[1].id").value("2"))
//                .andExpect(jsonPath("$[1].email").value("email2"))
//                .andExpect(jsonPath("$[1].jobTitle").value("job2"))
//                .andExpect(jsonPath("$[1].phone").value("+124"))
//                .andExpect(jsonPath("$[1].imageURL").value("url2"))
//                .andExpect(jsonPath("$[1].personalInformation.id").value("2"))
//                .andExpect(jsonPath("$[1].personalInformation.birthDate[0]").value("2021"))
//                .andExpect(jsonPath("$[1].personalInformation.birthDate[1]").value("11"))
//                .andExpect(jsonPath("$[1].personalInformation.birthDate[2]").value("11"))
//                .andExpect(jsonPath("$[1].personalInformation.sex").value("f"))
//                .andExpect(jsonPath("$[1].personalInformation.country").value("Romania"))
//                .andExpect(jsonPath("$[1].personalInformation.address").value("adr2"))
//                .andExpect(jsonPath("$[1].personalInformation.familyStatus").value("married"));
//    }
//
//    @Test
//    void getEmployeeByIdSuccess() throws Exception {
//
//        Long employeeId = 1L;
//
//        when(employeeService.findEmployeeById(employeeId))
//                .thenReturn(TestHelper.getEmployees().get(0));
//
//        mockMvc.perform(get("/employee/find/{$}", employeeId))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("name1"))
//                .andExpect(jsonPath("$.id").value("1"))
//                .andExpect(jsonPath("$.email").value("email1"))
//                .andExpect(jsonPath("$.jobTitle").value("job1"))
//                .andExpect(jsonPath("$.phone").value("+123"))
//                .andExpect(jsonPath("$.imageURL").value("url1"))
//                .andExpect(jsonPath("$.personalInformation.id").value("1"))
//                .andExpect(jsonPath("$.personalInformation.birthDate[0]").value("2020"))
//                .andExpect(jsonPath("$.personalInformation.birthDate[1]").value("10"))
//                .andExpect(jsonPath("$.personalInformation.birthDate[2]").value("10"))
//                .andExpect(jsonPath("$.personalInformation.sex").value("m"))
//                .andExpect(jsonPath("$.personalInformation.country").value("Moldova"))
//                .andExpect(jsonPath("$.personalInformation.address").value("adr1"))
//                .andExpect(jsonPath("$.personalInformation.familyStatus").value("married"));
//    }
//
//    //TO DO Add test with exceptions
//    void getEmployeeByIdThrowsNotFound() throws Exception {
//
//    }
//
//    //TO DO Request 400 expected 200
//    @Test
//    void addEmployeeSuccess() throws Exception {
//
//        Employee employee = TestHelper.getEmployee();
//
//        when(employeeService.addEmployee(employee)).thenReturn(employee);
//
//        objectMapper.registerModule(new JavaTimeModule());
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//
//        mockMvc.perform(post("/employee/add")
//                        .contentType(APPLICATION_JSON)
//                                .accept(APPLICATION_JSON)
//                        .characterEncoding("utf-8")
//                        .content(objectMapper.writeValueAsString(employee)))
//                .andDo(print())
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name").value("name1"))
//                .andExpect(jsonPath("$.id").value("1"))
//                .andExpect(jsonPath("$.email").value("email5@gmail.com"))
//                .andExpect(jsonPath("$.jobTitle").value("job1"))
//                .andExpect(jsonPath("$.phone").value("+123"))
//                .andExpect(jsonPath("$.imageURL").value("url1"))
//                .andExpect(jsonPath("$.personalInformation.id").value("1"))
//                .andExpect(jsonPath("$.personalInformation.birthDate[0]").value("2020"))
//                .andExpect(jsonPath("$.personalInformation.birthDate[1]").value("10"))
//                .andExpect(jsonPath("$.personalInformation.birthDate[2]").value("10"))
//                .andExpect(jsonPath("$.personalInformation.sex").value("m"))
//                .andExpect(jsonPath("$.personalInformation.country").value("Moldova"))
//                .andExpect(jsonPath("$.personalInformation.address").value("adr1"))
//                .andExpect(jsonPath("$.personalInformation.familyStatus").value("married"));
//    }
//
//    @Test
//    void deleteEmployeeSuccess() throws Exception {
//
//        mockMvc.perform(delete("/employee/delete/{$}", 1))
//                .andDo(print())
//                .andExpect(status().isOk());
//
//    }
}
