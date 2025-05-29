package com.gawlo.patientservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PatientRequestDto {
  @NotBlank(message = "Name is required")
  @Size(max = 100, message = "Name cannot exceed 100 characters")
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}