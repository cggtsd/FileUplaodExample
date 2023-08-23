package cgg.springmvc.fileupload.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WelcomeController {

	@RequestMapping("/home")
	public String showUser() {
		
		return "user";
	}
	
	@RequestMapping("/welcome")
	public String welcome(@RequestParam("user") String name,Model m) {
		System.out.println(name);
		m.addAttribute("name",name);
		return "welcome";
	}
}
