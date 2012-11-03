package library.domain;



import library.mapper.BookshelfMapper;
import library.mapper.QrCodeMarkMapper;
import library.utils.GlobalUtils;
import library.utils.SQLFactory;

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

	public static void deleteAllQrCodesFromLibrary(int idLibrary) {
					
			QrCodeMarkMapper qrCodeMapper = SQLFactory.section.getMapper(QrCodeMarkMapper.class);
			qrCodeMapper.deleteByLibraryId(idLibrary);
			
			
		
	}

	public static void insertQrCodeToBD(Node node) {
		QrCodeMark qrCode = changeNodeToQrCodeMark(node);
		
		QrCodeMarkMapper qrCodeMapper = SQLFactory.section.getMapper(QrCodeMarkMapper.class);
		qrCodeMapper.insert(qrCode);
		SQLFactory.section.commit();
		
	}

	private static QrCodeMark changeNodeToQrCodeMark(Node node) {
		QrCodeMark qrCode = new QrCodeMark();
		qrCode.setIdQrMark(node.getContentId());
		qrCode.setIdLibrary(node.getIdLibrary());	
		//como no maximo o vcalor de x tem 3 casas, e o de y tem 2, posso agrupar ambos os numeros em um numero de 5 digitos no maximo 
 		//ou 3 no minimo, onde sempre o x comecara na 3 casa.
 		int idQrMark = GlobalUtils.CASA_DE_GRANDEZA_X_QRCODE*node.getPositionX() + node.getPositionY();
 		qrCode.setIdQrMark(idQrMark);
 		String paramID = "?valueID="+idQrMark;
 		qrCode.setUrl(GlobalUtils.DOMAIN_NAME+GlobalUtils.PATH_BOOKSEARCH+paramID);
		return qrCode;
	}

	public static int returnTheLastQrNodeID() {
		int lastQrCodeId =-1; 
		QrCodeMarkMapper qrCodeMapper = SQLFactory.section.getMapper(QrCodeMarkMapper.class);
		lastQrCodeId = qrCodeMapper.selectBiggestId();
		
		return lastQrCodeId;
	}
	
	public static void setNewLibraryId(){
		GlobalUtils.idLibrary = returnTheLastQrNodeID() +1;
	}

}