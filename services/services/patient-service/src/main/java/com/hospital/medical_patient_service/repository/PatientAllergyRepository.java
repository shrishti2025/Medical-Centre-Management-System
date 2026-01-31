package com.hospital.medical_patient_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.medical_patient_service.entity.PatientAllergy;

public interface PatientAllergyRepository
extends JpaRepository<PatientAllergy, Long> {

List<PatientAllergy> findByPatientId(Long patientId);
}

