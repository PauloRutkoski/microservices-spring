package com.rutkoski.hrpayroll.resource;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rutkoski.hrpayroll.model.Payment;
import com.rutkoski.hrpayroll.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/payments")
public class PaymentController {
    @Autowired
    private PaymentService service;

    @HystrixCommand(fallbackMethod = "getPaymentAlternative")
    @GetMapping(value = "/{workerId}/{days}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long workerId,@PathVariable int days){
        Payment payment = service.getPayment(workerId, days);
        if(payment == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(payment);
    }
    
    public ResponseEntity<Payment> getPaymentAlternative(Long workerId,int days){
    	return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }

}
