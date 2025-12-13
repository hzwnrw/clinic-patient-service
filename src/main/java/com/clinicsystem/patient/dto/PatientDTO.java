package com.clinicsystem.patient.dto;

import com.clinicsystem.patient.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor

public class PatientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String dateOfBirth; // as String for simplicity
    private String gender;
    private String phoneNumber;
    private String email;
    private String address;
    private List<Object> medicalRecords = new ArrayList<>();
    private List<Object> appointments = new ArrayList<>();
    private List<Object> emergencyContacts = new ArrayList<>();

    public PatientDTO(Long id, String firstName, String lastName, String dateOfBirth, String gender, String phoneNumber, String email, String address, List<Object> medicalRecords, List<Object> appointments, List<Object> emergencyContacts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.medicalRecords = medicalRecords;
        this.appointments = appointments;
        this.emergencyContacts = emergencyContacts;
    }

    public PatientDTO(Patient patient) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }
}
