package com.hospital.medical_patient_service.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hospital.medical_patient_service.dto.ApiResponse;
import com.hospital.medical_patient_service.entity.PatientAllergy;
import com.hospital.medical_patient_service.services.PatientAllergyService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/patients/allergies")
public class PatientAllergyController {
    
    @Autowired
    private PatientAllergyService service;

    @PostMapping
    public ApiResponse<PatientAllergy> addAllergy(
            @RequestParam Long patientId,
            @Valid @RequestBody PatientAllergy allergy) {
        return ApiResponse.success(
            "Allergy added successfully",
            service.addAllergy(allergy, patientId)
        );
    }

    @GetMapping("/patient/{patientId}")
    public ApiResponse<List<PatientAllergy>> getAllergies(@PathVariable Long patientId) {
        return ApiResponse.success(
            "Allergies fetched successfully", 
            service.getAllergies(patientId)
        );
    }

    @PutMapping("/{allergyId}")
    public ApiResponse<PatientAllergy> updateAllergy(
            @PathVariable Long allergyId,
            @RequestParam Long patientId,
            @Valid @RequestBody PatientAllergy allergy) {
        return ApiResponse.success(
            "Allergy updated successfully",
            service.updateAllergy(allergyId, allergy, patientId)
        );
    }
}





//package com.hospital.medical_patient_service.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.hospital.medical_patient_service.dto.ApiResponse;
//import com.hospital.medical_patient_service.entity.PatientAllergy;
//import com.hospital.medical_patient_service.services.PatientAllergyService;
//
//import jakarta.validation.Valid;
//
//
//@RestController
//@RequestMapping("/patients/allergies")
//public class PatientAllergyController {
//    @Autowired
//    private PatientAllergyService service;
//
//    // ✅ CREATE
//    @PostMapping
//    public ResponseEntity<ApiResponse<PatientAllergy>> addAllergy(@Valid @RequestBody PatientAllergy allergy) {
//        try {
//            return ResponseEntity.ok(ApiResponse.success("Allergy added", 
//                service.addAllergy(allergy, allergy.getPatientId())));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
//        }
//    }
//
//    // ✅ READ ALL (by patient)
//    @GetMapping("/patient/{patientId}")
//    public ResponseEntity<ApiResponse<List<PatientAllergy>>> getAllergies(@PathVariable Long patientId) {
//        return ResponseEntity.ok(ApiResponse.success("Allergies fetched", 
//            service.getAllergies(patientId)));
//    }
//}



//@RestController
//@RequestMapping("/patients/allergies")
//public class PatientAllergyController {
//
//    @Autowired
//    private PatientAllergyService service;
//
//    @PostMapping
//    public ApiResponse<PatientAllergy> addAllergy(
//            @Valid @RequestBody PatientAllergy allergy,
//            Authentication authentication) {
//
//        Long userId = (Long) authentication.getPrincipal();
//        return ApiResponse.success(
//                "Allergy added successfully",
//                service.addAllergy(allergy, userId)
//        );
//    }
//
//    @GetMapping
//    public ApiResponse<List<PatientAllergy>> getAllergies(
//            Authentication authentication) {
//
//        Long userId = (Long) authentication.getPrincipal();
//        return ApiResponse.success(
//                "Allergies fetched successfully",
//                service.getAllergies(userId)
//        );
//    }
//
//    @PutMapping("/{allergyId}")
//    public ApiResponse<PatientAllergy> updateAllergy(
//            @PathVariable Long allergyId,
//            @Valid @RequestBody PatientAllergy allergy,
//            Authentication authentication) {
//
//        Long userId = (Long) authentication.getPrincipal();
//        return ApiResponse.success(
//                "Allergy updated successfully",
//                service.updateAllergy(allergyId, allergy, userId)
//        );
//    }
//}
