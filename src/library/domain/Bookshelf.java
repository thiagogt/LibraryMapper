package library.domain;

public class Bookshelf {
    private Integer idBookshelf;

    private Integer idLibrary;

    private String codeId;

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