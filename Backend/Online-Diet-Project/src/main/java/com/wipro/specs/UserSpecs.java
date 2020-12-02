package com.wipro.specs;

import org.springframework.data.jpa.domain.Specification;

import com.wipro.model.User;

public class UserSpecs {

	public static Specification<User> getUserByEmailSpec(String email) {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.equal(root.get("email"), email);
		};
	}

	public static Specification<User> getUserByPasswordSpec(String password) {
		return (root, query, criteriaBuilder) -> {
			return criteriaBuilder.equal(root.get("password"), password);
		};
	}
	
	public static Specification<User> getOtherUsers() {
		return (root, query, cb) -> {
			return cb.notEqual(root.get("userType"), "ADMIN");
		};
	}
}
