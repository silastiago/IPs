package pcrn.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.UnidadePolicial;
import pcrn.service.UnidadePolicialService;


@Named
@ViewScoped
public class UnidadePolicialBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UnidadePolicialService unidadePolicialService;
	private UnidadePolicial unidadePolicial = new UnidadePolicial();
	private UnidadePolicial unidadePolicialSelecionada;
	private List<UnidadePolicial> listaUnidadesPoliciais = new ArrayList<UnidadePolicial>();
	
	public void cadastrar(){
		//Esta linha salva a entidade grupo.
		unidadePolicialService.salvar(unidadePolicial);
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		try {
			fc.getExternalContext().redirect("../Consulta/UnidadePolicial.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editar(){
		//Esta linha salva a entidade grupo.
		unidadePolicialService.salvar(unidadePolicial);
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		try {
			fc.getExternalContext().redirect("../Consulta/UnidadePolicial.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void excluir(){
		//Esta linha salva a entidade grupo.
		unidadePolicialService.remover(unidadePolicialSelecionada);		
		unidadePolicialSelecionada = null;
		listar();
	}
	
	public List<UnidadePolicial> listar(){
		//Esta linha lista os grupos e joga em uma lista de grupos.
		listaUnidadesPoliciais = unidadePolicialService.listar();
		//Retorna a lista de grupos
		return listaUnidadesPoliciais;
	}	
	
	public void novo(){
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("../Novo/UnidadePolicialNovo.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void edicao(){
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("../Edicao/UnidadePolicialEdicao.xhtml?codigo="+unidadePolicialSelecionada.getCodigo());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public UnidadePolicialService getUnidadePolicialService() {
		return unidadePolicialService;
	}

	public void setUnidadePolicialService(UnidadePolicialService unidadePolicialService) {
		this.unidadePolicialService = unidadePolicialService;
	}

	public UnidadePolicial getUnidadePolicial() {
		return unidadePolicial;
	}

	public void setUnidadePolicial(UnidadePolicial unidadePolicial) {
		this.unidadePolicial = unidadePolicial;
	}

	public UnidadePolicial getUnidadePolicialSelecionada() {
		return unidadePolicialSelecionada;
	}

	public void setUnidadePolicialSelecionada(UnidadePolicial unidadePolicialSelecionada) {
		this.unidadePolicialSelecionada = unidadePolicialSelecionada;
	}

	public List<UnidadePolicial> getListaUnidadesPoliciais() {
		return listaUnidadesPoliciais;
	}

	public void setListaUnidadesPoliciais(List<UnidadePolicial> listaUnidadesPoliciais) {
		this.listaUnidadesPoliciais = listaUnidadesPoliciais;
	}
	
}