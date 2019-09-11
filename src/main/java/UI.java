import java.util.LinkedList;
import java.util.Scanner;

public class UI {
    
    public static final String STARTMESSAGE = "Hello! I'm Duke\n"
        + "What can I do for you?";
    
    //public static final String ENDMESSAGE = "Bye. Hope to see you again soon!";

    public static String formattedPrint(LinkedList<String> strings) {
        String output = "";
        for (String string: strings) {
            output = output.concat(string);
            output = output.concat("\n");
        }
        return output;
    }
}