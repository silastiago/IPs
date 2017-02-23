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
	private Equipamento equipamentoSelecionado;
	private List<Equipamento> listaEquipamento;

	public EquipamentoBean() {
		listaEquipamento  = new ArrayList<Equipamento>();
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