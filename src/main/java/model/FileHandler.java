package model;

import java.io.*;
import java.sql.Timestamp;
import java.time.Instant;

public class FileHandler {
    private File file;
    private Users usersCollection;
    private final byte[] HEADER = new byte[]{(byte) 0xFF, (byte) 0xEE, 0x20, 0x23, (byte) 0xEE, (byte) 0xFF};

    public FileHandler(File file) {
        this.file = file;
        createUsersFile();
    }

    public void createUsersFile() {
        if (!file.exists()) {
            usersCollection = new Users();
            User admin = new User("admin", "admin", 0, "admin@admin.local");
            usersCollection.putUser(admin);
            newFileUsers();
        }
    }

    public Users readUsersFile() {
        usersCollection = new Users();
        try (FileInputStream fis = new FileInputStream(file)) {

            fis.skip(6);
            ObjectInputStream ois = new ObjectInputStream(fis);

            usersCollection = (Users) ois.readObject();
            return usersCollection;
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void resetFile() {
        file.delete();
        newFileUsers();
    }

    private void newFileUsers() {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            baos.writeBytes(HEADER);
            baos.writeTo(fos);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(usersCollection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean validateHeader(FileInputStream fis) {
        boolean isEquals = false;
        byte[] fileHeader = new byte[6];

        try {
            fis.read(fileHeader);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i > HEADER.length; i++) {
            if(fileHeader[i] == HEADER[i]) {
                isEquals = true;
            } else {
                isEquals = false;
            }
        }

        return isEquals;
    }

    public void manageLoggerFile(Session session, boolean login) {
        File sessionFile = new File("session.log");

        StringBuilder sb = new StringBuilder(Timestamp.from(Instant.now()).toString());


        try (FileWriter fileWriter = new FileWriter(sessionFile, true)) {
            BufferedWriter buffer = new BufferedWriter(fileWriter);

            if (login) {
                buffer.write(sb.subSequence(0, 19) + " -> " + session.getSessionUserName() + " LOGIN");
                buffer.newLine();
            } else {
                buffer.write(sb.subSequence(0, 19) + " -> " + session.getSessionUserName() + " LOGOUT");
                buffer.newLine();
            }

            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
