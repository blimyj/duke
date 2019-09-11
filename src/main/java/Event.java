import java.time.LocalDateTime;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Event extends Task {
    protected LocalDateTime atDateTime;

    /**
     * Creates an Event Object.
     * @param description task description
     * @param atDateTime time of event
     * @throws DukeException when there is a problem parsing the date time string provided by user
     */
    public Event(String description, String atDateTime) throws DukeException {
        super(description);
        this.taskType = TaskType.EVENT;
        try {
            this.atDateTime = LocalDateTime.parse(atDateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        } catch (DateTimeParseException e) {
            throw new DukeException("DateTime Parsing Failed: DateTime Format should follow \"dd/MM/yyyy HH:mm\" " 
                    + "format." 
                    + e.getMessage());
        }
    }

    /**
     * Converts DateTime to String in the format of dd/MM/yyyy HH:mm.
     * @return DateTime as String in the format of dd/MM/yyyy HH:mm
     */
    public String getAtDateTimeString() {
        try {
            String string = atDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            return string;
        } catch (DateTimeException e) {
            return "DateCannotBeFormatted";
        }
    }

    @Override
    public String toSaveString() {
        String saveString = super.toSaveString() + "@@@" + this.getAtDateTimeString();
        return saveString;
    }
    
    @Override
    public String toString() {
        String str = "["
                + "E"
                + "]["
                + this.getStatusIcon()
                + "] "
                + this.getDescription()
                + " (at: "
                + this.getAtDateTimeString()
                + ")";
        return str;
    }
}