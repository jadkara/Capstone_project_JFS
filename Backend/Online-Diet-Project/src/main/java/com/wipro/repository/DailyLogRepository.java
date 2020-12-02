package com.wipro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wipro.model.DailyLog;

@Repository
public interface DailyLogRepository extends JpaRepository<DailyLog, Long>, JpaSpecificationExecutor<DailyLog>{

}
