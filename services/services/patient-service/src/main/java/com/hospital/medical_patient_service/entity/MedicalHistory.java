package com.hospital.medical_patient_service.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medical_history")
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyId;

    @Column(name = "patient_id", nullable =false)
    private Long patientId;

    
    @NotBlank(message = "Condition name is required")
    @Size(min = 2, max = 100, message = "Condition name must be between 2 and 100 characters")
    @Column(name = "condition_name")
    private String conditionName;

    @NotNull(message = "Diagnosis date is required")
    @PastOrPresent(message = "Diagnosis date cannot be in the future")
    @Column(name = "diagnosis_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate diagnosisDate;

    @Size(max = 255, message = "Notes cannot exceed 255 characters")
    private String notes;

    
}
