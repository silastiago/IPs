package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.Grupo;
import repository.IGrupo;
import util.Repositorios;

/** Esta � uma Classe concreta que une as implementacoes das interfaces e das paginas xhtml referentes a entidade Grupo.
 *   
 * @author silas
 * @since 18-08-2016
 */

@ManagedBean(name="GrupoBean")
@ViewScoped
public class GrupoBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Grupo grupo = new Grupo();
	private Grupo grupoSelecionado;
	private List<Grupo> listaGrupos = new ArrayList<Grupo>();

	/** Este metodo cadastra um Grupo.
	 */
	public void cadastrar(){
		//Esta linha estou instanciando a interface com sua implementacao.
		IGrupo grupos = this.repositorios.getGrupos();
		//Esta linha salva a entidade grupo.
		grupos.salvar(this.grupo);

		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("Grupo.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void editar(){
		//Esta linha estou instanciando a interface com sua implementacao.
		IGrupo grupos = this.repositorios.getGrupos();
		//Esta linha salva a entidade grupo.
		grupos.salvar(this.grupo);

		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("Grupo.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/** Este metodo Remove um grupo.
	 *  @param grupo, Este grupo � o objeto Grupo que voc� ir� remover.
	 */
	public void excluir(){
		//Esta linha estou instanciando a interface com sua implementa��o.
		IGrupo grupos = this.repositorios.getGrupos();
		//Esta linha remove o grupo.
		grupos.remover(grupoSelecionado);
		//Atualizar a lista de grupos
		
		grupoSelecionado = null;
		listar();
	}

	/** Este metodo lista todos os grupos cadastrados.
	 * 	@return retorna a lista de todos os grupos cadastradas no sistema.
	 */
	public List<Grupo> listar(){
		//Esta linha estou instanciando a interface com sua implementa��o.
		IGrupo grupos = this.repositorios.getGrupos();
		//Esta linha lista os grupos e joga em uma lista de grupos.
		listaGrupos = grupos.listar();
		//Retorna a lista de grupos
		return listaGrupos;
	}	

	public void novo(){
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("GrupoNovo.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void edicao(){
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("GrupoEdicao.xhtml?codigo="+grupoSelecionado.getCodigo());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Grupo getGrupoSelecionado() {
		return grupoSelecionado;
	}

	public void setGrupoSelecionado(Grupo grupoSelecionado) {
		this.grupoSelecionado = grupoSelecionado;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Grupo> getListaGrupos() {
		return listaGrupos;
	}

	public void setListaGrupos(List<Grupo> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}
}