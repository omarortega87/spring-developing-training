package com.training.payroll.employees;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
 * @RestController indicates that the data returned by each method is written straight int the response
 * body instead of rendering a template
 */
@RestController
public class EmployeeController {

  private final EmployeeRepository repository;
  private final EmployeeModelAssembler assembler;

  // An EmployeeRepository is injected by constructor into the controller
  EmployeeController(EmployeeRepository repository, EmployeeModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  /*
   * CollectionModel is another Spring HATEOAS container. It is aimed to
   * encapsulating collections of resources instead of a single resource entity.
   * It encapsulates a collection of employee resources
   */
  @GetMapping("/employees")
  CollectionModel<EntityModel<Employee>> all() {

    List<EntityModel<Employee>> employees = repository.findAll().stream()
        .map(assembler::toModel)
        .collect(Collectors.toList());

    return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
  }

  @PostMapping("/employees")
  ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) {

    EntityModel<Employee> entityModel = assembler.toModel(repository.save(newEmployee));

    return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(entityModel);

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
    return assembler.toModel(employee);
  }

  @PutMapping("/employees/{id}")
  ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
    Employee updatedEmployee = repository.findById(id)
        .map(employee -> {
          employee.setName(newEmployee.getName());
          employee.setRole(newEmployee.getRole());
          return repository.save(employee);
        })
        .orElseGet(() -> {
          return repository.save(newEmployee);
        });

    EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);

    // You can retrieve the Link created by the EmployeeModelAssembler with a SELF
    // rel.
    // This method returns a Link, which must be turned into a URI with the toUri
    // method
    return ResponseEntity
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(entityModel);
  }

  @DeleteMapping("/employees/{id}")
  ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
    repository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
