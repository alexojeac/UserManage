package model;

import gui.Login;

public class Main {

    public static void main(String[] args) {
        App app = new App();
        Login login = new Login(app);

        login.setVisible(true);
    }

}
