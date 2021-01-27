import java.util.ArrayList;

/**
 * Raditharan Prabhu
 * A15771941
 * The PhotoRoom class also implements the MessageExchange interface.
 */
public class PhotoRoom implements MessageExchange {

    // instance variables
    private ArrayList<User> users;
    private ArrayList<Message> log;

    /**
     * The constructor should initialize the following class variables that you have to declare:
     * private ArrayList<User> users that records all users who joined this chat room.
     * private ArrayList<Message> log that records all messages being sent to this chat room.
     */

    public PhotoRoom() {
        this.users = new ArrayList<User>();
        this.log = new ArrayList<Message>();
    }

    /**
     * Method returns the log of this chat room.
     * @return log
     */
    public ArrayList<Message> getLog() {
        return log;
    }

    /**
     * Method adds the given user u to this room. If this user is not a PremiumUser,
     * deny this operation by returning false.
     * @param u User to add.
     * @return boolean
     */

    public boolean addUser(User u) {
        if (!(u instanceof PremiumUser)){
            return false;
        }
        else {
            users.add(u);
            return true;
        }
    }

    /**
     * Method removes the given user u from this room.
     * @param u User to remove.
     */

    public void removeUser(User u) {
        users.remove(u);
    }

    /**
     * Method returns the users of this chat room.
     * @return
     */

    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Method adds a new message to the log of this chat room. If this message is not a
     * PhotoMessage, deny this operation by returning false.
     * @param m Message to add.
     * @return boolean
     */

    public boolean recordMessage(Message m) {
        if (m instanceof PhotoMessage){
            log.add(m);
            return true;
        }
        else {
            return false;
        }
    }

}
