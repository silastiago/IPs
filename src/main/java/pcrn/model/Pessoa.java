package pcrn.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/** Esta Classe que possui os metodos de acesso getter e setters que representa um usuario do sistema, 
 * e tamb�m possui o mapeamento relacional das tabelas via hibernate, da entidade Pessoa.
*   
* @author silas
*
*/

@Entity
@Table
public class Pessoa implements Serializable, Cloneable{
	
	private Integer codigo;
	private String login;
	private String senha;
	private List<Grupo> grupos;

	@Id
	@GeneratedValue
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	@NotEmpty(message = "Login deve ser informado")
	@Column(name="login")
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login.toUpperCase();
	}
	
	@NotEmpty(message = "Senha deve ser informada")
	@Column(name="senha")
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}	
	
	@NotNull(message = "Grupo deve ser informado")
	@ManyToMany
	@JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name="codigo_pessoa"),
			inverseJoinColumns = @JoinColumn(name = "codigo_grupo"))
	public List<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		return super.clone();
	}

}