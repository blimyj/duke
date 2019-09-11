import java.util.LinkedList;

public class Duke {
    private UI ui;
    private TaskList tasks;
    private FileHandler fileHandler;
    
    public Duke() {
        
        //Initialize FileHandler
        fileHandler = new FileHandler("../data", "save1.txt");
            
        //Try to load old data.
        try {
            tasks = fileHandler.loadData();
        } catch (DukeException e) {
            //Does nothing for now
            tasks = new TaskList(new LinkedList<Task>());
        }
    }
    
    public void main(String[] args) {
        
        //Start UI
        //ui = new UI();
        //ui.start(tasks, fileHandler);
    }
    
    public String getResponse(String input) {
        String output;
        try {
            output = this.formattedPrint(Parser.parseAndExecute(input, tasks, fileHandler));
        } catch (DukeException e) {
            LinkedList<String> msg = new LinkedList<>();
            msg.add(e.getMessage());
            output = this.formattedPrint(msg);
        }
        
        return output;
    }
    
    public String formattedPrint(LinkedList<String> strings) {
        String output = "";
        for (String string: strings) {
            output = output.concat(string);
            output = output.concat("\n");
        }
        return output;
    }
}
