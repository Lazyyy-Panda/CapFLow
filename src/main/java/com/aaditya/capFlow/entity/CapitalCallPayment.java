package com.aaditya.capFlow.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "capital_call_payments")
public class CapitalCallPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "capital_call_id", nullable = false)
    private CapitalCall capitalCall;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDate paymentDate;

    public CapitalCallPayment() {
    }

    public CapitalCallPayment(
            CapitalCall capitalCall,
            BigDecimal amount,
            LocalDate paymentDate
    ) {
        this.capitalCall = capitalCall;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public Long getId() {
        return id;
    }

    public CapitalCall getCapitalCall() {
        return capitalCall;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }
}