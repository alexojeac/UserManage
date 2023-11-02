package model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;

public class JSON {
    public static void createJsonFileUser(String name, String pass, int age, String mail, File selectedFile) {
        JsonObject user = new JsonObject();
        user.addProperty("nombre", name);
        user.addProperty("contrasena", pass);
        user.addProperty("edad", age);
        user.addProperty("email", mail);

        Gson gson = new Gson();
        String jsonString = gson.toJson(user);

        try (FileWriter fileWriter = new FileWriter(selectedFile)) {
            BufferedWriter buffer = new BufferedWriter(fileWriter);

            buffer.write(jsonString);
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /*JsonObject usuario
        usuario.addProperty("usuario", name);

        JsonArray arrayUsuarios = new JsonArray();

        JsonObject usuario = new JsonObject();
        usuario.addProperty("nombre", name);
        usuario.addProperty("edad", 10);*/
}
