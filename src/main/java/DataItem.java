
public class DataItem {

    private String telephoneNumber;
    private String conversationDate;
    private String callNumber;
    private String callTown;
    private String callDuration;

    DataItem(){

    }

    DataItem(String telephoneNumber, String conversationDate, String callNumber, String callTown, String callDuration) {
        this.telephoneNumber = telephoneNumber;
        this.conversationDate = conversationDate;
        this.callNumber = callNumber;
        this.callTown = callTown;
        this.callDuration = callDuration;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getConversationDate() {
        return conversationDate;
    }

    public String getCallNumber() {
        return callNumber;
    }

    public String getCallTown() {
        return callTown;
    }

    public String getCallDuration() {
        return callDuration;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setConversationDate(String conversationDate) {
        this.conversationDate = conversationDate;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public void setCallTown(String callTown) {
        this.callTown = callTown;
    }

    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration;
    }

}
