package senac.PizzaTime.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import senac.PizzaTime.Entities.Funcionarios;
import senac.PizzaTime.Entities.Produtos;
import senac.PizzaTime.service.FuncionarioService;
import senac.PizzaTime.service.ProdutoService;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins="*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping (value  = "/pizzatime")
public class PizzaTimeResource {

    @Autowired
    FuncionarioService funcionarioService;
    @Autowired
    ProdutoService produtoService;

    //FUNÇÕES FUNCIONÁRIOS
    // listar tudo
    @GetMapping(value = "/funcionarios")
    public List<Funcionarios> findAllFuncionarios(){
        List<Funcionarios> funcionarios = funcionarioService.findAll();
        return funcionarios;
    }

    //listar por id
    @GetMapping(value = "/funcionarios/{id}")
    public ResponseEntity<Funcionarios> findByIDFuncionario(@PathVariable Integer id){
        Funcionarios funcionario = funcionarioService.findById(id);
        return ResponseEntity.ok().body(funcionario);
    }

    // cadastrar
    @PostMapping (value = "/funcionarios")
    public ResponseEntity<Funcionarios> insertFuncionario(@RequestBody Funcionarios funcionario){
        funcionario = funcionarioService.insert(funcionario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(funcionario.getId()).toUri();
        return ResponseEntity.created(uri).body(funcionario);
    }

    //deletar
    @DeleteMapping(value = "/funcionarios/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable Integer id){
        funcionarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //editar
    @PutMapping(value = "/funcionarios/{id}")
    public ResponseEntity<Funcionarios> updateFuncionario(@PathVariable Integer id, @RequestBody Funcionarios funcionario){
        Funcionarios alterado = funcionarioService.update(id, funcionario);
        return ResponseEntity.ok().body(alterado);
    }

    //login
    @GetMapping("/login/{cpf}/{senha}")
    public ResponseEntity<Map<String, Object>> login(
            @PathVariable String cpf,
            @PathVariable String senha) {

        System.out.println("Tentativa de login - CPF: " + cpf + " | Senha: " + senha);

        try {
            // Remove formatação do CPF
            String cpfNumerico = cpf.replaceAll("[^0-9]", "");

            Optional<Funcionarios> funcionarioOpt = funcionarioService.findByCPF(cpfNumerico);

            if (funcionarioOpt.isEmpty()) {
                System.out.println("CPF não encontrado: " + cpfNumerico);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("success", false, "message", "CPF não cadastrado"));
            }

            Funcionarios funcionario = funcionarioOpt.get();

            // Comparação direta (apenas para desenvolvimento)
            if (!funcionario.getSenha().equals(senha)) {
                System.out.println("Senha incorreta para CPF: " + cpfNumerico);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("success", false, "message", "Senha incorreta"));
            }

            // Retorna mais informações do funcionário
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Login bem-sucedido");
            response.put("funcionario", Map.of(
                    "id", funcionario.getId(),
                    "nome", funcionario.getNome(),
                    "email", funcionario.getEmail(),
                    "cargo", funcionario.getCargo()
            ));

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("Erro durante login: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", "Erro no servidor"));
        }
    }


    //FUNÇÃO PRODUTOS
    // listar todos
    @GetMapping("/produtos")
    public ResponseEntity<List<Produtos>> findAllprodutos(){
        List<Produtos> produtos = produtoService.findAll();
        // Sempre retorna 200 com array vazio se não houver produtos
        return ResponseEntity.ok(produtos);
    }

    //listar por id
    @GetMapping(value = "/produtos/{id}")
    public ResponseEntity<Produtos> findByIdproduto(@PathVariable Integer id){
        Produtos produto = produtoService.findById(id);
        return ResponseEntity.ok().body(produto);
    }

    @GetMapping("/produtos/categoria/{categoria}")
    public ResponseEntity<List<Produtos>> listarPorCategoria(@PathVariable String categoria) {
        List<Produtos> produtos = produtoService.listarPorCategoria(categoria);
        return ResponseEntity.ok(produtos);
    }

    @PostMapping (value = "/produtos")
    public ResponseEntity<Produtos> insertProduto(@RequestBody Produtos produto){
        produto = produtoService.insert(produto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/produto/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @DeleteMapping (value = "/produtos/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Integer id){
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping (value = "/produtos/{id}")
    public ResponseEntity<Produtos> updateProduto(@PathVariable Integer id, @RequestBody Produtos produto){
        Produtos alterado = produtoService.update(id, produto);
        return ResponseEntity.ok().body(alterado);
    }
}
