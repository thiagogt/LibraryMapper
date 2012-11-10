package library.domain;

import java.util.ArrayList;

import library.mapper.LibraryMapper;
import library.utils.GlobalUtils;
import library.utils.SQLFactory;

public class Library {
    public static Integer idLibrary;

    public  Integer sizeX;
    public  Integer sizeY;

    public  ArrayList<Bookshelf> bookShelves;
    public  ArrayList<QrCodeMark> qrCodesMarks;
	
    public  Node[][] map;
	
    public Library(){
    	idLibrary = GlobalUtils.idLibrary;
    	
    	if(GlobalUtils.mapLibrary == null){
			Mapping(GlobalUtils.LIBRARY_WIDTH, GlobalUtils.LIBRARY_HEIGHT);
			GlobalUtils.mapLibrary = map;
    	}
    	else{
    		map = new Node[GlobalUtils.LIBRARY_HEIGHT][];
			for (int i = 0; i < GlobalUtils.LIBRARY_HEIGHT; i++)
				map[i] = GlobalUtils.mapLibrary[i].clone();
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
		
		for (int i = 0; i < sizeY; i++) {
			for (int j = 0; j < sizeX; j++) {
				
				Node node = Node.getNodeByPosition(i,j);
				if(node!=null)
					map[i][j] = node;
				else
					map[i][j].setContentType("Forbidden");
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