# ToDoListApp
 Simple Java project that uses a database. For this example, we'll use SQLite as the database since it's a self-contained, serverless database that doesn't require any additional setup.  Here, we'll create a basic "ToDoList" application where you can add and retrieve tasks from the database.



Here, we'll create a basic "ToDoList" application where you can add and retrieve tasks from the database.

Step 1: Set up the Java project
Create a new directory (folder) on your computer where you want to store your Java project. Let's call it "ToDoListApp."

Step 2: Download SQLite JDBC driver
Download the SQLite JDBC driver (JAR file) from the following link: http://www.java2s.com/Code/JarDownload/sqlite/sqlite-jdbc-3.7.2.jar.zip

Step 3: Add the SQLite JDBC driver to the project
Copy the downloaded "sqlite-jdbc-x.x.x.jar" file into the "ToDoListApp" folder.

Step 4: Write the Java code
Inside the "ToDoListApp" folder, create a new file called "Task.java" with the following code to represent a task:

java
public class Task {
    private int id;
    private String description;

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}


Next, create a new file called "DatabaseManager.java" to handle the database operations:

java
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:todo.db";

    public DatabaseManager() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS tasks (id INTEGER PRIMARY KEY AUTOINCREMENT, description TEXT)";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTask(Task task) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO tasks (description) VALUES (?)")) {
            pstmt.setString(1, task.getDescription());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM tasks")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                tasks.add(new Task(id, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}


Step 5: Use the database in the main application
Create a new file called "Main.java" to use the "DatabaseManager" and interact with the database:

java
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


Step 6: Compile and run the application
Open a terminal (command prompt), navigate to the "ToDoListApp" folder, and compile all the Java files, including the SQLite JDBC driver:


javac -cp sqlite-jdbc-x.x.x.jar *.java


Run the application:


java -cp .:sqlite-jdbc-x.x.x.jar Main


You should see the tasks "Buy groceries" and "Clean the house" printed on the screen. The tasks are now stored in the "todo.db" SQLite database.

Remember to replace "x.x.x" with the version number of the downloaded SQLite JDBC driver.

Congratulations! You've created a simple Java project with a database using SQLite. This example was minimal, but you can extend it to add more features, such as updating tasks, marking them as complete, or even creating a simple GUI for a more user-friendly experience.
