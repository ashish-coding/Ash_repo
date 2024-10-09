package com.springBoot.sts;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
	
	@RequestMapping("/test")
	public String test(){
		return "spring boot project using Spring STS";
		
	}

}
