package com.hospital.medical_patient_service.dto;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private Long patientId;
    private Long userId;
    private String fullName;
    private String gender;
    private LocalDate dob;
    private String phone;
    private String address;
    private String bloodGroup;
    private String emergencyContact;
}
