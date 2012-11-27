package library.domain;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import library.domain.Node;
import library.mapper.LibraryMapper;
import library.utils.GlobalUtils;
import library.utils.SQLFactory;

public class Library {
    public static Integer idLibrary;

    public  Integer sizeX;
    public  Integer sizeY;

    public  ArrayList<Bookshelf> bookShelves;
    public  ArrayList<QrCodeMark> qrCodesMarks;
    private static Log log = LogFactory.getLog(Library.class);
    public volatile Node[][] map;
	
    public Library(){
    	idLibrary = GlobalUtils.idLibrary;
    	
    	if(GlobalUtils.mapLibrary == null){
			Mapping(GlobalUtils.LIBRARY_WIDTH, GlobalUtils.LIBRARY_HEIGHT);
			GlobalUtils.mapLibrary = new Node[GlobalUtils.LIBRARY_HEIGHT][GlobalUtils.LIBRARY_WIDTH];
			System.out.println("Pronto!");
			for (int i = 0; i < GlobalUtils.LIBRARY_HEIGHT; i++){
				for (int j = 0; j < GlobalUtils.LIBRARY_WIDTH; j++){
					try {
						GlobalUtils.mapLibrary[i][j] = map[i][j].clone();
					} catch (CloneNotSupportedException e) {
						log.error("Nao foi possivel clonar elemento ",e);
						e.printStackTrace();
					}
				}
    		}
			
    	}
    	else{
    		map = new Node[GlobalUtils.LIBRARY_HEIGHT][GlobalUtils.LIBRARY_WIDTH];
			for (int i = 0; i < GlobalUtils.LIBRARY_HEIGHT; i++){
				for (int j = 0; j < GlobalUtils.LIBRARY_WIDTH; j++){
					try {
						map[i][j] = GlobalUtils.mapLibrary[i][j].clone();
					} catch (CloneNotSupportedException e) {
						log.error("Nao foi possivel clonar elemento ",e);
						e.printStackTrace();
					}
				}
    		}
	    }
    }
    
    public Library(Integer idLibrary2, int lIBRARY_WIDTH, int lIBRARY_HEIGHT) {
		idLibrary = idLibrary2;
		sizeX = lIBRARY_WIDTH;
		sizeY = lIBRARY_HEIGHT;
	}

	
    
	public  void Mapping(int newSize_X, int newSize_Y){
		
		sizeX = newSize_X;
		sizeY = newSize_Y;
		map = new Node[sizeY][sizeX];
		System.out.println("Iniciando a leitura do banco de dados..");
		for (int i = 0; i < sizeY; i++) {
			for (int j = 0; j < sizeX; j++) {
				
				Node node = Node.getNodeByPosition(null,i,j);
				if(node!=null)
					map[i][j] = node;
				else
					map[i][j].setContentType("Forbidden");
			}
			if(i == sizeY/2){
				System.out.print("..");
			}
		}
	}
	public static void deleteAllLibrary(int idLibrary2) {
		
		
		Node.deleteAllNodesFromLibrary(idLibrary2);
		QrCodeMark.deleteAllQrCodesFromLibrary(idLibrary2);
		Bookshelf bookshelf = new Bookshelf();
		bookshelf.deleteAllShelvesFromLibrary(idLibrary2);
		deleteLibrary(idLibrary2);
		
		SQLFactory.section.commit();
		
	}
	
	public static void insertNodesToLibrary(Node node) {
		Bookshelf bookshelf = new Bookshelf();
		
		Node.insertNodesToBD(node);
		if (node.getContentType().equals("Shelf")) {
			bookshelf.insertShelfToBD(node);
			
		}
		if (node.getContentType().equals("QrCode")) {
			QrCodeMark.insertQrCodeToBD(node);
			
		}
	}
	private static void insertLibraryToBD(Integer idLibrary2) {
		Library library = new Library(idLibrary2,GlobalUtils.MAP_HTML_COLUMNS,GlobalUtils.MAP_HTML_ROWS);
		LibraryMapper libraryMapper = SQLFactory.section.getMapper(LibraryMapper.class);
		libraryMapper.insert(library);
		
	}
	public static int returnTheLastLibraryID() {
		int lastIdLibrary =-1; 
		LibraryMapper libraryMapper = SQLFactory.section.getMapper(LibraryMapper.class);
		lastIdLibrary = libraryMapper.selectBiggestId();
		
		return lastIdLibrary;
	}
	
	public static void setNewLibrary(){
		GlobalUtils.idLibrary = returnTheLastLibraryID() +1;
		insertLibraryToBD(GlobalUtils.idLibrary); 
	}
	
	public static void deleteLibrary(int idLibrary2){
		LibraryMapper library = SQLFactory.section.getMapper(LibraryMapper.class);
		library.deleteByPrimaryKey(idLibrary2);
		
	}
 }