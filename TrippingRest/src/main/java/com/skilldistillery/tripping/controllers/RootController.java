package com.skilldistillery.tripping.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin({"*", "http://localhost:4200"})
public class RootController {

	@RequestMapping(path = "/")
	public String home() {
		return "index.html";
	}
}
