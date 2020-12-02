package com.wipro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.model.GroupEntity;
import com.wipro.repository.GroupRepository;
import com.wipro.specs.GroupSpecs;

@Service
public class GroupService {

	@Autowired
	private GroupRepository groupRepository;
	
	public List<GroupEntity> getAllGroup() {
		return groupRepository.findAll();
	}

	public List<GroupEntity> getGroupsByBatchId(String batchId) {
		return groupRepository.findAll(GroupSpecs.getGroupsByBatchId(batchId));
	}

	public GroupEntity save(GroupEntity group) {
		return groupRepository.save(group);
		
	}
}
