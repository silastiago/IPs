package pcrn.conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import pcrn.model.Grupo;
import pcrn.repository.Grupos;


@FacesConverter(forClass=Grupo.class)
public class GrupoConversor implements Converter{

	private Grupos grupos;


	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Grupo retorno = null;			

		if (value != null && !value.equals("")) {
			retorno = grupos.porCodigo(new Integer(value));

			if (retorno == null) {

				String descricaoErro = "Grupo não existe";
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, descricaoErro, descricaoErro);

				throw new ConverterException(message);
			}

		}

		return retorno;
	}


	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {

			Integer codigo = ((Grupo) value).getCodigo();
			return codigo == null ? "" : codigo.toString();

		}

		return null;
	}

}