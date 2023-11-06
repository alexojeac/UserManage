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

public class XML {
    public static void createXmlFileUser(User user, File selectedFile) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            DOMImplementation implementation = db.getDOMImplementation();
            Document doc = implementation.createDocument(selectedFile.getAbsolutePath(), "root", null);

            Element rootElement = doc.createElement("ususarios");
            doc.appendChild(rootElement);
            Element userElement = doc.createElement("usuario");
            rootElement.appendChild(userElement);
            Element nameElement = doc.createElement("nombre");
            nameElement.appendChild(doc.createTextNode(user.getName()));
            Element passElement = doc.createElement("contrasena");
            passElement.appendChild(doc.createTextNode(user.getPasswordHash()));
            Element ageElement = doc.createElement("edad");
            ageElement.appendChild(doc.createTextNode(String.valueOf(user.getAge())));
            Element mailElement = doc.createElement("email");
            mailElement.appendChild(doc.createTextNode(user.getEmail()));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(selectedFile.getAbsolutePath()));

            transformer.transform(source, result);

        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
