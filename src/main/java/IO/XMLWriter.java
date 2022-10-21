package IO;

import Viev.Scenario;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.IOException;
import java.io.StringReader;

public class XMLWriter {
    public static void printXML(Scenario scenario) throws JDOMException, IOException {
        SAXBuilder sb = new SAXBuilder();
        Document document = sb.build(new StringReader(scenario.toString()));

        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
        outputter.output(document, System.out);
    }
}
