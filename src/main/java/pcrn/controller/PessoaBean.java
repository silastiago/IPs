package pcrn.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import pcrn.model.Pessoa;
import pcrn.service.PessoaService;
import pcrn.util.FacesUtil;

@Named
@RequestScoped
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
	
	private HttpServletRequest getRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (HttpServletRequest) context.getExternalContext().getRequest();
	}

	public void cadastrar() {
		
		String senha = this.pessoa.getSenha();
		
		this.pessoa.setSenha(FacesUtil.md5(senha));
		pessoaService.salvar(pessoa);
		
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("Pessoa.xhtml");
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
			fc.getExternalContext().redirect("Pessoa.xhtml");
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

	public void logar(){
		
		String pagina = "";
		FacesContext fc = FacesContext.getCurrentInstance();

		String senha = pessoa.getSenha();
		System.out.println("Senha no metodo logar: " + senha);
		pessoa.setSenha(FacesUtil.md5(senha));
		System.out.println("Senha criptografada no metodo logar: " + pessoa.getSenha());

		if (pessoaService.login(pessoa) == false) {
			pagina = "login.xhtml?faces-redirect=true";
			fc.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Login ou Senha errado"));
		}else{
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
			session.setAttribute("usuario", pessoa.getLogin());
			session.setAttribute("senha", pessoa.getSenha());
			pagina = "site/index.xhtml";
			try {
				fc.getExternalContext().redirect(pagina);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//return pagina;
	}

	public void sair() {
		pessoaService.logout();

		try {

			FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml?faces-redirect=true");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
			fc.getExternalContext().redirect("PessoaNovo.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void edicao(){
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("PessoaEdicao.xhtml?codigo="+pessoaSelecionada.getCodigo());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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