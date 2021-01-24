public class TextMessage extends Message {

    // Error message to use in OperationDeniedException
    private static final String EXCEED_MAX_LENGTH =
            "Your input exceeded the maximum length limit.";

    public TextMessage(User sender, String text) throws OperationDeniedException {
        super(sender);
        this.contents = text;
        if (text.length() > 500){
            throw new OperationDeniedException(EXCEED_MAX_LENGTH);
        }
        else if ((sender == null) || (text.length() == 0)){
            throw new IllegalArgumentException();
        }
    }


    public String getContents() {

        String output = getSender().displayName() + " [" + getDate().toString() + "]: " +contents;
        return output;
    }

}
