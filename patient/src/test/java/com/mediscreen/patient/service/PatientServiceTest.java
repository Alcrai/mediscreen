package com.mediscreen.patient.service;

import com.mediscreen.patient.model.Patient;
import com.mediscreen.patient.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {
    @Mock
    private PatientRepository patientRepository;
    private PatientService patientService;
    private Patient patient;

    @BeforeEach
    public void init(){
        patientService = new PatientService(patientRepository);
        patient = new Patient(1,"Alex","Benson","23/10/1978","Male","1 Culver St","Clover","15640","123-123-1234");
    }

    @Test
    public void findAllTest(){
        List<Patient> patients = new ArrayList<>();
        patients.add(patient);
        when(patientRepository.findAll()).thenReturn(patients);
        assertThat(patientService.findAll().size()).isEqualTo(1);
        verify(patientRepository).findAll();
    }

    @Test
    public void findByIdTest(){
       int id =1;
       when(patientRepository.findById(id)).thenReturn(Optional.of(patient));
       assertThat(patientService.findById(id)).isEqualTo(patient);
       verify(patientRepository).findById(id);
    }

    @Test
    public void addPatientTest(){
        Patient patient2 = new Patient(2,"Florence","Ben","23/10/1978","Male","1 Culver St","Clover","15640","123-123-1234");
        when(patientRepository.save(patient2)).thenReturn(patient2);
        assertThat(patientService.addPatient(patient2)).isEqualTo(patient2);
        verify(patientRepository).save(patient2);
    }

    @Test
    public void updatePatientTest(){
        int id = 1;
        when(patientRepository.existsById(id)).thenReturn(true);
        patient.setFirstName("Killian");
        when(patientRepository.save(patient)).thenReturn(patient);
        Patient patient2 = patientService.updatePatient(patient);
        assertThat(patient2.getFirstName()).isEqualTo("Killian");
        verify(patientRepository).existsById(id);
        verify(patientRepository).save(patient);
    }

    @Test
    public void deletePatientTest(){
        int id = 1;
        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));
        assertThat(patientService.deletePatient(id)).isEqualTo(patient);
        verify(patientRepository, times(2)).findById(id);
        verify(patientRepository).deleteById(id);
    }
}
