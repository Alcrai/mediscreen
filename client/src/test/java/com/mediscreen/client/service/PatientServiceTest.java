package com.mediscreen.client.service;

import com.mediscreen.client.beans.PatientBean;
import com.mediscreen.client.proxies.PatientsProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {
    @Mock
    private PatientsProxy patientsProxy;

    private PatientService patientService;
    private PatientBean patient;

    @BeforeEach
    public void init(){
        patientService = new PatientService(patientsProxy);
        patient = new PatientBean("1","Alex","Benson","23/10/1978","Male","1 Culver St","Clover","15640","123-123-1234");
    }
    @Test
    void findAll() {
        List<PatientBean> patients = new ArrayList<>();
        patients.add(patient);
        when(patientsProxy.patientBeanList()).thenReturn(patients);
        assertThat(patientService.findAll().size()).isEqualTo(1);
        verify(patientsProxy).patientBeanList();
    }

    @Test
    void addPatient() {
        PatientBean patient2 = new PatientBean("2","Florence","Ben","23/10/1978","Male","1 Culver St","Clover","15640","123-123-1234");
        when(patientsProxy.addPatientBean(patient2)).thenReturn(patient2);
        assertThat(patientService.addPatient(patient2)).isEqualTo(patient2);
        verify(patientsProxy).addPatientBean(patient2);
    }

    @Test
    void getPatientBeanById() {
        String id ="1";
        when(patientsProxy.getPatientBeanById(id)).thenReturn(patient);
        assertThat(patientService.getPatientBeanById(id)).isEqualTo(patient);
        verify(patientsProxy).getPatientBeanById(id);
    }

    @Test
    void updatePatient() {
        PatientBean patient2 = new PatientBean("2","Florence","Ben","23/10/1978","Male","1 Culver St","Clover","15640","123-123-1234");
        when(patientsProxy.updatePatientBean(patient2)).thenReturn(patient2);
        assertThat(patientService.updatePatient(patient2)).isEqualTo(patient2);
        verify(patientsProxy).updatePatientBean(patient2);
    }

    @Test
    void deletePatient() {
        PatientBean patient2 = new PatientBean("2","Florence","Ben","23/10/1978","Male","1 Culver St","Clover","15640","123-123-1234");
        when(patientsProxy.deletePatientById("2")).thenReturn(patient2);
        assertThat(patientService.deletePatient("2")).isEqualTo(patient2);
        verify(patientsProxy).deletePatientById("2");
    }

    @Test
    void findByName() {
        List<PatientBean> patients = new ArrayList<>();
        patients.add(patient);
        when(patientsProxy.patientBeanList()).thenReturn(patients);
        assertThat(patientService.findByName("Benson")).hasSize(1);
        verify(patientsProxy).patientBeanList();
    }
}