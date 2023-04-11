package com.mediscreen.client.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReportBean {
    private String idPatient;
    private int age;
    private int numberTrigger;
    private String assess;

    public ReportBean(String idPatient, int age, int numberTrigger, String assess) {
        this.idPatient = idPatient;
        this.age = age;
        this.numberTrigger = numberTrigger;
        this.assess = assess;
    }
}
