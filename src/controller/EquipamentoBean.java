package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Equipamento;
import repository.IEquipamento;
import util.Repositorios;

@ManagedBean(name="EquipamentoBean")
@ViewScoped
public class EquipamentoBean implements Serializable{

	private Equipamento equipamento = new Equipamento();
	private Equipamento equipamentoSelecionado;
	private List<Equipamento> listaEquipamento;
	private Repositorios repositorios = new Repositorios();
	
	public EquipamentoBean() {
		listaEquipamento  = new ArrayList<Equipamento>();
	}
	
	
	public void cadastrar(){
		//Esta linha estou instanciando a interface com sua implementacao.
		IEquipamento equipamentos = this.repositorios.getEquipamentos();
		//Esta linha salva a entidade grupo.
		equipamentos.salvar(equipamento);

		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("Equipamento.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editar(){
		//Esta linha estou instanciando a interface com sua implementacao.
		IEquipamento equipamentos = this.repositorios.getEquipamentos();
		//Esta linha salva a entidade grupo.
		equipamentos.salvar(equipamento);

		int codigoDelegacia = equipamento.getDelegacia().getCodigo();
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("Equipamento.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void excluir(){
		//Esta linha estou instanciando a interface com sua implementacao.
		IEquipamento equipamentos = this.repositorios.getEquipamentos();

		//Esta linha salva a entidade grupo.
		equipamentos.remover(equipamentoSelecionado);		

		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("Equipamento.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Equipamento> listarDelegacias(){
		listaEquipamento = repositorios.getEquipamentos().listar(equipamento.getDelegacia().getCodigo());
		return listaEquipamento;
	}


	public List<Equipamento> listar(){

		//Esta linha estou instanciando a interface com sua implementa��o.
		IEquipamento equipamentos = this.repositorios.getEquipamentos();
		if (this.equipamento == null) {
			listaEquipamento = null;
		}else {
			//Esta linha lista os grupos e joga em uma lista de grupos.
			listaEquipamento = equipamentos.listar(this.equipamento.getDelegacia().getCodigo());
		}

		//Retorna a lista de grupos
		return listaEquipamento;
	}

	public void novo(){
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("EquipamentoNovo.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void edicao(){
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("EquipamentoEdicao.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Equipamento getEquipamentoSelecionado() {
		return equipamentoSelecionado;
	}


	public void setEquipamentoSelecionado(Equipamento equipamentoSelecionado) {
		this.equipamentoSelecionado = equipamentoSelecionado;
	}


	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public List<Equipamento> getListaEquipamento() {
		return listaEquipamento;
	}

	public void setListaEquipamento(List<Equipamento> listaEquipamento) {
		this.listaEquipamento = listaEquipamento;
	}
}