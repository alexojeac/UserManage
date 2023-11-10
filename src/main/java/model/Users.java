package model;

import java.io.Serializable;
import java.util.HashMap;

public class Users implements Serializable {
    private static final long serialVersionUID = 9889L;
    HashMap<String, User> users;

    public Users() {
        users = new HashMap<>();
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public void putUser(User user) {
        this.users.put(user.getName(), user);
    }

    public User getKeyValor(String key) {
        return users.get(key);
    }

    public void removeUser(String userName) {
        users.remove(userName);
    }

}
