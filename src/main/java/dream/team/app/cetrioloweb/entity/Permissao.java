package dream.team.app.cetrioloweb.entity;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="per_permissao")
@AttributeOverride(name = "id", column = @Column(name = "per_id"))
public class Permissao extends GerarID {

	@Column(name = "per_tipo", length = 10, nullable = false)
	private String tipo;

	@JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy="permissao")
    private Set<Usuario> usuarios;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Permissao [tipo=" + tipo + "]";
	}

}
