package br.com.jortec.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.jortec.model.Cliente;
import br.com.jortec.model.Emergencia;


public class EmergenciaDao {
	
	
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myUnit");
	EntityManager manager = entityManagerFactory.createEntityManager();
	
	public void salvar(Emergencia em) {       
		try{
			manager.getTransaction().begin();
			manager.persist(manager.merge(em));
			manager.getTransaction().commit();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
		  manager.close();
		}
		
	}
	
	public List listar(){
		return manager.createQuery("select e from Emergencia e order by id desc", Emergencia.class)
				.getResultList();
	}
	

	



	
    
	

}
