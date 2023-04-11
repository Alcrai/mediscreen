package com.mediscreen.history.service;

import com.mediscreen.history.model.Note;
import com.mediscreen.history.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> findAllByIdPatient(String idPatient){
        return noteRepository.findByPatientId(idPatient);
    }

    public Note findNoteById(String idNote){
        return noteRepository.findById(idNote).get();
    }

    public Note addNote(Note note){
        return noteRepository.insert(note);
    }

    public Note updateNote(Note note){
        Note existingNote = new Note();
        existingNote.setId(note.getId());
        Example<Note> example = Example.of(existingNote);
        Optional<Note> noteO = noteRepository.findOne(example);
        existingNote = noteO.get();
        existingNote.setContent(note.getContent());
        return noteRepository.save(existingNote);
    }

    public Note deleteNoteByIdNote(String id){
        Note existingNote = new Note();
        existingNote.setId(id);
        Example<Note> example = Example.of(existingNote);
        existingNote = noteRepository.findOne(example).get();
        if(!(existingNote == null)){noteRepository.deleteById(id);}
        return existingNote;
    }

}
