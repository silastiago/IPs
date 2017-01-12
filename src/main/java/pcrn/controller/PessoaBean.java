package pcrn.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Grupo;
import pcrn.model.Pessoa;
import pcrn.repository.Grupos;
import pcrn.service.PessoaService;
import pcrn.util.FacesUtil;

@Named
@ViewScoped
public class PessoaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private PessoaService pessoaService;	
	
	private Pessoa pessoa = new Pessoa();
	private Pessoa pessoaSelecionada;
	private List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
	private List<Grupo> listaGrupos;
	
	@Inject
	private Grupos grupos;
	
	@PostConstruct
	public void inicializar(){
		listaGrupos = grupos.listar();
	}
	
	

	public void cadastrar() {
		
		String senha = this.pessoa.getSenha();
		
		this.pessoa.setSenha(FacesUtil.md5(senha));
		pessoaService.salvar(pessoa);
		
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("../Consulta/Pessoa.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void editar() {
		
		String senha = this.pessoa.getSenha();
		this.pessoa.setSenha(FacesUtil.md5(senha));
		pessoaService.salvar(pessoa);

		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("../Consulta/Pessoa.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void excluir(){
		
		pessoaService.remover(pessoaSelecionada);
		pessoaSelecionada = null;
		listar();
	}

	public List<Pessoa> listar(){
		//Esta linha lista os tipos e joga em uma lista de tipos.
		listaPessoas = pessoaService.listar();
		//retorna a lista de tipos.
		return listaPessoas;
	}
	

	public void novo(){
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("../Novo/PessoaNovo.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void edicao(){
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("../Edicao/PessoaEdicao.xhtml?codigo="+pessoaSelecionada.getCodigo());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Grupo> getListaGrupos() {
		return listaGrupos;
	}



	public void setListaGrupos(List<Grupo> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}



	public Pessoa getPessoaSelecionada() {
		return pessoaSelecionada;
	}

	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) throws CloneNotSupportedException {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}
}