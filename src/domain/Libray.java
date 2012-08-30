package domain;

import java.util.ArrayList;

import utils.GlobalUtils;

public class Libray {
	int size_X;
	int size_Y;
	ArrayList<BookShelf> bookShelves;
	ArrayList<QRCodesMark> qrCodesMarks;
	Node[][] map;
	
	public void Library(int newSize_X, int newSize_Y,int totalQRMarkers){
		setSize_X(newSize_X);
		setSize_Y(newSize_Y);
		setMap(new Node[this.size_X][this.size_Y]);
		
		for (int i = 0; i < this.size_X; i++) {
			for (int j = 0; j < this.size_Y; j++) {
				this.map[i][j].mapValue = GlobalUtils.EMPTY_VALUE;
				this.map[i][j].contents = new Content[totalQRMarkers];
				
			}
			
		}
	}
	
	public int getSize_X() {
		return size_X;
	}
	public void setSize_X(int size_X) {
		this.size_X = size_X;
	}
	public int getSize_Y() {
		return size_Y;
	}
	public void setSize_Y(int size_Y) {
		this.size_Y = size_Y;
	}
	public ArrayList<BookShelf> getBookShelves() {
		return bookShelves;
	}
	public void setBookShelves(ArrayList<BookShelf> bookShelves) {
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
