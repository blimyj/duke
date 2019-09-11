import java.time.LocalDateTime;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime byDeadline;

    /**
     * Creates a deadline event.
     * @param description task description
     * @param byDeadline deadline for task
     * @throws DukeException when there is a problem parsing the date time string provided by user
     */
    public Deadline(String description, String byDeadline) throws DukeException {
        super(description);
        this.taskType = TaskType.DEADLINE;
        try {
            this.byDeadline = LocalDateTime.parse(byDeadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
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
    public String getByDeadlineString() {
        try {
            String string = byDeadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            return string;
        } catch (DateTimeException e) {
            return "DateCannotBeFormatted";
        }
    }

    @Override
    public String toSaveString() {
        String saveString = super.toSaveString() + "@@@" + this.getByDeadlineString();
        return saveString;
    }
    
    @Override
    public String toString() {
        String str = "["
                + "D"
                + "]["
                + this.getStatusIcon()
                + "] "
                + this.getDescription()
                + " (by: "
                + this.getByDeadlineString()
                + ")";
        return str;
    }
}