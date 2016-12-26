package repository;

import java.util.List;

import model.Delegacia;
import model.Grupo;

/** Esta é uma Interface que possui as assinaturas dos metodos da classe Grupo,
*   
* @author silas
* @since 15-08-2016
*/

public interface IDelegacia {

	/** Este metodo lista todos os grupos cadastrados.
	*   
	* @return retorna a lista dos grupos cadastrados.
	*   	
	*/
	public List<Delegacia> listar();
	
	/** Este metodo pesquisa um grupo por seu id.
	*  	
	*  @param codigo, Este codigo é o id do Grupo que você está procurando.
	*  @return retorna o Grupo daquele id que você está pesquisando.
	*   	
	*/
	public Delegacia porCodigo(Integer codigo);
	
	/** Este metodo cria ou altera um grupo.
	*  	
	*  @param grupo, Este grupo é o objeto Grupo que você irá criar ou modificar.
	*   	
	*/
	public void salvar(Delegacia delegacia);
	
	/** Este metodo Remove um grupo.
	*  	
	*  @param grupo, Esta grupo é o objeto Grupo que você irá remover.
	*   	
	*/
	public void remover(Delegacia delegacia);
}