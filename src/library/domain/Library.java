package library.domain;

import java.util.ArrayList;

import javax.swing.text.AbstractDocument.Content;

import library.utils.GlobalUtils;



public class Library {
    private Integer idLibrary;

    private Integer sizeX;

    private Integer sizeY;

    ArrayList<Bookshelf> bookShelves;
	ArrayList<QrCodeMark> qrCodesMarks;
	Node[][] map;
	
	public void Library(int newSize_X, int newSize_Y,int totalQRMarkers){
		setSizeX(newSize_X);
		setSizeY(newSize_Y);
		setMap(new Node[this.sizeX][this.sizeY]);
		
		for (int i = 0; i < this.sizeX; i++) {
			for (int j = 0; j < this.sizeY; j++) {
				this.map[i][j].mapValue = GlobalUtils.EMPTY_VALUE;
				this.map[i][j].contents = new Content[totalQRMarkers];
				
			}
			
		}
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
    
    public ArrayList<Bookshelf> getBookShelves() {
		return bookShelves;
	}
	public void setBookShelves(ArrayList<Bookshelf> bookShelves) {
		this.bookShelves = bookShelves;
	}
	public ArrayList<QRCodesMark> getQrCodesMarks() {
		return qrCodesMarks;
	}
	public void setQrCodesMarks(ArrayList<QRCodesMark> qrCodesMarks) {
		this.qrCodesMarks = qrCodesMarks;
	}
	public Node[][] getMap() {
		return map;
	}
	public void setMap(Node[][] map) {
		this.map = map;
	}
}