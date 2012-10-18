package library.parser;


import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import library.domain.Book;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParseColmeiaXML extends DefaultHandler{
	
 public static ArrayList<Book> extractBookList(String xmlRecords) throws ParserConfigurationException, SAXException, IOException{

	 DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    InputSource is = new InputSource();
    is.setCharacterStream(new StringReader(xmlRecords));
    Document doc = db.parse(is);
    NodeList nodes = doc.getElementsByTagName("livro");
    return parseBookListFromRoot(nodes);
    

}



  private static ArrayList<Book> parseBookListFromRoot(NodeList nodes) {

	    int size = nodes.getLength();
	    ArrayList<Book> books = new ArrayList<Book>();
		
	    for (int i = 0; i < size; i++) {
	      Element element = (Element) nodes.item(i);
	      Book book = new Book();
	      
	      book.setIdLivro(i);
	      NodeList title = element.getElementsByTagName("titulo");
	      Element line = (Element) title.item(0);
	      book.setNomeLivro(getCharacterDataFromElement(line));
	     
	      
	      NodeList autor = element.getElementsByTagName("autor");
	      int autorSize = autor.getLength();
	      ArrayList<String> autores = new ArrayList<String>();
	      for (int j = 0; j < autorSize; j++) {
	    	  line = (Element) autor.item(j);
	    	  autores.add(getCharacterDataFromElement(line));
	          
	      }
	      book.setAutorLivro(autores);
	            

	      NodeList editora = element.getElementsByTagName("editora");
	      
	      line = (Element) editora.item(0);
	      book.setPublisher(getCharacterDataFromElement(line));
	      

	      NodeList samples = element.getElementsByTagName("numero");
	      int totalSamples = samples.getLength();
	      book.setCopiasNessaBiblioteca(totalSamples);
	      

	      NodeList shelfId = element.getElementsByTagName("chamada");
	      line = (Element) shelfId.item(0);
	      book.setBookShelf(getCharacterDataFromElement(line));
	      
	      
	      books .add(book);
	    }
	    return books;
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
