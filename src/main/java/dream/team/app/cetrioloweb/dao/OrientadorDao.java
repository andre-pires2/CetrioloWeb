package dream.team.app.cetrioloweb.dao;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import dream.team.app.cetrioloweb.entity.Orientador;

public class OrientadorDao {
	
	private EntityManager manager = PersistenceManager
									.getInstance().getEntityManager();
	
	public void createOrUpdateOrientador(Orientador orientador) throws RollbackException {
		try {
			manager.getTransaction().begin();
			if (orientador.getId() == null) {
				manager.persist(orientador);
			} else {
				manager.merge(orientador);
			}
			manager.getTransaction().commit();
		} catch (RollbackException e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}
	
	public Orientador searchOrientador(Long orientadorId) {
		return manager.find(Orientador.class, orientadorId);
	}
	
	public void deleteOrientador(Long orientadorId) throws RollbackException {
		Orientador orientador = this.searchOrientador(orientadorId);
		try {
			manager.getTransaction().begin();
			manager.remove(orientador);
			manager.getTransaction().commit();
		} catch (RollbackException e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}

}
