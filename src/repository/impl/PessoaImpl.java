package repository.impl;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import model.Pessoa;
import repository.IPessoa;

/** Esta é uma Classe concreta que implementa a Interface Pessoas,
*   
* @author silas
* @since 16-08-2016
*/
public class PessoaImpl implements IPessoa{
	private Session sessao;

	/**
     * Constructor.
     * @param sessao será a sessao que o hibernate cria para conexoes com o banco.
     */
	public PessoaImpl(Session sessao){
		this.sessao = sessao;
	}

	/** Este metodo lista as pessoas cadastradas.
	* 	@return retorna a lista das pessoas cadastradas.
	* 	Este metodo sobrescreve o da interface Pessoas.
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Pessoa> listar() {
		return sessao.createCriteria(Pessoa.class).addOrder(Order.asc("login")).list();
	}

	/** Este metodo pesquisa uma pessoa por seu id.
	*  	
	*  @param codigo, Este codigo é o id da pessoa que você está procurando.
	*  @return retorna a Pessoa daquele id que você está pesquisando.
	*  Este metodo sobrescreve o da interface Pessoas.	
	*/
	@Override
	public Pessoa porCodigo(Integer codigo) {
		return (Pessoa) sessao.get(Pessoa.class, codigo);
	}

	/** Este metodo cria ou altera uma pessoa.
	*  	
	*  @param pessoa, Esta pessoa é o objeto Pessoa que você irá criar ou modificar.
	*  Este metodo sobrescreve o da interface Pessoas.
	*/
	@Override
	public void salvar(Pessoa pessoa) {
		this.sessao.merge(pessoa);
	}

	/** Este metodo Remove uma pessoa.
	*  	
	*  @param pessoa, Esta pessoa é o objeto Pesssoa que você irá remover.
	*  Este metodo sobrescreve o da interface Pessoas.
	*/
	@Override
	public void remover(Pessoa pessoa) {
		this.sessao.delete(pessoa);
	}
	
	/** Este metodo faz o login do usuario no sistema.
	 * 	Este metodo ainda não está funcionando por alguns bugs.
	*   
	*   @param pessoa, Esta pessoa é o objeto Pessoa que irá fazer o login.
	* 	@return boolean, retorna verdadeiro se a pessoa fez o login correto caso contrario retorna false.
	*   Este metodo sobrescreve o da interface Pessoas.
	*/
	@Override
	public boolean login(Pessoa pessoa) {
		boolean pessoaExistente = true;
		Criteria c = this.sessao.createCriteria(Pessoa.class);
		c.add(Restrictions.eq("login", pessoa.getLogin()));
		c.add(Restrictions.eq("senha", pessoa.getSenha()));
		if (c.uniqueResult() == null) {
			pessoaExistente = false;
		}else{
			pessoaExistente = true;
		}
		
		return pessoaExistente;
	}

	/** Este metodo faz o logout do usuario no sistema.
	* 	Este metodo ainda não está funcionando por alguns bugs.
	*   	
	*/
	@Override
	public void logout() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.removeAttribute("usuario");
		session.removeAttribute("senha");
		session.invalidate();
	}
	
	
	@Override
	public Pessoa retornaPessoa(String login) {
		Criteria c = this.sessao.createCriteria(Pessoa.class);
		c.add(Restrictions.eq("login", login));
		Pessoa results =  (Pessoa) c.uniqueResult();
		return results;
	}
	
}