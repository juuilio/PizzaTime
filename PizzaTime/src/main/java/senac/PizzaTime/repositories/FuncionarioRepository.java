package senac.PizzaTime.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import senac.PizzaTime.Entities.Funcionarios;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionarios, Integer> {

    @Query("SELECT f FROM Funcionarios f WHERE f.cpf = :cpf")
    Optional<Funcionarios> findByCPF(@Param("cpf") String cpf);


}
