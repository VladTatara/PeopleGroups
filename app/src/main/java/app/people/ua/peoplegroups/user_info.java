package app.people.ua.peoplegroups;

public class user_info {
    private String id;
    private String username_on_main;
    private String imageURL;
    private String UserMail;

    private user_info(String id, String username, String imageURL, String userMail) {
        this.id = id;
        this.username_on_main = username;
        this.imageURL = imageURL;
        this.UserMail = userMail;
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
    public String getuserMail() {
        return UserMail;
    }

    public void setuserMail(String userMail) {
        this.UserMail = userMail;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }


}
