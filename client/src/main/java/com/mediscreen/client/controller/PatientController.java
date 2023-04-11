package com.mediscreen.client.controller;

import com.mediscreen.client.beans.PatientBean;
import com.mediscreen.client.service.PatientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
@Log4j2
@Controller
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping("/patient/list")
    public String homePatient(Model model){
        List<PatientBean> patients =  patientService.findAll();
        model.addAttribute("patients", patients);
        log.info("Load view Patient List");
        return "patient/list";
    }

    @GetMapping("/patient/add")
    public String addPatient(PatientBean patient,Model model) {
        log.info("Load view add Patient");
        model.addAttribute("patient",patient);
        return "patient/add";
    }

    @PostMapping("/patient/validate")
    public String validate(@Valid PatientBean patient, BindingResult result, Model model) {
        if (!result.hasErrors()) {
                log.info("add new user : "+patient.toString());
                patientService.addPatient(patient);
                return "redirect:/patient/list";
            }else {
            log.error("Error Add Patient");
            return "redirect:/patient/add";
        }
    }

    @GetMapping("/patient/update/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        PatientBean patient = patientService.getPatientBeanById(id);
        model.addAttribute("patient", patient);
        log.info("load view for update Patient");
        return "patient/update";
    }

    @PostMapping("/patient/update/{id}")
    public String updatePatient(@PathVariable("id") String id, @Valid PatientBean patient,
                             BindingResult result, Model model,RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "redirect:/patient/update";
        }
        patientService.updatePatient(patient);
        log.info("update patient : " + patient.toString());
        model.addAttribute("patients", patientService.findAll());
        return "redirect:/patient/list";
    }

    @GetMapping("/patient/delete/{id}")
    public String deletePatient(@PathVariable("id") String id, Model model) {
        PatientBean patient = patientService.getPatientBeanById(id);
        if (!patient.getId().isEmpty()) {
            patientService.deletePatient(id);
            log.info("delete patient with id : " + id);
            model.addAttribute("patient", patientService.findAll());

        }else{
            log.error("Id does not exist");
        }
        return "redirect:/patient/list";
    }


}
