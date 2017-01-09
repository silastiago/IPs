package pcrn.repository;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import pcrn.interfaces.IPessoa;
import pcrn.model.Pessoa;

public class Pessoas implements Serializable, IPessoa{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public boolean login(Pessoa pessoa) {
		Query query = manager.createQuery("from Pessoa where login = :codigo and senha = :senha");
		query.setParameter("login", pessoa.getLogin());
		query.setParameter("senha", pessoa.getSenha());
		if (query.getSingleResult() == null) {
			return false;
		}
		return true;
	}

	public void logout() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.removeAttribute("usuario");
		session.removeAttribute("senha");
		session.invalidate();
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> listar() {
		List<Pessoa> listaPessoa = null;
		Query query = manager.createQuery("Select c from Pessoa c");
		listaPessoa = query.getResultList();
		return listaPessoa;
	}

	public Pessoa porCodigo(Integer codigo) {
		return manager.find(Pessoa.class, codigo);
	}

	public void salvar(Pessoa pessoa) {
		manager.merge(pessoa);
	}

	public void remover(Pessoa pessoa) {
		manager.remove(pessoa);
	}

}
