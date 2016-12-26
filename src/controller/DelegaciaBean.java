package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.Delegacia;
import repository.IDelegacia;
import util.Repositorios;

@ManagedBean(name="DelegaciaBean")
@RequestScoped
public class DelegaciaBean implements Serializable{

	private Delegacia delegacia = new Delegacia();
	private List<Delegacia> listaDelegacia = new ArrayList<Delegacia>();
	private Repositorios repositorios = new Repositorios();

	public void cadastrar(){
		//Esta linha estou instanciando a interface com sua implementacao.
		IDelegacia delegacias = this.repositorios.getDelegacias();
		//Esta linha salva a entidade grupo.
		delegacias.salvar(delegacia);
	}

	public void excluir(Delegacia delegacia){
		//Esta linha estou instanciando a interface com sua implementacao.
		IDelegacia delegacias = this.repositorios.getDelegacias();
		//Esta linha salva a entidade grupo.
		delegacias.remover(delegacia);
		//Atualizar a lista de grupos
		this.listarDelegacias();
	}	
	
	public List<Delegacia> listarDelegacias(){
		//Esta linha estou instanciando a interface com sua implementação.
		IDelegacia delegacias = this.repositorios.getDelegacias();
		//Esta linha lista os grupos e joga em uma lista de grupos.
		listaDelegacia = delegacias.listar();
		//Retorna a lista de grupos
		return listaDelegacia;
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