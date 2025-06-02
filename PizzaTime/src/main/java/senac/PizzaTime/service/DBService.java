package senac.PizzaTime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import senac.PizzaTime.Entities.Funcionarios;
import senac.PizzaTime.Entities.Produtos;
import senac.PizzaTime.repositories.FuncionarioRepository;
import senac.PizzaTime.repositories.ProdutoRepository;

import java.util.Arrays;


@Service
public class DBService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;


    @Bean
    public String instaciarDB(){
        Funcionarios f1 = new Funcionarios("Admin Master", "Administrador", "admin@pizzatime.com", "34776234017", "senha123");
        Funcionarios f2 = new Funcionarios("Gerente Loja", "Funcionario", "gerente@pizzatime.com", "37185745004", "senha123");
        Funcionarios f3 = new Funcionarios("Atendente 1", "Funcionario", "atendente1@pizzatime.com", "65649371059", "senha123");

        Produtos p1 = new Produtos("Pizza Margherita", "Salgada","Molho de tomate, mussarela e manjericão",45.90);
        Produtos p2 = new Produtos("Pizza Calabresa", "Salgada","Molho de tomate, mussarela e calabresa",45);
        Produtos p3 = new Produtos("Refrigerante Coca-cola", "Bebida","Lata 350ml",6);

        funcionarioRepository.saveAll(Arrays.asList(f1,f2,f3));
        produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
        return "";
    }
}
