package library.domain;


import library.mapper.LibraryMapper;
import library.mapper.NodeMapper;
import library.mapper.BookshelfMapper;

import library.utils.GlobalUtils;
import library.utils.SQLFactory;

public class Bookshelf {
    private Integer idBookshelf;

    private Integer idLibrary;

    private String codeIdInitial;
    private String codeIdFinal;
	int position_X;
	
   	int position_Y;

   	public int getPosition_X() {
   		return position_X;
   	}

   	public void setPosition_X(int position_X) {
   		this.position_X = position_X;
   	}

    public String getCodeIdFinal() {
		return codeIdFinal;
	}

	public void setCodeIdFinal(String codeIdFinal) {
		this.codeIdFinal = codeIdFinal;
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

    public String getCodeIdInitial() {
        return codeIdInitial;
    }

    public void setCodeIdInitial(String codeIdInitial) {
        this.codeIdInitial = codeIdInitial;
    }

	public static void deleteAllShelvesFromLibrary(int idLibrary) {
		BookshelfMapper shelfMapper = SQLFactory.section.getMapper(BookshelfMapper.class);
		shelfMapper.deleteByLibraryId(idLibrary);
			
		
	}

	public static void insertShelfToBD(Node node) {
		Bookshelf shelf = changeNodeToBookShelf(node);
		
		BookshelfMapper shelfMapper = SQLFactory.section.getMapper(BookshelfMapper.class);
		shelfMapper.insert(shelf);
		SQLFactory.section.commit();
		
	}

	private static Bookshelf changeNodeToBookShelf(Node node) {
		Bookshelf shelf = new Bookshelf();
		shelf.setIdBookshelf(node.getContentId());
		shelf.setCodeIdInitial(node.getCodeIdInitialShelf());
		shelf.setCodeIdFinal(node.getCodeIdFinalShelf());
		shelf.setIdLibrary(node.getIdLibrary());
		return shelf;
	}
	public static int returnTheLastBookShelfID() {
		int lastBookShelfId =-1; 
		BookshelfMapper libraryMapper = SQLFactory.section.getMapper(BookshelfMapper.class);
		lastBookShelfId = libraryMapper.selectBiggestId();
		
		return lastBookShelfId;
	}
	
	public static void setNewLibraryId(){
		GlobalUtils.idLibrary = returnTheLastBookShelfID() +1;
	}
}