package com.mediscreen.disease.service;

import com.mediscreen.disease.beans.NoteBean;
import com.mediscreen.disease.beans.PatientBean;
import com.mediscreen.disease.model.Report;
import com.mediscreen.disease.model.Trigger;
import com.mediscreen.disease.proxies.NoteProxy;
import com.mediscreen.disease.proxies.PatientsProxy;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Log4j2
@Service
public class DiseaseService {
    private PatientsProxy patientsProxy;
    private NoteProxy noteProxy;

    @Autowired
    public DiseaseService(PatientsProxy patientsProxy, NoteProxy noteProxy) {
        this.patientsProxy = patientsProxy;
        this.noteProxy = noteProxy;
    }

    public int calculAge(String idPatient) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmdd");
        Date dateNow = new Date();
        PatientBean patient = patientsProxy.getPatientBeanById(idPatient);
        String birthString = patient.getDateOfBirth();
        Pattern p = Pattern.compile("-");
        Matcher m = p.matcher(birthString);
        Float birthday = Float.valueOf(m.replaceAll(""));
        Float dateNowInt = Float.valueOf(dateFormat.format(dateNow.getTime()));
        Float result = (dateNowInt - birthday) / 10000;
        return result.intValue();
    }

    public int numberTrigger(String idPatient) {
        List<String> triggers = new ArrayList<>();
        Trigger[] arrayTrigger = Trigger.values();
        for (int i = 0; i < arrayTrigger.length; i++) {
            triggers.add(arrayTrigger[i].value());
        }
        List<NoteBean> history = noteProxy.getHistory(idPatient);
        final int[] result = {0};
        history.forEach(c -> {
            triggers.forEach(t -> {
                result[0] += countTriggerInNote(c, t);
            });
        });
        return result[0];
    }

    public Report reportDisease(String idPatient) {
        Report report;
        int age = calculAge(idPatient);
        int nbTrigger = numberTrigger(idPatient);
        PatientBean patient = patientsProxy.getPatientBeanById(idPatient);
        String gender = patient.getGender();
        log.info("gender:"+gender);

        if ((age > 30 && nbTrigger >= 8) || (gender.equals("F")  && (age <= 30) && (nbTrigger >= 7)) ||
                (gender.equals("M") && (age <= 30) && (nbTrigger >= 5))) {
            report = new Report(idPatient,age, nbTrigger, "Early Onset");
            return report;
        } else {
            if ((age > 30 && nbTrigger >= 6) || (gender.equals("M") && (age <= 30) && (nbTrigger >= 4)) ||
                    (gender.equals("M") && (age <= 30) && (nbTrigger >= 3))) {
                report = new Report(idPatient,age, nbTrigger, "In Danger");
                return report;
            } else {
                if ((age >= 30) && (nbTrigger >= 2)) {
                    report = new Report(idPatient,age, nbTrigger, "Borderline");
                    return report;
                } else {
                    if (nbTrigger == 0) {
                        report = new Report(idPatient,age, nbTrigger, "None");
                        return report;
                    }
                }
            }
        }
        return new Report(idPatient, age, nbTrigger, "Inconclusive");
    }

    private int countTriggerInNote(NoteBean note, String trigger) {
        Pattern regex = Pattern.compile(trigger.toLowerCase());
        Matcher matcher;
        matcher = regex.matcher(note.getContent().toLowerCase());
        if (matcher.find()) {
            return 1;
        } else {
            return 0;
        }
    }

}
