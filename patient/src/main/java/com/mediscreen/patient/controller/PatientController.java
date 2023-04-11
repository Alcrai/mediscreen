package com.mediscreen.patient.controller;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.service.PatientService;
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

@Log4j2
@RestController
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patient")
    public List<Patient> getPatientList(){
        log.info("Load view Patient List");
        return patientService.findAll();
    }

    @GetMapping("/patient/{id}")
    public Patient getPatientById(@PathVariable("id")int id){
        log.info("Load patient by Id="+id);
        return patientService.findById(id);
    }

    @PostMapping("/patient/add")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        Patient patientAdded = patient;
        if(Objects.isNull(patientAdded)){
            log.error("patient is null");
            return ResponseEntity.noContent().build();
        }
        log.info("Add patient :"+ patient.toString());
        patientService.addPatient(patient);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(patient)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/patient/update")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient){
        Patient patientAdded = patient;
        if(Objects.isNull(patientAdded)){
            log.error("patient is null");
            return ResponseEntity.noContent().build();
        }
        log.info("update patient :"+ patient.toString());
        patientService.updatePatient(patient);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(patient)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/patient/delete/{id}")
    public Map<String,Boolean> deletePatientById(@PathVariable("id")int id){
        log.info("delete patient by id:"+ id);
        patientService.deletePatient(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
