package conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import model.Pessoa;
import repository.IPessoa;
import util.Repositorios;


@FacesConverter(forClass=Pessoa.class)
public class PessoaConversor implements Converter{

	private Repositorios repositorios = new Repositorios();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Pessoa retorno = null;
		IPessoa pessoas = repositorios.getPessoas();
		
		if (value != null && !value.equals("")) {
			
			retorno = pessoas.porCodigo(new Integer(value));
			
		if (retorno == null) {
			
			String descricaoErro = "Pessoa Não existe";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, descricaoErro, descricaoErro);
			throw new ConverterException(message);
			
		}

		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			
			Integer codigo = ((Pessoa) value).getCodigo();
			return codigo == null ? "" : codigo.toString();
			
		}
		
		return null;
	}

}