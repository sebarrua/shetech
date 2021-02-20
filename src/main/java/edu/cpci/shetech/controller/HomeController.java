package edu.cpci.shetech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	
	@RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
	public String HomePage () {
		System.out.println("Index Home Page");
		return "index.html";
	}
}
