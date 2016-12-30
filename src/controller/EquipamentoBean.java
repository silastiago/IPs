package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import model.Equipamento;
import repository.IEquipamento;
import util.Repositorios;

@ManagedBean(name="EquipamentoBean")
@RequestScoped
public class EquipamentoBean implements Serializable{

	private Equipamento equipamento = new Equipamento();
	private List<Equipamento> listaEquipamento = new ArrayList<Equipamento>();
	private Repositorios repositorios = new Repositorios();

	public void cadastrar(int codigo){
		//Esta linha estou instanciando a interface com sua implementacao.
		IEquipamento equipamentos = this.repositorios.getEquipamentos();
		//Esta linha salva a entidade grupo.
		equipamentos.salvar(equipamento);
		
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("Equipamento.xhtml?codigo="+codigo);
			
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
	}

	public void excluir(Equipamento equipamento){
		//Esta linha estou instanciando a interface com sua implementacao.
		IEquipamento equipamentos = this.repositorios.getEquipamentos();
		//Esta linha salva a entidade grupo.
		equipamentos.remover(equipamento);

		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("Equipamento.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Equipamento> listar(int codigo_delegacia){
		//Esta linha estou instanciando a interface com sua implementa��o.
		IEquipamento equipamentos = this.repositorios.getEquipamentos();
		//Esta linha lista os grupos e joga em uma lista de grupos.
		listaEquipamento = equipamentos.listar(codigo_delegacia);
		//Retorna a lista de grupos
		return listaEquipamento;
	}
	
	public void novo(int codigo){
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("EquipamentoNovo.xhtml?codigo="+codigo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void voltar(int codigo){
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("Equipamento.xhtml?codigo="+codigo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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