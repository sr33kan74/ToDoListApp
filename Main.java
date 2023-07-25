// this is to use the databasemanager and interact with the database

import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main (String args[]) {
        DatabaseManager dbManager = new DatabaseManager();
        dbManager.createTable();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to ToDoListApp!");
        System.out.println("-----------------------");

        int choice;
        do {
            System.out.println("1. Add Task");
            System.out.println("2. Update Task");
            System.out.println("3. Delete Task");
            System.out.println("4. View All Tasks");
            System.out.println("5. Exit");
            System.out.println("\nPlease enter your choice (1-5): ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter the task description: ");
                    String description = scanner.nextLine();
                    Task newTask = new Task(0, description);
                    dbManager.addTask(newTask);
                    System.out.println("Task added successfully");
                    break;
                case 2:
                    System.out.println("Enter the task ID to update: ");
                    int taskIdToUpdate = scanner.nextInt();
                    scanner.nextLine(); // Consume the newLine character

                    Task existingTask = dbManager.getTaskById(taskIdToUpdate);
                    if (existingTask == null) {
                        System.out.println("Task not found with ID: " + taskIdToUpdate);
                    } else {
                        System.out.println("Enter the new task description: ");
                        String newDescription = scanner.nextLine();
                        existingTask.setDescription(newDescription);
                        dbManager.updateTask(existingTask);
                        System.out.println("Task updated successfully!");
                    }
                    break;
                case 3:
                    System.out.println("Enter the task ID to delete: ");
                    int taskIdToDelete = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    Task taskToDelete = dbManager.getTaskById(taskIdToDelete);
                    if (taskToDelete == null) {
                        System.out.println("task not found with ID: " + taskIdToDelete);
                    } else {
                        dbManager.deleteTask(taskToDelete);
                        System.out.println("Task deleted succesfully!");
                    }
                    break;
                case 4:
                    List<Task> tasks = dbManager.getAllTasks();
                    for (Task task : tasks) {
                        System.out.println(task);
                    }
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        while (choice != 5);
        scanner.close();
    }
}