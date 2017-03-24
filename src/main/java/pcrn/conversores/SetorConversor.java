package pcrn.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pcrn.model.Setor;
import pcrn.repository.Setores;
import pcrn.util.cdi.CDIServiceLocator;


@FacesConverter(forClass = Setor.class)
public class SetorConversor implements Converter{

	private Setores setores;


	public SetorConversor() {
		setores = CDIServiceLocator.getBean(Setores.class);
	}

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Setor retorno = null;


		if (value != null) {
			retorno = setores.porCodigo(new Integer(value));

		}

		return retorno;
	}


	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			Setor setor = (Setor) value;
			
			return setor.getCodigo() == null ? null : setor.getCodigo().toString();

		}

		return null;
	}

}
