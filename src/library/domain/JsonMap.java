package library.domain;

public class JsonMap {

	String id;
	String type;
	int top;
	int left;
	int heigth;
	
	int width;
	String idShelf;
	

	public JsonMap() {
		
	}

	public JsonMap(String id2, String type2, int top2, int left2, int heigth2,int width2,String idShelf) {
		this.id = id2;
		this.type = type2;
		this.top = top2;
		this.left = left2;
		this.heigth = heigth2;
		this.width = width2;
		this.idShelf = idShelf;
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

	public String getIdShelf() {
		return idShelf;
	}

	public void setIdShelf(String idShelf) {
		this.idShelf = idShelf;
	}

	
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getHeigth() {
		return heigth;
	}
	public void setHeigth(int heigth) {
		this.heigth = heigth;
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

