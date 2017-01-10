package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Delegacia;
import repository.IDelegacia;
import util.Repositorios;

@ManagedBean(name="DelegaciaBean")
@ViewScoped
public class DelegaciaBean implements Serializable{

	private Delegacia delegacia = new Delegacia();
	private Delegacia delegaciaSelecionada;
	private List<Delegacia> listaDelegacia = new ArrayList<Delegacia>();
	private Repositorios repositorios = new Repositorios();

	public DelegaciaBean() {
		listaDelegacia = listar();
	}
	
	public void cadastrar(){
		//Esta linha estou instanciando a interface com sua implementacao.
		IDelegacia delegacias = this.repositorios.getDelegacias();
		//Esta linha salva a entidade grupo.
		delegacias.salvar(delegacia);
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		try {
			fc.getExternalContext().redirect("Delegacia.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editar(){
		//Esta linha estou instanciando a interface com sua implementacao.
		IDelegacia delegacias = this.repositorios.getDelegacias();
		//Esta linha salva a entidade grupo.
		delegacias.salvar(delegacia);
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		try {
			fc.getExternalContext().redirect("Delegacia.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void excluir(){
		//Esta linha estou instanciando a interface com sua implementacao.
		IDelegacia delegacias = this.repositorios.getDelegacias();
		//Esta linha salva a entidade grupo.
		delegacias.remover(delegaciaSelecionada);
		System.out.println("ID Delegacia Selecionada: "+ delegaciaSelecionada.getCodigo());
		System.out.println("Delegacia Selecionada: "+ delegaciaSelecionada.getNome());
		
		//Atualizar a lista de grupos
		
		//delegaciaSelecionada = null;
		listar();
	}
	
	public List<Delegacia> listar(){
		//Esta linha estou instanciando a interface com sua implementa��o.
		IDelegacia delegacias = this.repositorios.getDelegacias();
		//Esta linha lista os grupos e joga em uma lista de grupos.
		listaDelegacia = delegacias.listar();
		//Retorna a lista de grupos
		return listaDelegacia;
	}	
	
	public void novo(){
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("DelegaciaNovo.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void edicao(){
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("DelegaciaEdicao.xhtml?codigo="+delegaciaSelecionada.getCodigo());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Delegacia getDelegaciaSelecionada() {
		return delegaciaSelecionada;
	}

	public void setDelegaciaSelecionada(Delegacia delegaciaSelecionada) {
		this.delegaciaSelecionada = delegaciaSelecionada;
	}

	public Delegacia getDelegacia() {
		return delegacia;
	}

	public void setDelegacia(Delegacia delegacia) {
		this.delegacia = delegacia;
	}

	public List<Delegacia> getListaDelegacia() {
		return listaDelegacia;
	}

	public void setListaDelegacia(List<Delegacia> listaDelegacia) {
		this.listaDelegacia = listaDelegacia;
	}
}