package com.wipro.specs;

import org.springframework.data.jpa.domain.Specification;

import com.wipro.model.Challenger;

public class ChallengerSpecs {

	public static Specification<Challenger> getChallengerByEmail(String email) {
		return (root, query, cb) -> {
			return cb.equal(root.get("email"), email);
		};
	}
}
