package library.domain;


import library.mapper.NodeMapper;
import library.mapper.BookshelfMapper;

import library.utils.SQLFactory;

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
		shelf.setCodeId(node.getCodeIdShelf());
		shelf.setIdLibrary(node.getIdLibrary());
		return shelf;
	}
}