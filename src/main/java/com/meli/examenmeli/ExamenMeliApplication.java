package com.meli.examenmeli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class ExamenMeliApplication {
	@RequestMapping("/")
	@ResponseBody
	String home() { return "Api esta arriba"; 	}

	public static void main(String[] args) {
		SpringApplication.run(ExamenMeliApplication.class, args);
	}

}