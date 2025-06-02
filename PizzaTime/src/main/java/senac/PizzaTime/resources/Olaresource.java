package senac.PizzaTime.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Olaresource {
    @RequestMapping("/")
    @ResponseBody

    public String Ola(){
        return "Aplicação funcionando";
    }
}
