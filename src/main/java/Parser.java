import java.util.LinkedList;

public class Parser {

    /**
     * Parses the input and executes the command.
     * @param input String input provided by user
     * @param tasks TaskList of Tasks that this user currently has
     * @param fileHandler FileHandler of the save file for this user
     * @return response of Duke after parsing and executing the command.
     * @throws DukeException when command or arguments of input are invalid
     */
    public static LinkedList<String> parseAndExecute(String input, TaskList tasks, FileHandler fileHandler) 
            throws DukeException {
        //Split input incase it has a command and argument
        String[] inputArray = input.split(" ", 2);

        String[] cmdArgs = new String[0];
        Task newTask;

        LinkedList<String> result = new LinkedList<>();

        switch (inputArray[0]) { 
        case "list":
            Task currTask;

            //Append to result
            for (int i = 1; i <= tasks.getTaskListLength(); i++) {
                currTask = tasks.getTask(i - 1);
                result.add(i + ". " + currTask);
            }

            break;
        case "done":
            if (inputArray.length < 2) {
                throw new DukeException("OOPS!!! You need to specify the number of the task "
                        + "you want to mark as done!");
            }
            int taskNum = Integer.parseInt(inputArray[1]);
            int taskIndex = taskNum - 1;

            Task modifiedTask = tasks.getTask(taskIndex);
            modifiedTask.markAsDone();

            //Saving
            fileHandler.saveTasks(tasks);

            //Append to result
            result.add("Nice! I've marked this task as done:");
            result.add("  " + modifiedTask.toString());

            break;
        case "todo":
            if (inputArray.length < 2) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            newTask = new Todo(inputArray[1]);
            tasks.addTask(newTask);

            //Saving
            fileHandler.saveTasks(tasks);

            //Append to result
            result.add("Got it. I've added this task:");
            result.add("  " + newTask.toString());
            result.add("Now you have " + tasks.getTaskListLength() + " task(s) in the list.");

            break;
        case "event":
            if (inputArray.length < 2) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            }

            cmdArgs = inputArray[1].split(" /at ", 2);
            newTask = new Event(cmdArgs[0], cmdArgs[1]);

            tasks.addTask(newTask);

            //Saving
            fileHandler.saveTasks(tasks);

            //Append to result
            result.add("Got it. I've added this task:");
            result.add("  " + newTask.toString());
            result.add("Now you have " + tasks.getTaskListLength() + " task(s) in the list.");

            break;
        case "deadline":
            if (inputArray.length < 2) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            cmdArgs = inputArray[1].split(" /by ", 2);
            newTask = new Deadline(cmdArgs[0], cmdArgs[1]);

            tasks.addTask(newTask);

            //Saving
            fileHandler.saveTasks(tasks);

            //Append to result
            result.add("Got it. I've added this task:");
            result.add("  " + newTask.toString());
            result.add("Now you have " + tasks.getTaskListLength() + " task(s) in the list.");

            break;
        case "delete":
            if (inputArray.length < 2) {
                throw new DukeException("OOPS!!! You need to specify the number of the task " 
                        + "you want to delete!");
            }
            int taskIndexToRemove = Integer.parseInt(inputArray[1]) - 1;
            Task removedTask = tasks.removeTask(taskIndexToRemove);

            //Saving
            fileHandler.saveTasks(tasks);

            //Append to result
            result.add("Noted. I've removed this task:");
            result.add("  " + removedTask.toString());
            result.add("Now you have " + tasks.getTaskListLength() + " task(s) in the list.");

            break;
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        
        return result;
    }
}