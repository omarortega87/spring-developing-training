package com.training.payroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * @SpringBootApplication is a meta-annotation that pulls in component scanning, auto-configuration
 * and property support
 */
@SpringBootApplication
public class PayrollApplication {

  public static void main(String[] args) {
    SpringApplication.run(PayrollApplication.class, args);
  }

}
