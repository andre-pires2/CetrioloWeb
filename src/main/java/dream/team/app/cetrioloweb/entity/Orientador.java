package dream.team.app.cetrioloweb.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="ori_orientador")
@PrimaryKeyJoinColumn(name = "ori_id")
public class Orientador extends Usuario {
	
    @OneToMany(fetch = FetchType.LAZY, mappedBy="orientador")
    private Set<Aluno> alunosOrientados;

	public Set<Aluno> getAlunosOrientados() {
        return alunosOrientados;
    }

    public void setAlunosOrientados(Set<Aluno> alunosOrientados) {
        this.alunosOrientados = alunosOrientados;
    }
}
