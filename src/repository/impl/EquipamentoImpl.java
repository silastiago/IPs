package repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import model.Equipamento;
import repository.IEquipamento;

public class EquipamentoImpl implements IEquipamento{

	private Session sessao;
	
	public EquipamentoImpl(Session sessao) {
		this.sessao = sessao;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Equipamento> listar(int codigo_delegacia) {
		List<Equipamento> listaEquipamento = new ArrayList<Equipamento>();
		Query query = sessao.createQuery("from Equipamento where codigo_delegacia = :code ");
		query.setParameter("code", codigo_delegacia);
		listaEquipamento = query.list();
		return listaEquipamento;
	}
	
	

	/*
	@SuppressWarnings("unchecked")
	@Override
	public List<Equipamento> listar(int codigo_delegacia) {
		List<Equipamento> listaEquipamento = new ArrayList<Equipamento>();
		listaEquipamento = this.sessao.createCriteria(Equipamento.class).add(Restrictions.eq("codigo_delegacia", codigo_delegacia)).list();
		return listaEquipamento;
	}
	*/
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<Equipamento> listar() {
		List<Equipamento> listaEquipamento = new ArrayList<Equipamento>();
		listaEquipamento = this.sessao.createCriteria(Equipamento.class).addOrder(Order.asc("nome")).list();
		return listaEquipamento;
	}*/
	
	@Override
	public Equipamento porCodigo(Integer codigo) {
		return (Equipamento) sessao.get(Equipamento.class, codigo);
	}

	@Override
	public void salvar(Equipamento equipamento) {
		sessao.merge(equipamento);
	}

	@Override
	public void remover(Equipamento equipamento) {
		sessao.delete(equipamento);		
	}

	@Override
	public List<Equipamento> validacaoIPCadastro(Equipamento equipamento) {
		Query query = sessao.createQuery("from Equipamento where codigo_delegacia = :codigo_delegacia and ip = :ip");
		query.setParameter("codigo_delegacia", equipamento.getDelegacia().getCodigo());
		query.setParameter("ip", equipamento.getIp());
		List<Equipamento> results = query.list();
		return results;
	}
	
	@Override
	public List<Equipamento> validacaoIPEdicao(Equipamento equipamento) {
		Query query = sessao.createQuery("from Equipamento where codigo_delegacia = :codigo_delegacia and ip = :ip  and not codigo = :codigo");
		query.setParameter("codigo_delegacia", equipamento.getDelegacia().getCodigo());
		query.setParameter("ip", equipamento.getIp());
		query.setParameter("codigo", equipamento.getCodigo());
		List<Equipamento> results = query.list();
		return results;
	}
	

}
