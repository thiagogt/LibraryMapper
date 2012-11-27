package library.bean;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import library.domain.Book;
import library.domain.CanvasMap;
import library.domain.Library;
import library.domain.Node;
import library.parser.ParseColmeiaXML;
import library.utils.*;

import java.io.File;


@ManagedBean(name="search")
@SessionScoped

public class SearchBean implements Serializable{
	
	String query;
	ArrayList<Book> books;
	String selectedIten;
	Book selectedBook;
	String printMapNode;
	String idDoQr;
	StringBuilder outPutCanvas;
	ArrayList<Node> pathNodeSearch;
	public boolean naoExisteAEstante;
	
	private static Log log = LogFactory.getLog(SearchBean.class);
	
	public ArrayList<Node> getPathNodeSearch() {
		return pathNodeSearch;
	}

	public void setPathNodeSearch(ArrayList<Node> pathNodeSearch) {
		this.pathNodeSearch = pathNodeSearch;
	}

	public StringBuilder getOutPutCanvas() {
		return outPutCanvas;
	}
	public void setOutPutCanvas(StringBuilder outPutCanvas) {
		this.outPutCanvas = outPutCanvas;
	}
	public String getIdDoQr() {
		return idDoQr;
	}

	public void setIdDoQr(String idDoQr) {
		this.idDoQr = idDoQr;
	}

	public String getSelectedIten() {
		return selectedIten;
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
	
	public void setSelectedIten(String selectedIten) {
		//System.out.println(selectedIten);
		
		int inicialOfIten = selectedIten.indexOf("\"");
		int finalOfIten = selectedIten.lastIndexOf("\"");
		if(inicialOfIten>=0 && finalOfIten>=0){
			String iten = selectedIten.substring(inicialOfIten+1, finalOfIten);
			//System.out.println("Esse eh o iten cortado "+iten);
			
			this.selectedIten = iten;
			int position =Integer.parseInt(this.selectedIten);
			
			//System.out.println("Position: "+position);
			this.selectedBook = books.get(position);
		}
	}
	
	public String initSearch(){
		try {
			String xml = ServiceWeb.querySearchOnColmeia(this.query);
			this.books = 	ParseColmeiaXML.extractBookList(xml);

		} catch (Exception e) {
			log.error("ERRO: Nao foi possivel carregar toda a busca! : ",e);
			e.printStackTrace();
		}
		return "bookSelection.xhtml";
	}
	
	public String createMap() throws IOException {
		try {
			criaArquivoPrintMapHTML();
			if(!naoExisteAEstante)
				setPrintMapNode(GlobalUtils.PRINT_MAP_FILE);
			else{
				log.info("A estante "+selectedBook.getBookShelf()+" nao existe");
				setPrintMapNode(GlobalUtils.PRINT_ERRO_FILE);
			}
		} catch (Exception e) {
			log.error("\nCreate Map ERROR : ",e);
			e.printStackTrace();
			setPrintMapNode(GlobalUtils.PRINT_ERRO_FILE);
		}
		//System.out.println(printMapNode);
		return printMapNode;
	}

	private void criaArquivoPrintMapHTML() throws IOException, InterruptedException {
		 CanvasMap canvasMap = new CanvasMap(); 
		StringBuilder out = new StringBuilder();  
		canvasMap.init(out, selectedBook,this); 
		outPutCanvas= out;

		  
	}
	
}
