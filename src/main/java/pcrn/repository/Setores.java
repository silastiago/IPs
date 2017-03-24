package pcrn.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.EntityManager;

import pcrn.interfaces.ISetor;
import pcrn.model.Equipamento;
import pcrn.model.Setor;

public class Setores implements Serializable, ISetor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Setor> listar() {
		List<Setor> listaSetores = null;
		Query query = manager.createQuery("Select s from Setor s order by nome_setor asc");
		listaSetores = query.getResultList();
		return listaSetores;
	}

	public Setor porCodigo(int codigo) {	
		return manager.find(Setor.class, codigo);
	}

	public void salvar(Setor setor) {
		manager.merge(setor);		
	}

	public void remover(Setor setor) {
		manager.remove(manager.getReference(Setor.class, setor.getCodigo()));
	}

	@Override
	public List<Setor> listar(int codigo_unidade) {
		List<Setor> listaSetores = new ArrayList<Setor>();
		Query query = manager.createQuery("from Setor where codigo_unidade = :codigo_unidade");
		query.setParameter("codigo_unidade", codigo_unidade);
		listaSetores = query.getResultList();
		return listaSetores;
	}

}
