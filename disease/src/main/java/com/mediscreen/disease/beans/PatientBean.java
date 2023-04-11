package com.mediscreen.disease.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class PatientBean {
    private String id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String address;
    private String city;
    private String zipCode;
    private String phone;

    public PatientBean(String id, String firstName, String lastName, String dateOfBirth, String gender, String address, String city, String zipCode, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.phone = phone;
    }
}
