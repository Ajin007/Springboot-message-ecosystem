package com.example.EcoSystemProject.Model;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Ecotask")
public class Ecotask  {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long Id;
	
	private String taskname;
	private Boolean completed;
	private String PhoneNumber;
	
	
	
}
