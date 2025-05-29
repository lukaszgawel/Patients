package com.gawlo.patientservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gawlo.patientservice.dto.PatientRequestDto;
import com.gawlo.patientservice.dto.PatientResponseDto;
import com.gawlo.patientservice.service.PatientService;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {PatientController.class})
class PatientControllerTest {

  private static final String URL = "/api/v1/patients";
  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private PatientService service;

  @Test
  public void whenGetPatients_thenShouldReturnList() throws Exception {
    PatientResponseDto patient = createPatientResponseDto();
    when(service.getPatients()).thenReturn(List.of(patient));

    this.mockMvc.perform(get(URL))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$[0].id").value(patient.getId()))
        .andExpect(jsonPath("$[0].name").value(patient.getName()));

    Mockito.verify(service, Mockito.times(1)).getPatients();
  }

  @Test
  public void whenCreatePatients_thenShouldReturnNewlyCreated() throws Exception {
    PatientResponseDto patient = createPatientResponseDto();
    when(service.createPatient(any(PatientRequestDto.class))).thenReturn(patient);
    String reqBody = new ObjectMapper().writeValueAsString(createPatientRequestDto());

    this.mockMvc.perform(post(URL)
            .contentType(APPLICATION_JSON_VALUE)
            .accept(APPLICATION_JSON_VALUE)
            .characterEncoding(StandardCharsets.UTF_8)
            .content(reqBody))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.id").value(patient.getId()))
        .andExpect(jsonPath("$.name").value(patient.getName()));

    Mockito.verify(service, Mockito.times(1)).createPatient(any());
  }

  private PatientResponseDto createPatientResponseDto() {
    PatientResponseDto patient = new PatientResponseDto();
    patient.setId("12345");
    patient.setName("patient_007");
    return patient;
  }

  private PatientRequestDto createPatientRequestDto() {
    PatientRequestDto patient = new PatientRequestDto();
    patient.setName("patient_007");
    return patient;
  }
}