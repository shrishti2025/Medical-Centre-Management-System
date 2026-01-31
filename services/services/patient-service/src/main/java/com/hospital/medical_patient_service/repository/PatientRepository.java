package com.hospital.medical_patient_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.medical_patient_service.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByUserId(Long userId);
}

