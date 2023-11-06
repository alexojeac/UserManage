package model;

import java.io.*;

public class App {
    private final String filename = "usuarios.bin";
    private Users users;
    private Session session;
    private User loggedUser;
    private FileHandler fileHandler;


    public App() {
        fileHandler = new FileHandler(new File(filename));
        users = fileHandler.readUsersFile();
    }

    public Boolean checkUser(String userName, String pass) {
        this.loggedUser = users.getKeyValor(userName);
        if (loggedUser != null && loggedUser.chekPass(pass)) {
            return true;
        } else {
            return false;
        }
    }

    public void changePass(String newPass) {
        User user = users.getKeyValor(loggedUser.getName());
        user.setPasswordHash(newPass);
        user.setPass(newPass);
        users.removeUser(loggedUser.getName());
        users.putUser(user);
        fileHandler.resetFile();
    }

    public void createUser(String name, String password, int age, String email) {
        User user = new User(name, password, age, email);
        users.putUser(user);
        fileHandler.resetFile();
    }

    public void deleteUser(String userName) {
        users.removeUser(userName);
        fileHandler.resetFile();
    }

    public void setSession(boolean login) {
        session = new Session();
        session.setUser(loggedUser);
        fileHandler.manageLoggerFile(session, login);
    }

    public Session getSession() {
        return session;
    }

    public String getUserAge() {
        return String.valueOf(loggedUser.getAge());
    }

    public String getUserMail() {
        return loggedUser.getEmail();
    }

    public String getUserPass() {
        return loggedUser.getPasswordHash();
    }

    public User getLoggedUser() {
        return loggedUser;
    }

}