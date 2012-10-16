package library.parser;


import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParseColmeiaXML extends DefaultHandler{
	
 public static void extractBookList(String xmlRecords) throws ParserConfigurationException, SAXException, IOException{
    DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    InputSource is = new InputSource();
    is.setCharacterStream(new StringReader(xmlRecords));

    Document doc = db.parse(is);
    NodeList nodes = doc.getElementsByTagName("livro");
    int size = nodes.getLength();
    for (int i = 0; i < size; i++) {
      Element element = (Element) nodes.item(i);

      NodeList name = element.getElementsByTagName("titulo");
      Element line = (Element) name.item(0);
      String example = getCharacterDataFromElement(line).toString();
      System.out.println("titulo: " + example);

      NodeList title = element.getElementsByTagName("assunto");
      line = (Element) title.item(0);
      System.out.println("assunto: " + getCharacterDataFromElement(line));
    }

}



  public static String getCharacterDataFromElement(Element e) {
    Node child = e.getFirstChild();
    if (child instanceof CharacterData) {
      CharacterData cd = (CharacterData) child;
      return cd.getData();
    }
    return "";
  }
}
