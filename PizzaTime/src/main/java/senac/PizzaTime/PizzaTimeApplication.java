package senac.PizzaTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import senac.PizzaTime.repositories.FuncionarioRepository;

@SpringBootApplication

public class PizzaTimeApplication {
	public static void main(String[] args) {
		SpringApplication.run(PizzaTimeApplication.class, args);
	}

}
