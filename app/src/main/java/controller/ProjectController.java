/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;
/**
 *
 * @author Iara
 */
public class ProjectController {
    public void save (Project project){
       String sql = "INSERT INTO projects(name, description, createdAt, updatedAt) VALUES (?,?,?,?)";
        
        Connection conn = null;
        PreparedStatement statement =  null;
        
        try {
            // iniciando a conexão com o banco de dados
            conn = ConnectionFactory.getConnection();
            
            // preparando o a query
            statement = conn.prepareStatement(sql);
            
            // setando os statements
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new java.sql.Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new java.sql.Date(project.getUpdatedAt().getTime()));
            
            // excecutando
            statement.execute();
            
        } catch (SQLException ex) {
           throw new RuntimeException("Erro ao salvar a tarefa" + ex.toString());
        } finally{
            ConnectionFactory.closeConnection(conn,statement);
        }
 
            
    }
    
    public void update (Project project){
       String sql = "UPDATE projects SET name = ?, description = ?, createdAt = ?, updatedAt = ? WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, (java.sql.Date) new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, (java.sql.Date) new Date(project.getUpdatedAt().getTime()));
            statement.setInt(5,project.getId());
            statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar a tarefa", ex);
        } finally{
            ConnectionFactory.closeConnection(conn,statement);
        }
    }
    
    
    public void delete(int idProject){
        String sql = "DELETE FROM projects WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idProject);
             statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao deletar o projeto");
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
    
    }
    
    public List<Project> getAll(){
        String sql = "SELECT * FROM projects";
        
        List<Project> projects = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            
            resultSet = statement.executeQuery();
            
            // Enquanto existir dados no banco de dados:           
            while(resultSet.next()){ 
                Project project = new Project();
                
                // setar informações do proketo com base nas informações do resultSet
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("Name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatedAt(resultSet.getDate("CreatedAt"));
                project.setUpdatedAt(resultSet.getDate("UpdatedAt")); 
                projects.add(project);

            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao buscar os projetos");
        } finally{
            ConnectionFactory.closeConnection(conn,statement,resultSet);
        }
        
     return projects;
    }
}
