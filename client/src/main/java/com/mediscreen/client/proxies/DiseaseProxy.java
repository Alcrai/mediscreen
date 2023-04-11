package com.mediscreen.client.proxies;

import com.mediscreen.client.beans.ReportBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "disease", url = "http://mediscreen-disease:8093")
//@FeignClient(name = "disease", url = "http://localhost:8093")
public interface DiseaseProxy {
    @GetMapping("/disease/assess/{idPatient}")
    public ReportBean getAssessPatient(@PathVariable("idPatient")String idPatient);
}
