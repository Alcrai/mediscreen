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
class HistoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void homeHistory() throws Exception{
        mockMvc.perform(get("/history/patientList"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("history/patientList"));
    }

    @Test
    void showReport() throws Exception{
        mockMvc.perform(get("/assess/{id}","1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("history/patientList"));
    }

    @Test
    void getHistoryOfPatient() throws Exception{
        mockMvc.perform(get("/history/notelist/{idPatient}","1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("history/notelist"));
    }

    @Test
    void getAddNote() throws Exception{
        mockMvc.perform(get("/history/noteadd/{idPatient}","1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("history/noteadd"));
    }

    @Test
    void noteAddValidate() throws Exception{
        mockMvc.perform(post("/history/noteadd/validate/{idPatient}","1")
                        .param("patientId", "1")
                        .param("date", "1978-10-23")
                        .param("content", "Fumeur"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void showNoteUpdate() throws Exception{
        mockMvc.perform(get("/history/noteupdate/{idNote}","1n"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("history/noteupdate"));
    }
    @Disabled
    @Test
    void updatePatient() throws Exception{
        mockMvc.perform(post("/history/noteupdate/validate/{idNote}","1n")
                        .param("id", "1n")
                        .param("patientId", "1")
                        .param("date", "1978-10-23")
                        .param("content", "Fumeur"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void deleteNote() throws Exception{
        mockMvc.perform(get("/history/notedelete/{idNote}","1n"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}