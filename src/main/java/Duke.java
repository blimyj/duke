import java.util.LinkedList;

public class Duke {
    private TaskList tasks;
    private FileHandler fileHandler;

    /**
     * Creates a Duke object.
     */
    public Duke() {
        
        //Initialize FileHandler
        fileHandler = new FileHandler("../data", "save1.txt");
            
        //Try to load old data.
        try {
            tasks = fileHandler.loadData();
        } catch (DukeException e) {
            tasks = new TaskList(new LinkedList<Task>());
        }
    }

    /**
     * Gets the response from Duke based on users input.
     * @param input String input from user
     * @return String response from Duke
     */
    public String getResponse(String input) {
        String output;
        try {
            output = UI.formattedPrint(Parser.parseAndExecute(input, tasks, fileHandler));
        } catch (DukeException e) {
            LinkedList<String> msg = new LinkedList<>();
            msg.add(e.getMessage());
            output = UI.formattedPrint(msg);
        }
        
        return output;
    }
}
