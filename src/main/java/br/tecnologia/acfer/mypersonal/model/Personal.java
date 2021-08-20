
package br.tecnologia.acfer.mypersonal.model;

import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Stateful
@Entity
@Table(name = "Personais")
@NamedQueries({
    @NamedQuery(name = "findUserById", query = "SELECT u FROM Personal u WHERE u.email = :email") })
public class Personal implements Serializable{
    private static final long serialVersionUID = 1L;
    
   // @Id
   // @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    private String nome ;
    private String nascimento;
    private String cidade;
    @Id
    @Column(name="email", nullable=false, length=255)
    private String email;
    
    @Column(name="senha", nullable=false, length=64)
    private String senha ;
    private String endereco;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    
}
