package com.mariesto.paymentservice.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mariesto.paymentservice.persistence.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findPaymentByPaymentReferenceId(String paymentReferenceId);

}
