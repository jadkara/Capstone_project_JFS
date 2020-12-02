package com.wipro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.model.Batch;
import com.wipro.repository.BatchRepository;

@Service
public class BatchService {

	@Autowired
	private BatchRepository batchRepository;
	
	public List<Batch> getAllBatch() {
		return batchRepository.findAll();
	}

	public Batch save(Batch batch) {
		return batchRepository.save(batch);
	}
	
}
