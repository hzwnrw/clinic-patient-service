package com.clinicsystem.patient.mapper;

import com.clinicsystem.patient.dto.PatientDTO;
import com.clinicsystem.patient.model.Gender;
import com.clinicsystem.patient.model.Patient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PatientMapper {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public static PatientDTO toDTO(Patient patient) {
        return new PatientDTO(
                patient.getId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getDateOfBirth() != null ? formatter.format(patient.getDateOfBirth()) : null,
                patient.getGender() != null ? patient.getGender().toString() : null,
                patient.getPhoneNumber(),
                patient.getEmail(),
                patient.getAddress(),
                new ArrayList<>(), // Map these if needed
                new ArrayList<>(),
                new ArrayList<>()
        );
    }

    public static Patient toEntity(PatientDTO dto) {
        Date date = null;
        try {
            if (dto.getDateOfBirth() != null) {
                date = formatter.parse(dto.getDateOfBirth());
            }
        } catch (Exception e) {
            throw new RuntimeException("Invalid date format, expected yyyy-MM-dd");
        }

        return Patient.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .dateOfBirth(date)
                .gender(dto.getGender() != null ? Gender.valueOf(dto.getGender()) : null)
                .phoneNumber(dto.getPhoneNumber())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .build();
    }
}
