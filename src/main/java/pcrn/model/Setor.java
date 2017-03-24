package pcrn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="setor")
public class Setor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	private String nome;
	private UnidadePolicial unidadePolicial;
	
	@Id
	@GeneratedValue
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	@NotEmpty(message = "Nome do setor deve ser informada")
	@Column(name="nome_setor")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@NotNull(message = "Unidade Policial deve ser informada")
	@ManyToOne
	@JoinColumn(name="codigo_unidade", referencedColumnName="codigo")
	public UnidadePolicial getUnidadePolicial() {
		return unidadePolicial;
	}
	public void setUnidadePolicial(UnidadePolicial unidadePolicial) {
		this.unidadePolicial = unidadePolicial;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((unidadePolicial == null) ? 0 : unidadePolicial.hashCode());
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
		Setor other = (Setor) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (unidadePolicial == null) {
			if (other.unidadePolicial != null)
				return false;
		} else if (!unidadePolicial.equals(other.unidadePolicial))
			return false;
		return true;
	}
}