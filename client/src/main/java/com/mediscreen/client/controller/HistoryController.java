package com.mediscreen.client.controller;

import com.mediscreen.client.beans.NoteBean;
import com.mediscreen.client.beans.PatientBean;
import com.mediscreen.client.beans.ReportBean;
import com.mediscreen.client.service.DiseaseService;
import com.mediscreen.client.service.NoteService;
import com.mediscreen.client.service.PatientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Log4j2
@Controller
public class HistoryController {
    private PatientService patientService;
    private NoteService noteService;
    private DiseaseService diseaseService;

    @Autowired
    public HistoryController(PatientService patientService, NoteService noteService, DiseaseService diseaseService) {
        this.patientService = patientService;
        this.noteService = noteService;
        this.diseaseService = diseaseService;
    }

    @GetMapping("/history/patientList")
    public String homeHistory(Model model , ReportBean reportBean) {
        model.addAttribute("patients", patientService.findAll());
        model.addAttribute("report",reportBean);
        log.info("load View patientlist");
        return "history/patientList";
    }

    @GetMapping("/assess/{id}")
    public String showReport(@PathVariable("id")String idPatient,Model model , RedirectAttributes redirectAttributes) {
        model.addAttribute("patients", patientService.findAll());
        model.addAttribute("report", diseaseService.showReport(idPatient));
        log.info("load View patientlist and report" + diseaseService.showReport(idPatient).toString());
        return "history/patientList";
    }

    @GetMapping("/history/notelist/{idPatient}")
    public String getHistoryOfPatient(Model model, @PathVariable("idPatient") String idPatient) {
        model.addAttribute("patient", patientService.getPatientBeanById(idPatient));
        model.addAttribute("notes", noteService.getHistoryOfPatient(idPatient));
        log.info("load View noteList");
        return "history/notelist";
    }

    @GetMapping("/history/noteadd/{idPatient}")
    public String getAddNote(@PathVariable("idPatient") String idPatient,NoteBean note, Model model) {
        model.addAttribute("note", note);
        model.addAttribute("patient", patientService.getPatientBeanById(idPatient));
        log.info("load View noteAdd");
        return "history/noteadd";
    }

    @PostMapping("/history/noteadd/validate/{idPatient}")
    public String noteAddValidate(@Valid NoteBean note, @PathVariable("idPatient") String idPatient, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            model.addAttribute("patient", patientService.getPatientBeanById(idPatient));
            log.info("add new note : " + note.toString());
            note.setPatientId(idPatient);
            noteService.addNote(note);
            return "redirect:/history/notelist/" + idPatient;
        } else {
            log.error("Error Add Note");
            return "redirect:/history/noteadd/" + idPatient;
        }
    }

    @GetMapping("/history/noteupdate/{idNote}")
    public String showNoteUpdate(@PathVariable("idNote") String idNote, Model model) {
        NoteBean note = noteService.getNoteById(idNote);
        PatientBean patient = patientService.getPatientBeanById(note.getPatientId());
        model.addAttribute("note", note);
        model.addAttribute("patient", patient);
        log.info("load view for update note idNote" + idNote);
        return "history/noteupdate";
    }

    @PostMapping("/history/noteupdate/validate/{idNote}")
    public String updatePatient(@PathVariable("idNote") String idNote, @Valid NoteBean note,
                                BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "redirect:/history/noteupdate";
        }
        note.setId(idNote);
        model.addAttribute("notes", noteService.getHistoryOfPatient(note.getPatientId()));
        model.addAttribute("patient", patientService.getPatientBeanById(note.getPatientId()));
        noteService.updateNote(note);
        log.info("update note : " + note.toString());

        return "redirect:/history/notelist/" + note.getPatientId();
    }

    @GetMapping("/history/notedelete/{idNote}")
    public String deleteNote(@PathVariable("idNote") String idNote, Model model,RedirectAttributes redirectAttributes) {
        NoteBean noteExist = noteService.getNoteById(idNote);
        if (noteExist.getId()!=null) {
            noteService.deleteNoteById(idNote);
            log.info("delete note with id : " + idNote);
            model.addAttribute("note", noteService.getHistoryOfPatient(noteExist.getPatientId()));
            model.addAttribute("patient", patientService.getPatientBeanById(noteExist.getPatientId()));

        }else{
            log.error("Id does not exist");
        }
        return "redirect:/history/notelist/" + noteExist.getPatientId();
    }

}
