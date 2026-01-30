package com.hospital.medical_patient_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hospital.medical_patient_service.dto.ApiResponse;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> handleNotFound(ResourceNotFoundException ex) {
	    return ResponseEntity
	            .status(HttpStatus.NOT_FOUND)
	            .body(ApiResponse.error(ex.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<String>> handleValidation(MethodArgumentNotValidException ex) {
	    String error = ex.getBindingResult()
	            .getFieldErrors()
	            .get(0)
	            .getDefaultMessage();

	    return ResponseEntity
	            .status(HttpStatus.BAD_REQUEST)
	            .body(ApiResponse.error(error));
	}
	
	  @ExceptionHandler(IllegalArgumentException.class)
	    public ResponseEntity<ApiResponse<String>> handleIllegalArgument(IllegalArgumentException ex) {
	        return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body(ApiResponse.error(ex.getMessage()));
	    }

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<String>> handleGeneral(Exception ex) {
	    return ResponseEntity
	            .status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body(ApiResponse.error("Something went wrong"));
	}

}
