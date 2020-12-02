package com.wipro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.wipro.model.DietPlan;

@Repository
public interface DietPlanRepository extends JpaRepository<DietPlan, Long>, JpaSpecificationExecutor<DietPlan> {
  
}
