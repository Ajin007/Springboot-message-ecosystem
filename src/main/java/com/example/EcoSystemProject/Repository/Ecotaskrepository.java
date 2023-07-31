package com.example.EcoSystemProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.EcoSystemProject.Model.Ecotask;

@Repository
public interface Ecotaskrepository extends JpaRepository<Ecotask, Long> {
	
	

}
