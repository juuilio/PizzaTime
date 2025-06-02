package senac.PizzaTime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senac.PizzaTime.Entities.Funcionarios;
import senac.PizzaTime.Entities.Produtos;
import senac.PizzaTime.repositories.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;

    public List<Produtos> findAll() {
        List<Produtos> produtos = produtoRepository.findAll();
        return produtos;
    }

    public Produtos findById(Integer id){
        Optional<Produtos> produto = produtoRepository.findById(id);
        return produto.orElse(null);
    }

    //pizza salgadas, doces e bebidas
    public List<Produtos> listarPorCategoria(String categoria) {
        return produtoRepository.listarPorCategoria(categoria);
    }

    public Produtos insert(Produtos produto) {
        return produtoRepository.save(produto);
    }

    public void delete(Integer id) {
        produtoRepository.deleteById(id);
    }

    public Produtos update(Integer id, Produtos produto){
        Produtos produtoAtualizado = findById(id);
        if(produtoAtualizado != null){
            produtoAtualizado.setNome(produto.getNome());
            produtoAtualizado.setCategoria(produto.getCategoria());
            produtoAtualizado.setDescricao(produto.getDescricao());
            produtoAtualizado.setPreco(produto.getPreco());
            return produtoRepository.save(produtoAtualizado);
        }
        return null;
    }
}
