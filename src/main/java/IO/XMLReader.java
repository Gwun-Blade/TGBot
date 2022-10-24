package IO;

import Viev.MyBlock;
import Viev.MyButton;
import Viev.MyPhoto;
import core.Main;
import org.checkerframework.checker.units.qual.A;
import org.w3c.dom.Document;
import org.jdom2.input.SAXBuilder;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class XMLReader {
    public static List<MyBlock> readXML(String xml) { //где проверка на 0
        ArrayList<MyBlock> ans = new ArrayList<>();
        Document doc = convertStringToDocument(xml);
        doc.getDocumentElement().normalize();

        System.out.println("Root is " + doc.getDocumentElement().getNodeName());
        System.out.println("________________________________");
        NodeList list = doc.getElementsByTagName("block");

        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);

            if (node.getNodeType() == Element.ELEMENT_NODE) {
                Element element = (Element) node;
                int id = Integer.parseInt(element.getAttribute("id"));

                String text = element.getElementsByTagName("text").item(0).getTextContent();
                ArrayList<MyPhoto> photos = new ArrayList<>();
                for (int index = 0; index < element.getElementsByTagName("photo").getLength(); index++) {
                    photos.add(new MyPhoto(element.getElementsByTagName("photo").item(index).getTextContent()));
                }
                ArrayList<MyButton> buttons = new ArrayList<>();
                for (int index = 0; index < element.getElementsByTagName("button").getLength(); index++) {
                    buttons.add(new MyButton(element.getElementsByTagName("button").item(index).getTextContent(),
                            element.getElementsByTagName("button").item(index).getAttributes().getNamedItem("id").getTextContent()));
                }
//                System.out.println(id);
//                System.out.println("text: " + text);
//                for (MyPhoto photo :
//                        photos) {
//                    System.out.println("Photo: " + photo);
//                }
//                for (MyButton button :
//                        buttons) {
//                    System.out.println(button);
//                }
                ans.add(new MyBlock(id, text, photos, buttons));
            }
        }
        return ans;
    }
    private static Document convertStringToDocument(String xml) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xml)));
            return doc;
        } catch (Exception e) {
            Main.log(Level.WARNING, "Ошибка при преобразовании строки в xml документ");
        }
        return null;
    }
}
