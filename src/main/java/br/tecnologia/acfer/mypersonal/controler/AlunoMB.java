
package br.tecnologia.acfer.mypersonal.controler;

import br.tecnologia.acfer.mypersonal.model.Aluno;
import br.tecnologia.acfer.mypersonal.repository.AlunoRepository;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class AlunoMB {
    
    @EJB
    private Aluno aluno;
    
    @EJB
    private AlunoRepository alunoRepository;

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
    public String salvar(){
        
       Aluno a = new Aluno();
       
       a.setNome(aluno.getNome());
       a.setCidade(aluno.getCidade());
       a.setEndereco(aluno.getEndereco());
       a.setNascimento(aluno.getNascimento());
       a.setEmail(aluno.getEmail());
       a.setSenha(aluno.getSenha());
       
        
        alunoRepository.create(a);
        System.out.println("Personal " + aluno.getNome()+ "Salvo com sucesso");
        
        return "index.xhtml";
    }
    
    
}
