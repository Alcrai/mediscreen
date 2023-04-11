package com.mediscreen.client.service;

import com.mediscreen.client.beans.PatientBean;
import com.mediscreen.client.proxies.PatientsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private final PatientsProxy patientsProxy;

    @Autowired
    public PatientService(PatientsProxy patientsProxy) {
        this.patientsProxy = patientsProxy;
    }

    public List<PatientBean> findAll() {
        return patientsProxy.patientBeanList();
    }

    public PatientBean addPatient(PatientBean patient) {
        return patientsProxy.addPatientBean(patient);
    }

    public PatientBean getPatientBeanById(String id) {
        return patientsProxy.getPatientBeanById(id);
    }

    public PatientBean updatePatient(PatientBean patient) {
        return patientsProxy.updatePatientBean(patient);
    }

    public PatientBean deletePatient(String id) {
        return patientsProxy.deletePatientById(id);
    }

    public List<PatientBean> findByName(String lastName) {
        List<PatientBean> patientByName = findAll();
        return patientByName.stream().filter(p->p.getLastName()==lastName).collect(Collectors.toList());
    }
}
