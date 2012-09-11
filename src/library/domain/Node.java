package library.domain;

public class Node {
    private Integer idNode;

    private Integer positionX;

    private Integer positionY;

    private Integer contentId;

    private Integer idLibrary;

    private String contentType;
    
    int mapValue;

    public int getMapValue() {
		return mapValue;
	}

	public void setMapValue(int mapValue) {
		this.mapValue = mapValue;
	}

	public Integer getIdNode() {
        return idNode;
    }

    public void setIdNode(Integer idNode) {
        this.idNode = idNode;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }

    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getIdLibrary() {
        return idLibrary;
    }

    public void setIdLibrary(Integer idLibrary) {
        this.idLibrary = idLibrary;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}