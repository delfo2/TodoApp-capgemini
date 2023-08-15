package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Tasks;
import util.ConnectionFactory;

public class taskController {
	public void save (Tasks task) {
		String sql = "INSERT INTO tasks ("
				+ "idProject,"
				+ "nome,"
				+ "descricao,"
				+ "taCompleta,"
				+ "notas,"
				+ "expiraEm,"
				+ "criadoEm,"
				+ "atualizadoEm) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = ConnectionFactory.connect();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, task.getIdProject());
			statement.setString(2, task.getName());
			statement.setString(3, task.getDescription());
			statement.setBoolean(4, task.isComplete());
			statement.setString(5, task.getNotes());
			statement.setDate(6, new Date(task.getDeadLine().getTime()));
			statement.setDate(7, new Date(task.getCreatedAt().getTime()));
			statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
			statement.execute();
			
		} catch (Exception e) {
	        throw new RuntimeException("It wasnt possible add into database." + e.getMessage());
	    } finally {
			ConnectionFactory.disconnect(connection, statement);
		}
		
	}
	public void update (Tasks task) {
		String sql = "UPDATE tasks SET "
				+ "idProject = ?,"
				+ "nome = ?,"
				+ "descricao = ?,"
				+ "taCompleta = ?,"
				+ "notas = ?,"
				+ "expiraEm = ?,"
				+ "criadoEm = ?,"
				+ "atualizadoEm = ? " 
				+ "where id = ?;";
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = ConnectionFactory.connect();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, task.getIdProject());
			statement.setString(2, task.getName());
			statement.setString(3, task.getDescription());
			statement.setBoolean(4, task.isComplete());
			statement.setString(5, task.getNotes());
			statement.setDate(6, new Date(task.getDeadLine().getTime()));
			statement.setDate(7, new Date(task.getCreatedAt().getTime()));
			statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
			statement.setInt(9, task.getId());
			statement.execute();
		} catch (Exception e) {
			throw new RuntimeException("It wasnt possible update database." + e.getMessage());
		} finally {
			ConnectionFactory.disconnect(connection, statement);
		}
		
	}
	public void removeById (int id) {
		String sql = "DELETE FROM tasks WHERE id = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = ConnectionFactory.connect();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.execute();
		} catch (Exception e) {
			throw new RuntimeException("It wasnt possible remove from database." + e.getMessage());
		} finally {
			ConnectionFactory.disconnect(connection, statement);
		}
	}
	public List<Tasks> getAll (int idProject) {
		String sql = "SELECT * FROM tasks WHERE idProject = ?";
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		List<Tasks> tasks = new ArrayList<Tasks>();
		
		try {
			connection = ConnectionFactory.connect();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, idProject);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Tasks task = new Tasks(
					resultSet.getInt("id"),
					resultSet.getInt("idProject"),
					resultSet.getString("nome"),
					resultSet.getString("descricao"),
					resultSet.getBoolean("taCompleta"),
					resultSet.getString("notas"),
					resultSet.getDate("expiraEm"),
					resultSet.getDate("criadoEm"),
					resultSet.getDate("atualizadoEm")
				);
				tasks.add(task);
			}
			
		} catch (Exception e) {
			throw new RuntimeException("It wasnt possible get database." + e.getMessage());
		} finally {
			ConnectionFactory.disconnect(connection, statement, resultSet);
		}
		
		return tasks;
	}
}
