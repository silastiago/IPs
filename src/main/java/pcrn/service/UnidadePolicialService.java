package pcrn.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import pcrn.util.jpa.Transactional;
import pcrn.model.UnidadePolicial;
import pcrn.repository.UnidadesPoliciais;

public class UnidadePolicialService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private UnidadesPoliciais unidadesPoliciais;

	@Transactional
	public List<UnidadePolicial> listar() {
		return unidadesPoliciais.listar();
	}
	
	@Transactional
	public UnidadePolicial porCodigo(Integer codigo) {
		return unidadesPoliciais.porCodigo(codigo);
	}
	
	@Transactional
	public void salvar(UnidadePolicial unidadesPolicial) {
		unidadesPoliciais.salvar(unidadesPolicial);
	}
	
	@Transactional
	public void remover(UnidadePolicial unidadePolicial) {
		unidadesPoliciais.remover(unidadePolicial);
	}
	
}