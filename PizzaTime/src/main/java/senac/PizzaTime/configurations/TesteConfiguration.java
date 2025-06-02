package senac.PizzaTime.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import senac.PizzaTime.service.DBService;

import java.text.ParseException;

@Configuration
@Profile("teste")
public class TesteConfiguration {
    @Autowired
    DBService dbService;
}
