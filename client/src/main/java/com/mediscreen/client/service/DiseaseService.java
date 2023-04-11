package com.mediscreen.client.service;

import com.mediscreen.client.beans.ReportBean;
import com.mediscreen.client.proxies.DiseaseProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiseaseService {
    private DiseaseProxy diseaseProxy;
    @Autowired
    public DiseaseService(DiseaseProxy diseaseProxy) {
        this.diseaseProxy = diseaseProxy;
    }

    public ReportBean showReport(String idpatient){
        return diseaseProxy.getAssessPatient(idpatient);
    }
}
