package com.mediscreen.patient.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mediscreen.patient.model.Patient;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Disabled
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getPatientList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/patient")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty());
    }

    @Test
    void getPatientById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/patient/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    void addPatient() throws Exception {
        Patient patient = new Patient();
        patient.setFirstName("Alex");
        patient.setLastName("Bland");
        patient.setDateOfBirth("1978-10-23");
        patient.setGender("F");
        patient.setAddress("1509 Culver St");
        patient.setZipCode("45025");
        patient.setCity("Cannes");
        patient.setPhone("123-123-1234");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(patient);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/patient/add")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    @Test
    void updatePatient() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/patient/update")
                        .content("{\"id\":\"1000\",\"firstName\":\"Lili\", \"lastName\":\"Bland\",\"birthOfDay\":\"1978-10-23\",\"gender\":\"F\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void deletePatientById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/patient/delete/{id}", 1))
                .andExpect(status().is2xxSuccessful());
    }
}