package senac.PizzaTime.resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import senac.PizzaTime.Entities.Funcionarios;
import senac.PizzaTime.Entities.Produtos;
import senac.PizzaTime.service.FuncionarioService;
import senac.PizzaTime.service.ProdutoService;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class PizzaTimeResourceTest {

    @Mock
    private FuncionarioService funcionarioService;

    @Mock
    private ProdutoService produtoService;

    @InjectMocks
    private PizzaTimeResource pizzaTimeResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // --- FUNCIONARIOS ---

    @Test
    void testFindAllFuncionarios() {
        List<Funcionarios> funcionarios = List.of(new Funcionarios(), new Funcionarios());
        when(funcionarioService.findAll()).thenReturn(funcionarios);

        List<Funcionarios> result = pizzaTimeResource.findAllFuncionarios();

        assertThat(result).hasSize(2);
        verify(funcionarioService).findAll();
    }

    @Test
    void testFindByIDFuncionario() {
        Funcionarios funcionario = new Funcionarios();
        funcionario.setId(1);
        when(funcionarioService.findById(1)).thenReturn(funcionario);

        ResponseEntity<Funcionarios> response = pizzaTimeResource.findByIDFuncionario(1);

        assertThat(response.getBody()).isEqualTo(funcionario);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        verify(funcionarioService).findById(1);
    }

    @Test
    void testInsertFuncionario() {
        // Simula contexto de servlet
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Funcionarios funcionario = new Funcionarios();
        funcionario.setId(10);
        when(funcionarioService.insert(ArgumentMatchers.any(Funcionarios.class))).thenReturn(funcionario);

        ResponseEntity<Funcionarios> response = pizzaTimeResource.insertFuncionario(funcionario);

        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody()).isEqualTo(funcionario);
        verify(funcionarioService).insert(funcionario);

        // Limpa o contexto após o teste
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    void testDeleteFuncionario() {
        doNothing().when(funcionarioService).delete(5);

        ResponseEntity<Void> response = pizzaTimeResource.deleteFuncionario(5);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);
        verify(funcionarioService).delete(5);
    }

    @Test
    void testUpdateFuncionario() {
        Funcionarios funcionario = new Funcionarios();
        funcionario.setId(2);
        when(funcionarioService.update(eq(2), any(Funcionarios.class))).thenReturn(funcionario);

        ResponseEntity<Funcionarios> response = pizzaTimeResource.updateFuncionario(2, funcionario);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(funcionario);
        verify(funcionarioService).update(2, funcionario);
    }

    @Test
    void testLoginSuccess() {
        Funcionarios funcionario = new Funcionarios();
        funcionario.setId(1);
        funcionario.setNome("João");
        funcionario.setEmail("joao@email.com");
        funcionario.setCargo("Atendente");
        funcionario.setSenha("1234");
        String cpf = "12345678901";
        when(funcionarioService.findByCPF(cpf)).thenReturn(Optional.of(funcionario));

        ResponseEntity<Map<String, Object>> response = pizzaTimeResource.login(cpf, "1234");

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).containsEntry("success", true);
        assertThat(response.getBody()).containsEntry("message", "Login bem-sucedido");
    }

    @Test
    void testLoginCpfNotFound() {
        String cpf = "00000000000";
        when(funcionarioService.findByCPF(cpf)).thenReturn(Optional.empty());

        ResponseEntity<Map<String, Object>> response = pizzaTimeResource.login(cpf, "senha");

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
        assertThat(response.getBody()).containsEntry("success", false);
        assertThat(response.getBody()).containsEntry("message", "CPF não cadastrado");
    }

    @Test
    void testLoginWrongPassword() {
        Funcionarios funcionario = new Funcionarios();
        funcionario.setSenha("senhaCorreta");
        String cpf = "12345678901";
        when(funcionarioService.findByCPF(cpf)).thenReturn(Optional.of(funcionario));

        ResponseEntity<Map<String, Object>> response = pizzaTimeResource.login(cpf, "senhaErrada");

        assertThat(response.getStatusCodeValue()).isEqualTo(401);
        assertThat(response.getBody()).containsEntry("success", false);
        assertThat(response.getBody()).containsEntry("message", "Senha incorreta");
    }

    // --- PRODUTOS ---

    @Test
    void testFindAllProdutos() {
        List<Produtos> produtos = List.of(new Produtos(), new Produtos());
        when(produtoService.findAll()).thenReturn(produtos);

        ResponseEntity<List<Produtos>> response = pizzaTimeResource.findAllprodutos();

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).hasSize(2);
        verify(produtoService).findAll();
    }

    @Test
    void testFindByIdProduto() {
        Produtos produto = new Produtos();
        produto.setId(1);
        when(produtoService.findById(1)).thenReturn(produto);

        ResponseEntity<Produtos> response = pizzaTimeResource.findByIdproduto(1);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(produto);
        verify(produtoService).findById(1);
    }

    @Test
    void testListarPorCategoria() {
        List<Produtos> produtos = List.of(new Produtos());
        when(produtoService.listarPorCategoria("pizza")).thenReturn(produtos);

        ResponseEntity<List<Produtos>> response = pizzaTimeResource.listarPorCategoria("pizza");

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).hasSize(1);
        verify(produtoService).listarPorCategoria("pizza");
    }

    @Test
    void testInsertProduto() {
        // Simula contexto de servlet
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Produtos produto = new Produtos();
        produto.setId(10);
        when(produtoService.insert(any(Produtos.class))).thenReturn(produto);

        ResponseEntity<Produtos> response = pizzaTimeResource.insertProduto(produto);

        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody()).isEqualTo(produto);
        verify(produtoService).insert(produto);

        // Limpa o contexto após o teste
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    void testDeleteProduto() {
        doNothing().when(produtoService).delete(5);

        ResponseEntity<Void> response = pizzaTimeResource.deleteProduto(5);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);
        verify(produtoService).delete(5);
    }

    @Test
    void testUpdateProduto() {
        Produtos produto = new Produtos();
        produto.setId(2);
        when(produtoService.update(eq(2), any(Produtos.class))).thenReturn(produto);

        ResponseEntity<Produtos> response = pizzaTimeResource.updateProduto(2, produto);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(produto);
        verify(produtoService).update(2, produto);
    }
}
