package pcrn.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** Classe FiltroSegurancaCentral que implementa a seguran�a de acesso na Central de Servi�os.
*
* @author silas
*
*/


@WebFilter(urlPatterns="/site/")
//@WebFilter(urlPatterns="/site/central/")
public class FiltroSeguranca implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/** Metodo que implementa a politica de acesso na Central de Servi�os.
	 *	@param ServletRequest, ServletResponse, FilterChain, requisi��o http, resposta a requisi��o
	 *  e filtro s�o respectivamente os parametros a ser utilizados.   
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			 
			//Atributo de requisi��o http 
	       HttpServletRequest req = (HttpServletRequest) request;
	       //Atributo de resposta de requisi��o http
	       HttpServletResponse res = (HttpServletResponse) response;
	       //Atributo de sessao de requisi��o http
	       HttpSession ses = req.getSession(false);
	       //String da requisi��o http
	       String reqURI = req.getRequestURI();
	       //Comparando se os atributos de sess�o est�o nulos, se n�o forem o sistema libera o acesso para a pagina solicitada
			//sen�o redireciona o usuario para pagina de login do sistema.
	       if (  (ses != null && ses.getAttribute("usuario") != null && ses.getAttribute("senha") != null)
	                                  || reqURI.indexOf("/site/") >= 0 && reqURI.contains("javax.faces.resource") )
	              chain.doFilter(request, response);
	       else   
	       	//Usuario n�o tem sessao aberta ainda, portanto o servidor redireciona ele para a pagina de login.
	              res.sendRedirect(req.getContextPath() + "/Login.xhtml");  
	 }
	catch(Throwable t) {
	    System.out.println( t.getMessage());
	}
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}