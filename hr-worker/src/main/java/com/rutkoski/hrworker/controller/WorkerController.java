package com.rutkoski.hrworker.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rutkoski.hrworker.model.Worker;
import com.rutkoski.hrworker.repositories.WorkerRepository;

@RestController
@RequestMapping(value = "/workers")
@RefreshScope
public class WorkerController {
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(WorkerController.class);
	
	@Value("${test.config}")
	private String testConfig;
	
	@Autowired
	private Environment env;
	
    @Autowired
    private WorkerRepository repository;
    
    @GetMapping(value="/configs")
    public ResponseEntity<Void> getConfigs(){
       logger.info("CONFIG = " + testConfig);
       return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Worker>> findAll(){
        List<Worker> list = repository.findAll();
        if(list.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id){
    	logger.info("PORT=" + env.getProperty("local.server.port"));
    	
        Optional<Worker> optionalWorker = repository.findById(id);
        if(optionalWorker.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(optionalWorker.get());
    }
}
