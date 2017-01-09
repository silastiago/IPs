package pcrn.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import pcrn.model.Pessoa;
import pcrn.repository.Pessoas;

@FacesConverter(forClass=Pessoa.class)
public class PessoaConversor implements Converter{

	private Pessoas pessoas;

	
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pessoa retorno = null;
			
		if (retorno == null) {
			retorno = pessoas.porCodigo(new Integer(value));
		}

		return retorno;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			return ((Pessoa) value).getCodigo().toString();
			
		}
		
		return null;
	}

}