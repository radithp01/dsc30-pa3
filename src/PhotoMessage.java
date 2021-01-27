import java.util.Locale;
/**
 * Raditharan Prabhu
 * A15771941
 */
/**
 * The PhotoMessage class will also ‘extend’ the abstract class Message.
 * In addition, this class will also have its own instance variable extension,
 * which is the file extension of the photo.
 */
public class PhotoMessage extends Message {

    // Error message to use in OperationDeniedException
    private static final String INVALID_INPUT =
            "The source path given cannot be parsed as photo.";

    // instance variable
    private String extension;

    /**
     * Initialize contents with the argument photoSource, and make the file
     * extension extracted from photoSource to lowercase and store it to the
     * variable extension (excluding the dot).
     * @param sender
     * @param photoSource
     * @throws OperationDeniedException
     */

    public PhotoMessage(User sender, String photoSource) throws OperationDeniedException {
        super(sender);
        if ((sender == null) || (photoSource == null)){
            throw new IllegalArgumentException();
        }
        if (!(getSender() instanceof PremiumUser)){
            throw new OperationDeniedException(DENIED_USER_GROUP);
        }
        this.contents = photoSource;
        String lowerPhoto= photoSource.toLowerCase();
        String[] arrays = lowerPhoto.split("\\.", 0);
        extension = arrays[arrays.length -1];
        String[] acceptable = { "jpg", "jpeg", "gif", "png", "tif", "tiff", "raw"};
        int checker = 0;
        for (int i = 0; i < acceptable.length; i++){
            if (acceptable[i].equals(extension)){
                checker += 1;
            }
        }
        if (checker == 0){
            throw new OperationDeniedException(INVALID_INPUT);
        }

    }

    /**
     * Returns a String in the form:
     * “SenderName [2020-01-15]: Picture at image/example.tif”
     * @return contents of the PhotoMessage
     */
    public String getContents() {
        String output = getSender().displayName()+" ["+getDate().toString()+"]: Picture at "+
                this.contents;
        return output;
    }

    /**
     * Method will return the extension of this message.
     * @return Extension of the PhotoMessage
     */

    public String getExtension() {
        return extension;
    }

}
