package com.training.payroll;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

  @Override
  public EntityModel<Employee> toModel(Employee employee) {

    return EntityModel.of(employee, //
        // ask that Spring HATEOAS build a link to the one method of EmployeeController
        linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
        // this builds a link to the aggregate root, all() and call all employees
        linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
  }
}
