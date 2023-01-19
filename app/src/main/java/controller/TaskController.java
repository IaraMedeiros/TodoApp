/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author Iara
 */
public class TaskController {
    
    // SALVAR TAREFA
    public void save( Task task){
        // ITENS NA PROGRAMAÇÃO DO BANCO DE DADOS
        String sql = "INSERT INTO tasks(IdProject,name,description,status,notes,deadline,createdAt, updatedAt) VALUES (?,?,?,?,?,?,?,?)";
        
        Connection conn = null;
        PreparedStatement statement = null;
        // CONECTANDO INFORMAÇÕES PARA DENTRO DO STATEMENT
        try {
            // estabelecer a conexão com o banco de dados
            conn = ConnectionFactory.getConnection();
            
            //preparando a query 
            statement = conn.prepareStatement(sql);
            
            // setando os statements   
            statement.setInt(1, task.getIdProject());
            statement.setString(2,task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isStatus());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new java.sql.Date(task.getDeadline().getTime()));
            statement.setDate(7, new java.sql.Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new java.sql.Date(task.getUpdatedAt().getTime()));
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar a tarefa", ex);
        } finally{
            ConnectionFactory.closeConnection(conn,statement);
        }
 
            
    }
    
    
    // Atualizar tarefaeeee
    public void update(Task task){
        String sql = "UPDATE tasks SET idProject = ?, name = ?, description = ?, status = ?, notes = ?, deadline = ?, createdAt = ?, updatedAt = ? WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            // estabelecer a conexão com o banco de dados
            conn = ConnectionFactory.getConnection();
            
            //preparando a query 
            statement = conn.prepareStatement(sql);
            
            //setando os valores
            statement.setInt(1, task.getIdProject());
            statement.setString(2,task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isStatus());
            statement.setString(5,task.getNotes());
                if (task.getDeadline() != null){
                statement.setDate(6, new java.sql.Date(task.getDeadline().getTime()));
                } else{
                    throw new RuntimeException("Erro ");
                }
            statement.setDate(7, new java.sql.Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new java.sql.Date(task.getUpdatedAt().getTime()));
            statement.setInt(9,task.getId());
            
            // excecutando a query
            statement.execute();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar a tarefa", ex);
        } finally{
            ConnectionFactory.closeConnection(conn,statement);
        }
    }
    
    
    // REMOVER TAREFA
    public void removeById(int taskId)throws SQLException{
        String sql = "DELETE FROM tasks WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            // estabelecer a conexão com o banco de dados
            conn = ConnectionFactory.getConnection();
              //preparando a query do statement
            statement = conn.prepareStatement(sql);
            // setando os valores
            statement.setInt(1, taskId);
            // excecutando a tarefa
            statement.execute();
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar a tarefa");
        } finally{
            ConnectionFactory.closeConnection(conn, statement);
        }
    }
    
    // LISTAGEM DOS ITENS DAS TAREFAS
    public List<Task> getAll(int idProject){
        //script sql
        String sql = "SELECT * FROM tasks WHERE idProject = ? ";
        
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null; // ResultSet é um tipo de dado que representa o resultado de um banco de dados
       
        
        // Lista de tarefas que será devolvida quando a chamada do método acontecer
        
        List<Task> tasks = new ArrayList<>(); //Lista é um tipo de array
        
        try {
            // criando conexão com o banco de dados
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            
            // setando op valor que corresponde ao filtro de busca
            statement.setInt(1, idProject);
            
            // valor retornado pela excecução da query  
            resultSet = statement.executeQuery();
            
            // enquantohouver valores a serem percorridos pelo resultset   
            while(resultSet.next()){
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setStatus(resultSet.getBoolean("status"));
                task.setNotes(resultSet.getString("notes"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setUpdatedAt(resultSet.getDate("deadline"));
                // Adicionando a task criada
                  tasks.add(task);
               
            }
            
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir a tarefa"
                    + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(conn, statement,resultSet);
        }
        // Lista de tarefas que foi carregada
        return tasks;
    
    }
}
