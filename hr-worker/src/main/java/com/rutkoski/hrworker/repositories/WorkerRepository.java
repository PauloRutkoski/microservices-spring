package com.rutkoski.hrworker.repositories;

import com.rutkoski.hrworker.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
}
