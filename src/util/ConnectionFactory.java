package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionFactory {
	public static final String driver = "com.mysql.cj.jdbc.Driver";
	public static final String jdbcUrl = "jdbc:mysql://localhost:3306/todoapp";
	public static final String user = "root";
	public static final String password = "12345";
    
	public static Connection connect() {
    	try {
	        Class.forName(driver);
	        Connection c = DriverManager.getConnection(jdbcUrl, user, password);
	        return c;
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Driver não encontrado.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new Error("Erro ao conectar ao banco de dados.");
	    }
    }
	public static void disconnect(Connection connect) {
		try {
			if(connect != null) {
				connect.close();
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao fechar a conexão com o banco de dados.");
		}
	}
	public static void disconnect(Connection connect, Statement statement) {
		try {
			if(connect != null) {
				connect.close();
			}
			if(statement != null) {
				statement.close();
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao fechar a conexão com o banco de dados.");
		}
	}
	public static void disconnect(
			Connection connect,
			Statement statement,
			ResultSet resultSet) {
		
		try {
			if(connect != null) {
				connect.close();
			}
			if(statement != null) {
				statement.close();
			}
			if(resultSet != null) {
				resultSet.close();
			}
		} catch (Exception e) {
			throw new RuntimeException("Erro ao fechar a conexão com o banco de dados.");
		}
	}
}
