package com.training.payroll.orders;

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
public class LoadDataBaseOrders {

  private static final Logger log = LoggerFactory.getLogger(LoadDataBaseOrders.class);

  @Bean
  CommandLineRunner loadOrders(OrderRepository orderRepository) {
    return args -> {
      orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
      orderRepository.save(new Order("iPhone Pro", Status.IN_PROGRESS));

      orderRepository.findAll().forEach(order -> {
        log.info("Preloaded " + order);
      });
    };
  }
}
