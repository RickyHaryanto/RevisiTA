package com.example.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.client.model.User;

import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);

	@Query(
        value = "SELECT user_id from user where email=?", 
        nativeQuery = true)
        String cekemail(String param);
}