package util;

import java.io.Serializable;

import org.hibernate.Session;

import repository.IDelegacia;
import repository.IEquipamento;
import repository.IGrupo;
import repository.IPessoa;
import repository.impl.DelegaciaImpl;
import repository.impl.EquipamentoImpl;
import repository.impl.GrupoImpl;
import repository.impl.PessoaImpl;



/** Esta é uma Classe concreta que instancia as interfaces,
*   
* @author silas
* @since 16-08-2016
*/
public class Repositorios implements Serializable {

	/** Este metodo retorna a interface Pessoas instanciada com sua implementacao.
	* 	@return retorna a interface Pessoas.
	*/
	public IPessoa getPessoas() {
		return new PessoaImpl(this.getSession());
	}
	
	/** Este metodo retorna a interface Grupos instanciada com sua implementacao.
	* 	@return retorna a interface Grupos.
	*/
	public IGrupo getGrupos(){
		return new GrupoImpl(this.getSession());
	}
	
	/** Este metodo retorna a interface IDelegacia instanciada com sua implementacao.
	* 	@return retorna a interface IDelegacia.
	*/
	public IDelegacia getDelegacias() {
		return new DelegaciaImpl(this.getSession());
	}
	
	
	/** Este metodo retorna a interface Pessoas instanciada com sua implementacao.
	* 	@return retorna a interface Pessoas.
	*/
	public IEquipamento getEquipamentos() {
		return new EquipamentoImpl(this.getSession());
	}
	
	
	/** Este metodo pega a sessao do hibernate.
	* 	@return retorna a Sesion.
	*/
	private Session getSession() {
		return (Session) FacesUtil.getRequestAttribute("session");
	}
}