import java.util.LinkedList;
import java.util.Scanner;

public class UI {
    
    public static final String STARTMESSAGE = "Hello! I'm Duke\n"
        + "What can I do for you?";
    
    //public static final String ENDMESSAGE = "Bye. Hope to see you again soon!";

    /**
     * Appends a newline after every String for Duke to output.
     * @param strings LinkedList of Strings to concatenate
     * @return String of concatenated strings with newline after each string has been concatenated.
     */
    public static String formattedPrint(LinkedList<String> strings) {
        String output = "";
        for (String string: strings) {
            output = output.concat(string);
            output = output.concat("\n");
        }
        return output;
    }
}