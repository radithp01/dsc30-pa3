import java.time.LocalDate;

/**
 * Create an abstract class called Message
 */
public abstract class Message {

    // Error message to use in OperationDeniedException
    protected static final String DENIED_USER_GROUP =
            "This operation is disabled in your user group.";

    // instance variables
    private LocalDate date;
    private User sender;
    protected String contents;

    /**
     * Constructor will set the sender and date fields.
     * @param sender
     */

    public Message(User sender) {
        this.date = LocalDate.now();
        if (sender == null){
            throw new IllegalArgumentException();
        }
        this.sender = sender;
    }

    /**
     * Method will return the date of this message.
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Method will return the sender of this message.
     * @return Sender
     */

    public User getSender() {
        return sender;
    }

    public abstract String getContents();

}