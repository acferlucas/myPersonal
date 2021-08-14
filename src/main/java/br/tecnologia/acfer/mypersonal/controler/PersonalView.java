
package br.tecnologia.acfer.mypersonal.controler;

import br.tecnologia.acfer.mypersonal.model.Personal;
import br.tecnologia.acfer.mypersonal.repository.PersonalRepository;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class PersonalView implements Serializable{
    
    private List<Personal> personais;
    
    @EJB
    private PersonalRepository personalRepository;
    
    @PostConstruct
    public void init(){
        System.out.println("Iniciando via JSF - ViewScoped");
        System.out.println(personalRepository.findAll());
        this.personais = personalRepository.findAll();
    }

    public List<Personal> getPersonais() {
        return personais;
    }
    
}
