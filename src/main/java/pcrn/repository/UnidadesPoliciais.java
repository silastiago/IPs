package pcrn.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.EntityManager;

import pcrn.interfaces.IUnidadePolicial;
import pcrn.model.UnidadePolicial;

public class UnidadesPoliciais implements Serializable, IUnidadePolicial{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<UnidadePolicial> listar() {
		List<UnidadePolicial> listaUnidadesPoliciais = null;
		Query query = manager.createQuery("Select u from UnidadePolicial u order by nome_unidade asc");
		listaUnidadesPoliciais = query.getResultList();
		return listaUnidadesPoliciais;
	}

	public UnidadePolicial porCodigo(int codigo) {	
		return manager.find(UnidadePolicial.class, codigo);
	}

	public void salvar(UnidadePolicial unidadePolicial) {
		manager.merge(unidadePolicial);		
	}

	public void remover(UnidadePolicial unidadePolicial) {
		//manager.remove(delegacia);
		manager.remove(manager.getReference(UnidadePolicial.class, unidadePolicial.getCodigo()));
	}

}
