package dream.team.app.cetrioloweb.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name = "alu_aluno")
@PrimaryKeyJoinColumn(name = "alu_id")
public class Aluno extends Usuario {
	
	@Column(name = "alu_curso", length = 30, nullable = false)
	private String curso;
	
	//bd: fk para orientador
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ori_usu_id")
	private Orientador orientador;
	
	//bd: interseccao com N-N ama_aluno_materias
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "ama_aluno_materia",
		joinColumns = { @JoinColumn(name = "alu_id") },
		inverseJoinColumns = { @JoinColumn(name = "mat_id") 
	})
	
    private Set<Materia> materias;


	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public Orientador getOrientador() {
		return orientador;
	}

	public void setOrientador(Orientador orientador) {
		this.orientador = orientador;
	}

	public Set<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(Set<Materia> materias) {
		this.materias = materias;
	}

}