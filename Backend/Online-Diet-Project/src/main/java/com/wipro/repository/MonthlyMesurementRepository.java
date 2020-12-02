package com.wipro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.model.MonthlyMeasurement;

@Repository
public interface MonthlyMesurementRepository extends JpaRepository<MonthlyMeasurement, Long> {

}
