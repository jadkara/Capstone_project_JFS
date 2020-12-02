package com.wipro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.model.MonthlyMeasurement;
import com.wipro.repository.MonthlyMesurementRepository;

@Service
public class MonthlyMesurementService {

	@Autowired
	private MonthlyMesurementRepository monthlyMesurementRepository;
	
	public List<MonthlyMeasurement> getAllMonthlyMesurement() {
		return monthlyMesurementRepository.findAll();
	}
	
	public MonthlyMeasurement saveMonthlyMeasurement(MonthlyMeasurement monthlyMeasurement) {
		return monthlyMesurementRepository.save(monthlyMeasurement);
	}
}
