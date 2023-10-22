package com.mariesto.paymentservice.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mariesto.paymentservice.model.ChargeRequest;
import com.mariesto.paymentservice.model.ChargeResponse;
import com.mariesto.paymentservice.model.PaymentStatusResponse;
import com.mariesto.paymentservice.model.RefundResponse;
import com.mariesto.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@RequestMapping (path = "/api/v1/payments", produces = "application/json")
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping (value = "/charge", consumes = "application/json")
    public ResponseEntity<ChargeResponse> chargePayment(@Valid @RequestBody final ChargeRequest request) {
        log.info("charge request : {}", request);
        final ChargeResponse response = paymentService.chargePayment(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping (value = "/{paymentReferenceId}")
    public ResponseEntity<PaymentStatusResponse> getPaymentStatus(@PathVariable ("paymentReferenceId") final String paymentReferenceId) {
        log.info("get payment status request with id : {}", paymentReferenceId);
        final PaymentStatusResponse response = paymentService.getPaymentStatus(paymentReferenceId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping (value = "/refund/{paymentReferenceId}")
    public ResponseEntity<RefundResponse> refundPayment(@PathVariable ("paymentReferenceId") final String paymentReferenceId) {
        log.info("refund request for : {}", paymentReferenceId);
        final RefundResponse response = paymentService.refundPayment(paymentReferenceId);
        return ResponseEntity.ok().body(response);
    }

}
