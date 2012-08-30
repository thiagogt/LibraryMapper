package domain;

public class Content {
	Node parent;
	int qrId;
	int pathValue;
	
	
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public int getQrId() {
		return qrId;
	}
	public void setQrId(int qrId) {
		this.qrId = qrId;
	}
	public int getPathValue() {
		return pathValue;
	}
	public void setPathValue(int pathValue) {
		this.pathValue = pathValue;
	}
}
