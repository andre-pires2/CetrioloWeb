package dream.team.app.cetrioloweb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import dream.team.app.cetrioloweb.entity.Materia;

public class MateriaDao {

	private EntityManager manager = PersistenceManager
									.getInstance().getEntityManager();
	
	public void createOrUpdateMateria(Materia materia) throws RollbackException {
		try {
			manager.getTransaction().begin();
			if (materia.getId() == null) {
				manager.persist(materia);
			} else {
				manager.merge(materia);
			}
			manager.getTransaction().commit();
		} catch (RollbackException e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}
	
	public Materia searchMateria(Long materiaId) {
		return manager.find(Materia.class, materiaId);
	}
	
	public void deleteMateria(Long materiaId) throws RollbackException {
		Materia materia = this.searchMateria(materiaId);
		try {
			manager.getTransaction().begin();
			manager.remove(materia);
			manager.getTransaction().commit();
		} catch (RollbackException e ) {
			manager.getTransaction().rollback();
			throw e;
		}
	}
	
	public List<Materia> executeQuery() {
		System.out.println("-- executing query --");
		String queryText = "select m from Materia m inner join m.alunosCurso a where a.nomeUsuario = :nomeUsuario and a.curso = :curso";
		TypedQuery<Materia> query = manager.createQuery(queryText, Materia.class);
		query.setParameter("nomeUsuario", "Gabriel A");
		query.setParameter("curso", "Matematica");
		List<Materia> resultado = query.getResultList();
		resultado.forEach(System.out::println);
        return resultado;
	}
}
