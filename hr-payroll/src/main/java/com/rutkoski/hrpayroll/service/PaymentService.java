package com.rutkoski.hrpayroll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rutkoski.hrpayroll.feignclients.WorkerFeignClient;
import com.rutkoski.hrpayroll.model.Payment;
import com.rutkoski.hrpayroll.model.Worker;

@Service
public class PaymentService {
    @Autowired
    private WorkerFeignClient workerFeignClient;
    

    public Payment getPayment(Long workerId, int days){
        Worker worker = workerFeignClient.findById(workerId).getBody();
        if(worker == null){
            return null;
        }
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
