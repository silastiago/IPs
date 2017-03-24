package pcrn.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pcrn.model.UnidadePolicial;
import pcrn.repository.UnidadesPoliciais;
import pcrn.util.cdi.CDIServiceLocator;


@FacesConverter(forClass = UnidadePolicial.class)
public class UnidadePolicialConversor implements Converter{

	private UnidadesPoliciais unidadesPoliciais;


	public UnidadePolicialConversor() {
		unidadesPoliciais = CDIServiceLocator.getBean(UnidadesPoliciais.class);
	}

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		UnidadePolicial retorno = null;


		if (value != null) {
			retorno = unidadesPoliciais.porCodigo(new Integer(value));

		}

		return retorno;
	}


	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			UnidadePolicial unidadePolicial = (UnidadePolicial) value;
			
			return unidadePolicial.getCodigo() == null ? null : unidadePolicial .getCodigo().toString();

		}

		return null;
	}

}
