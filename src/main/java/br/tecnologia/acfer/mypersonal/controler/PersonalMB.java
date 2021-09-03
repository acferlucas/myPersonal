
package br.tecnologia.acfer.mypersonal.controler;

import br.tecnologia.acfer.mypersonal.model.Personal;
import br.tecnologia.acfer.mypersonal.repository.PersonalRepository;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


@RequestScoped
@Named
public class PersonalMB implements Serializable{
    
	private static Logger log = Logger.getLogger(PersonalMB.class.getName());
	
	
    @EJB
    private Personal personal;
    
    @EJB
    private PersonalRepository personalRepository;
    

     private List<Personal> personais;

    private String validaemail;

     
    @PostConstruct
    public void init(){
        System.out.println("Iniciando via JSF - ViewScoped");
       
        this.personais = personalRepository.findAll();
    }
     
     
     

    public String getValidaemail() {
		return validaemail;
	}

	public void setValidaemail(String validaemail) {
		this.validaemail = validaemail;
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
       validaemail = personal.getEmail();
       
        
        personalRepository.create(p);
        System.out.println("Personal " + personal.getNome()+ "Salvo com sucesso");
        
        return "index.xhtml";
    }

    public List<Personal> getPersonais() {
        return personais;
    }
    
    
    
    public void validatePassword(ComponentSystemEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIComponent components = event.getComponent();
        // get password
        UIInput uiInputPassword = (UIInput) components.findComponent("senha");
        String password = uiInputPassword.getLocalValue() == null ? "" : uiInputPassword.getLocalValue().toString();
        String passwordId = uiInputPassword.getClientId();

        // Let required="true" do its job.
        if (password.isEmpty()) {
            return;
        }
        if (personalRepository.findUserById(validaemail) != null) {
            FacesMessage msg = new FacesMessage("User with this e-mail already exists");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(passwordId, msg);
            facesContext.renderResponse();
        }
    }
    
}
