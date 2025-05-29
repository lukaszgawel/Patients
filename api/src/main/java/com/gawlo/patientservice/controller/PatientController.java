package com.gawlo.patientservice.controller;

import com.gawlo.patientservice.dto.PatientRequestDto;
import com.gawlo.patientservice.dto.PatientResponseDto;
import com.gawlo.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/patients")
@Tag(name = "Patient", description = "API for managing Patients")
public class PatientController {

  private final PatientService patientService;

  public PatientController(PatientService patientService) {
    this.patientService = patientService;
  }

  @GetMapping
  @Operation(summary = "Get Patients")
  public ResponseEntity<List<PatientResponseDto>> getPatients() {
    List<PatientResponseDto> patients = patientService.getPatients();

    return new ResponseEntity<>(patients, HttpStatus.OK);
  }

  @PostMapping
  @Operation(summary = "Create a new Patient")
  public ResponseEntity<PatientResponseDto> createPatient(
      @Validated
      @RequestBody PatientRequestDto patientRequestDTO) {
    PatientResponseDto patient = patientService.createPatient(patientRequestDTO);

    return new ResponseEntity<>(patient, HttpStatus.CREATED);
  }
}
