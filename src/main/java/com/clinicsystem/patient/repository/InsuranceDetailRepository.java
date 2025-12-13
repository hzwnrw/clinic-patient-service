package com.clinicsystem.patient.repository;

import com.clinicsystem.patient.model.InsuranceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InsuranceDetailRepository extends JpaRepository<InsuranceDetail, Long> {}
