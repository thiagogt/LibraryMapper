package domain;

public class Book {

	int id;
	String nomeLivro;
	String autorLivro;
	int copiasNessaBiblioteca;
	BookShelf bookShelf;
	
	
	public BookShelf getBookShelf() {
		return bookShelf;
	}
	public void setBookShelf(BookShelf bookShelf) {
		this.bookShelf = bookShelf;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeLivro() {
		return nomeLivro;
	}
	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}
	public String getAutorLivro() {
		return autorLivro;
	}
	public void setAutorLivro(String autorLivro) {
		this.autorLivro = autorLivro;
	}
	public int getCopiasNessaBiblioteca() {
		return copiasNessaBiblioteca;
	}
	public void setCopiasNessaBiblioteca(int copiasNessaBiblioteca) {
		this.copiasNessaBiblioteca = copiasNessaBiblioteca;
	}
}
