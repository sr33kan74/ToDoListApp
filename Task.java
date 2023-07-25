// for representing a task
public class Task {
    private int id;
    private String description;

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public void setDescription(String description) {
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
        return "task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}