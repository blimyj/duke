public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    /**
     * Constructor for Task.
     * @param description Description of Task
     */
    public Task(String description) {
        assert description != "" : "Task description can't be empty!";
        this.description = description;
        this.isDone = false;
        this.taskType = TaskType.UNDECIDED;
    }

    //Getters

    /**
     * Retrieves UTF-8 Encoded Status Icon of the task based on completion of task.
     * @return UTF-8 Encoded Status Icon of tick or cross
     */
    public String getStatusIcon() { 
        
        return (isDone ? "✓" : "✘");
        //return tick or X symbols
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

    /**
     * Marks a task as done.
     * @return true
     */
    public boolean markAsDone() {
        
        this.isDone = true;
        return true;
    }

    /**
     * Converts task to a save string.
     * @return String of Task for save file format
     */
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
        
        assert !this.getDescription().contains("@@@") : "Should not contain \"@@@\"";
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