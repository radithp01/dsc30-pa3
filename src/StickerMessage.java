/**
 * Raditharan Prabhu
 * A15771941
 */
/**
 * The StickerMessage class will also ‘extend’ the abstract class
 * Message. In addition, this class will also have its own instance
 * variable packName, which is the name of the sticker pack this sticker
 * belongs to.
 */
public class StickerMessage extends Message {

    // instance variable
    private String packName;

    /**
     * Extract the name of a sticker pack and a sticker from stickerSource.
     * Initialize contents with the sticker name extracted, and initialize
     * packName with the sticker pack name extracted.
     * @param sender
     * @param stickerSource
     * @throws OperationDeniedException
     */
    public StickerMessage(User sender, String stickerSource) throws OperationDeniedException {
        super(sender);
        if (!(getSender() instanceof PremiumUser)){
            throw new OperationDeniedException(DENIED_USER_GROUP);
        }
        if ((sender == null) || (stickerSource == null)){
            throw new IllegalArgumentException();
        }
        String[] arrays = stickerSource.split("/");
        packName = arrays[0];
        contents = arrays[1];

    }

    /**
     * Returns a String in the form:
     * “SenderName [2019-10-29]: Sticker [Questioning] from Pack [yourcraft-8]”
     * @return Contents of StickerMessage
     */

    public String getContents() {
        String output = getSender().displayName()+" ["+getDate().toString()+
                "]: Sticker ["+contents+"] from Pack ["+ getPackName()+"]";
        return output;
    }

    /**
     * Method will return the packName of this message.
     * @return Name of the pack
     */
    public String getPackName() {
        return packName;
    }
}
