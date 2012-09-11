package library.domain;

public class QrCodeMark {
    private Integer idQrMark;

    private Integer idLibrary;

    private String url;

    public Integer getIdQrMark() {
        return idQrMark;
    }

    public void setIdQrMark(Integer idQrMark) {
        this.idQrMark = idQrMark;
    }

    public Integer getIdLibrary() {
        return idLibrary;
    }

    public void setIdLibrary(Integer idLibrary) {
        this.idLibrary = idLibrary;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}