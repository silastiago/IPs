package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import model.Delegacia;
import model.Grupo;
import repository.IDelegacia;
import repository.IGrupo;
import util.Repositorios;

/** Esta é uma Classe concreta que une as implementacoes das interfaces e das paginas xhtml referentes a entidade Grupo.
*   
* @author silas
* @since 18-08-2016
*/

@ManagedBean(name="GrupoBean")
@RequestScoped
public class GrupoBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Grupo grupo = new Grupo(); 	
	private List<Grupo> listaGrupos = new ArrayList<Grupo>();

	/** Este metodo cadastra um Grupo.
	*/
	public void cadastrar(){
		//Esta linha estou instanciando a interface com sua implementacao.
		IGrupo grupos = this.repositorios.getGrupos();
		//Esta linha salva a entidade grupo.
		grupos.salvar(grupo);
	}

	/** Este metodo Remove um grupo.
	*  @param grupo, Este grupo é o objeto Grupo que você irá remover.
	*/
	public void excluir(Grupo grupo){
		//Esta linha estou instanciando a interface com sua implementação.
		IGrupo grupos = this.repositorios.getGrupos();
		//Esta linha remove o grupo.
		grupos.remover(grupo);
		//Atualizar a lista de grupos
		this.listarGrupos();
	}
	
	/** Este metodo lista todos os grupos cadastrados.
	* 	@return retorna a lista de todos os grupos cadastradas no sistema.
	*/
	public List<Grupo> listarGrupos(){
		//Esta linha estou instanciando a interface com sua implementação.
		IGrupo grupos = this.repositorios.getGrupos();
		//Esta linha lista os grupos e joga em uma lista de grupos.
		listaGrupos = grupos.listar();
		//Retorna a lista de grupos
		return listaGrupos;
	}
	
	public void onRowEdit(RowEditEvent event) throws IOException {
		Grupo grupoTemporario = (Grupo) event.getObject();
		IGrupo grupos = this.repositorios.getGrupos();
		grupos.editar(grupoTemporario);
		//String codigo = delegaciaTemporaria.getCodigo();
		
	    FacesContext.getCurrentInstance().getExternalContext().redirect("Grupo.xhtml");
    }
	
	public void onRowCancel(RowEditEvent event) throws IOException {
	    FacesContext.getCurrentInstance().getExternalContext().redirect("Grupo.xhtml");
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