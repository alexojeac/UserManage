package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.*;
import java.util.HashMap;

public class JSON {
    public static void createJsonFileUser(String name, String pass, int age, String mail, File selectedFile) {
        User user = new User(name, pass, age, mail);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder = gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        try (FileWriter writer = new FileWriter(selectedFile)) {
            gson.toJson(user, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void createJsonFileUsersMap(HashMap users) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder = gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        try (FileWriter writer = new FileWriter("users.json")) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
