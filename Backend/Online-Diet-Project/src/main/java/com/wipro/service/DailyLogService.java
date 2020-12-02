package com.wipro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.model.DailyLog;
import com.wipro.repository.DailyLogRepository;

@Service
public class DailyLogService {

	@Autowired
	private DailyLogRepository dailyLogRepository;
	
	public List<DailyLog> getAllDailyLog() {
		return dailyLogRepository.findAll();
	}

	public DailyLog saveDailyLog(DailyLog log) {
		return dailyLogRepository.save(log);
	}
}
