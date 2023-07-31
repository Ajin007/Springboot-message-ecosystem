package com.example.EcoSystemProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EcoSystemProject.Model.Ecotask;
import com.example.EcoSystemProject.Model.Ecotaskrequest;
import com.example.EcoSystemProject.Model.User;
import com.example.EcoSystemProject.Service.EcosystemService;
import com.example.EcoSystemProject.Service.TwilioService;

@RestController
@RequestMapping("api/v1/eco-challenge")
public class EcochallengeController {
	
	@Autowired
	private TwilioService twilioservice;
	
	@Autowired
	private EcosystemService ecosystemservice;
	
	@PostMapping("/user")
	private ResponseEntity<User> createuser(@RequestBody User user) {
		return new ResponseEntity<User>(ecosystemservice.adduser(user),HttpStatus.CREATED);
		
		
	}
	
	@PostMapping("/ecotask")
	private ResponseEntity<Ecotask> createTask(@RequestBody Ecotaskrequest ecotaskrequest){
	
		
		return new ResponseEntity<Ecotask>(ecosystemservice.addtoTask(ecotaskrequest),HttpStatus.CREATED);
		
	}
	
	@PutMapping("/ecotask/{id}")
	public Ecotask CompleteEcotask(@PathVariable("id")Long taskid) {
		return ecosystemservice.completeTask(taskid);
		
	}
	
	@PostMapping("/{taskId}/send-updates")
	public ResponseEntity<String> UpdateEcoId(@PathVariable Long taskId){
		ecosystemservice.Ecotaskupdate(taskId);
		return ResponseEntity.ok("Task updates send succesfully");
	}
		
	
	}