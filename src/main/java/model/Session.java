package model;

public class Session {
    private static User user;

    private static final Session instance = new Session();

    private Session() {}

    public static Session getInstance() {
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
