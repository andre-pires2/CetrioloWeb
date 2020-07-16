package dream.team.app.cetrioloweb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import dream.team.app.cetrioloweb.entity.Aluno;

public class AlunoDao {
	
	private static EntityManager manager = PersistenceManager
											.getInstance().getEntityManager();
	
	public Aluno searchAluno(Long alunoId) {
		return manager.find(Aluno.class, alunoId);
	}
	
	public void createOrUpdateAluno(Aluno aluno) throws RollbackException {
		try {
			manager.getTransaction().begin();
			if (aluno.getId() == null) {
				manager.persist(aluno);
			} else {
				manager.merge(aluno);
			}
			manager.getTransaction().commit();
		} catch (RollbackException e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}
	
	public void deleteAluno(Long alunoId) throws RollbackException {
		Aluno aluno = this.searchAluno(alunoId);
		try {
			manager.getTransaction().begin();
			manager.remove(aluno);
			manager.getTransaction().commit();
		} catch (RollbackException e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}
	
    public List<Aluno> executeQuery(String nomeUsuario, String email) {
        System.out.println("-- executing query ALUNO--");
        String queryText = "select a " +
                "from Aluno a " +
                "inner join a.orientador o where o.nomeUsuario = :nomeUsuario and o.email = :email";
        TypedQuery<Aluno> query = (TypedQuery<Aluno>) manager.createQuery(queryText, Aluno.class);
        query.setParameter("nomeUsuario", nomeUsuario);
        query.setParameter("email", email);
        List<Aluno> resultado = query.getResultList();
        resultado.forEach(System.out::println);
        return resultado;
    }
	
}