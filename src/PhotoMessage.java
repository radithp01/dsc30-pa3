import java.util.Locale;

public class PhotoMessage extends Message {

    // Error message to use in OperationDeniedException
    private static final String INVALID_INPUT =
            "The source path given cannot be parsed as photo.";

    // instance variable
    private String extension;

    public PhotoMessage(User sender, String photoSource) throws OperationDeniedException {
        super(sender);
        this.contents = photoSource;
        String[] arrays = photoSource.split(".");
        extension = arrays[arrays.length -1].toLowerCase();
        String[] acceptable = { "jpg", "jpeg", "gif", "png", "tif", "tiff", "raw"};
        if ((sender == null) || (photoSource.length() == 0)){
            throw new IllegalArgumentException();
        }
        int checker = 0;
        for (int i = 0; i < acceptable.length; i++){
            if (acceptable[i] == extension){
                checker += 1;
            }
        }
        if (checker == 0){
            throw new OperationDeniedException(INVALID_INPUT);
        }
        if (!(getSender() instanceof PremiumUser)){
            throw new OperationDeniedException(DENIED_USER_GROUP);
        }
    }

    public String getContents() {
        String output = getSender().displayName()+" ["+getDate().toString()+"]: Picture at "+
                this.contents;
        return output;
    }

    public String getExtension() {
        return extension;
    }

}
