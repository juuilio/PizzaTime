package senac.PizzaTime.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import senac.PizzaTime.Entities.Funcionarios;
import senac.PizzaTime.repositories.FuncionarioRepository;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class FuncionarioServiceTest {

	@Mock
	private FuncionarioRepository funcionarioRepository;

	@InjectMocks
	private FuncionarioService funcionarioService;

	private Funcionarios funcionario;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		funcionario = new Funcionarios("João", "Atendente", "joao@email.com", "12345678901", "senha123");
		funcionario.setId(1);
	}

	@Test
	void testFindAll() {
		List<Funcionarios> list = Arrays.asList(funcionario);
		when(funcionarioRepository.findAll()).thenReturn(list);

		List<Funcionarios> result = funcionarioService.findAll();

		assertThat(result).isEqualTo(list);
		verify(funcionarioRepository).findAll();
	}

	@Test
	void testFindById_Found() {
		when(funcionarioRepository.findById(1)).thenReturn(Optional.of(funcionario));

		Funcionarios result = funcionarioService.findById(1);

		assertThat(result).isEqualTo(funcionario);
		verify(funcionarioRepository).findById(1);
	}

	@Test
	void testFindById_NotFound() {
		when(funcionarioRepository.findById(2)).thenReturn(Optional.empty());

		Funcionarios result = funcionarioService.findById(2);

		assertThat(result).isNull();
		verify(funcionarioRepository).findById(2);
	}

	@Test
	void testFindByCPF_Valid() {
		when(funcionarioRepository.findByCPF("12345678901")).thenReturn(Optional.of(funcionario));

		Optional<Funcionarios> result = funcionarioService.findByCPF("123.456.789-01");

		assertThat(result).isPresent().contains(funcionario);
		verify(funcionarioRepository).findByCPF("12345678901");
	}

	@Test
	void testFindByCPF_Invalid() {
		Optional<Funcionarios> result = funcionarioService.findByCPF("123");

		assertThat(result).isEmpty();
		verify(funcionarioRepository, never()).findByCPF(anyString());
	}

	@Test
	void testInsert() {
		when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);

		Funcionarios result = funcionarioService.insert(funcionario);

		assertThat(result).isEqualTo(funcionario);
		verify(funcionarioRepository).save(funcionario);
	}

	@Test
	void testDelete() {
		funcionarioService.delete(1);

		verify(funcionarioRepository).deleteById(1);
	}

	@Test
	void testGravarFuncionario() {
		when(funcionarioRepository.save(funcionario)).thenReturn(funcionario);

		Funcionarios result = funcionarioService.gravarFuncionario(funcionario);

		assertThat(result).isEqualTo(funcionario);
		verify(funcionarioRepository).save(funcionario);
	}

	@Test
	void testUpdate_Found() {
		Funcionarios novo = new Funcionarios("Maria", "Gerente", "maria@email.com", "10987654321", "novaSenha");
		when(funcionarioRepository.findById(1)).thenReturn(Optional.of(funcionario));
		when(funcionarioRepository.save(any(Funcionarios.class))).thenAnswer(invocation -> invocation.getArgument(0));

		Funcionarios result = funcionarioService.update(1, novo);

		assertThat(result.getNome()).isEqualTo("Maria");
		assertThat(result.getCargo()).isEqualTo("Gerente");
		assertThat(result.getEmail()).isEqualTo("maria@email.com");
		assertThat(result.getCpf()).isEqualTo("10987654321");
		assertThat(result.getSenha()).isEqualTo("novaSenha");
		verify(funcionarioRepository).save(any(Funcionarios.class));
	}

	@Test
	void testUpdate_NotFound() {
		when(funcionarioRepository.findById(2)).thenReturn(Optional.empty());

		Funcionarios novo = new Funcionarios();
		Funcionarios result = funcionarioService.update(2, novo);

		assertThat(result).isNull();
		verify(funcionarioRepository, never()).save(any());
	}
}