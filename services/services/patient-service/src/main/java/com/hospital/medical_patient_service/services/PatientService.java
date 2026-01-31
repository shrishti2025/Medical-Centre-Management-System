package com.hospital.medical_patient_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.medical_patient_service.entity.Patient;
import com.hospital.medical_patient_service.exception.ResourceNotFoundException;
import com.hospital.medical_patient_service.repository.PatientRepository;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repo;

    public Patient createPatient(Patient patient, Long userId) {
    	if (userId == null) {
            throw new IllegalArgumentException("User ID is required");
        }

        Patient existing = repo.findByUserId(userId);

        if (existing != null) {
            throw new IllegalArgumentException(
                "Patient profile already exists for this user"
            );
        }
        patient.setPatientId(null);
        patient.setUserId(userId);
        return repo.save(patient);
    }

    public Patient getByUserId(Long userId) {
    	if (userId == null) {
            throw new IllegalArgumentException("User ID is required");
        }

        Patient patient = repo.findByUserId(userId);

        if (patient == null) {
            throw new ResourceNotFoundException(
                "Patient profile not found for userId: " + userId
            );
        }

        return patient;
    }

    public Patient updatePatient(Patient patient, Long userId) {
        Patient existing = repo.findByUserId(userId);
        if (existing == null) {
            throw new ResourceNotFoundException(
                "Patient profile not found for userId: " + userId
            );
        }
        patient.setPatientId(existing.getPatientId());
        patient.setUserId(userId);
        return repo.save(patient);
    }
    
 
    public void deletePatient(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID is required");
        }
        
        Patient patient = repo.findByUserId(userId);
        if (patient == null) {
            throw new ResourceNotFoundException("Patient profile not found for userId: " + userId);
        }
        
        repo.delete(patient);
    }

    public void deleteAllPatients() {
        repo.deleteAll();
    }

}


//@Service
//public class PatientService {
//
//  @Autowired
//  private PatientRepository patientRepository;
//
//  public Patient createPatient(Patient patient, Long userId) {
//
//      if (patientRepository.findByUserId(userId) != null) {
//          throw new ResourceNotFoundException("Patient profile already exists");
//      }
//
//      patient.setUserId(userId);
//      return patientRepository.save(patient);
//  }
//
//  public Patient getByUserId(Long userId) {
//      Patient patient = patientRepository.findByUserId(userId);
//      if (patient == null) {
//          throw new ResourceNotFoundException("Patient not found");
//      }
//      return patient;
//  }
//
//
//  public Patient updatePatient(Patient updatedPatient, Long userId) {
//
//      Patient existing = getByUserId(userId);
//      if (existing == null) {
//          throw new RuntimeException("Patient not found");
//      }
//
//      existing.setFullName(updatedPatient.getFullName());
//      //existing.setAge(updatedPatient.getAge());
//      existing.setGender(updatedPatient.getGender());
//      existing.setPhone(updatedPatient.getPhone());
//      existing.setAddress(updatedPatient.getAddress());
//
//      return patientRepository.save(existing);
//  }
//}
