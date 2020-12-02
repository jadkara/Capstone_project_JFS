package com.wipro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.model.User;
import com.wipro.repository.UserRepository;
import com.wipro.specs.UserSpecs;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User save(User user) {
		return userRepository.save(user);
	}

	public User getUserByEmail(String email) {
		return userRepository.findOne(UserSpecs.getUserByEmailSpec(email)).orElse(null);
	}

	public User getUserByEmailPassword(String email, String password) {
		return userRepository
				.findOne(UserSpecs.getUserByEmailSpec(email).and(UserSpecs.getUserByPasswordSpec(password)))
				.orElse(null);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	public List<User> getOtherUsers() throws Exception {
		return userRepository.findAll(UserSpecs.getOtherUsers());
	}
}
