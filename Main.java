// this is to use the databasemanager and interact with the database
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        dbManager.createTable();

        Task task1 = new Task(1, "Buy groceries");
        Task task2 = new Task(2, "Clean the house");

        dbManager.addTask(task1);
        dbManager.addTask(task2);

        List<Task> tasks = dbManager.getAllTasks();

        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}