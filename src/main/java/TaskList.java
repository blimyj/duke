import java.util.LinkedList;

public class TaskList {
    private LinkedList<Task> tasks;
    
    public TaskList(LinkedList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getTaskListLength() {
        return tasks.size();
    }
    
    public Task getTask(int index) {
        return tasks.get(index);
    }
    
    public void addTask(Task task) {
        tasks.add(task);
    }
    
    public Task removeTask(int index) {
        Task removedTask = tasks.remove(index);
        return removedTask;
    }
}