package com.mariesto.paymentservice.service;

import java.util.Optional;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.mariesto.paymentservice.constant.PaymentStatus;
import com.mariesto.paymentservice.constant.PaymentType;
import com.mariesto.paymentservice.exception.PaymentException;
import com.mariesto.paymentservice.model.ChargeRequest;
import com.mariesto.paymentservice.model.ChargeResponse;
import com.mariesto.paymentservice.model.PaymentStatusResponse;
import com.mariesto.paymentservice.model.RefundResponse;
import com.mariesto.paymentservice.persistence.entity.Payment;
import com.mariesto.paymentservice.persistence.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class PaymentService {

    private final PaymentPublisher publisher;

    private final PaymentRepository paymentRepository;

    private final ModelMapper modelMapper;

    public ChargeResponse chargePayment(final ChargeRequest request) {
        final Payment payment = new Payment();
        payment.setPaymentReferenceId(UUID.randomUUID().toString());
        payment.setAmount(request.getAmount());
        payment.setUserId(request.getUserId());
        payment.setOrderId(request.getOrderId());
        payment.setType(PaymentType.PAYMENT);
        payment.setPaymentStatus(PaymentStatus.INITIATED);
        final Payment persistedPayment = paymentRepository.save(payment);

        PaymentMessage paymentMessage = new PaymentMessage();
        paymentMessage.setUserId(payment.getUserId());
        paymentMessage.setPaymentReferenceId(payment.getPaymentReferenceId());
        paymentMessage.setAmount(payment.getAmount());
        publisher.publishCreditEvent(paymentMessage);

        return modelMapper.map(persistedPayment, ChargeResponse.class);
    }

    public PaymentStatusResponse getPaymentStatus(final String paymentReferenceId) {
        final Payment payment = findPaymentByPaymentReferenceId(paymentReferenceId);
        return modelMapper.map(payment, PaymentStatusResponse.class);
    }

    public RefundResponse refundPayment(final String paymentReferenceId) {
        final Payment payment = findPaymentByPaymentReferenceId(paymentReferenceId);
        if (payment.getPaymentStatus() == PaymentStatus.PROCESSING) {
            throw new PaymentException("payment not in settled status");
        }
        if (payment.getPaymentStatus() == PaymentStatus.REFUNDED) {
            throw new PaymentException("payment already refunded");
        }

        final Payment refundPayment = modelMapper.map(payment, Payment.class);
        refundPayment.setType(PaymentType.REFUND);
        refundPayment.setPaymentStatus(PaymentStatus.REFUNDED);
        final Payment persistedRefund = paymentRepository.save(refundPayment);

        return modelMapper.map(persistedRefund, RefundResponse.class);
    }

    private Payment findPaymentByPaymentReferenceId(final String paymentReferenceId) {
        final Payment fetchedPayment = paymentRepository.findPaymentByPaymentReferenceId(paymentReferenceId);
        Optional<Payment> payment = Optional.ofNullable(fetchedPayment);
        if (payment.isEmpty()) {
            throw new PaymentException("payment not found");
        }
        return payment.get();
    }
}
