package com.mediscreen.history.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "history")
@Data
public class Note {
    @Id
    private String id;
    private String patientId;
    private String date;
    private String content;

    public Note(String id, String patientId, String date, String content) {
        this.id = id;
        this.patientId = patientId;
        this.date = date;
        this.content = content;
    }

    public Note() {
    }
}
