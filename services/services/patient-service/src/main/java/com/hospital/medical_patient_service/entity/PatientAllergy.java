package com.hospital.medical_patient_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient_allergies")
public class PatientAllergy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long allergyId;
    
	private Long patientId;
	
	@NotBlank(message = "Allergy name cannot be empty")
    @Size(min = 2, max = 100, message = "Allergy name must be between 2 and 100 characters")
    private String allergyName;
	
	@NotBlank(message = "Severity is required")
    private String severity;
    
	 @Size(max = 255, message = "Notes cannot exceed 255 characters")
    private String notes;

   
}
