package com.mediscreen.disease.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Trigger {
    HEMOGLOBINE_A1C("Hémoglobine A1C"),
    MICROALBUMINE("Microalbumine"),
    TAILLE("Taille"),
    POIDS("Poids"),
    FUMEUR("Fumeur"),
    ANORMAL("Anormal"),
    CHOLESTEROL("Cholestérol"),
    VERTIGE("Vertige"),
    RECHUTE("Rechute"),
    REACTION("Réaction"),
    ANTICORPS("Anticorps");

    private String valeur;
    Trigger(String valeur) {
        this.valeur = valeur;
    }

    public String value(){
        return valeur;
    }

}
