package com.wipro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.model.DietPlan;
import com.wipro.repository.DietPlanRepository;
import com.wipro.specs.DietPlanSpecs;

@Service
public class DietPlanService {
	
	@Autowired
	private DietPlanRepository dietPlanRepository;
	
	public List<DietPlan> getAllDietPlan() {
		return dietPlanRepository.findAll();
	}
	
	public DietPlan save(DietPlan dietPlan) {
		return dietPlanRepository.save(dietPlan);
	}
	
	public DietPlan getDietPlanByBatch(String batchId) {
		return dietPlanRepository.findOne(DietPlanSpecs.getDietPlanByBatchId(batchId)).orElse(null);
	}
	
	public void deletePreviousDietPlan(String batchId) {
		dietPlanRepository.delete(getDietPlanByBatch(batchId));
	}

}
