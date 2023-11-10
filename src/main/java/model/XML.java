package model;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XML {
    public static void createXmlFileUser(User user, File selectedFile) {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element rootElement = doc.createElement("users");
            doc.appendChild(rootElement);

            Element userElement = doc.createElement("user");
            rootElement.appendChild(userElement);

            Element nombre = doc.createElement("name");
            nombre.appendChild(doc.createTextNode(user.getName()));
            userElement.appendChild(nombre);

            Element password = doc.createElement("password");
            password.appendChild(doc.createTextNode(user.getPasswordHash()));
            userElement.appendChild(password);

            Element edad = doc.createElement("age");
            edad.appendChild(doc.createTextNode(String.valueOf(user.getAge())));
            userElement.appendChild(edad);

            Element email = doc.createElement("email");
            email.appendChild(doc.createTextNode(user.getEmail()));
            userElement.appendChild(email);

            FileWriter fw = new FileWriter(selectedFile);
            TransformerFactory.newInstance().newTransformer().transform(new DOMSource(doc), new StreamResult(fw));
            fw.close();

        } catch (ParserConfigurationException | TransformerException | IOException e) {
            e.printStackTrace();
        }
    }

    public static File createXmlFileUsers(Users users) {
        File file = new File("usuarios.xml");
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("Users");
            doc.appendChild(rootElement);

            for (User user : users.getUsers().values()) {
                Element usuarioElement = doc.createElement("User");
                rootElement.appendChild(usuarioElement);

                Element nameElement = doc.createElement("Name");
                nameElement.appendChild(doc.createTextNode(String.valueOf(user.getName())));
                usuarioElement.appendChild(nameElement);

                Element idElement = doc.createElement("Password");
                idElement.appendChild(doc.createTextNode(String.valueOf(user.getPasswordHash())));
                usuarioElement.appendChild(idElement);

                Element ageElement = doc.createElement("Age");
                ageElement.appendChild(doc.createTextNode(String.valueOf(user.getAge())));
                usuarioElement.appendChild(ageElement);

                Element correoElement = doc.createElement("Email");
                correoElement.appendChild(doc.createTextNode(user.getEmail()));
                usuarioElement.appendChild(correoElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
}
