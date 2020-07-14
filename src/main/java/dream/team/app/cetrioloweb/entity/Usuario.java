package dream.team.app.cetrioloweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "usu_usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario extends GerarID {

    @Column(name="usu_nome", length = 50, nullable = false)
    private String nomeUsuario;

    @Column(name="usu_email", length = 50, nullable = false)
    private String email;

    @Column(name="usu_telefone", length = 20)
    private String telefone;

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
