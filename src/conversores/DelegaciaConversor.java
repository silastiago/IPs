package conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import model.Delegacia;
import repository.IDelegacia;
import util.Repositorios;


@FacesConverter(forClass=Delegacia.class)
public class DelegaciaConversor implements Converter{

	private Repositorios repositorios = new Repositorios();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Delegacia retorno = null;
		IDelegacia delegacias = repositorios.getDelegacias();
		
		if (value != null && !value.equals("")) {
			retorno = delegacias.porCodigo(new Integer(value));
			
		if (retorno == null) {
			
			String descricaoErro = "Delegacia não existe";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, descricaoErro, descricaoErro);
			
			throw new ConverterException(message);
		}

		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			
			Integer codigo = ((Delegacia) value).getCodigo();
			return codigo == null ? "" : codigo.toString();
			
		}
		
		return null;
	}

}
