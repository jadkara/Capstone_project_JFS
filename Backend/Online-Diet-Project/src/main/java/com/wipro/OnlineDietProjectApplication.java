package com.wipro;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wipro.model.Batch;
import com.wipro.model.GroupEntity;
import com.wipro.model.User;
import com.wipro.service.BatchService;
import com.wipro.service.GroupService;
import com.wipro.service.UserService;

@SpringBootApplication
public class OnlineDietProjectApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BatchService batchService;
	
	@Autowired
	private GroupService groupService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(OnlineDietProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (userService.getAllUsers().size() == 0) {
			Batch belowBMI25 = batchService.save(new Batch("BelowBMI25", new Date(), new Date(), new Date()));
			Batch aboveBMI25 = batchService.save(new Batch("AboveBMI25", new Date(), new Date(), new Date()));
			
			System.out.println("belowBMI25.getBatchId()="+belowBMI25.getBatchId());
			System.out.println("aboveBMI25.getBatchId()="+aboveBMI25.getBatchId());
			
			GroupEntity group = groupService.save(new GroupEntity("Fat Group", belowBMI25.getBatchId()));
			groupService.save(new GroupEntity("Younger Group", belowBMI25.getBatchId()));
			groupService.save(new GroupEntity("Middle Agged Group", belowBMI25.getBatchId()));
			
			System.out.println("group.getGroupId()="+group.getGroupId());
			userService
					.save(new User("Roger Martin", "+91 9555555555", "roger@gmail.com", "ADMIN", "wipro@123", group.getGroupId(), belowBMI25.getBatchId(), "roger"));
			userService
					.save(new User("Steve Martin", "+91 9555555555", "steve@gmail.com", "ADMIN", "wipro@123", group.getGroupId(), belowBMI25.getBatchId(), "Steve"));
		}
		
	}

}
