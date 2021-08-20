package br.tecnologia.acfer.mypersonal.controler;


import java.io.Serializable;
import java.security.Principal;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.tecnologia.acfer.mypersonal.model.Personal;
import br.tecnologia.acfer.mypersonal.repository.PersonalRepository;


@ManagedBean
@SessionScoped
//@RequestScoped
//@Named
public class LoginMB implements Serializable {
    private static final long serialVersionUID = 3254181235309041386L;
    private static Logger log = Logger.getLogger(LoginMB.class.getName());
    
    @EJB
    private PersonalRepository userEJB;
    private String email;
    private String password;
    private Personal user;
    public String login() {
		System.out.println("Hello World 1");

    	System.out.println("login entrou");
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(email, password);
        } catch (ServletException e) {
    		System.out.println("Hello World!!!!!");
        	System.out.println("login falhou");
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed!", null));
            return "index";
        }
        Principal principal = request.getUserPrincipal();
        System.out.println("achou o "+ principal.getName());
        this.user = userEJB.findUserById(principal.getName());
        log.info("Authentication done for user: " + principal.getName());
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("Personal", user);
        if (request.isUserInRole("personais")) {
        	System.out.println("achou o personal "+ user.getNome());
            return "/personal/cadastroaluno?faces-redirect=true";
        } else {
        	System.out.println("não achou o personal");
            return "index";
        }
    }
    
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            this.user = null;
            request.logout();
            // clear the session
            ((HttpSession) context.getExternalContext().getSession(false)).invalidate();
        } catch (ServletException e) {
            log.log(Level.SEVERE, "Failed to logout user!", e);
        }
        return "/index?faces-redirect=true";
    }
    
    public Personal getAuthenticatedUser() {
        return user;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
