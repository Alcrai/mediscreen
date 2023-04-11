package com.mediscreen.client.service;

import com.mediscreen.client.beans.NoteBean;
import com.mediscreen.client.beans.ReportBean;
import com.mediscreen.client.proxies.DiseaseProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiseaseServiceTest {
    @Mock
    private DiseaseProxy diseaseProxy;
    private DiseaseService diseaseService;
    private ReportBean reportBean;

    @BeforeEach
    void setUp() {
        diseaseService = new DiseaseService(diseaseProxy);
        reportBean = new ReportBean("1",24,8,"Early Onset");
    }

    @Test
    void showReport() {
        when(diseaseProxy.getAssessPatient("1")).thenReturn(reportBean);
        assertThat(diseaseService.showReport("1").getAssess()).isEqualTo("Early Onset");
        verify(diseaseProxy).getAssessPatient("1");

    }
}