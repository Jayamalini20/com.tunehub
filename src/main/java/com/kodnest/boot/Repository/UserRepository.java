package com.kodnest.boot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodnest.boot.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	public User findByEmail(String email);

}
