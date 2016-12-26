package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Classe ConnectionFactory � uma fabrica de conex�es, ela servir� para que quando precisarmos utilizar algum metodo que persista no banco,
 *  n�o precisa implementar novamente o metodo da conexao, basta apenas instancias esta classe.
 *
 * @author Silas Tiago
 *
 */

public class ConnectionFactory {
	private String url = "jdbc:postgresql://localhost:5432/ips";
	private String usuario = "postgres";
	private String senha = "postgres";
	String driver = "org.postgresql.Driver";
	Connection conexao;

/** Metodo getConnection retorna uma conexao
 *
 * @return Connection retorna uma conexao
 */
	public Connection getConnection(){
		System.out.println("Iniciando conexao ");
		try{
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
			conexao = DriverManager.getConnection(url, usuario, senha);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return conexao;
		}

	/**	Metodo fecharConexao como o proprio nome j� diz, ele fecha uma conexao ap�s o termino de uma conexao.
	 *
	 */
	public void fecharConexao(){
		try {
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}