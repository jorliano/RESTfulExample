package br.com.jortec.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import persistencia.JpaEntity;
import br.com.jortec.model.Cliente;
import br.com.jortec.rest.ClienteService;

public class ClienteDao {
	final Logger logger = Logger.getLogger(ClienteService.class);
	private EntityManager manager = JpaEntity.getEntityManager();

	public void salvar(Cliente cliente) {
		logger.info("Metodo logar chamado");
		try {
			manager.getTransaction().begin();
			logger.info("trasação chamada");
			manager.persist(cliente);
			logger.info("dados persistidos");
			manager.getTransaction().commit();
			logger.info("comitado");
		} catch (Exception e) {
			logger.info("Erro ao salvar cliente" + e.getMessage());
		}
	}
	public void deletar(Cliente c) {
		logger.info("Metodo logar chamado");
		try {
			manager.getTransaction().begin();		
			manager.remove( manager.merge(c));			
			manager.getTransaction().commit();
			logger.info("dados alterrados");
		} catch (Exception e) {
			logger.info("Erro ao salvar cliente" + e.getMessage());
		}
		
	}

	public Cliente buscaPor(String login, String senha) {
		logger.info("buscaPor cliente " + login+" "+ senha);
		try {
			return manager
					.createQuery(
							"select c from Cliente c "
									+ "where login = :login and senha =:senha",
							Cliente.class).setParameter("login", login)
					.setParameter("senha", senha).getSingleResult();
		} catch (NoResultException e) {
			logger.error("falhou ao buscar dados");
			return null;
		}
	}
	
	public Cliente buscaPorLoginNome(String login, String nome) {
		logger.info("buscaPor cliente " + login+" "+ nome);
		try {
			return manager
					.createQuery(
							"select c from Cliente c "
									+ "where login = :login and nome =:nome",
							Cliente.class).setParameter("login", login)
					.setParameter("nome", nome).getSingleResult();
		} catch (NoResultException e) {
			logger.error("falhou ao buscar dados");
			return null;
		}
	}

	public Cliente buscaPorId(long id) {

		try {
			return manager
					.createQuery("select c from Cliente c where id = :id",
							Cliente.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List listar(){
		return  manager.createQuery("select c from Cliente c order by id", Cliente.class)
				.getResultList();
	}

	

}
