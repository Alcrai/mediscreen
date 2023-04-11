package com.mediscreen.disease.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class NoteBean {
    private String id;
    private String patientId;
    private String date;
    private String content;

    public NoteBean(String id, String patientId, String date, String content) {
        this.id = id;
        this.patientId = patientId;
        this.date = date;
        this.content = content;
    }
}
