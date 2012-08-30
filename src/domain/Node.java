package domain;



public class Node {
	int mapValue;
	int[] wasVisitedFromQR;
	Content[] contents;
	
	public int getMapValue() {
		return mapValue;
	}
	public void setMapValue(int mapValue) {
		this.mapValue = mapValue;
	}
	public int[] getWasVisitedFromQR() {
		return wasVisitedFromQR;
	}
	public void setWasVisitedFromQR(int[] wasVisitedFromQR) {
		this.wasVisitedFromQR = wasVisitedFromQR;
	}
	public Content[] getContents() {
		return contents;
	}
	public void setContents(Content[] contents) {
		this.contents = contents;
	}
	
	
}
