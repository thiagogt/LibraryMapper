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

import library.domain.Book;
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
	ArrayList<Book> books;
	String selectedIten;
	Book selectedBook;
	String printMapNode;
	
	
	

	
	
	
	public String getSelectedIten() {
		return selectedIten;
	}

	public void setSelectedIten(String selectedIten) {
		System.out.println(selectedIten);
		
		int inicialOfIten = selectedIten.indexOf("\"");
		int finalOfIten = selectedIten.lastIndexOf("\"");
		if(inicialOfIten>=0 && finalOfIten>=0){
			String iten = selectedIten.substring(inicialOfIten+1, finalOfIten);
			System.out.println("Esse eh o iten cortado "+iten);
			
			this.selectedIten = iten;
			int position =Integer.parseInt(this.selectedIten);
			
			System.out.println("Position: "+position);
			this.selectedBook = books.get(position);
		}
	}

	public Book getSelectedBook() {
		return selectedBook;
	}

	public void setSelectedBook(Book selectedBook) {
		this.selectedBook = selectedBook;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
		
	}
	public String getPrintMapNode() {
		return printMapNode;
	}

	public void setPrintMapNode(String printMapNode) {
		this.printMapNode = printMapNode;
	}

	
	public String initSearch(){
		
		try {
			String xml = ServiceWeb.querySearchOnColmeia(this.query);
			this.books = 	ParseColmeiaXML.extractBookList(xml);
			for (Book book : books) {
				System.out.println(book.getNomeLivro());	
				System.out.println(book.getAutorLivro());
				System.out.println(book.getBookShelf());
				System.out.println(book.getCopiasNessaBiblioteca());
				System.out.println(book.getPublisher());
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println("ERRO: Nao foi possivel carregar toda a busca! : "+e);
		}
		
		
		
		return "bookSelection.xhtml";
	}
	
	public String createMap() {
		this.printMapNode = loadNodesFromBD();
		return "index.xhtml";
	}

	private String loadNodesFromBD() {
		String allNodes = null;
		
		return allNodes;
	}

	
}
