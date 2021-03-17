/**
 * Raditharan Prabhu
 * A15771941
 */
/**
 * The TextMessage class will ‘extend’ the abstract class Message.
 */
public class TextMessage extends Message {

    // Error message to use in OperationDeniedException
    private static final String EXCEED_MAX_LENGTH =
            "Your input exceeded the maximum length limit.";
    private static final int MAXLENGTH = 500;

    /**
     * Initialize contents with the argument text.
     * @param sender
     * @param text
     * @throws OperationDeniedException
     */
    public TextMessage(User sender, String text) throws OperationDeniedException {
        super(sender);
        if (text.length() > MAXLENGTH){
            throw new OperationDeniedException(EXCEED_MAX_LENGTH);
        }
        else if ((sender == null) || (text == null)){
            throw new IllegalArgumentException();
        }
        this.contents = text;

    }

    /**
     * Returns a String in the form:
     * “SenderName [2020-01-15]: A sample text message.”
     * @return Contents of Text Message.
     */
    public String getContents() {

        String output = getSender().displayName() + " [" + getDate().toString() + "]: " +contents;
        return output;
    }

}
