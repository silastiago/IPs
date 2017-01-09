package pcrn.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pcrn.model.Grupo;
import pcrn.repository.Grupos;


@FacesConverter(forClass=Grupo.class)
public class GrupoConversor implements Converter{

	private Grupos grupos;

	
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Grupo retorno = null;			
			
		if (retorno == null) {
			retorno = grupos.porCodigo(new Integer(value));	

		}

		return retorno;
	}

	
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			return ((Grupo) value).getCodigo().toString();
			
		}
		
		return null;
	}

}