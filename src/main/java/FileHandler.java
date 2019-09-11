import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;


public class FileHandler {
    
    private String saveLocation;
    private String fileName;
    
    public FileHandler(String saveLocation, String fileName) {
        this.saveLocation = saveLocation;
        this.fileName = fileName;
    }

    /**
     * Writes Tasks from TaskList to save file.
     * @param tasks TaskList of Tasks
     * @throws DukeException when there is an IOException
     */
    public void saveTasks(TaskList tasks) throws DukeException {
        
        String strToAdd;
        String filePath = saveLocation + "/" + fileName;
        //Future: Check if filePath is valid?

        try {
            //Check if dir exists, creates if dir does not exist.
            File dataDir = new File(saveLocation);
            if (!dataDir.exists()) { 
                dataDir.mkdirs(); 
            }
            //Future: Check if parent dir exists?

            //Check if file exists, creates if file does not exist.
            File saveFile =  new File(saveLocation + "/" + fileName);
            if (!saveFile.exists()) { 
                saveFile.createNewFile(); 
            }
            
            //Initialise FileWriter
            FileWriter fw = new FileWriter(filePath);
            Task task;
            for (int i = 0; i < tasks.getTaskListLength(); i++) { 
                task = tasks.getTask(i);
                strToAdd = task.toSaveString();
                fw.write(strToAdd);
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Save Failed: " + e.getMessage());
        }
    }

    /**
     * Generates TaskList based on save file.
     * @return TaskList of Tasks based on save file
     * @throws DukeException If save file cannot be found
     */
    public TaskList loadData() throws DukeException {
        TaskList tasks = new TaskList(new LinkedList<Task>());
        
        File saveFile =  new File(saveLocation + "/" + fileName);
        try {
            //Read line by line
            Scanner s = new Scanner(saveFile); // create a Scanner using the File as the source
            String taskString;
            String[] taskArr;
            Task newTask;
            
            while (s.hasNext()) {
                taskString = (s.nextLine());
                
                //Splits string based on "@@@" delimiter
                taskArr = taskString.split("@@@");
                
                //Instantiates class using appropriate constructor
                switch (taskArr[0]) {
                case "TODO":
                    newTask = new Todo(taskArr[2]);
                    break;
                case "DEADLINE":
                    newTask = new Deadline(taskArr[2], taskArr[3]);
                    break; 
                case "EVENT":
                    newTask = new Event(taskArr[2], taskArr[3]);
                    break;
                default:
                    newTask = new Todo("Error Task");
                    break;
                }
                
                //Marks task as done if saveFile indicates that it is done
                if (taskArr[1].equals("true")) {
                    newTask.markAsDone();
                }
                //Append to tasks
                tasks.addTask(newTask);
            }
            
            return tasks;
        } catch (FileNotFoundException e) {
            //If file does not exist, throw DukeException File not Found
            throw new DukeException("Save File does not exist.");
        }
    }
}
