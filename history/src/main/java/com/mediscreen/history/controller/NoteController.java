package com.mediscreen.history.controller;

import com.mediscreen.history.model.Note;
import com.mediscreen.history.service.NoteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@Log4j2
public class NoteController {
    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/history/{idPatient}")
    public List<Note> getHistory(@PathVariable("idPatient") String idPatient){
        log.info("load history of id ="+idPatient);
        return noteService.findAllByIdPatient(idPatient);
    }

    @GetMapping("history/note/{idNote}")
    public Note getNoteById(@PathVariable("idNote") String idNote){
        log.info("Load note by id:" + idNote);
        return noteService.findNoteById(idNote);
    }

    @PostMapping("/history/add")
    public ResponseEntity<Note> addNote(@RequestBody Note note){
        Note noteAdded = note;
        if(Objects.isNull(noteAdded)){
            log.error("note is null");
            return ResponseEntity.noContent().build();
        }
        Note noteSave = noteService.addNote(note);
        log.info("Add note :"+ noteSave.toString());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(note)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/history/update")
    public ResponseEntity<Note> updateNote(@RequestBody Note note){
        Note noteAdded = note;
        if(Objects.isNull(noteAdded)){
            log.error("Note is null");
            return ResponseEntity.noContent().build();
        }
        Note noteUpdate = noteService.updateNote(note);
        log.info("Update note :"+ noteUpdate.toString());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(note)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/history/delete/{id}")
    public Map<String,Boolean> deleteNoteById(@PathVariable("id")String id){
        log.info("delete patient by id:"+ id);
        noteService.deleteNoteByIdNote(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
