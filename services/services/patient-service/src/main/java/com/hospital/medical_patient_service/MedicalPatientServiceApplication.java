package com.hospital.medical_patient_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MedicalPatientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalPatientServiceApplication.class, args);
    }
}
	