package com.example.EcoSystemProject.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.EcoSystemProject.Model.Ecotask;
import com.example.EcoSystemProject.Model.Ecotaskrequest;
import com.example.EcoSystemProject.Model.User;
import com.example.EcoSystemProject.Repository.Ecotaskrepository;
import com.example.EcoSystemProject.Repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EcosystemService {
	
	@Autowired
	private Ecotaskrepository ecotaskrepository;
	
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private TwilioService twilioservice;


	//create user
	public User adduser(User user) {
		
		return userrepository.save(user);
		
	}
	
	//create Ecotask
	public Ecotask addtoTask(Ecotaskrequest ecotaskrequest) {

		Ecotask ecotask = new Ecotask();
		User user=userrepository.findByPhoneNumber(ecotaskrequest.getUserphoneNumber());
		log.info(String.valueOf(user));
		ecotask.setTaskname(ecotaskrequest.getTaskname());
		ecotask.setCompleted(false);
		ecotask.setPhoneNumber(ecotaskrequest.getUserphoneNumber());
		return ecotaskrepository.save(ecotask);
		}
	
	//complete the task
	public Ecotask completeTask(Long taskId) {
		Ecotask ecotask=ecotaskrepository.findById(taskId).orElse(null);
		assert ecotask != null;
		ecotask.setCompleted(true);
		log.info(String.valueOf(ecotask.getCompleted()));
		return ecotaskrepository.save(ecotask);
		
		
	}
	
	//update the task
	public void Ecotaskupdate(Long taskId) {
		 Ecotask ecotask=ecotaskrepository.findById(taskId).orElse(null);
		 assert ecotask != null;
		 User user=userrepository.findByPhoneNumber(ecotask.getPhoneNumber());
		 
		 List<String> followers=user.getFollowers();
		 for (String follower:followers) {
			 twilioservice.sendmsg(follower, "Your friend has completed a task: " + ecotask.getTaskname());
		 }
	}
		//show users with the followers
		 public User userfollowers(Long id,String follower) {
			 User user=userrepository.findById(id).orElse(null);
			 assert user != null;
			 user.getFollowers().add(follower);
			 userrepository.save(user);
			 return user;
	}
		 
		 //Notification and remainders
		 public void Sendnotificationandremainders(Long userId) {
			 User user=userrepository.findById(userId).orElse(null);
			 assert user != null;
			 String message = "Hello there. Your friend, " + user.getFirstName() + " has completed an " +
			           "EcoTask Challenge. Don't forget to do your part today!";
			 
			 for(String follower:user.getFollowers()) {
				 twilioservice.sendmsg("+234" + follower.substring(1), message);
			 }
		 }


	

}
