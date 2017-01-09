package pcrn.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import pcrn.interfaces.IEquipamento;
import pcrn.model.Equipamento;

public class Equipamentos implements Serializable, IEquipamento{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Equipamento> listar(int codigo_delegacia) {
		List<Equipamento> listaEquipamento = new ArrayList<Equipamento>();
		Query query = manager.createQuery("from Equipamento where codigo_delegacia = :codigo ");
		query.setParameter("codigo", codigo_delegacia);
		listaEquipamento = query.getResultList();
		return listaEquipamento;
	}

	public Equipamento porCodigo(Integer codigo) {
		return manager.find(Equipamento.class, codigo);
	}

	public void salvar(Equipamento equipamento) {
		manager.merge(equipamento);
	}

	public void remover(Equipamento equipamento) {
		manager.remove(equipamento);
	}

	@SuppressWarnings("unchecked")
	public List<Equipamento> validacaoIPCadastro(Equipamento equipamento) {
		Query query = manager.createQuery("from Equipamento where codigo_delegacia = :codigo_delegacia and ip = :ip");
		query.setParameter("codigo_delegacia", equipamento.getDelegacia().getCodigo());
		query.setParameter("ip", equipamento.getIp());
		List<Equipamento> listaEquipamento = query.getResultList();
		return listaEquipamento;
	}

	@SuppressWarnings("unchecked")
	public List<Equipamento> validacaoIPEdicao(Equipamento equipamento) {
		Query query = manager.createQuery("from Equipamento where codigo_delegacia = :codigo_delegacia and ip = :ip  and not codigo = :codigo");
		query.setParameter("codigo_delegacia", equipamento.getDelegacia().getCodigo());
		query.setParameter("ip", equipamento.getIp());
		query.setParameter("codigo", equipamento.getCodigo());
		List<Equipamento> listaEquipamento = query.getResultList();
		return listaEquipamento;
	}

}
