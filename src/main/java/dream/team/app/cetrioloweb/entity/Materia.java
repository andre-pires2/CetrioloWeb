package dream.team.app.cetrioloweb.entity;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="mat_materia")
@AttributeOverride(name = "id", column = @Column(name = "mat_id"))
public class Materia extends GerarID{

    @Column(name = "mat_nome", length = 50, nullable = false)
    private String nome;
    
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "materias")
    private Set<Aluno> alunosCurso;
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
    	this.nome = nome;
    }    
    
    public Set<Aluno> getAlunosCurso() {
    	return alunosCurso;
    }
    
    public void setAlunosCurso(Set<Aluno> alunosCurso) {
    	this.alunosCurso = alunosCurso;
    }
}
