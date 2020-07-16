package dream.team.app.cetrioloweb.dao;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import dream.team.app.cetrioloweb.entity.Permissao;

public class PermissaoDao {
	private static EntityManager manager = PersistenceManager
			.getInstance().getEntityManager();
	
	public void createOrUpdatePermissao(Permissao permissao) throws RollbackException {
		try {
			manager.getTransaction().begin();
			if (permissao.getId() == null) {
				manager.persist(permissao);
			} else {
				manager.merge(permissao);
			}
			manager.getTransaction().commit();
		} catch (RollbackException e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}

	public Permissao searchPermissao(Long PermissaoId) {
		return manager.find(Permissao.class, PermissaoId);
	}
}
