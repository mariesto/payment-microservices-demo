package com.mariesto.paymentservice.persistence.entity;

import java.sql.Timestamp;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.mariesto.paymentservice.constant.PaymentStatus;
import com.mariesto.paymentservice.constant.PaymentType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentReferenceId;

    @Enumerated (value = EnumType.STRING)
    private PaymentType type;

    private String orderId;

    private Double amount;

    private String userId;

    @Enumerated (value = EnumType.STRING)
    private PaymentStatus paymentStatus;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

}
