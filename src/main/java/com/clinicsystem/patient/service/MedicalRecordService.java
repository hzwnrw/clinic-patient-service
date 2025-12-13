package com.clinicsystem.patient.service;

import com.clinicsystem.patient.model.MedicalRecord;
import com.clinicsystem.patient.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public MedicalRecord createMedicalRecord(MedicalRecord record) {
        return medicalRecordRepository.save(record);
    }

    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepository.findAll();
    }

    public void deleteMedicalRecord(Long id) {
        medicalRecordRepository.deleteById(id);
    }
}
