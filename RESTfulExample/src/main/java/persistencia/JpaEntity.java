package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.engine.spi.Managed;

public class JpaEntity {
	
	private static EntityManagerFactory entityManagerFactory;

	private JpaEntity() {
	}

	public static EntityManager getEntityManager() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence
					.createEntityManagerFactory("myUnit");
		}
		return entityManagerFactory.createEntityManager();
	}

	
	
	
}
