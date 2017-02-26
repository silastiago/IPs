package pcrn.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import pcrn.model.Equipamento;
import pcrn.service.EquipamentoService;
import pcrn.util.FacesUtil;
import pcrn.util.report.ExecutorRelatorio;

@Named
@ViewScoped
public class EquipamentoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EquipamentoService equipamentoService;
	
	@Inject
	private HttpServletResponse response;
	
	@Inject
	private EntityManager manager;
	
	@Inject
	private FacesContext facesContext;
	
	private Equipamento equipamento = new Equipamento();
	private Equipamento ipLivre;
	private Equipamento equipamentoSelecionado;
	private List<Equipamento> listaEquipamento;
	private List<Equipamento> listaIpsLivres;
	private List<Integer> listaIPsLIvres;
	private int renderizarTabela = 0;
	private boolean renderizarLista = false;
	

	public EquipamentoBean() {
		listaEquipamento  = new ArrayList<Equipamento>();
		listaIpsLivres = new ArrayList<Equipamento>();
		ipLivre = new Equipamento();
	}
	
	
	public void emitir() {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("codigo_delegacia", equipamento.getDelegacia().getCodigo());
		
		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/ips.jasper",
				this.response, parametros, "Relatorio_de_Ip.pdf");
		
		Session session = manager.unwrap(Session.class);
		session.doWork(executor);
		
		if (executor.isRelatorioGerado()) {
			facesContext.responseComplete();
		} else {
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}
	}
	
	public List<Equipamento> listarIPsLivres(){
		renderizarTabela = 2;
		renderizarLista = false;
		
		for (int i = 1; i < 256; i++) {
			if (equipamentoService.listarIPsLIvres(i, equipamento.getDelegacia().getCodigo()).size() == 1) {
				
			}else{
				System.out.println("Ips Livres: " + i);
				ipLivre = new Equipamento();
				ipLivre.setQuartoOctal(i);
				listaIpsLivres.add(ipLivre);
			}
		}		
		
		return listaIpsLivres;
	}
	

	public void cadastrar(){
		List<Equipamento> listaEquipamentos = equipamentoService.validacaoIPCadastro(equipamento);
		if (listaEquipamentos.size() > 0) {
			for (int i = 0; i < listaEquipamentos.size(); i++) {
				FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Equipamento com o ip já cadastrado com o nome da maquina: " + listaEquipamentos.get(i).getNome() + " por favor utilizar outro ip"));
			}
		}else{

			//Esta linha salva a entidade grupo.
			equipamento.setQuartoOctal(FacesUtil.pegarQuartoOctal(equipamento.getIp()));
			equipamentoService.salvar(equipamento);
			

			FacesContext fc = FacesContext.getCurrentInstance();

			try {
				fc.getExternalContext().redirect("../Consulta/Equipamento.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void editar(){
		List<Equipamento> listaEquipamentos = equipamentoService.validacaoIPEdicao(equipamento);
		if (listaEquipamentos.size() > 0) {
			for (int i = 0; i < listaEquipamentos.size(); i++) {
				FacesContext.getCurrentInstance().addMessage("message" , new FacesMessage(FacesMessage.SEVERITY_ERROR, "","Equipamento com o ip já cadastrado com o nome da maquina: " + listaEquipamentos.get(i).getNome() + " por favor utilizar outro ip"));
			}
		}else{

			//Esta linha salva a entidade grupo.
			equipamento.setQuartoOctal(FacesUtil.pegarQuartoOctal(equipamento.getIp()));
			equipamentoService.salvar(equipamento);

			FacesContext fc = FacesContext.getCurrentInstance();

			try {
				fc.getExternalContext().redirect("../Consulta/Equipamento.xhtml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void excluir(){

		//Esta linha salva a entidade grupo.
		equipamentoService.remover(equipamentoSelecionado);		

		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("../Consulta/Equipamento.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Equipamento> listarDelegacias(){
		renderizarTabela = 2;
		renderizarLista = true;
		System.out.println(renderizarTabela);
		listaEquipamento = equipamentoService.listar(equipamento.getDelegacia().getCodigo());
		return listaEquipamento;
	}


	public List<Equipamento> listar(){
		if (this.equipamento == null) {
			listaEquipamento = null;
		}else {
			//Esta linha lista os grupos e joga em uma lista de grupos.
			listaEquipamento = equipamentoService.listar(this.equipamento.getDelegacia().getCodigo());
		}

		//Retorna a lista de grupos
		return listaEquipamento;
	}

	public void novo(){
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("../Novo/EquipamentoNovo.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void edicao(){
		FacesContext fc = FacesContext.getCurrentInstance();

		try {
			fc.getExternalContext().redirect("../Edicao/EquipamentoEdicao.xhtml?codigo="+equipamentoSelecionado.getCodigo());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public Equipamento getIpLivre() {
		return ipLivre;
	}


	public void setIpLivre(Equipamento ipLivre) {
		this.ipLivre = ipLivre;
	}


	public List<Equipamento> getListaIpsLivres() {
		return listaIpsLivres;
	}


	public void setListaIpsLivres(List<Equipamento> listaIpsLivres) {
		this.listaIpsLivres = listaIpsLivres;
	}


	public int getRenderizarTabela() {
		return renderizarTabela;
	}


	public void setRenderizarTabela(int renderizarTabela) {
		this.renderizarTabela = renderizarTabela;
	}


	public boolean isRenderizarLista() {
		return renderizarLista;
	}


	public void setRendericarLista(boolean renderizarLista) {
		this.renderizarLista = renderizarLista;
	}


	public List<Integer> getListaIPsLIvres() {
		return listaIPsLIvres;
	}


	public void setListaIPsLIvres(List<Integer> listaIPsLIvres) {
		this.listaIPsLIvres = listaIPsLIvres;
	}


	public Equipamento getEquipamentoSelecionado() {
		return equipamentoSelecionado;
	}


	public void setEquipamentoSelecionado(Equipamento equipamentoSelecionado) {
		this.equipamentoSelecionado = equipamentoSelecionado;
	}


	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public List<Equipamento> getListaEquipamento() {
		return listaEquipamento;
	}

	public void setListaEquipamento(List<Equipamento> listaEquipamento) {
		this.listaEquipamento = listaEquipamento;
	}
}