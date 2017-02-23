package pcrn.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


/** Esta � uma Classe utilitaria que implementa alguns metodos que servirao para ser reutilizados em algumas classes.
*   
* @author silas
* @since 17-08-2016
*/

public class FacesUtil {
	
	/** Este metodo captura o atributo da requisicao pelo nome .
	* 	
	* 	@param name, Este name e o nome do atributo que voce vai pegar.
	* 	@return retorna o objeto referente aquele name.
	*/
	public static Object getRequestAttribute(String name) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		return request.getAttribute(name);
	}
	
	public static String md5(String senha) {
			MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       m.update(senha.getBytes(),0, senha.length());
	       String hash = new BigInteger(1,m.digest()).toString(16);
		return hash;
	}
	
	public static void addErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}
	
	public static int pegarQuartoOctal(String ip){
		String[] ipTemp = ip.split("\\.");
		String quartoOctal = ipTemp[3];
		return Integer.parseInt(quartoOctal);
	}
	
	
}