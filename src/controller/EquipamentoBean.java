package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.Equipamento;
import repository.IEquipamento;
import util.Repositorios;

@ManagedBean(name="EquipamentoBean")
@RequestScoped
public class EquipamentoBean implements Serializable{

	private Equipamento equipamento = new Equipamento();
	private List<Equipamento> listaEquipamento = new ArrayList<Equipamento>();
	private Repositorios repositorios = new Repositorios();

	public void cadastrar(){
		//Esta linha estou instanciando a interface com sua implementacao.
		IEquipamento equipamentos = this.repositorios.getEquipamentos();
		//Esta linha salva a entidade grupo.
		equipamentos.salvar(equipamento);
	}

	public void editar(){
		//Esta linha estou instanciando a interface com sua implementacao.
		IEquipamento equipamentos = this.repositorios.getEquipamentos();
		//Esta linha salva a entidade grupo.
		equipamentos.salvar(equipamento);
	}
	
	public void excluir(Equipamento equipamento){
		//Esta linha estou instanciando a interface com sua implementacao.
		IEquipamento equipamentos = this.repositorios.getEquipamentos();
		//Esta linha salva a entidade grupo.
		equipamentos.remover(equipamento);
		//Atualizar a lista de grupos
		this.listar();
	}

	public List<Equipamento> listar(){
		//Esta linha estou instanciando a interface com sua implementação.
		IEquipamento equipamentos = this.repositorios.getEquipamentos();
		//Esta linha lista os grupos e joga em uma lista de grupos.
		listaEquipamento = equipamentos.listar();
		//Retorna a lista de grupos
		return listaEquipamento;
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