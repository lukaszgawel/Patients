package com.gawlo.patientservice.mapper;

import com.gawlo.patientservice.dto.PatientRequestDto;
import com.gawlo.patientservice.dto.PatientResponseDto;
import com.gawlo.patientservice.model.Patient;
import java.time.LocalDate;

public class PatientMapper {
  public static PatientResponseDto toDTO(Patient patient) {
    PatientResponseDto patientDTO = new PatientResponseDto();
    patientDTO.setId(patient.getId().toString());
    patientDTO.setName(patient.getName());

    return patientDTO;
  }

  public static Patient toModel(PatientRequestDto patientRequestDTO) {
    Patient patient = new Patient();
    patient.setName(patientRequestDTO.getName());
    return patient;
  }
}