package com.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.model.USER;

public interface UserRepository extends JpaRepository<USER, Integer>{
	
	public Optional<USER> findByEmail(String email);
	

}
