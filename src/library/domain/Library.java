package library.domain;

import java.util.ArrayList;

public class Library {
    private Integer idLibrary;

    private Integer sizeX;

    private Integer sizeY;
    private ArrayList<Bookshelf> bookShelves;
	private ArrayList<QrCodeMark> qrCodesMarks;
	private Node[][] map;
	
	public Library(int newSize_X, int newSize_Y){
		setSizeX(newSize_X);
		setSizeY(newSize_Y);
		map = new Node[this.sizeX][this.sizeY];
		
		for (int i = 0; i < this.sizeX; i++) {
			for (int j = 0; j < newSize_Y; j++) {
				this.map[i][j] = Node.getNodeByPosition(i,j);
							
			}
			
		}
	}
		
    
    public ArrayList<Bookshelf> getBookShelves() {
		return bookShelves;
	}


	public void setBookShelves(ArrayList<Bookshelf> bookShelves) {
		this.bookShelves = bookShelves;
	}


	public ArrayList<QrCodeMark> getQrCodesMarks() {
		return qrCodesMarks;
	}


	public void setQrCodesMarks(ArrayList<QrCodeMark> qrCodesMarks) {
		this.qrCodesMarks = qrCodesMarks;
	}


	public Node[][] getMap() {
		return map;
	}


	public void setMap(Node[][] map) {
		this.map = map;
	}


	public Integer getIdLibrary() {
        return idLibrary;
    }

    public void setIdLibrary(Integer idLibrary) {
        this.idLibrary = idLibrary;
    }

    public Integer getSizeX() {
        return sizeX;
    }

    public void setSizeX(Integer sizeX) {
        this.sizeX = sizeX;
    }

    public Integer getSizeY() {
        return sizeY;
    }

    public void setSizeY(Integer sizeY) {
        this.sizeY = sizeY;
    }
}