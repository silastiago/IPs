package pcrn.conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import pcrn.model.Pessoa;
import pcrn.repository.Pessoas;
import pcrn.util.cdi.CDIServiceLocator;

@FacesConverter("pessoaConverter")
public class PessoaConversor implements Converter{

	private Pessoas pessoas;

	public PessoaConversor() {
		this.pessoas = CDIServiceLocator.getBean(Pessoas.class);
	}
	
	
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pessoa retorno = null;
			
		if (value != null && !value.equals("")) {
			retorno = pessoas.porCodigo(new Integer(value));

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

			Integer codigo = ((Pessoa) value).getCodigo();
			return codigo == null ? "" : codigo.toString();

		}

		return null;
	}

}