package com.mediscreen.disease.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Report {
    private String idPatient;
    private int age;
    private int numberTrigger;
    private String assess;

    public Report(String idPatient, int age, int numberTrigger, String assess) {
        this.idPatient = idPatient;
        this.age = age;
        this.numberTrigger = numberTrigger;
        this.assess = assess;
    }
}
