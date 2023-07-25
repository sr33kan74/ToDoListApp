// this is to handle the database operations
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

    public Task getTaskById(int taskId) {
        Task task = null;
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tasks WHERE id = ?")) {
            pstmt.setInt(1, taskId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                task = new Task(id, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    public void updateTask(Task task) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement pstmt = conn.prepareStatement("UPDATE tasks SET description = ? WHERE id = ?")) {
          pstmt.setString(1, task.getDescription());
          pstmt.setInt(2, task.getId());
          pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTask(Task task) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement("UPDATE tasks SET description = ? WHERE id = ?")) {
            pstmt.setString(1, task.getDescription());
            pstmt.setInt(2, task.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}