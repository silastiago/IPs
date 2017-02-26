package pcrn.repository.filter;

import java.io.Serializable;

public class PontoFiltro implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	private String dataCriacaoDe;
	private String dataCriacaoAte;
	private String nomePessoa;
	
	public String getDataCriacaoDe() {
		return dataCriacaoDe;
	}
	public void setDataCriacaoDe(String dataCriacaoDe) {
		this.dataCriacaoDe = dataCriacaoDe;
	}
	public String getDataCriacaoAte() {
		return dataCriacaoAte;
	}
	public void setDataCriacaoAte(String dataCriacaoAte) {
		this.dataCriacaoAte = dataCriacaoAte;
	}
	public String getNomePessoa() {
		return nomePessoa;
	}
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}
}