package pcrn.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Setor;
import pcrn.model.UnidadePolicial;
import pcrn.service.SetorService;


@Named
@ViewScoped
public class SetorBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private SetorService setorService;
	private Setor setor = new Setor();
	private Setor setorSelecionado;
	private List<Setor> listaSetores = new ArrayList<Setor>();
	
	public void cadastrar(){
		//Esta linha salva a entidade grupo.
		setorService.salvar(setor);
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		try {
			fc.getExternalContext().redirect("../Consulta/Setor.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editar(){
		//Esta linha salva a entidade grupo.
		setorService.salvar(setor);
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		try {
			fc.getExternalContext().redirect("../Consulta/Setor.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void excluir(){
		//Esta linha salva a entidade grupo.
		setorService.remover(setorSelecionado);		
		setorSelecionado = null;
		listar();
	}
	
	public List<Setor> listar(){
		//Esta linha lista os grupos e joga em uma lista de grupos.
		listaSetores = setorService.listar();
		//Retorna a lista de grupos
		return listaSetores;
	}	
	
	public void novo(){
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("../Novo/SetorNovo.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void edicao(){
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("../Edicao/SetorEdicao.xhtml?codigo="+setorSelecionado.getCodigo());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Setor> carregaSetor(ValueChangeEvent evento){
		UnidadePolicial unidadesPolicial = (UnidadePolicial) evento.getNewValue();
		listaSetores = setorService.listar(unidadesPolicial.getCodigo());
		
		return listaSetores;
	}
	
	
	public SetorService getSetorService() {
		return setorService;
	}

	public void setSetorService(SetorService setorService) {
		this.setorService = setorService;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Setor getSetorSelecionado() {
		return setorSelecionado;
	}

	public void setSetorSelecionado(Setor setorSelecionado) {
		this.setorSelecionado = setorSelecionado;
	}

	public List<Setor> getListaSetores() {
		return listaSetores;
	}

	public void setListaSetores(List<Setor> listaSetores) {
		this.listaSetores = listaSetores;
	}
}