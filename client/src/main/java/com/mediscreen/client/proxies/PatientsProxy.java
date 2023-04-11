package com.mediscreen.client.proxies;

import com.mediscreen.client.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "patient", url = "http://mediscreen-patient:8090")
//@FeignClient(name = "patient", url = "http://localhost:8090")
public interface PatientsProxy {
    @GetMapping("/patient")
    List<PatientBean> patientBeanList();
    @GetMapping("/patient/{id}")
    PatientBean getPatientBeanById(@PathVariable("id")String id);
    @PostMapping("/patient/add")
    PatientBean addPatientBean(@RequestBody PatientBean patientBean);
    @PutMapping("/patient/update")
    PatientBean updatePatientBean(@RequestBody PatientBean patientBean);
    @DeleteMapping("patient/delete/{id}")
    PatientBean deletePatientById(@PathVariable("id")String id);
}
