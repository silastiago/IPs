package repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import model.Grupo;
import repository.IGrupo;

/** Esta � uma Classe concreta que implementa a Interface Grupos,
*   
* @author silas
* @since 16-08-2016
*/
public class GrupoImpl implements IGrupo{
	private Session sessao;

	/**
     * Constructor.
     * @param sessao ser� a sessao que o hibernate cria para conexoes com o banco.
     */
	public GrupoImpl(Session sessao){
		this.sessao = sessao;
	}

	/** Este metodo lista os grupos cadastrados.
	* 	@return retorna a lista dos gruopos cadastrados.
	* 	Este metodo sobrescreve o da interface Grupos.
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Grupo> listar() {
		return sessao.createCriteria(Grupo.class).addOrder(Order.asc("nome")).list();
	}

	/** Este metodo pesquisa um grupo por seu id.
	*  	
	*  @param codigo, Este codigo � o id do grupo que voc� est� procurando.
	*  @return retorna o Grupo daquele id que voc� est� pesquisando.
	*  Este metodo sobrescreve o da interface Grupos.	
	*/
	@Override
	public Grupo porCodigo(Integer codigo) {
		return (Grupo) sessao.get(Grupo.class, codigo);
	}

	/** Este metodo cria ou altera um grupo.
	*  	
	*  @param grupo, Este grupo � o objeto Grupo que voc� ir� criar ou modificar.
	*  Este metodo sobrescreve o da interface Grupos.
	*/
	@Override
	public void salvar(Grupo grupo) {
		this.sessao.merge(grupo);
	}

	/** Este metodo Remove um grupo.
	*  	
	*  @param grupo, Esta grupo � o objeto Grupo que voc� ir� remover.
	*  Este metodo sobrescreve o da interface Grupos.
	*/
	@Override
	public void remover(Grupo grupo) {
		this.sessao.delete(grupo);
	}

	@Override
	public void editar(Grupo grupo) {
		sessao.update(grupo);
		
	}
}