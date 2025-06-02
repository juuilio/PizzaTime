package senac.PizzaTime.Entities;

import jakarta.persistence.*;


import java.io.Serializable;

@Entity (name = "Funcionarios")
public class Funcionarios implements Serializable {
    private static final long  serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ID")
    int id;

    @Column (name = "Nome")
    String nome;

    @Column (name = "Cargo")
    String cargo;

    @Column (name = "Email")
    String email;

    @Column (name = "CPF")
    String cpf;

    @Column (name = "Senha")
    String senha;

    public Funcionarios(){}

    public Funcionarios(String nome, String cargo, String email, String cpf, String senha){
        this.nome = nome;
        this.cargo = cargo;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
