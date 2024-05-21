package com.it332.edudeck.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="tblsubscription")
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean getFreeTrial() {
        return freeTrial;
    }

    public void setFreeTrial(Boolean freeTrial) {
        this.freeTrial = freeTrial;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean freeTrial;
    private Double amount;

    // Getters and Setters
}
