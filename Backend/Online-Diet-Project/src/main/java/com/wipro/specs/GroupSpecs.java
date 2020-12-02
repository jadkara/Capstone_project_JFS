package com.wipro.specs;

import org.springframework.data.jpa.domain.Specification;

import com.wipro.model.GroupEntity;

public class GroupSpecs {
	public static Specification<GroupEntity> getGroupsByBatchId(String batchId) {
		return (root, query, cb) -> {
			return cb.equal(root.get("batchId"), batchId);
		};
	}

}
