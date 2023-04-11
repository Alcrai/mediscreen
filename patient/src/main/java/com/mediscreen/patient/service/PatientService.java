package com.mediscreen.patient.service;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Log4j2
@Service
public class PatientService {
    private PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient findById(int id) {
        return patientRepository.findById(id).get();
    }

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Patient patient) {
        if(patientRepository.existsById(patient.getId())){
            return patientRepository.save(patient);
        }else{
            log.error("id does not exist, update refused");
            return null;
        }
    }

    public Patient deletePatient(int id) {
        Patient patient= new Patient();
        if(patientRepository.findById(id).isPresent()){
            patient = patientRepository.findById(id).get();
            patientRepository.deleteById(id);
        }else{
            log.error("id does not exist delete refused");
        }
        return patient;
    }
}
