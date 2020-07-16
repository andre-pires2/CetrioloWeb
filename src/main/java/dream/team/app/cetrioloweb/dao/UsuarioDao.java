package dream.team.app.cetrioloweb.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import dream.team.app.cetrioloweb.entity.Usuario;

public class UsuarioDao {
	
	private static EntityManager manager = PersistenceManager
			.getInstance().getEntityManager();

	public Usuario searchUsuario(Long UsuarioId) {
		return manager.find(Usuario.class, UsuarioId);
	}
	
    public Usuario searchUsuarioByEmail(String email) {
        String queryText = "select u " +
                "from Usuario u " +
                "where u.email = :email";
        TypedQuery<Usuario> query = (TypedQuery<Usuario>) manager.createQuery(queryText, Usuario.class);
        query.setParameter("email", email);
        Usuario resultado = query.getSingleResult();
        return resultado;
    }

}

