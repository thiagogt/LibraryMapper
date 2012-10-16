package library.bean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServlet;

import test.ServiceWebTest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import library.parser.ParseColmeiaXML;
import library.utils.*;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


@ManagedBean(name="search")
@SessionScoped

public class SearchBean {
	
	String query;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
		
	}
	public String initSearch(){
		System.out.println("esse eh a query requisitada: "+this.query);
		try {
			String xml = ServiceWeb.querySearchOnColmeia(this.query);
			ParseColmeiaXML.extractBookList(xml);
		} catch (Exception e) {
			System.out.println("ERRO: Nao foi possivel carregar a busca! : "+e);
		}
		
		
		
		return "mapCreation.xhtml";
	}
//	
//	public void parserLibraryXML(String xml) throws ParserConfigurationException, UnsupportedEncodingException, SAXException, IOException{  
//	
//	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//	    DocumentBuilder db = dbf.newDocumentBuilder();
//	    Document doc = db.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
//	
//	    print(doc.getDocumentElement(), "");
//	}
//	
//	private static void print(Node e, String tab) {
//	
//	    if (e.getNodeType() == Node.TEXT_NODE) {
//	        System.out.println(tab + e.getNodeValue());
//	        return;
//	    }
//	
//	
//	    System.out.print(tab + e.getNodeName());
//	
//	    NamedNodeMap as = e.getAttributes();
//	    if (as != null && as.getLength() > 0) {
//	        System.out.print(" attributes=[");
//	        for (int i = 0; i < as.getLength(); i++) 
//	            System.out.print((i == 0 ? "" : ", ") + as.item(i));
//	        System.out.print("]");
//	    }
//	    System.out.println();
//	
//	    if (e.getNodeValue() != null)
//	        System.out.println(tab + " " + e.getNodeValue());
//	
//	    NodeList childs = e.getChildNodes();
//	    for (int i = 0; i < childs.getLength(); i++)
//	        print(childs.item(i), tab + " ");
//	}
}
