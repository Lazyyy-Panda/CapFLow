package com.aaditya.capFlow.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "capital_calls")
public class CapitalCall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "capital_commitment_id", nullable = false)
    private CapitalCommitment capitalCommitment;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    public CapitalCall() {
    }

    public CapitalCall(
            CapitalCommitment capitalCommitment,
            BigDecimal amount,
            LocalDate dueDate,
            PaymentStatus status
    ) {
        this.capitalCommitment = capitalCommitment;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public CapitalCommitment getCapitalCommitment() {
        return capitalCommitment;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}