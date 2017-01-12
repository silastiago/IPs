package pcrn.conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import pcrn.model.Equipamento;
import pcrn.repository.Equipamentos;
import pcrn.util.cdi.CDIServiceLocator;

@FacesConverter("equipamentoConverter")
public class EquipamentoConversor implements Converter{

	private Equipamentos equipamentos;

	public EquipamentoConversor() {
		equipamentos = CDIServiceLocator.getBean(Equipamentos.class);
	}
	
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Equipamento retorno = null;
			
		if (value != null && !value.equals("")) {
			retorno = equipamentos.porCodigo(new Integer(value));

			if (retorno == null) {

				String descricaoErro = "Delegacia não existe";
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, descricaoErro, descricaoErro);

				throw new ConverterException(message);
			}

		}

		return retorno;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {

			Integer codigo = ((Equipamento) value).getCodigo();
			return codigo == null ? "" : codigo.toString();

		}

		return null;
	}
}