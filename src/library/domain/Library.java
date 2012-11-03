package library.domain;

import java.util.ArrayList;

import library.mapper.LibraryMapper;
import library.utils.GlobalUtils;
import library.utils.SQLFactory;

public class Library {
    public static Integer idLibrary;

    public static Integer sizeX;
    public static Integer sizeY;

    public static ArrayList<Bookshelf> bookShelves;
    public static ArrayList<QrCodeMark> qrCodesMarks;
	
    public static Node[][] map;
	
	public static void Mapping(int newSize_X, int newSize_Y){
		
		sizeX = newSize_X;
		sizeY = newSize_Y;
		map = new Node[sizeX][sizeY];
		
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
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
		Bookshelf.deleteAllShelvesFromLibrary(idLibrary2);
		deleteLibrary(idLibrary2);
		
		SQLFactory.section.commit();
		
	}
	public static void insertNodesToLibrary(Node node) {
		node.setIdLibrary(idLibrary);
		Node.insertNodesToBD(node);
		if (node.getContentType().equals("Shelf")) {
			Bookshelf.insertShelfToBD(node);
			
		}
		if (node.getContentType().equals("QrCode")) {
			QrCodeMark.insertQrCodeToBD(node);
			
		}
	}
	public static int returnTheLastLibraryID() {
		int lastIdLibrary =-1; 
		LibraryMapper libraryMapper = SQLFactory.section.getMapper(LibraryMapper.class);
		lastIdLibrary = libraryMapper.selectBiggestId();
		
		return lastIdLibrary;
	}
	
	public static void setLatestLibraryId(){
		GlobalUtils.idLibrary = returnTheLastLibraryID() +1;
	}
	
	public static void deleteLibrary(int idLibrary2){
		LibraryMapper library = SQLFactory.section.getMapper(LibraryMapper.class);
		library.deleteByPrimaryKey(idLibrary2);
		SQLFactory.section.commit();
	}
 }