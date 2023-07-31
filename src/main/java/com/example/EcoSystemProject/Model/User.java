package com.example.EcoSystemProject.Model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//stores the table in a Redis hash 
@RedisHash("user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String FirstName;
	private String LastName;
	private String PhoneNumber;
	
	@Enumerated
	private List<String> Followers;
	
	

}
