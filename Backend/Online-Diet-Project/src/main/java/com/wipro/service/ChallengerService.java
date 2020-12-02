package com.wipro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.model.Challenger;
import com.wipro.repository.ChallengerRepository;
import com.wipro.specs.ChallengerSpecs;

@Service
public class ChallengerService {

	@Autowired
	private ChallengerRepository challengerRepository;
	
	public List<Challenger> getAllChallengers() {
		return challengerRepository.findAll();
	}

	public Challenger save(Challenger challenger) {
		return challengerRepository.save(challenger);
	}
	
	public Challenger getChallengerByEmail(String email) {
		return challengerRepository.findOne(ChallengerSpecs.getChallengerByEmail(email)).orElse(null);
	}

	public void delete(String email) {
		challengerRepository.deleteById(email);
	}
}
