package com.mediscreen.client.service;

import com.mediscreen.client.beans.NoteBean;
import com.mediscreen.client.proxies.NoteProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {
    @Mock
    private NoteProxy noteProxy;

    private NoteService noteService;
    private NoteBean noteBean;

    @BeforeEach
    void setUp() {
        noteService = new NoteService(noteProxy);
        noteBean = new NoteBean("1n","1","2023-04-08","il est pas malade");
    }

    @Test
    void getHistoryOfPatient() {
        List<NoteBean> history = new ArrayList<>();
        history.add(noteBean);
        when(noteProxy.getHistory("1")).thenReturn(history);
        assertThat(noteService.getHistoryOfPatient("1")).hasSize(1);
        verify(noteProxy).getHistory("1");
    }

    @Test
    void getNoteById() {
        when(noteProxy.getNoteById("1n")).thenReturn(noteBean);
        assertThat(noteService.getNoteById("1n").getContent()).isEqualTo("il est pas malade");
        verify(noteProxy).getNoteById("1n");
    }

    @Test
    void addNote() {
        when(noteProxy.addNote(noteBean)).thenReturn(noteBean);
        assertThat(noteService.addNote(noteBean).getContent()).isEqualTo("il est pas malade");
        verify(noteProxy).addNote(noteBean);
    }

    @Test
    void updateNote() {
        when(noteProxy.updateNote(noteBean)).thenReturn(noteBean);
        assertThat(noteService.updateNote(noteBean).getContent()).isEqualTo("il est pas malade");
        verify(noteProxy).updateNote(noteBean);
    }

    @Test
    void deleteNoteById() {
        when(noteProxy.deleteNoteById("1n")).thenReturn(noteBean);
        assertThat(noteService.deleteNoteById("1n").getId()).isEqualTo("1n");
        verify(noteProxy).deleteNoteById("1n");
    }
}