package com.wipro.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wipro.model.DailyLog;
import com.wipro.model.GroupEntity;
import com.wipro.model.MonthlyMeasurement;
import com.wipro.model.DietPlan;
import com.wipro.model.Batch;
import com.wipro.model.Challenger;
import com.wipro.model.User;
import com.wipro.service.BatchService;
import com.wipro.service.ChallengerService;
import com.wipro.service.DailyLogService;
import com.wipro.service.DietPlanService;
import com.wipro.service.EmailService;
import com.wipro.service.GroupService;
import com.wipro.service.MonthlyMesurementService;
import com.wipro.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/v1")
public class ResourceController {

	@Autowired
	private UserService userService;

	@Autowired
	private ChallengerService challengerService;

	@Autowired
	private DailyLogService dailyLogService;

	@Autowired
	private MonthlyMesurementService monthlyMesurementService;

	@Autowired
	private BatchService batchService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private DietPlanService dietPlanService;

	@Autowired
	private EmailService emailService;

	private Gson gson;

	public ResourceController() {
		gson = new Gson();
	}

	/*
	 * User operations
	 */
	@PostMapping(value = "/login")
	public User login(@RequestBody String input) {
		Type type = new TypeToken<Map<String, String>>() {
		}.getType();
		Map<String, String> json = gson.fromJson(input, type);
		String email = json.get("email");
		String password = json.get("password");
		User user = this.userService.getUserByEmailPassword(email, password);
		if(user != null && "CHALLENGER".equalsIgnoreCase(user.getUserType())) {
			Challenger challenger = challengerService.getChallengerByEmail(email);
			if("Approved".equalsIgnoreCase(challenger.getStatus())) {
				return user;
			} else {
				return null;
			}
		}
		return user;
	}

