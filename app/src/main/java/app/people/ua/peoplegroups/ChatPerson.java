package app.people.ua.peoplegroups;

public class ChatPerson {
    private String sender;
    private String receiver;
    private String message;

    public ChatPerson(String sender, String receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public ChatPerson() {
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getMessage() {
        return message;
    }
}
