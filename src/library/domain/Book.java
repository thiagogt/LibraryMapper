package library.domain;

import java.util.ArrayList;

public class Book {

	
	String nomeLivro;
	ArrayList<String> autorLivro;
	String publisher;
	String bookShelf;
	int copiasNessaBiblioteca;
	
	
	
	
	public String getBookShelf() {
		return bookShelf;
	}
	public void setBookShelf(String bookShelf) {
		this.bookShelf = bookShelf;
	}
	
	public String getNomeLivro() {
		return nomeLivro;
	}
	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}
	public ArrayList<String> getAutorLivro() {
		return autorLivro;
	}
	public void setAutorLivro(ArrayList<String> autorLivro) {
		this.autorLivro = autorLivro;
	}
	public int getCopiasNessaBiblioteca() {
		return copiasNessaBiblioteca;
	}
	public void setCopiasNessaBiblioteca(int copiasNessaBiblioteca) {
		this.copiasNessaBiblioteca = copiasNessaBiblioteca;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String characterDataFromElement) {
		this.publisher = characterDataFromElement;
		
	}
}
