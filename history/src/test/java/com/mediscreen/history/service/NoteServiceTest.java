package com.mediscreen.history.service;

import com.mediscreen.history.model.Note;
import com.mediscreen.history.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {
    @Mock
    private NoteRepository noteRepository;
    private NoteService noteService;
    private Note note;

    @BeforeEach
    public void init(){
        noteService = new NoteService(noteRepository);
        note = new Note("1aa","1","01/01/2021","Lorem Ipsum");
    }

    @Test
    public void findAllbyIdPatient(){
        Note note1 = new Note("2aa","2","01/01/2021","Lorem Ipsum");
        Note note2 = new Note("3aa","1","02/01/2021","Lorem Ipsum");
        List<Note> notes = new ArrayList<>();
        notes.add(note);
        notes.add(note2);
        String id = "1";
        when(noteRepository.findByPatientId(id)).thenReturn(notes);
        assertThat(noteService.findAllByIdPatient(id).size()).isEqualTo(2);
        verify(noteRepository).findByPatientId(id);
    }

    @Test
    void addNote() {
        Note note1 = new Note("2aa","2","01/01/2021","Lorem Ipsum");
        when(noteRepository.insert(note1)).thenReturn(note1);
        assertThat(noteService.addNote(note1)).isEqualTo(note1);
        verify(noteRepository).insert(note1);
    }

    @Test
    void updateNote() {
        Note noteUpdate = new Note("1aa","1","01/01/2021","Blala");
        when(noteRepository.findOne(any(Example.class))).thenReturn(Optional.of(note));
        when(noteRepository.save(noteUpdate)).thenReturn(noteUpdate);
        Note noteTest =noteService.updateNote(noteUpdate);
        assertThat(noteTest.getContent()).isEqualTo("Blala");
        verify(noteRepository).findOne(any(Example.class));
        verify(noteRepository).save(noteUpdate);
    }

    @Test
    void deleteNoteByIdNote() {
        String id ="1aa";
        when(noteRepository.findOne(any(Example.class))).thenReturn(Optional.of(note));
        assertThat(noteService.deleteNoteByIdNote(id)).isEqualTo(note);
        verify(noteRepository).findOne(any(Example.class));
        verify(noteRepository).deleteById(id);
    }
}