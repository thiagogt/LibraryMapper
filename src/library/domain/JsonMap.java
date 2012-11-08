package library.domain;

public class JsonMap {

	String id;
	String type;
	int top;
	int left;
	int height;
	
	int width;
	String idInitialShelf;
	private String idFinalShelf;
	

	public JsonMap() {
		
	}

	public JsonMap(String id2, String type2, int top2, int left2, int heigth2,int width2,String idShelfInitial,String idShelfFinal) {
		this.id = id2;
		this.type = type2;
		this.top = top2;
		this.left = left2;
		this.height = heigth2;
		System.out.println("ola aii  "+heigth2);
		this.width = width2;
		this.idInitialShelf = idShelfInitial;
		this.idFinalShelf = idShelfFinal;
	}

	public String getIdFinalShelf() {
		return idFinalShelf;
	}

	public void setIdFinalShelf(String idFinalShelf) {
		this.idFinalShelf = idFinalShelf;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIdInitialShelf() {
		return idInitialShelf;
	}

	public void setIdInitialShelf(String idInitialShelf) {
		this.idInitialShelf = idInitialShelf;
	}

	
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int heigth) {
		this.height = heigth;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}

	public String getMapItens(){
		
		return null;
	}	
}

