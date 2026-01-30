package com.hospital.medical_patient_service.dto;

import java.util.List;

import com.hospital.medical_patient_service.entity.MedicalHistory;
import com.hospital.medical_patient_service.entity.Patient;
import com.hospital.medical_patient_service.entity.PatientAllergy;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PatientProfileResponse {

    private Patient patient;
    private List<PatientAllergy> allergies;
    private List<MedicalHistory> medicalHistory;
}
