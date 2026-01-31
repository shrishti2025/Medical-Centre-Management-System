package com.hospital.medical_patient_service.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hospital.medical_patient_service.entity.Patient;
import com.hospital.medical_patient_service.entity.PatientAllergy;
import com.hospital.medical_patient_service.exception.ResourceNotFoundException;
import com.hospital.medical_patient_service.repository.PatientAllergyRepository;
import com.hospital.medical_patient_service.repository.PatientRepository;

@Service
public class PatientAllergyService {

    @Autowired
    private PatientAllergyRepository allergyRepository;

    @Autowired
    private PatientRepository patientRepository;

    public PatientAllergy addAllergy(PatientAllergy allergy, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
            .orElseThrow(() -> new ResourceNotFoundException("Patient not found: " + patientId));
        
        allergy.setAllergyId(null);
        allergy.setPatientId(patient.getPatientId());
        return allergyRepository.save(allergy);
    }

    public List<PatientAllergy> getAllergies(Long patientId) {
        return allergyRepository.findByPatientId(patientId);
    }

    public PatientAllergy updateAllergy(Long allergyId, PatientAllergy allergy, Long patientId) {
        PatientAllergy existing = allergyRepository.findById(allergyId)
            .orElseThrow(() -> new ResourceNotFoundException("Allergy not found"));

        if (!existing.getPatientId().equals(patientId)) {
            throw new ResourceNotFoundException("Unauthorized access");
        }

        existing.setAllergyName(allergy.getAllergyName());
        existing.setSeverity(allergy.getSeverity());
        existing.setNotes(allergy.getNotes());

        return allergyRepository.save(existing);
    }
}




//package com.hospital.medical_patient_service.services;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.hospital.medical_patient_service.entity.Patient;
//import com.hospital.medical_patient_service.entity.PatientAllergy;
//import com.hospital.medical_patient_service.exception.ResourceNotFoundException;
//import com.hospital.medical_patient_service.repository.PatientAllergyRepository;
//import com.hospital.medical_patient_service.repository.PatientRepository;
//
//@Service
//public class PatientAllergyService {
//
//    @Autowired
//    private PatientAllergyRepository allergyRepository;
//
//    @Autowired
//    private PatientRepository patientRepository;
//
//    public PatientAllergy addAllergy(PatientAllergy allergy, Long userId) {
//
//        Patient patient = patientRepository.findByUserId(userId);
//        if (patient == null) {
//            throw new ResourceNotFoundException("Patient not found");
//        }
//
//        allergy.setAllergyId(null);
//        allergy.setPatientId(patient.getPatientId());
//        return allergyRepository.save(allergy);
//    }
//
//    public List<PatientAllergy> getAllergies(Long userId) {
//
//        Patient patient = patientRepository.findByUserId(userId);
//        if (patient == null) {
//            throw new ResourceNotFoundException("Patient not found");
//        }
//
//        return allergyRepository.findByPatientId(patient.getPatientId());
//    }
//
//    public PatientAllergy updateAllergy(Long allergyId,
//                                        PatientAllergy allergy,
//                                        Long userId) {
//
//        Patient patient = patientRepository.findByUserId(userId);
//        if (patient == null) {
//            throw new ResourceNotFoundException("Patient not found");
//        }
//
//        PatientAllergy existing = allergyRepository.findById(allergyId)
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("Allergy not found"));
//
//        if (!existing.getPatientId().equals(patient.getPatientId())) {
//            throw new ResourceNotFoundException("Unauthorized access");
//        }
//
//        existing.setAllergyName(allergy.getAllergyName());
//        existing.setSeverity(allergy.getSeverity());
//        existing.setNotes(allergy.getNotes());
//
//        return allergyRepository.save(existing);
//    }
//}
