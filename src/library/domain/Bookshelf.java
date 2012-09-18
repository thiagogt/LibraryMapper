package library.domain;

public class Bookshelf {
    private Integer idBookshelf;

    private Integer idLibrary;

    private String codeId;
    int position_X;
	
   	int position_Y;

   	public int getPosition_X() {
   		return position_X;
   	}

   	public void setPosition_X(int position_X) {
   		this.position_X = position_X;
   	}

   	public int getPosition_Y() {
   		return position_Y;
   	}

   	public void setPosition_Y(int position_Y) {
   		this.position_Y = position_Y;
   	}


    public Integer getIdBookshelf() {
        return idBookshelf;
    }

    public void setIdBookshelf(Integer idBookshelf) {
        this.idBookshelf = idBookshelf;
    }

    public Integer getIdLibrary() {
        return idLibrary;
    }

    public void setIdLibrary(Integer idLibrary) {
        this.idLibrary = idLibrary;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }
}