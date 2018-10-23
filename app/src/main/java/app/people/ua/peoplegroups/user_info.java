package app.people.ua.peoplegroups;

public class user_info {
    private String id;
    private String username_on_main;
    private String imageURL;

    public user_info(String id, String username, String imageURL) {
        this.id = id;
        this.username_on_main = username;
        this.imageURL = imageURL;
    }

    public user_info() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username_on_main;
    }

    public void setUsername(String username) {
        this.username_on_main = username;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
