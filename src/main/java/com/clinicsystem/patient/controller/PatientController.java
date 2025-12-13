package com.clinicsystem.patient.controller;

import com.clinicsystem.patient.dto.PatientDTO;
import com.clinicsystem.patient.mapper.PatientMapper;
import com.clinicsystem.patient.model.Patient;
import com.clinicsystem.patient.repository.PatientRepository;
import com.clinicsystem.patient.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    private final PatientService patientService;
    private final PatientRepository patientRepository;
    public PatientController(PatientService patientService, PatientRepository patientRepository) {
        this.patientService = patientService;
        this.patientRepository = patientRepository;
    }

    // Create a new patient
    @PostMapping("/register")
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patient) {
        return ResponseEntity.ok(patientService.createPatient(patient));
    }

    // Get all patients with pageable support
    @GetMapping("/all")
    public Page<PatientDTO> getAllPatients(Pageable pageable) {
        Page<Patient> patientPage = patientRepository.findAll(pageable);
        logger.info("Fetched {} patients", patientPage.getNumberOfElements());
        patientPage.getContent().forEach(patient ->
                logger.info("Patient: id={}, firstName={}, lastName={}",
                        patient.getId(), patient.getFirstName(), patient.getLastName()));
        return patientPage.map(PatientMapper::toDTO);
    }


    // Get a patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatientById(@PathVariable Long id) {
        Optional<PatientDTO> patient = patientService.getPatientById(id);
        return patient.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update patient details
    @PutMapping("/{id}")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable Long id, @RequestBody PatientDTO updatedPatient) {
        return ResponseEntity.ok(patientService.updatePatient(id, updatedPatient));
    }

    // Delete a patient
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
