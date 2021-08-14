
package br.tecnologia.acfer.mypersonal.repository;

import br.tecnologia.acfer.mypersonal.model.Personal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class PersonalRepository {
    
    @PersistenceContext(name = "mypersonal")
    protected EntityManager entityManager;
    
    public void create(Personal personal){
        entityManager.persist(personal);
        
    }
    
        @SuppressWarnings("unchecked")
       public List<Personal> findAll() {
         return entityManager.createQuery("FROM " +
         Personal.class.getName()).getResultList();
       }
}
