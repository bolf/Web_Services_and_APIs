package com.udacity.orders.entity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@EntityListeners({AuditingEntityListener.class})
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String customerName;

    @NotNull
    private Long vehicleID;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public Order() {}

    public Order(Long id, @NotNull String customerName, @NotNull Long vehicleID, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.customerName = customerName;
        this.vehicleID = vehicleID;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(Long vehicleID) {
        this.vehicleID = vehicleID;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}