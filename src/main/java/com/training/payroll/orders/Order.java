package com.training.payroll.orders;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CUSTOMER_ORDER")
class Order {

  private @Id @GeneratedValue Long id;

  private String description;
  private Status status;

  Order() {
  }

  Order(String description, Status status) {
    this.description = description;
    this.status = status;
  }

  public Long getId() {
    return this.id;
  }

  public String getDescription() {
    return description;
  }

  public Status getStatus() {
    return status;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  @Override
}
