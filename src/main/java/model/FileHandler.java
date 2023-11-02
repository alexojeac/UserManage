package model;

import java.io.*;

public class FileHandler {
    private File file;
    private Users usersCollection;

    public FileHandler(File file) {
        this.file = file;
        createUsersFile();
    }

    public void createUsersFile() {
        if (!file.exists()) {
            byte[] header = new byte[]{(byte) 0xFF, (byte) 0xEE, 0x20, 0x23, (byte) 0xEE, (byte) 0xFF};
            usersCollection = new Users();
            User admin = new User("admin", "admin", 0, "admin@admin.local");
            usersCollection.putUser(admin);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                baos.writeBytes(header);
                oos.writeObject(usersCollection);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Users readUsersFile() {
        usersCollection = new Users();
        try (FileInputStream fis = new FileInputStream(file)) {
            ObjectInputStream ois = new ObjectInputStream(fis);

            try {
                usersCollection = (Users) ois.readObject();
                return usersCollection;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void resetFile() {
        file.delete();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(usersCollection);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
