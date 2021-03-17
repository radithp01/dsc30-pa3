import java.util.ArrayList;
import java.util.List;
/**
 * Raditharan Prabhu
 * A15771941
 */

/**
 * The StandardUser class extends the User class. Users belonging to this class will have
 * a limitation when fetching a message.
 */
public class StandardUser extends User {

    // Message to append when fetching non-text message
    private static final String FETCH_DENIED_MSG =
            "This message cannot be fetched because you are not a premium user.";
    private static final int HUNDRED = 100;

    /**
     * Calls the super class with the input parameters.
     * @param username
     * @param bio
     */

    public StandardUser(String username, String bio) {
        super(username,bio);
    }

    /**
     * Fetches messages from the log of the MessageExchange. This method appends the
     * contents (by calling getContents()) of the messages to a string with a new line
     * character after each and every message (including the last line). The fetched
     * message should preserve the same order as they are in the log.
     * @param me
     * @return string
     */
    public String fetchMessage(MessageExchange me) {
        if (me == null){
            throw new IllegalArgumentException();
        }
        if (!(me.getUsers().contains(this))){
            throw new IllegalArgumentException();
        }
        ArrayList<Message> log = me.getLog();
        StringBuilder output = new StringBuilder("");
        if (log.size() <= HUNDRED) {
            for (int i = 0; i < log.size(); i++) {
                if (log.get(i) instanceof TextMessage) {
                    output.append(log.get(i).getContents() + "\n");
                } else {
                    output.append(FETCH_DENIED_MSG + "\n");
                }
            }
        } else {
            for (int i = 0; i < HUNDRED; i++) {
                if (log.get(log.size()-(HUNDRED - i)) instanceof TextMessage) {
                    output.append(log.get(i).getContents() + "\n");
                } else {
                    output.append(FETCH_DENIED_MSG + "\n");
                }
            }
        }
        return output.toString();
    }

    /**
     * Returns the username.
     * @return username
     */
    public String displayName() {
        return username;  // placeholder for checkpoint test.
                               // replace it with your own after checkpoint submission.
    }
}
