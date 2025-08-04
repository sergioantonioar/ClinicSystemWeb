package org.example.clinic_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClinicSystemApplication {

  public static void main(String[] args) {
    SpringApplication.run(ClinicSystemApplication.class, args);
  }

}
