import java.util.ArrayList;
/**
 * Raditharan prabhu
 * A15771941
 */

/**
 * The PremiumUser class extends the User class. Users belonging to this class will have no
 * limitation of fetching messages. Also, they will have a custom title that will always display
 * before the name.
 */
public class PremiumUser extends User {

    // instance variable
    private String customTitle;

    /**
     * Calls the super class with the input parameters. Also initializes the customTitle class
     * variable which is exclusive to the PremiumUser class.
     * @param username
     * @param bio
     */
    public PremiumUser(String username, String bio) {
        super(username,bio);
        this.customTitle = "Premium";
    }

    /**
     * Fetches all messages from the log of the MessageExchange. This method appends the contents
     * (by calling getContents()) of the messages to a string with a new line character after each
     * and every message (including the last line).
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
        /*
        Creates the message using a for loop.
         */
        for (int i = 0; i < log.size(); i++){
            output.append(log.get(i).getContents() + "\n");
        }
        String finalOutput = output.toString();
        return finalOutput;
    }

    /**
     * Exactly the same as User.createChatRoom(), except this time the room created and returned
     * is of type PhotoRoom.
     * @param users
     * @return
     */

    public MessageExchange createPhotoRoom(ArrayList<User> users) {
        if (users == null){
            throw new IllegalArgumentException();
        }
        PhotoRoom photo = new PhotoRoom();
        try {
            this.joinRoom(photo);
        } catch (Exception e) {
            e.getMessage();
        }

        for (int i = 0; i < users.size(); i++) {
            try {
                users.get(i).joinRoom(photo);
            } catch (Exception e) {
                e.getMessage();
                continue;
            }
        }

        return photo;
    }

    /**
     * returns the username and customTitle in the following format:
     * “<customTitle> username” where customTitle and username are replaced with their values.
     * @return
     */
    public String displayName() {
        return "<" + customTitle + "> " + username;
    }

    /**
     * Sets the customTitle variable to the new value.
     * @param newTitle
     */
    public void setCustomTitle(String newTitle) {
        customTitle = newTitle;
    }
}
