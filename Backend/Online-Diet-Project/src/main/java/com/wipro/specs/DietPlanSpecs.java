package com.wipro.specs;

import org.springframework.data.jpa.domain.Specification;

import com.wipro.model.DietPlan;

public class DietPlanSpecs {
	
	public static Specification<DietPlan> getDietPlanByBatchId(String batchId) {
		return (root, query, cb) -> {
			return cb.equal(root.get("batchId"), batchId);
		};
	}

}
