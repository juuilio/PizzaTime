package senac.PizzaTime.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import senac.PizzaTime.Entities.Produtos;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produtos, Integer> {
    @Query("SELECT produto FROM Produtos produto WHERE produto.categoria = :categoria ORDER BY produto.id")
    List<Produtos> listarPorCategoria(String categoria);
}
