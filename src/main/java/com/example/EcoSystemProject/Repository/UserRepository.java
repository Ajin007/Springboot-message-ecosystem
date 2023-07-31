package com.example.EcoSystemProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.EcoSystemProject.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByPhoneNumber(String phoneNumber);

}
