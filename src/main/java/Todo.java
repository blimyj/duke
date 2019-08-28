public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.taskType = TaskType.TODO;
    }

    @Override
    public String toString() {
        String str = "["
                + "T"
                + "]["
                + this.getStatusIcon()
                + "] "
                + this.getDescription();
        return str;
    }
}