	@PostMapping(value = "/save-user")
	public User addUser(@RequestBody User user, HttpServletResponse response) throws IOException {
		try {
			User existingUser = userService.getUserByEmail(user.getEmail());
			if (existingUser != null) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				System.out.println("User already Exist");
			} else {
				user.setUserType("CHALLENGER");
				//Random Refferal generator
				String uniqueString = user.getEmail() + LocalDate.now().toString();
				Random random = new Random();
				int length = 10;
				StringBuffer randomString = new StringBuffer();
				for(int i = 0; i < length; i++) {
					int index = random.nextInt(uniqueString.length());
					char randomChar = uniqueString.charAt(index);
					randomString.append(randomChar);
				}
				user.setReferralCode(randomString.toString());
				System.out.println("Refferal Code = "+randomString.toString());
				response.setStatus(HttpServletResponse.SC_CREATED);
				return userService.save(user);
			}
		} catch (Exception ex) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
			System.out.println(ex);
		}
		return null;
	}

	@GetMapping(value = "/get-users")
	public List<User> getAllUser(HttpServletResponse response) throws IOException {
		try {
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			return userService.getAllUsers();
		} catch (Exception ex) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
			System.out.println(ex);
		}
		return Collections.EMPTY_LIST;
	}

	@GetMapping(value = "/get-other-users")
	public List<User> getOtherUsers(HttpServletResponse response) throws IOException {
		try {
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			return this.userService.getOtherUsers();
		} catch (Exception ex) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
			System.out.println(ex);
		}
		return Collections.EMPTY_LIST;
	}

	@PutMapping(value = "/update-user")
	public User updateUser(@RequestBody User user, HttpServletResponse response) throws IOException {
		try {
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			return userService.save(user);
		} catch (Exception ex) {
			ex.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
		}
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}

	@PostMapping(value = "/delete-user")
	public ResponseEntity<String> deleteUser(@RequestBody String input) throws IOException {
		String id = "";
		try {
			Type type = new TypeToken<Map<String, User>>() {
			}.getType();
			Map<String, User> json = gson.fromJson(input, type);
			User user = json.get("user");
			id = String.valueOf(user.getId());
			this.userService.deleteUser(user);
		} catch (Exception ex) {
			System.out.println(ex);
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(id, HttpStatus.OK);
	}

	/*
	 * Challenger Operations
	 */
	@GetMapping(value = "/get-challengers")
	public List<Challenger> getChallengers(HttpServletResponse response) throws IOException {
		try {
			response.setStatus(HttpServletResponse.SC_OK);
			return challengerService.getAllChallengers();
		} catch (Exception ex) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
			System.out.println(ex);
		}
		List<Challenger> list = new ArrayList<Challenger>();
		list.add(new Challenger());
		return list;
	}

	@PostMapping(value = "/get-challenger-by-email")
	public Challenger getChallengers(@RequestBody String input, HttpServletResponse response) throws IOException {
		try {
			Type type = new TypeToken<Map<String, String>>() {
			}.getType();
			Map<String, String> json = gson.fromJson(input, type);
			String email = json.get("email");
			response.setStatus(HttpServletResponse.SC_OK);
			return challengerService.getChallengerByEmail(email);
		} catch (Exception ex) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
			System.out.println(ex);
		}
		return null;
	}

	@PostMapping(value = "/save-challenger")
	public Challenger saveChallenger(@RequestBody Challenger challenger, HttpServletResponse response)
			throws IOException {
		try {
			Challenger existingChallenger = challengerService.getChallengerByEmail(challenger.getEmail());
			if (existingChallenger != null) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				System.out.println("Challenger already Exist");
			} else {
				response.setStatus(HttpServletResponse.SC_CREATED);
				double height = challenger.getHeight();
				double weight = challenger.getWeight();
				
				double bmi = (100*100*weight)/(height*height);
				challenger.setBmi(bmi);
				Challenger challengerSaved = challengerService.save(challenger);
				return challengerSaved;
			}
		} catch (Exception ex) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
			System.out.println(ex);
		}
		return null;
	}

	/*
	 * Update challenger status approval/rejection
	 */
	@PutMapping(value = "/update-challenger-status")
	public Challenger updateUserChallengerStatus(@RequestBody Challenger challenger, HttpServletResponse response)
			throws IOException {
		try {
			String status = challenger.getStatus();
			String emailId = challenger.getEmail();
			if ("Approved".equalsIgnoreCase(status)) {
				return challengerService.save(challenger);
			} else {
				String rejectionReason = challenger.getRejectionReason();
				userService.deleteUser(userService.getUserByEmail(emailId));
				challengerService.delete(emailId);
			}
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
		} catch (Exception ex) {
			ex.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
		}
		return null;
	}

	@PutMapping(value = "/update-challenger")
	public Challenger updateUserChallenger(@RequestBody Challenger challenger, HttpServletResponse response)
			throws IOException {
		try {
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			return challengerService.save(challenger);
		} catch (Exception ex) {
			ex.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
		}
		return null;
	}

	/*
	 * Daily/Monthly log saving operations
	 */
	@GetMapping(value = "/get-all-daily-log")
	public List<DailyLog> getDailyLogs(HttpServletResponse response) throws IOException {
		try {
			response.setStatus(HttpServletResponse.SC_OK);
			return dailyLogService.getAllDailyLog();
		} catch (Exception ex) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
		}
		return Collections.EMPTY_LIST;
	}

	@PostMapping(value = "/save-daily-log")
	public DailyLog saveDailyUserlog(@RequestBody DailyLog log, HttpServletResponse response) throws IOException {
		try {
			response.setStatus(HttpServletResponse.SC_CREATED);
			return dailyLogService.saveDailyLog(log);
		} catch (Exception ex) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
		}
		return null;
	}

	@GetMapping(value = "/get-monthly-measurement-log")
	public List<MonthlyMeasurement> getMonthlyLogs(HttpServletResponse response) throws IOException {
		try {
			response.setStatus(HttpServletResponse.SC_OK);
			return monthlyMesurementService.getAllMonthlyMesurement();
		} catch (Exception ex) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
			System.out.println(ex);
		}
		return Collections.EMPTY_LIST;
	}

	@PostMapping(value = "/save-monthly-measurementlog")
	public @ResponseBody MonthlyMeasurement saveMonthlyMeasurement(@RequestBody MonthlyMeasurement monthlyMeasurement,
			HttpServletResponse response) throws IOException {
		try {
			response.setStatus(HttpServletResponse.SC_CREATED);
			return monthlyMesurementService.saveMonthlyMeasurement(monthlyMeasurement);
		} catch (Exception ex) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
			System.out.println(ex);
		}
		return null;
	}

	@PostMapping(value = "/save-diet-plan")
	public @ResponseBody DietPlan saveDietPlan(@RequestParam("file") MultipartFile file,
			@RequestParam("batchid") String batchid, HttpServletResponse response) throws IOException {
		try {
			System.out.println("Diet Plan received for : saveDietPlan :" + file.getOriginalFilename());
			// dietPlanService.deletePreviousDietPlan(batchid);
			DietPlan plan = new DietPlan(batchid, file.getBytes(), file.getOriginalFilename());
			response.setStatus(HttpServletResponse.SC_CREATED);
			return dietPlanService.save(plan);
		} catch (Exception ex) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
			System.out.println(ex);
		}
		return null;
	}

	@PostMapping(value = "/get-diet-plan-by-batch")
	public DietPlan getDietPlanFileForChallenger(@RequestBody String input, HttpServletResponse response)
			throws IOException {
		try {
			Type type = new TypeToken<Map<String, String>>() {
			}.getType();
			Map<String, String> json = gson.fromJson(input, type);
			String batchId = json.get("batchId");
			response.setStatus(HttpServletResponse.SC_OK);
			DietPlan dietPlan = dietPlanService.getDietPlanByBatch(batchId);
			return dietPlan;
		} catch (Exception ex) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
			System.out.println(ex);
		}
		return null;
	}

	/*
	 * Batches operations
	 */

	@GetMapping(value = "/get-batches")
	public List<Batch> getBatches(HttpServletResponse response) throws IOException {
		try {
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			return batchService.getAllBatch();
		} catch (Exception ex) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
		}
		return Collections.EMPTY_LIST;
	}

	@PostMapping(value = "/get-groups")
	public List<GroupEntity> getGroups(@RequestBody String input, HttpServletResponse response) throws IOException {
		try {
			Type type = new TypeToken<Map<String, String>>() {
			}.getType();
			Map<String, String> json = gson.fromJson(input, type);
			String batchId = json.get("batchId");
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			return groupService.getGroupsByBatchId(batchId);
		} catch (Exception ex) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
			System.out.println(ex);
		}
		return Collections.EMPTY_LIST;
	}
	
	@PostMapping(value = "/save-group")
	public GroupEntity addGroup(@RequestBody GroupEntity groupEntity, HttpServletResponse response) throws IOException {
		try {
			System.out.println("Inside Group save ");
			response.setStatus(HttpServletResponse.SC_CREATED);
			return groupService.save(groupEntity);
		} catch (Exception ex) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
			System.out.println(ex);
		}
		return null;
	}

	@PostMapping(value = "/send-email")
	public ResponseEntity sendEmail(@RequestBody String input, HttpServletResponse response) throws IOException {
		try {
			Type type = new TypeToken<Map<String, String>>() {
			}.getType();
			Map<String, String> json = gson.fromJson(input, type);

			String emailId = json.get("email");
			String status = json.get("status");
			String rejectionReason = json.get("rejectionReason");
			String fullname = json.get("fullname");
			System.out.println("status -" + status + ", emailID-" + emailId);
			if ("Approved".equalsIgnoreCase(status)) {
				emailService.sendWelcomeEmail(emailId, fullname);
			} else {
				emailService.sendRejectionEmail(emailId, fullname, rejectionReason);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(value = "/send-motivation")
	public ResponseEntity sendMotivation(@RequestBody String input, HttpServletResponse response)
			throws IOException, MessagingException {
		try {
			System.out.println("send-motivation ");

			Type type = new TypeToken<Map<String, String[]>>() {
			}.getType();

			Map<String, String[]> json = gson.fromJson(input, type);
			String[] senders = json.get("senders");
			String[] message = json.get("message");
			emailService.sendMotivationWithAttachment(null, senders, message[0], "");
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(value = "/send-motivation-attachment")
	public ResponseEntity sendMotivationWithAttachment(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "senders") String[] senders, @RequestParam(value = "message") String message,
			@RequestParam(value = "filename") String filename, HttpServletResponse response)
			throws IOException, MessagingException {
		try {
			System.out.println("send-motivation-attachment");

			emailService.sendMotivationWithAttachment(file, senders, message, filename);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
