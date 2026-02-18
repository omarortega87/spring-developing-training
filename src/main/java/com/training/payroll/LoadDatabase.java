package com.training.payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * Loading data when spring app loads
 * Spring boot runs ALL CommandLineRuner beans once the application context is loaded
 * This runner request a copy of the EmployeeRepository 
 */
@Configuration
public class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(EmployeeRepository repository) {
    return args -> {
      log.info("Preloading " + repository.save(new Employee("Bilbo Baggings", "burglar")));
      log.info("Preloading " + repository.save(new Employee("Frod Baggings", "thief")));
    };
  }
}
