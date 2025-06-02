package senac.PizzaTime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.PizzaTime.Entities.Funcionarios;
import senac.PizzaTime.repositories.FuncionarioRepository;
import senac.PizzaTime.repositories.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    @Autowired
    FuncionarioRepository funcionarioRepository;

    // Funções de Funcionários
    public List<Funcionarios> findAll(){
        List<Funcionarios> funcionarios = funcionarioRepository.findAll();
        return funcionarios;
    }

    public Funcionarios findById(Integer id){
        Optional<Funcionarios> funcionario = funcionarioRepository.findById(Integer.valueOf(id));
        return funcionario.orElse(null);
    }

    public Optional<Funcionarios> findByCPF(String cpf) {
        String cpfNumerico = cpf.replaceAll("[^0-9]", "");

        if (cpfNumerico.length() != 11) {
            return Optional.empty();
        }
        return funcionarioRepository.findByCPF(cpfNumerico);
    }

    public  Funcionarios insert(Funcionarios funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public void delete(Integer id) {
        funcionarioRepository.deleteById(Integer.valueOf(id));
    }

    public Funcionarios gravarFuncionario(Funcionarios funcionario){
        return funcionarioRepository.save(funcionario);
    }

    public Funcionarios update(Integer id, Funcionarios funcionario) {
        Funcionarios funcionarioAtualizado = findById(id);
        if(funcionarioAtualizado != null){
            funcionarioAtualizado.setNome(funcionario.getNome());
            funcionarioAtualizado.setCargo(funcionario.getCargo());
            funcionarioAtualizado.setEmail(funcionario.getEmail());
            funcionarioAtualizado.setCpf(funcionario.getCpf());
            funcionarioAtualizado.setSenha(funcionario.getSenha());

            return funcionarioRepository.save(funcionarioAtualizado);
        }
        return null;
    }
}
