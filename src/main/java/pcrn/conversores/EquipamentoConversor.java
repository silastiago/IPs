package pcrn.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pcrn.model.Equipamento;
import pcrn.repository.Equipamentos;
import pcrn.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Equipamento.class)
public class EquipamentoConversor implements Converter{

	private Equipamentos equipamentos;

	public EquipamentoConversor() {
		equipamentos = CDIServiceLocator.getBean(Equipamentos.class);
	}
	
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Equipamento retorno = null;
			
		if (value != null) {
			retorno = equipamentos.porCodigo(new Integer(value));

		}

		return retorno;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			Equipamento equipamento = (Equipamento) value;
			return equipamento.getCodigo() == null ? null : equipamento.getCodigo().toString();

		}

		return null;
	}
}