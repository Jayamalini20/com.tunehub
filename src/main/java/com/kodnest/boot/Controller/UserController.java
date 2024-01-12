package com.kodnest.boot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.boot.Entity.User;
import com.kodnest.boot.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	UserService service;
	

	//@PostMapping("/register")
	/*
	 public String addUsers(@RequestParam("username") String username,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("gender") String gender,
			@RequestParam("role") String role,
			@RequestParam("address") String address)
	{
		System.out.println(username+" "+email+" "+password+" "+gender+" "+role+" "+address);
		return "home";
	}
	*/
	
	@PostMapping("/register")
	public String addUsers(@ModelAttribute User user)
	{
		boolean userStatus=service.emailExists(user.getEmail());
		if(userStatus==false)
		{
			service.addUser(user);
			System.out.println("User Added...!");
		}
		else
		{
			System.out.println("User Alread Exist with same email ID...!");
		}
		
		return "home";
	}

	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email,
			@RequestParam("password") String password, HttpSession session)
	{
		
		if(service.validateUser(email,password)==true)
		{
			String role=service.getRole(email);
			session.setAttribute("email", email);//creating a session object for user to store user activity
			if(role.equals("admin"))
			{
				return "adminHome";
			}
			else
			{
				return "customerHome";
			}
			
		}
		else
		{
			return "login";
		}
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		session.invalidate();
		return "login";
	}
	
	
	
	
	
}
