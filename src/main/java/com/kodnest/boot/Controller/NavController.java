package com.kodnest.boot.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {
	@GetMapping("/")
    public String index() {
        return "index";
    }
	
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@GetMapping("/register")
	public String register()
	{
		return "register";
	}
	
	@GetMapping("/newSong")
	public String newSong()
	{
		return "newSong";
	}
}
