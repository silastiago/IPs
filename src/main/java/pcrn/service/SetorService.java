package pcrn.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import pcrn.util.jpa.Transactional;
import pcrn.model.Setor;
import pcrn.repository.Setores;

public class SetorService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private Setores setores;

	@Transactional
	public List<Setor> listar() {
		return setores.listar();
	}
	
	@Transactional
	public List<Setor> listar(int codigo_unidade) {
		return setores.listar(codigo_unidade);
	}
	
	@Transactional
	public Setor porCodigo(Integer codigo) {
		return setores.porCodigo(codigo);
	}
	
	@Transactional
	public void salvar(Setor setor) {
		setores.salvar(setor);
	}
	
	@Transactional
	public void remover(Setor setor) {
		setores.remover(setor);
	}
	
}