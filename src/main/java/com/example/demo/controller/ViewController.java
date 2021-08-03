package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {
	
	@GetMapping("/home")
	public String home() {
		return "home.html";
	}
	@GetMapping("/main")
	public String main(@RequestParam String user,
			@RequestParam String sx,@RequestParam String sy,
			@RequestParam String ex,@RequestParam String ey,
			Model model) {
		model.addAttribute("user",user);
		model.addAttribute("sx",sx);
		model.addAttribute("sy",sy);
		model.addAttribute("ex",ex);
		model.addAttribute("ey",ey);
		return "main.html";
	}
}
