package repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import model.Equipamento;
import repository.IEquipamento;

public class EquipamentoImpl implements IEquipamento{

	private Session sessao;
	
	public EquipamentoImpl(Session sessao) {
		this.sessao = sessao;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Equipamento> listar() {
		List<Equipamento> listaEquipamento = new ArrayList<Equipamento>();
		listaEquipamento = this.sessao.createCriteria(Equipamento.class).addOrder(Order.asc("nome")).list();
		return listaEquipamento;
	}

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

}
