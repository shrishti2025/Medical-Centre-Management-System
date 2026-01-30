package com.hospital.medical_patient_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.hospital.medical_patient_service.dto.ApiResponse;
import com.hospital.medical_patient_service.entity.Patient;
import com.hospital.medical_patient_service.services.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService service;

    // 🔐 ADMIN ONLY
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Patient>> createPatient(
            @Valid @RequestBody Patient patient) {

        Patient savedPatient =
                service.createPatient(patient, patient.getUserId());

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Patient profile created successfully",
                        savedPatient
                )
        );
    }

    // 🔐 ADMIN ONLY
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<?>> getAllPatients() {
        return ResponseEntity.ok(
                ApiResponse.success(
                        "GET all patients",
                        "Implement getAllPatients() in service"
                )
        );
    }

    // 🔐 ADMIN + PATIENT
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT')")
    public ResponseEntity<ApiResponse<Patient>> getPatientByUserId(
            @PathVariable Long userId) {

        Patient patient = service.getByUserId(userId);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Patient profile fetched successfully",
                        patient
                )
        );
    }

    // 🔐 ADMIN + PATIENT
    @PutMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT')")
    public ResponseEntity<ApiResponse<Patient>> updatePatient(
            @PathVariable Long userId,
            @Valid @RequestBody Patient patient) {

        Patient updatedPatient =
                service.updatePatient(patient, userId);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Patient profile updated successfully",
                        updatedPatient
                )
        );
    }

    // 🔐 ADMIN ONLY
    @DeleteMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<String>> deletePatient(
            @PathVariable Long userId) {

        service.deletePatient(userId);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Patient deleted successfully",
                        "OK"
                )
        );
    }
}




//package com.hospital.medical_patient_service.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.hospital.medical_patient_service.dto.ApiResponse;
//import com.hospital.medical_patient_service.entity.Patient;
//import com.hospital.medical_patient_service.services.PatientService;
//
//import jakarta.validation.Valid;
//
//@RestController
//@RequestMapping("/patients")
//public class PatientController {
//
//    @Autowired
//    private PatientService service;
//
//    @PostMapping
//    public ResponseEntity<ApiResponse<Patient>> createPatient(@Valid @RequestBody Patient patient) {
//        try {
//            Patient savedPatient = service.createPatient(patient, patient.getUserId());
//            return ResponseEntity.ok(ApiResponse.success("Patient profile created successfully", savedPatient));
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.internalServerError().body(ApiResponse.error("Failed to create patient: " + e.getMessage()));
//        }
//    }
//
//    @GetMapping
//    public ResponseEntity<ApiResponse<?>> getAllPatients() {
//        return ResponseEntity.ok(ApiResponse.success("GET all patients", "Add getAllPatients() in service"));
//    }
//
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<ApiResponse<Patient>> getPatientByUserId(@PathVariable Long userId) {
//        try {
//            Patient patient = service.getByUserId(userId);
//            return ResponseEntity.ok(ApiResponse.success("Patient profile fetched successfully", patient));
//        } catch (Exception e) {
//            return ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));
//        }
//    }
//
//    @PutMapping("/user/{userId}")
//    public ResponseEntity<ApiResponse<Patient>> updatePatient(
//            @PathVariable Long userId, @Valid @RequestBody Patient patient) {
//        try {
//            Patient updatedPatient = service.updatePatient(patient, userId);
//            return ResponseEntity.ok(ApiResponse.success("Patient profile updated successfully", updatedPatient));
//        } catch (Exception e) {
//            return ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));
//        }
//    }
//
//    @DeleteMapping("/user/{userId}")
//    public ResponseEntity<ApiResponse<String>> deletePatient(@PathVariable Long userId) {
//        try {
//            service.deletePatient(userId);
//            return ResponseEntity.ok(ApiResponse.success("Patient deleted successfully", "OK"));
//        } catch (Exception e) {
//            return ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));
//        }
//    }
//}
//



//package com.hospital.medical_patient_service.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import com.hospital.medical_patient_service.dto.ApiResponse;
//import com.hospital.medical_patient_service.entity.Patient;
//import com.hospital.medical_patient_service.services.PatientService;
//import jakarta.validation.Valid;
//
//@RestController
//@RequestMapping("/patients")
//public class PatientController {
//
//    @Autowired
//    private PatientService service;
//
//    // ✅ CREATE PATIENT - Matches createPatient(Patient, Long)
//    @PostMapping
//    public ResponseEntity<ApiResponse<Patient>> createPatient(
//            @Valid @RequestBody Patient patient) {
//
//        try {
//            // 🔥 Service method exactly match kiya
//            Patient savedPatient = service.createPatient(patient, patient.getUserId());
//            return ResponseEntity.ok(
//                ApiResponse.success("Patient profile created successfully", savedPatient)
//            );
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest()
//                .body(ApiResponse.error(e.getMessage()));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.internalServerError()
//                .body(ApiResponse.error("Failed to create patient: " + e.getMessage()));
//        }
//    }
//
//    // ✅ GET ALL PATIENTS (Testing - Service mein add karna padega)
//    @GetMapping
//    public ResponseEntity<ApiResponse<?>> getAllPatients() {
//        // Temporary - actual service method add kar lena
//        return ResponseEntity.ok(ApiResponse.success("GET all patients", "Add getAllPatients() in service"));
//    }
//
//    // ✅ GET PATIENT BY USER ID - Matches getByUserId(Long)
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<ApiResponse<Patient>> getPatientByUserId(@PathVariable Long userId) {
//        try {
//            Patient patient = service.getByUserId(userId);
//            return ResponseEntity.ok(
//                ApiResponse.success("Patient profile fetched successfully", patient)
//            );
//        } catch (Exception e) {
//            return ResponseEntity.status(404)
//                .body(ApiResponse.error(e.getMessage()));
//        }
//    }
//
//    // ✅ UPDATE PATIENT - Matches updatePatient(Patient, Long)  
//    @PutMapping("/user/{userId}")
//    public ResponseEntity<ApiResponse<Patient>> updatePatient(
//            @PathVariable Long userId,
//            @Valid @RequestBody Patient patient) {
//        try {
//            Patient updatedPatient = service.updatePatient(patient, userId);
//            return ResponseEntity.ok(
//                ApiResponse.success("Patient profile updated successfully", updatedPatient)
//            );
//        } catch (Exception e) {
//            return ResponseEntity.status(404)
//                .body(ApiResponse.error(e.getMessage()));
//        }
//   }
//
//////    // ✅ TEST ENDPOINT (Working hai)
//////    @PostMapping("/test")
//////    public ResponseEntity<String> test(@RequestBody Patient patient) {
//////        return ResponseEntity.ok("POST working! Received: " + patient);
//////    }
//    
//    @DeleteMapping("/user/{userId}")
//    public ResponseEntity<ApiResponse<String>> deletePatient(@PathVariable Long userId) {
//        try {
//            service.deletePatient(userId);
//            return ResponseEntity.ok(ApiResponse.success("Patient deleted successfully", "OK"));
//        } catch (Exception e) {
//            return ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));
//        }
//    }
//
//}
//
//
