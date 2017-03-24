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
		Query query = manager.createQuery("from Equipamento where codigo_unidade = :codigo order by quarto_octal asc");
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
		//manager.remove(equipamento);
		manager.remove(manager.getReference(Equipamento.class, equipamento.getCodigo()));
	}

	@SuppressWarnings("unchecked")
	public List<Equipamento> validacaoIPCadastro(Equipamento equipamento) {
		Query query = manager.createQuery("from Equipamento where codigo_unidade = :codigo_unidade and ip = :ip");
		query.setParameter("codigo_unidade", equipamento.getUnidadePolicial().getCodigo());
		query.setParameter("ip", equipamento.getIp());
		List<Equipamento> listaEquipamento = query.getResultList();
		return listaEquipamento;
	}

	@SuppressWarnings("unchecked")
	public List<Equipamento> validacaoIPEdicao(Equipamento equipamento) {
		Query query = manager.createQuery("from Equipamento where codigo_unidade = :codigo_unidade and ip = :ip  and not codigo = :codigo");
		query.setParameter("codigo_unidade", equipamento.getUnidadePolicial().getCodigo());
		query.setParameter("ip", equipamento.getIp());
		query.setParameter("codigo", equipamento.getCodigo());
		List<Equipamento> listaEquipamento = query.getResultList();
		return listaEquipamento;
	}

	@Override
	public List<Equipamento> listarIpsLivre(int quarto_octal, int codigo_delegacia) {
		
		Query query = manager.createQuery("from Equipamento where codigo_unidade = :codigo_unidade and quarto_octal = :quarto_octal");
		query.setParameter("codigo_unidade", codigo_delegacia);
		query.setParameter("quarto_octal", quarto_octal);
		
		return query.getResultList();
	}

}
