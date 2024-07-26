package com.kodnest.boot.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.boot.Entity.Song;
import com.kodnest.boot.Entity.User;
import com.kodnest.boot.Service.SongService;
import com.kodnest.boot.Service.UserService;

import jakarta.servlet.http.HttpSession;

//@CrossOrigin("*")
//@RestController
@Controller
public class UserController {
	@Autowired
	UserService service;
	
	@Autowired
	SongService songService;
	
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
		
		return "login";
	}

	@PostMapping("/validate")
	//public String validate(@RequestBody LoginData data, HttpSession session,Model model)
	//{
		//System.out.println("Call received");
		//String email=data.getEmail();
		//String password=data.getPassword();
	public String validate(@RequestParam("email") String email,
			@RequestParam("password") String password, HttpSession session, Model model)
	{
		if(service.validateUser(email,password)==true)
		{
			String role=service.getRole(email);
			session.setAttribute("email", email);//creating a session object for user to store user activity
			if(role.equals("admin"))
			{
				System.out.println("Admin Home Call received");
				return "adminHome";
			}
			else
			{
				System.out.println("Customer Home Call received");
				User user=service.getUser(email);
				boolean userStatus =user.isPremium();
				
				List<Song> songList=songService.fetchAllSongs();
				model.addAttribute("songs",songList);
				
				model.addAttribute("isPremium", userStatus);
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
