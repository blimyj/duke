import java.util.LinkedList;
import java.util.Scanner;

public class UI {

    private String dividerLine = new String("\u2501").repeat(80).concat("\n");
    
    //UI.start()
    //Prints Start Message
    //Awaits input
    //While input is not bye, pass input to parser
    
    //format the output by parser 
    //-> implies parser should always return LinkedList of strings or throws exception
    //print it out 
            
    //throw exception if required
    //return to awaiting input above
    //Else
    //print byebye message
    //exit.
    public void start(TaskList tasks, FileHandler fileHandler) {
        LinkedList<String> startMessage = new LinkedList<String>();
        startMessage.add("Hello from");
        startMessage.add(" ____        _        ");
        startMessage.add("|  _ \\ _   _| | _____ ");
        startMessage.add("| | | | | | | |/ / _ \\");
        startMessage.add("| |_| | |_| |   <  __/");
        startMessage.add("|____/ \\__,_|_|\\_\\___|");
        startMessage.add("Hello! I'm Duke");
        startMessage.add("What can I do for you?");
        
        this.formattedPrint(startMessage);
        
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        
        while (!input.equals("bye")) {
            try {
                this.formattedPrint(Parser.parseAndExecute(input, tasks, fileHandler));
            } catch (DukeException e) {
                LinkedList<String> msg = new LinkedList<>();
                msg.add("☹ " + e.getMessage());
                this.formattedPrint(msg);
            } finally {
                input = sc.nextLine();
            }
        }
        
        // Exit if bye
        LinkedList<String> byeMsg = new LinkedList<>();
        byeMsg.add("Bye. Hope to see you again soon!");
        this.formattedPrint(byeMsg);
    }
    
    public void formattedPrint(LinkedList<String> strings) {
        System.out.print(dividerLine);
        for (String string: strings) {
            System.out.print("  ");
            System.out.print(string);
            System.out.print("\n");
        }
        System.out.print(dividerLine);
    }
}