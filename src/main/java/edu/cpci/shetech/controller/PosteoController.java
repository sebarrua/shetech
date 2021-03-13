package edu.cpci.shetech.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/posteo")
public class PosteoController {

	@GetMapping(value="")
	public String Posteos(Model model, Principal principal) {
		String vista="posteosPage.html";
		return vista;
	}
	
	@GetMapping(value="/addPosteo")
	public String AddPosteo(Model model, Principal principal) {
		String vista="addPosteoPage.html";
		return vista;
	}

}
