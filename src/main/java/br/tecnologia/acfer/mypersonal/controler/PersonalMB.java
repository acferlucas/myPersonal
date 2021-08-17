
package br.tecnologia.acfer.mypersonal.controler;

import br.tecnologia.acfer.mypersonal.model.Personal;
import br.tecnologia.acfer.mypersonal.repository.PersonalRepository;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class PersonalMB implements Serializable{
    
    @EJB
    private Personal personal;
    
    @EJB
    private PersonalRepository personalRepository;
    
     private List<Personal> personais;
    
     
     
    @PostConstruct
    public void init(){
        System.out.println("Iniciando via JSF - ViewScoped");
       
        this.personais = personalRepository.findAll();
    }
     
     
     
    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public String salvar(){
        
       Personal p = new Personal();
       
       p.setNome(personal.getNome());
       p.setCidade(personal.getCidade());
       p.setEndereco(personal.getEndereco());
       p.setNascimento(personal.getNascimento());
       p.setEmail(personal.getEmail());
       p.setSenha(personal.getSenha());
       
        
        personalRepository.create(p);
        System.out.println("Personal " + personal.getNome()+ "Salvo com sucesso");
        
        return "usuariosLista.xhtml";
    }

    public List<Personal> getPersonais() {
        return personais;
    }
    
    
    
    
}
