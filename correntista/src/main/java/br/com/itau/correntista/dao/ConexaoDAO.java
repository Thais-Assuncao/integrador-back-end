package br.com.itau.correntista.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {
	private static Connection conn = null;
	
	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/icarros"; //Nome da base de dados
		String usuario  = "root"; //nome do usuário do MySQL
		String senha = "mysql"; //senha do MySQL
		String driver = "com.mysql.cj.jdbc.Driver";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, usuario, senha);
			return conn;

		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("Erro -->" + e.getMessage());
		}
		return conn;
	}
	
	public static void closeConnection() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.err.println("Erro -->" + e.getMessage());
			}
		}
	}

}
