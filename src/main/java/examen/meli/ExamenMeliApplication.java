package examen.meli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@EnableJpaRepositories
@EnableScheduling
@Controller
@SpringBootApplication
public class ExamenMeliApplication {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Bienvenidos a la API Realizada para MELI";
	}
	public static void main(String[] args) {
		SpringApplication.run(ExamenMeliApplication.class, args);
	}

}