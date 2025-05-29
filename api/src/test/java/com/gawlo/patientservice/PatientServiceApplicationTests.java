package com.gawlo.patientservice;

import com.gawlo.patientservice.controller.PatientController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PatientServiceApplicationTests {

  @Autowired
  private PatientController controller;

  @Test
  void contextLoads() {
    assertThat(controller).isNotNull();
  }

}
