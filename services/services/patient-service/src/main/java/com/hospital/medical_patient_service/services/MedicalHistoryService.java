
package com.hospital.medical_patient_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.medical_patient_service.entity.MedicalHistory;
import com.hospital.medical_patient_service.entity.Patient;
import com.hospital.medical_patient_service.exception.ResourceNotFoundException;
import com.hospital.medical_patient_service.repository.MedicalHistoryRepository;
import com.hospital.medical_patient_service.repository.PatientRepository;

@Service
public class MedicalHistoryService {

    @Autowired
    private MedicalHistoryRepository historyRepository;

    @Autowired
    private PatientRepository patientRepository;

    public MedicalHistory addHistory(MedicalHistory history, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
            .orElseThrow(() -> new ResourceNotFoundException("Patient not found: " + patientId));
        
        history.setHistoryId(null);
        history.setPatientId(patient.getPatientId());
        return historyRepository.save(history);
    }

    public List<MedicalHistory> getHistory(Long patientId) {
        return historyRepository.findByPatientId(patientId);
    }

    public MedicalHistory updateHistory(Long historyId, MedicalHistory history, Long patientId) {
        MedicalHistory existing = historyRepository.findById(historyId)
            .orElseThrow(() -> new ResourceNotFoundException("Medical history not found"));

        if (!existing.getPatientId().equals(patientId)) {
            throw new ResourceNotFoundException("Unauthorized access");
        }

        existing.setConditionName(history.getConditionName());
        existing.setDiagnosisDate(history.getDiagnosisDate());
        existing.setNotes(history.getNotes());

        return historyRepository.save(existing);
    }
}


//package com.hospital.medical_patient_service.services;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.hospital.medical_patient_service.entity.MedicalHistory;
//import com.hospital.medical_patient_service.entity.Patient;
//import com.hospital.medical_patient_service.exception.ResourceNotFoundException;
//import com.hospital.medical_patient_service.repository.MedicalHistoryRepository;
//import com.hospital.medical_patient_service.repository.PatientRepository;
//
//@Service
//public class MedicalHistoryService {
//
//    @Autowired
//    private MedicalHistoryRepository historyRepository;
//
//    @Autowired
//    private PatientRepository patientRepository;
//
//    public MedicalHistory addHistory(MedicalHistory history, Long userId) {
//
//        Patient patient = patientRepository.findByUserId(userId);
//        if (patient == null) {
//            throw new ResourceNotFoundException("Patient not found");
//        }
//
//        history.setHistoryId(null);
//        history.setPatientId(patient.getPatientId());
//        return historyRepository.save(history);
//    }
//
//    public List<MedicalHistory> getHistory(Long userId) {
//
//        Patient patient = patientRepository.findByUserId(userId);
//        if (patient == null) {
//            throw new ResourceNotFoundException("Patient not found");
//        }
//
//        return historyRepository.findByPatientId(patient.getPatientId());
//    }
//
//    public MedicalHistory updateHistory(Long historyId,
//                                        MedicalHistory history,
//                                        Long userId) {
//
//        Patient patient = patientRepository.findByUserId(userId);
//        if (patient == null) {
//            throw new ResourceNotFoundException("Patient not found");
//        }
//
//        MedicalHistory existing = historyRepository.findById(historyId)
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("History not found"));
//
//        if (!existing.getPatientId().equals(patient.getPatientId())) {
//            throw new ResourceNotFoundException("Unauthorized access");
//        }
//
//        existing.setConditionName(history.getConditionName());
//        existing.setDiagnosisDate(history.getDiagnosisDate());
//        existing.setNotes(history.getNotes());
//
//        return historyRepository.save(existing);
//    }
//}
