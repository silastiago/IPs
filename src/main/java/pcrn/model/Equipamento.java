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
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table
public class Equipamento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer codigo;
	private String nome;
	private String descricao;
	private String ip;
	private int quartoOctal;
	private UnidadePolicial unidadePolicial;
	private Setor setor;
	
	@Id
	@GeneratedValue
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	@NotEmpty(message = "Nome da maquina deve ser informado")
	@Column(name="nome_equipamento")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@NotNull(message = "Tipo da maquina deve ser informado")
	@Column
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Pattern(regexp="^([01]?[0-9]?[0-9]|2[0-4][0-9]|25[0-5]).([01]?[0-9]?[0-9]|2[0-4][0-9]|25[0-5]).([01]?[0-9]?[0-9]|2[0-4][0-9]|25[0-5]).([01]?[0-9]?[0-9]|2[0-4][0-9]|25[0-5])", message= "Numero de IP Invalido")
	@Column
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Column(name="quarto_octal")
	public int getQuartoOctal() {
		return quartoOctal;
	}
	public void setQuartoOctal(int quartoOctal) {
		this.quartoOctal = quartoOctal;
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
	
	@NotNull(message = "Setor deve ser informado")
	@ManyToOne
	@JoinColumn(name="codigo_setor", referencedColumnName="codigo")
	public Setor getSetor() {
		return setor;
	}
	
	
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + quartoOctal;
		result = prime * result + ((setor == null) ? 0 : setor.hashCode());
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
		Equipamento other = (Equipamento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (quartoOctal != other.quartoOctal)
			return false;
		if (setor == null) {
			if (other.setor != null)
				return false;
		} else if (!setor.equals(other.setor))
			return false;
		if (unidadePolicial == null) {
			if (other.unidadePolicial != null)
				return false;
		} else if (!unidadePolicial.equals(other.unidadePolicial))
			return false;
		return true;
	}
}
