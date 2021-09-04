package com.rutkoski.hrpayroll.service;

import com.rutkoski.hrpayroll.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public Payment getPayment(long workerId, int days){
        Payment payment = new Payment("Bob", 200.0, days);
        return payment;
    }
}
