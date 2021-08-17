
package br.tecnologia.acfer.mypersonal.repository;

import br.tecnologia.acfer.mypersonal.model.Aluno;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class AlunoRepository {
    
     @PersistenceContext(name = "mypersonal")
     protected EntityManager entityManager;
     
     public void create(Aluno aluno){
        entityManager.persist(aluno);
        
    }
     
     
    public List<Aluno> findAll() {
        
           return entityManager.createQuery("SELECT a FROM Aluno a", Aluno.class).getResultList();
    }
}
