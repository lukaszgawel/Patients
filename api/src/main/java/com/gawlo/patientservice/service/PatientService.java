package com.gawlo.patientservice.service;

import com.gawlo.patientservice.dto.PatientRequestDto;
import com.gawlo.patientservice.dto.PatientResponseDto;
import com.gawlo.patientservice.mapper.PatientMapper;
import com.gawlo.patientservice.model.Patient;
import com.gawlo.patientservice.repository.PatientRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

  private final PatientRepository patientRepository;

  public PatientService(PatientRepository patientRepository) {
    this.patientRepository = patientRepository;
  }

  public List<PatientResponseDto> getPatients() {
    List<Patient> patients = patientRepository.findAll();

    return patients.stream().map(PatientMapper::toDTO).toList();
  }

  public PatientResponseDto createPatient(PatientRequestDto patientRequestDTO) {
    Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));

    return PatientMapper.toDTO(newPatient);
  }

}
