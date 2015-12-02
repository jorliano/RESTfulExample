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
import br.com.jortec.model.Emergencia;
import br.com.jortec.model.Usuario;
import br.com.jortec.rest.ClienteService;

public class EmergenciaDao {
	final Logger logger = Logger.getLogger(ClienteService.class);

	private EntityManager manager = JpaEntity.getEntityManager();

	public void salvar(Emergencia em) {
		try {
			manager.getTransaction().begin();
			manager.persist(em);
			manager.getTransaction().commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			manager.close();
		}

	}

	public void deletar(Emergencia em) {
		logger.info("Metodo logar chamado");
		try {
			manager.getTransaction().begin();
			manager.remove(manager.merge(em));
			manager.getTransaction().commit();
			logger.info("dados deletados");
		} catch (Exception e) {
			logger.info("Erro ao deletar emergencia" + e.getMessage());
		}

	}

	public Emergencia buscaPorId(long id) {
		logger.info("pesquisa emergencia chamada");
		try {
			return manager
					.createQuery(
							"select em from Emergencia em where id = :id ",
							Emergencia.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List listar() {
		return manager
				.createQuery("select e from Emergencia e order by id desc",
						Emergencia.class).getResultList();
	}

	

}
