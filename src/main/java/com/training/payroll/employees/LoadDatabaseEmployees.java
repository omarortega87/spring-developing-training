package com.training.payroll.employees;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.training.payroll.orders.OrderRepository;

/*
 * Loading data when spring app loads
 * Spring boot runs ALL CommandLineRuner beans once the application context is loaded
 * This runner request a copy of the EmployeeRepository 
 */
@Configuration
public class LoadDatabaseEmployees {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabaseEmployees.class);

  @Bean
  CommandLineRunner loadEmployees(EmployeeRepository employeeRepository, OrderRepository orderRepository) {
    return args -> {
      // Users
      employeeRepository.save(new Employee("Bilbo", "Baggings", "burglar"));
      employeeRepository.save(new Employee("Frodo", "Baggings", "burglar"));
    };
  }
}
