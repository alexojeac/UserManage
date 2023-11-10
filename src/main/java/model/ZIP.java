package model;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZIP {
    public static void createZipFile(Users users, File selectedFile)  {
        try {
            File json = JSON.createJsonFileUsersMap(users);
            File xml = XML.createXmlFileUsers(users);
            List<File> srcFiles = Arrays.asList(json, xml);

            FileOutputStream fos = new FileOutputStream(selectedFile);
            ZipOutputStream zip = new ZipOutputStream(fos);

            for (File srcFile : srcFiles) {
                FileInputStream fis = new FileInputStream(srcFile);
                ZipEntry zipEntry = new ZipEntry(srcFile.getName());
                zip.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while((length = fis.read(bytes)) >= 0) {
                    zip.write(bytes, 0, length);
                }
                fis.close();
            }
            zip.close();
            fos.close();
            json.delete();
            xml.delete();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
