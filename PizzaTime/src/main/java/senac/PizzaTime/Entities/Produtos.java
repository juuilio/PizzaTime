package senac.PizzaTime.Entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity (name = "Produtos")
public class Produtos implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    int id;

    @Column (name = "Nome")
    String nome;

    @Column (name = "Categoria")
    String categoria;

    @Column (name = "Descricao")
    String descricao;

    @Column (name = "Preco")
    double preco;

    @Column (name = "Imagem")
    String img;

    public Produtos(){}

    public Produtos(String nome, String categoria, String descricao, double preco){
        this.nome = nome;
        this.categoria = categoria;
        this.descricao = descricao;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
