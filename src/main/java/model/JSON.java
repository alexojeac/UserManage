package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

public class JSON {
    private static User user;
    private App app;

    public static void createJsonFileUser(User user, File selectedFile) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder = gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        try (FileWriter writer = new FileWriter(selectedFile)) {
            gson.toJson(user, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static File createJsonFileUsersMap(Users users) {
        File file = new File("usuarios.json");
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder = gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
