package br.com.jortec.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import persistencia.JpaEntity;
import br.com.jortec.model.Cliente;
import br.com.jortec.model.Usuario;
import br.com.jortec.rest.ClienteService;

public class UsuarioDao {
	final Logger logger = Logger.getLogger(ClienteService.class);
	private EntityManager manager = JpaEntity.getEntityManager();

	public void salvar(Usuario usuario) {
		logger.info("Metodo logar chamado");
		try {
			manager.getTransaction().begin();
			logger.info("trasação chamada");
			manager.persist(manager.merge(usuario));
			logger.info("dados persistidos");
			manager.getTransaction().commit();
			logger.info("comitado");
		} catch (Exception e) {
			logger.info("Erro ao salvar cliente" + e.getMessage());
		}
	}
	public void editar(Usuario us) {
		logger.info("Metodo logar chamado");
		try {
			manager.getTransaction().begin();			
			manager.merge(us);						
			manager.getTransaction().commit();
			logger.info("dados editados");
		} catch (Exception e) {
			logger.info("Erro ao salvar cliente" + e.getMessage());
		}
		
	}
	public void deleta(Usuario us) {
		logger.info("Metodo logar chamado");
		try {
			manager.getTransaction().begin();			
			manager.remove(us);				
			manager.getTransaction().commit();
			logger.info("dados deletados");
		} catch (Exception e) {
			logger.info("Erro ao salvar cliente" + e.getMessage());
		}
		
	}

	public Usuario buscaPor(String login, String senha) {
       logger.info("pesquisa usuario chamada login : "+login+" senha : "+senha);
		try {
			return manager
					.createQuery(
							"select u from Usuario u "
									+ "where login = :login and senha =:senha",
							Usuario.class).setParameter("login", login)
					.setParameter("senha", senha).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	
	public Usuario buscaPorId(long id) {

		try {
			return manager
					.createQuery("select u from Usuario u " + "where id = :id",
							Usuario.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}


	public List listar(){
		return manager.createQuery("select u from Usuario u order by id", Usuario.class)
				.getResultList();
	}
	
	

}
