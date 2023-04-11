package com.mediscreen.client.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
@Disabled
@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void homePatient() throws Exception{
        mockMvc.perform(get("/patient/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("patient/list"));
    }

    @Test
    void addPatient() throws Exception{
        mockMvc.perform(get("/patient/add"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("patient/add"));
    }

    @Test
    void validate() throws Exception{
        mockMvc.perform(post("/patient/validate")
                .param("firstName", "Alex")
                .param("lastName", "Benson")
                .param("dateOfBirth", "1978-10-23")
                .param("gender", "F")
                .param("address", "25 rue")
                .param("city", "cannes")
                .param("zipCode", "25025")
                .param("phone", "123-123-1234"))
                .andExpect(status().is3xxRedirection());
    }
    @Disabled
    @Test
    void showUpdateForm() throws Exception{
        mockMvc.perform(get("/patient/update/{id}","1"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(view().name("patient/update"));
    }

    @Test
    void updatePatient() throws Exception{
        mockMvc.perform(post("/patient/update/{id}","1")
                        .param("id","1")
                        .param("firstName", "Alex")
                        .param("lastName", "Benson")
                        .param("dateOfBirth", "1978-10-23")
                        .param("gender", "F")
                        .param("address", "25 rue")
                        .param("city", "cannes")
                        .param("zipCode", "25025")
                        .param("phone", "123-123-1234"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void deletePatient() throws Exception{
        mockMvc.perform(get("/patient/delete/{id}","1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}