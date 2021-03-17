import java.util.ArrayList;
/**
 * Raditharan Prabhu
 * A15771941
 */

/**
 * The User class is the abstract class that defines the functionality of a user in our messaging
 * app.
 */
public abstract class User {

    // Error message to use in OperationDeniedException
    protected static final String JOIN_ROOM_FAILED =
            "Failed to join the chat room.";
    protected static final String INVALID_MSG_TYPE =
            "Cannot send this type of message to the specified room.";

    // instance variables
    protected String username;
    protected String bio;
    protected ArrayList<MessageExchange> rooms;

    /**
     * Initialize the rooms class variable to a new instance of its type. Assign the other two
     * class variables accordingly with the parameters in the constructor.
     * @param username
     * @param bio
     */
    public User(String username, String bio) {
        if ((username == null) || (bio == null)){
            throw new IllegalArgumentException();
        }
        rooms  = new ArrayList<MessageExchange>();
        this.username = username;
        this.bio = bio;
    }

    /**
     * This method updates the class variable bio with a new one.
     * @param newBio
     */

    public void setBio(String newBio) {
        if (newBio == null){
            throw new IllegalArgumentException();
        }
        bio = newBio;
    }

    /**
     * Returns the bio.
     * @return bio
     */

    public String displayBio() {
        return bio;
    }

    /**
     * This method adds the user to the list of users in the message exchange platform and adds
     * the platform to the list of rooms of this user.
     * @param me
     * @throws OperationDeniedException
     */

    public void joinRoom(MessageExchange me) throws OperationDeniedException {
        if (me == null){
            throw new IllegalArgumentException();
        }
        if (this.rooms.contains(me)){
            throw new OperationDeniedException(JOIN_ROOM_FAILED);
        }
        else {
            if (me.addUser(this)){
                this.rooms.add(me);
            }
            else{
                throw new OperationDeniedException(JOIN_ROOM_FAILED);
            }
        }
    }

    /**
     * Removes the message exchange platform from the list of rooms that this user is a
     * member of and removes the user from the list of users recorded in the MessageExchange
     * object.
     * @param me
     */

    public void quitRoom(MessageExchange me) {
        if (me == null){
            throw new IllegalArgumentException();
        }
        else {
            rooms.remove(me);
            me.removeUser(this);
        }
    }

    /**
     * Creates a new instance of the ChatRoom (will be implemented later) class.
     * For each user in the users list calls joinRoom method to join the room.
     * @param users
     * @return
     */

    public MessageExchange createChatRoom(ArrayList<User> users) {
        if (users == null){
            throw new IllegalArgumentException();
        }
        ChatRoom chat = new ChatRoom();
        try {
            this.joinRoom(chat);
        } catch (Exception e) {
            e.getMessage();
        }
        for (int i = 0; i < users.size();i++){
            try {
                users.get(i).joinRoom(chat);
            } catch (Exception e){
                e.getMessage();
                continue;
            }
        }
        return chat;
    }

    /**
     *Creates an instance of a message with the correct type specified by the
     * msgType (compare it to the enum variables in the MessageType class).
     * Record the message instance in the MessageExchange.
     * @param me
     * @param msgType
     * @param contents
     */


    public void sendMessage(MessageExchange me, MessageType msgType, String contents) {
        if ((me == null) || (msgType == null) || (contents == null)){
            throw new IllegalArgumentException();
        }
        if (!(me.getUsers().contains(this))){
            throw new IllegalArgumentException();
        }
        /*
        checks if message type is text.
         */
        if (msgType == MessageType.TEXT){
            try {
                TextMessage newText = new TextMessage(this,contents);
                if (me.recordMessage(newText) != true){
                    System.out.println(INVALID_MSG_TYPE);
                }
            } catch (Exception e){
                e.getMessage();
            }
        }
        /*
        checks if message type is photo.
         */
        else if (msgType == MessageType.PHOTO){
            try {
                PhotoMessage newPhoto = new PhotoMessage(this,contents);
                if (me.recordMessage(newPhoto) != true){
                    System.out.println(INVALID_MSG_TYPE);
                }
            } catch (Exception e){
                e.getMessage();
            }
        }
        /*
        checks if message type is sticker.
         */
        else if (msgType == MessageType.STICKER){
            try {
                StickerMessage newSticker = new StickerMessage(this,contents);
                if (me.recordMessage(newSticker) != true){
                    System.out.println(INVALID_MSG_TYPE);
                }
            } catch (Exception e){
                e.getMessage();
            }
        }
    }

    public abstract String fetchMessage(MessageExchange me);

    public abstract String displayName();
}
