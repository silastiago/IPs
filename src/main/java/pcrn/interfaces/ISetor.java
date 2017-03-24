package pcrn.interfaces;

import java.util.List;

import pcrn.model.Setor;


public interface ISetor {
	
	public List<Setor> listar();
	public List<Setor> listar(int codigo_unidade);
	public Setor porCodigo(int codigo);
	public void salvar(Setor setor);
	public void remover(Setor setor);
}