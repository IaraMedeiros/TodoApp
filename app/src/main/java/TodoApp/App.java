/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package TodoApp;

import controller.ProjectController;
import controller.TaskController;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import model.Project;
import model.Task;
import util.ConnectionFactory;

public class App {
 
    public static void main(String[] args) {
        
        
////        
//     ProjectController projectController = new ProjectController();
//     Project project = new Project();
//     project.setName("Nome do projeto");
//      project.setDescription("description");
//      projectController.save(project);
//       
 
//    project.setId(1);
//    project.setName("atualizado");
//    project.setDescription("description");   
//    projectController.update(project);

//    projectController.delete(1);
//
//    List<Project> projects = projectController.getAll();
//    System.out.println("projects total: " + projects.size());

        
//        task.setName("NewTask");
//        task.setDescription("Task description test ");
//        task.setStatus(false);
//        task.setDeadline(new Date());
//       
//        
//        taskController.save(task);


//            task.setIdProject(2);
//            task.setName("novaTarefa");
//            task.setDescription("Task description test ");
//            task.setStatus(false);
//            task.setDeadline(new Date());
       
           TaskController taskController = new TaskController();
           Task task = new Task();
           task.setId(1);
           task.setName("Criar interface");
           task.setDescription("descri??o nova");
           task.setStatus(false);
           task.setDeadline(new Date());
           task.setCreatedAt(new Date());
           task.setUpdatedAt(new Date());



           taskController.update(task);
            

     
       
       
       
    }
}
