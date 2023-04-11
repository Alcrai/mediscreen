package com.mediscreen.history.repository;

import com.mediscreen.history.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends MongoRepository<Note,String> {
    public List<Note> findByPatientId(String patientId);
}
