package model;

public class Session {
    private static User user;
    public Session() {}

    public void setUser(User user) {
        this.user = user;
    }

    public String getSessionUserName() {
        return user.getName();
    }
}
