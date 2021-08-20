
package br.tecnologia.acfer.mypersonal.repository;

import br.tecnologia.acfer.mypersonal.model.Group;
import br.tecnologia.acfer.mypersonal.model.Personal;
import br.tecnologia.acfer.mypersonal.util.AuthenticationUtils;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


@Stateless
public class PersonalRepository {
    
    @PersistenceContext(name = "mypersonal")
    protected EntityManager entityManager;
    
    public void create(Personal personal){
    	 try {
             personal.setSenha(AuthenticationUtils.encodeSHA256(personal.getSenha()));
         } catch (Exception e) {
             Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
             e.printStackTrace();
         }
         Group group = new Group();
         group.setEmail(personal.getEmail());
         group.setGroupname(Group.PERSONAIS_GROUP);
         
         
    	
        entityManager.persist(personal);
        entityManager.persist(group);
        
    }
    
    public Personal findUserById(String email) {
    	System.out.println("buscando email por " + email);
        TypedQuery<Personal> query = entityManager.createNamedQuery("findUserById", Personal.class);
        query.setParameter("email", email);
        Personal user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            // getSingleResult throws NoResultException in case there is no user in DB
            // ignore exception and return NULL for user instead
        }
        return user;
    }
    
        @SuppressWarnings("unchecked")
       public List<Personal> findAll() {
         return entityManager.createQuery("FROM " +
         Personal.class.getName()).getResultList();
       }
}
