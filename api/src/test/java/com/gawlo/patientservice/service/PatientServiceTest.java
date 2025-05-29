package com.gawlo.patientservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.gawlo.patientservice.dto.PatientRequestDto;
import com.gawlo.patientservice.dto.PatientResponseDto;
import com.gawlo.patientservice.model.Patient;
import com.gawlo.patientservice.repository.PatientRepository;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

  @Mock
  private PatientRepository repository;

  @InjectMocks
  private PatientService service;

  @Test
  public void whenGetPatients_thenShouldReturnList() {
    Patient patient = createPatient();
    when(repository.findAll()).thenReturn(List.of(patient));

    List<PatientResponseDto> patients = service.getPatients();

    assertNotNull(patients);
    assertEquals(1, patients.size());
    assertEquals(patient.getName(), patients.get(0).getName());
  }

  @Test
  public void whenCreatePatient_thenShouldReturnNewlyCreated() {
    Patient patient = createPatient();
    when(repository.save(any())).thenReturn(patient);

    PatientResponseDto patientResponseDto = service.createPatient(createPatientRequestDto());

    assertNotNull(patientResponseDto);
    assertEquals(patient.getName(), patientResponseDto.getName());
  }

  private Patient createPatient() {
    Patient patient = new Patient();
    patient.setId(UUID.randomUUID());
    patient.setName("patient_007");

    return patient;
  }

  private PatientRequestDto createPatientRequestDto() {
    PatientRequestDto patient = new PatientRequestDto();
    patient.setName("patient_007");

    return patient;
  }
}