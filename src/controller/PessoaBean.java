package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.event.RowEditEvent;

import model.Delegacia;
import model.Pessoa;
import repository.IDelegacia;
import repository.IPessoa;
import util.Repositorios;

@ManagedBean(name="PessoaBean")
@RequestScoped
public class PessoaBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Pessoa pessoa = new Pessoa();
	private List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
	//private List<Grupo> grupos = new ArrayList<Grupo>();

	/*@PostConstruct
	public void init(){
		Pessoas pessoas = this.repositorios.getPessoas();
		this.pessoas = pessoas.listar();
	}*/

	private HttpServletRequest getRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (HttpServletRequest) context.getExternalContext().getRequest();
	}
	
	public void cadastrar(){
		IPessoa pessoas = this.repositorios.getPessoas();
		pessoas.salvar(this.pessoa);
	}

	public void excluir(Pessoa pessoa){
		IPessoa pessoas = this.repositorios.getPessoas();
		pessoas.remover(pessoa);
		this.listarPessoas();
	}

	public void logar(){
		
		IPessoa pessoas = this.repositorios.getPessoas();
		String pagina = "";
		FacesContext fc = FacesContext.getCurrentInstance();
		
		if (pessoas.login(pessoa) == false) {
			pagina = "login.xhtml?faces-redirect=true";
			fc.addMessage("message", new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Login ou Senha errado"));
		}else{
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
			session.setAttribute("usuario", pessoa.getLogin());
			session.setAttribute("senha", pessoa.getSenha());
			//pagina = "site/index.xhtml?faces-redirect=true";
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
		IPessoa pessoas = this.repositorios.getPessoas();
		pessoas.logout();
		
		try {
			
			FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml?faces-redirect=true");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void onRowEdit(RowEditEvent event) throws IOException {
		Pessoa pessoaTemporaria = (Pessoa) event.getObject();
		IPessoa pessoas = this.repositorios.getPessoas();
		pessoas.editar(pessoaTemporaria);
		//String codigo = delegaciaTemporaria.getCodigo();
		
	    FacesContext.getCurrentInstance().getExternalContext().redirect("Pessoa.xhtml");
    }
	
	public void onRowCancel(RowEditEvent event) throws IOException {
	    FacesContext.getCurrentInstance().getExternalContext().redirect("Pessoa.xhtml");
    }
	
	
	public List<Pessoa> listarPessoas(){
		//Esta linha estou instanciando a interface com sua implementacao.
		IPessoa pessoas = this.repositorios.getPessoas();
		//Esta linha lista os tipos e joga em uma lista de tipos.
		listaPessoas = pessoas.listar();
		//retorna a lista de tipos.
		return listaPessoas;
	}
	
	public boolean visualizar_cadastro(){
		Pessoa pessoa = new Pessoa();
		String login = this.getRequest().getSession().getAttribute("usuario").toString();
		IPessoa pessoas = this.repositorios.getPessoas();
		pessoa = pessoas.retornaPessoa(login);
		
		if(pessoa.getGrupo().getNome().toUpperCase().equals("ADMINISTRADOR")){
			return true;
		}else{
			return false;
		}	
	}
	
	public boolean visualizar_consulta(){
		Pessoa pessoa = new Pessoa();
		String login = this.getRequest().getSession().getAttribute("usuario").toString();
		IPessoa pessoas = this.repositorios.getPessoas();
		pessoa = pessoas.retornaPessoa(login);
		
		if(pessoa.getGrupo().getNome().toUpperCase().equals("ADMINISTRADOR") || pessoa.getGrupo().getNome().toUpperCase().equals("CONSULTA")){
			return true;
		}else{
			return false;
		}	
	}
	
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) throws CloneNotSupportedException {
		this.pessoa = pessoa;
		if (this.pessoa == null) {
			this.pessoa = new Pessoa();
		}else {
			this.pessoa = (Pessoa) pessoa.clone();
		}
	}

	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}
}