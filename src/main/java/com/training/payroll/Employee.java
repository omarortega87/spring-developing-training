package com.training.payroll;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/*
 * @Entity is a JPA annotation to make this object ready for storage in a JPA-base data store.
 * id, name, and role are attributes of our Employee domain object.
 * id is marked with more JPA annotation to indicate that is a primary key and is automatically populated by the JPA provider
 * Spring DATA JPA repositorys are interfaces with methods that support creating, reading, updating, a deleting records agains a back end data store.
 */
@Entity
public class Employee {

  private @Id @GeneratedValue Long id;
  private String name;
  private String role;

  Employee() {
  }

  Employee(String name, String role) {
    this.name = name;
    this.role = role;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getRole() {
    return this.role;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Employee))
      return false;
    Employee employee = (Employee) o;
    return Objects.equals(this.id, employee.id) && Objects.equals(this.name, employee.name)
        && Objects.equals(this.role, employee.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.role);
  }

  @Override
  public String toString() {
    return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" +
        this.role + '\'' + '}';
  }

}
