public class StickerMessage extends Message {

    // instance variable
    private String packName;

    public StickerMessage(User sender, String stickerSource) throws OperationDeniedException {
        super(sender);
        String[] arrays = stickerSource.split("/");
        packName = arrays[0];
        contents = arrays[1];
        if (!(getSender() instanceof PremiumUser)){
            throw new OperationDeniedException(DENIED_USER_GROUP);
        }
        if ((sender == null) || (stickerSource.length() == 0)){
            throw new IllegalArgumentException();
        }
    }


    public String getContents() {
        String output = getSender().displayName()+" ["+getDate().toString()+
                "]: Sticker "+contents+" from Pack ["+ getPackName()+"]";
        return output;
    }

    public String getPackName() {
        return packName;
    }
}
