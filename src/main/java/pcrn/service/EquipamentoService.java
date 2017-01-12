package pcrn.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import pcrn.util.jpa.Transactional;
import pcrn.model.Equipamento;
import pcrn.repository.Equipamentos;

public class EquipamentoService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private Equipamentos equipamentos;
	
	@Transactional
	public List<Equipamento> listar(int codigo_delegacia) {
		return equipamentos.listar(codigo_delegacia);
	}
	
	@Transactional
	public Equipamento porCodigo(Integer codigo) {
		return equipamentos.porCodigo(codigo);
	}
	
	@Transactional
	public void salvar(Equipamento equipamento) {
		equipamentos.salvar(equipamento);
	}
	
	@Transactional
	public void remover(Equipamento equipamento) {
		equipamentos.remover(equipamento);
	}
	
	@Transactional
	public List<Equipamento> validacaoIPCadastro(Equipamento equipamento) {
		return equipamentos.validacaoIPCadastro(equipamento);
	}

	@Transactional
	public List<Equipamento> validacaoIPEdicao(Equipamento equipamento) {
		return equipamentos.validacaoIPEdicao(equipamento);
	}
}