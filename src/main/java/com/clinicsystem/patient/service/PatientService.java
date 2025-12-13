package com.clinicsystem.patient.service;

import com.clinicsystem.patient.dto.PatientDTO;
import com.clinicsystem.patient.mapper.PatientMapper;
import com.clinicsystem.patient.model.Gender;
import com.clinicsystem.patient.model.Patient;
import com.clinicsystem.patient.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public PatientDTO createPatient(PatientDTO dto) {
        Patient saved = patientRepository.save(PatientMapper.toEntity(dto));
        return PatientMapper.toDTO(saved);
    }

    public Page<PatientDTO> getAllPatients(Pageable pageable) {
        return patientRepository.findAll(pageable)
                .map(PatientMapper::toDTO);
    }

    public Optional<PatientDTO> getPatientById(Long id) {
        return patientRepository.findById(id).map(PatientMapper::toDTO);
    }

    public PatientDTO updatePatient(Long id, PatientDTO dto) {
        return patientRepository.findById(id).map(existing -> {
            existing.setFirstName(dto.getFirstName());
            existing.setLastName(dto.getLastName());
            existing.setDateOfBirth(PatientMapper.toEntity(dto).getDateOfBirth());
            existing.setGender(Gender.valueOf(dto.getGender()));
            existing.setPhoneNumber(dto.getPhoneNumber());
            existing.setEmail(dto.getEmail());
            existing.setAddress(dto.getAddress());
            return PatientMapper.toDTO(patientRepository.save(existing));
        }).orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Patient not found");
        }
        patientRepository.deleteById(id);
    }
}
