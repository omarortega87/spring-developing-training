package com.training.payroll;

import org.springframework.data.jpa.repository.JpaRepository;

/*
 * By declaring Repository interface, we can automatically:
 * Create new employees
 * Update existing employees
 * Delete employees
 * Find Employees (one, all, or search by simple or complex properties)
 *
 * To get all this free functionality, all we have to do is declare an interface that extends SpringData JPAs
 * We specify the domain type Employee, id as long 
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
