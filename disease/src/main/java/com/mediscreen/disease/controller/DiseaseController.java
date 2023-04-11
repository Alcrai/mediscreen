package com.mediscreen.disease.controller;

import com.mediscreen.disease.model.Report;
import com.mediscreen.disease.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiseaseController {
    private DiseaseService diseaseService;

    @Autowired
    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @GetMapping("/disease/assess/{idPatient}")
    public Report getAssessPatient(@PathVariable("idPatient")String idPatient){
        return diseaseService.reportDisease(idPatient);
    }

}

