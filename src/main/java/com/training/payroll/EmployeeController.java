package com.training.payroll;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/*
 * @RestController indicates that the data returned by each method is written straight int the response
 * body instead of rendering a template
 */
@RestController
public class EmployeeController {

  private final EmployeeRepository repository;

  // An EmployeeRepository is injected by constructor into the controller
  EmployeeController(EmployeeRepository repository) {
    this.repository = repository;
  }

  /*
   * CollectionModel is another Spring HATEOAS container. It is aimed to
   * encapsulating collections of resources instead of a single resource entity.
   * It encapsulates a collection of employee resources
   */
  @GetMapping("/employees")
  CollectionModel<EntityModel<Employee>> all() {

    List<EntityModel<Employee>> employees = repository.findAll().stream()
        .map(employee -> EntityModel.of(employee,
            linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
            linkTo(methodOn(EmployeeController.class).all()).withRel("employees")))
        .collect(Collectors.toList());

    return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
  }

  @PostMapping("/employees")
  Employee newEmployee(@RequestBody Employee newEmployee) {
    return repository.save(newEmployee);
  }

  // Single item
  // This implementation adds links
  // This tutorial is based on Spring MVC and uses the static helper methods from
  // WebMvcLinkBuilder to build these links. If you are using Spring WebFlux in
  // your project, you must instead use WebFluxLinkBuilder.
  @GetMapping("/employees/{id}")
  EntityModel<Employee> one(@PathVariable Long id) {
    Employee employee = repository.findById(id)
        .orElseThrow(() -> new EmployeeNotFoundException(id));

    // EntityModel<T> is a generic container from Spring HATEOAS that includes not
    // only the data but a collection of links
    return EntityModel.of(employee,
        // ask that Spring HATEOAS build a link to the one method of EmployeeController
        linkTo(methodOn(EmployeeController.class)
            .one(id)).withSelfRel(),
        // this builds a link to the aggregate root, all() and call all employees
        linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
  }

  @PutMapping("/employees/{id}")
  Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
    return repository.findById(id)
        .map(employee -> {
          employee.setName(newEmployee.getName());
          employee.setRole(newEmployee.getRole());
          return repository.save(employee);
        })
        .orElseGet(() -> {
          return repository.save(newEmployee);
        });
  }

  @DeleteMapping("/employees/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
  }

}
