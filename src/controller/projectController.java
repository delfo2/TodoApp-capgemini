package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Project;
import util.ConnectionFactory;

public class projectController {
	public void save (Project Project) {
		String sql = "INSERT INTO project ("
				+ "nome,"
				+ "descricao,"
				+ "criadoEm,"
				+ "atualizadoEm) "
				+ "VALUES (?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = ConnectionFactory.connect();
			statement = connection.prepareStatement(sql);
			statement.setString(1, Project.getName());
			statement.setString(2, Project.getDescription());
			statement.setDate(3, new Date(Project.getCreatedAt().getTime()));
			statement.setDate(4, new Date(Project.getUpdatedAt().getTime()));
			statement.execute();
			
		} catch (Exception e) {
			throw new RuntimeException("Wasnt possible save the project. " + e.getMessage());
		} finally {
			ConnectionFactory.disconnect(connection, statement);
		}
	}
	public void update (Project Project) {
		String sql = "UPDATE project SET "
				+ "nome = ?,"
				+ "descricao = ?,"
				+ "criadoEm = ?,"
				+ "atualizadoEm = ? "
				+ "WHERE id = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = ConnectionFactory.connect();
			statement = connection.prepareStatement(sql);
			statement.setString(1, Project.getName());
			statement.setString(2, Project.getDescription());
			statement.setDate(3, new Date(Project.getCreatedAt().getTime()));
			statement.setDate(4, new Date(Project.getUpdatedAt().getTime()));
			statement.setInt(5, Project.getId());
			statement.execute();
			
		} catch (Exception e) {
			throw new RuntimeException("Wasnt possible update the project. " + e.getMessage());
		} finally {
			ConnectionFactory.disconnect(connection, statement);
		}
		
	}
	public void delete (int id) {
		String sql = "DELETE FROM project WHERE id = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = ConnectionFactory.connect();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.execute();
			
		} catch (Exception e) {
			throw new RuntimeException("Wasnt possible delete the project. " + e.getMessage());
		} finally {
			ConnectionFactory.disconnect(connection, statement);
		}
	}
	public List<Project> getAll () {
		String sql = "SELECT * FROM project";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		List<Project> Projects = new ArrayList<Project>();
		
		try {
			connection = ConnectionFactory.connect();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Project Project = new Project(
					resultSet.getInt("id"),
					resultSet.getString("nome"),
					resultSet.getString("descricao"),
					resultSet.getDate("criadoEm"),
					resultSet.getDate("atualizadoEm")
				);
				Projects.add(Project);
			}
		} catch (Exception e) {
			throw new RuntimeException("Wasnt possible get all projects from database. " + e.getMessage());
		} finally {
			ConnectionFactory.disconnect(connection, statement, resultSet);
		}
		
		return Projects;
	}
}
