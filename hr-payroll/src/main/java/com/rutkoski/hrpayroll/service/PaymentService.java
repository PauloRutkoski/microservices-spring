package com.rutkoski.hrpayroll.service;

import com.rutkoski.hrpayroll.model.Payment;
import com.rutkoski.hrpayroll.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {
    @Value("${hr-worker.host}")
    private String workerHost;

    @Autowired
    private RestTemplate restTemplate;

    public Payment getPayment(Long workerId, int days){
        Map<String, String> paremeters = new HashMap<>();
        paremeters.put("id", workerId.toString());

        Worker worker = restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, paremeters);
        if(worker == null){
            return null;
        }
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
