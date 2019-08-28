public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;
    
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = TaskType.UNDECIDED;
    }

    //Getters
    public String getStatusIcon() {
        //return tick or X symbols
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getDescription() {
        return description;
    }
    
    public Boolean getIsDone() { 
        return isDone; 
    }

    public TaskType getTaskType() { 
        return taskType; 
    }
    
    //Setters
    public boolean markAsDone() {
        this.isDone = true;
        return true;
    }

    public String toSaveString() {
        //Concat item and two Separators
        //Future: Google best separator to use? or how to save files with delimiters??
        //https://stackoverflow.com/questions/6319551/whats-the-best-separator-delimiter-characters-for-a-plaintext-db-file
        //Future: Throw DukeException in input if delimiter is found in input. To disallow use of delimiter in input
        
        String saveString;
        //Get the three items
        saveString = this.getTaskType().name();
        saveString = saveString + "@@@";
        saveString = saveString + this.getIsDone().toString();
        saveString = saveString + "@@@";
        saveString = saveString + this.getDescription();
        
        return saveString;
    }
    
    @Override
    public String toString() {
        
        String str = "["
                + this.getStatusIcon()
                + "] "
                + this.getDescription();
        return str;
    }
}