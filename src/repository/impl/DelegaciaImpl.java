package repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import model.Delegacia;
import repository.IDelegacia;

public class DelegaciaImpl implements IDelegacia{

	private Session sessao;
	
	public DelegaciaImpl(Session sessao) {
		this.sessao = sessao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Delegacia> listar() {
		List<Delegacia> listaDelegacia = new ArrayList<Delegacia>();
		listaDelegacia = this.sessao.createCriteria(Delegacia.class).addOrder(Order.asc("nome")).list();
		return listaDelegacia;
	}

	@Override
	public Delegacia porCodigo(Integer codigo) {
		return (Delegacia) sessao.get(Delegacia.class, codigo);
	}

	@Override
	public void salvar(Delegacia delegacia) {
		sessao.merge(delegacia);		
	}

	@Override
	public void remover(Delegacia delegacia) {
		sessao.delete(delegacia);
	}
}