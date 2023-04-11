package com.mediscreen.disease.service;

import com.mediscreen.disease.beans.NoteBean;
import com.mediscreen.disease.beans.PatientBean;
import com.mediscreen.disease.model.Report;
import com.mediscreen.disease.proxies.NoteProxy;
import com.mediscreen.disease.proxies.PatientsProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DiseaseServiceTest {
    @Mock
    private PatientsProxy patientsProxy;
    @Mock
    private NoteProxy noteProxy;
    private DiseaseService diseaseService;
    private PatientBean patient;
    private NoteBean note;

    @BeforeEach
    public void setUp() {
        diseaseService = new DiseaseService(patientsProxy,noteProxy);
            patient = new PatientBean("1","Lucas","Ferguson","1968-06-22","M","2 Warren Street ","Dolling","45210","387-866-1399");
            note = new NoteBean("1n","1","01/01/2021","Le patient déclare qu'il «se sent très bien»\n" +
                    "Poids égal ou inférieur au poids recommandé"+"Anticorps");
    }

    @Test
    public void calculAgeTest(){
        String idPatient = "1";
        when(patientsProxy.getPatientBeanById(idPatient)).thenReturn(patient);
        assertThat(diseaseService.calculAge(idPatient)).isPositive();
        verify(patientsProxy).getPatientBeanById(idPatient);
    }

    @Test
    public void numberTriggerTest(){
        List<NoteBean> notes = new ArrayList<>();
        notes.add(note);
        when(noteProxy.getHistory("1")).thenReturn(notes);
        assertThat(diseaseService.numberTrigger("1")).isEqualTo(2);
        verify(noteProxy).getHistory("1");
    }

    @Test
    public void reportDiseasseTestEarlyOnset(){
        List<NoteBean> notes =new ArrayList<>();
        NoteBean note1 = new NoteBean("1n","1","01/01/2021","Le patient déclare qu'il «se sent très bien»\n" +
                "Poids égal ou inférieur au poids recommandé"+"Anticorps Réaction Rechute Vertige Cholestérol Anormal Fumeur ");
        notes.add(note1);
        Report report = new Report("1",54,8,"Early Onset");
        when(noteProxy.getHistory("1")).thenReturn(notes);
        when(patientsProxy.getPatientBeanById("1")).thenReturn(patient);
        Report result = diseaseService.reportDisease("1");
        assertThat(result.getAssess()).isEqualTo(report.getAssess());
        verify(noteProxy).getHistory("1");
        verify(patientsProxy,times(2)).getPatientBeanById("1");
    }
    @Test
    public void reportDiseasseTestInDanger(){
        List<NoteBean> notes =new ArrayList<>();
        NoteBean note1 = new NoteBean("1n","1","01/01/2021","Le patient déclare qu'il «se sent très bien»\n" +
                "Poids égal ou inférieur au poids recommandé"+"Anticorps  Vertige Cholestérol Anormal Fumeur ");
        notes.add(note1);
        Report report = new Report("1",54,6,"In Danger");
        when(noteProxy.getHistory("1")).thenReturn(notes);
        when(patientsProxy.getPatientBeanById("1")).thenReturn(patient);
        Report result = diseaseService.reportDisease("1");
        assertThat(result.getAssess()).isEqualTo(report.getAssess());
        verify(noteProxy).getHistory("1");
        verify(patientsProxy,times(2)).getPatientBeanById("1");
    }
    @Test
    public void reportDiseasseTestBorderLine(){
        List<NoteBean> notes =new ArrayList<>();
        NoteBean note1 = new NoteBean("1n","1","01/01/2021","Le patient déclare qu'il «se sent très bien»\n" +
                "Poids égal ou inférieur au poids recommandé"+"Anticorps ");
        notes.add(note1);
        Report report = new Report("1",54,2,"Borderline");
        when(noteProxy.getHistory("1")).thenReturn(notes);
        when(patientsProxy.getPatientBeanById("1")).thenReturn(patient);
        Report result = diseaseService.reportDisease("1");
        assertThat(result.getAssess()).isEqualTo(report.getAssess());
        verify(noteProxy).getHistory("1");
        verify(patientsProxy,times(2)).getPatientBeanById("1");
    }

    @Test
    public void reportDiseasseTestNone(){
        List<NoteBean> notes =new ArrayList<>();
        NoteBean note1 = new NoteBean("1n","1","01/01/2021","Le patient déclare qu'il «se sent très bien»");
        notes.add(note1);
        Report report = new Report("1",54,0,"None");
        when(noteProxy.getHistory("1")).thenReturn(notes);
        when(patientsProxy.getPatientBeanById("1")).thenReturn(patient);
        Report result = diseaseService.reportDisease("1");
        assertThat(result.getAssess()).isEqualTo(report.getAssess());
        verify(noteProxy).getHistory("1");
        verify(patientsProxy,times(2)).getPatientBeanById("1");
    }
}