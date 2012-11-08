package library.domain;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import library.mapper.LibraryMapper;
import library.mapper.NodeMapper;
import library.mapper.BookshelfMapper;

import library.utils.GlobalUtils;
import library.utils.SQLFactory;

	public class Bookshelf {
    private Integer idBookshelf;

    private Integer idLibrary;

    private int codeIdInitial;
    private int codeIdFinal;
    private String prefixCodeIdInitial;
    private String prefixCodeIdFinal;
    private String versionCodeIdInitial;
    private String versionCodeIdFinal;
    
	
	int position_X;
	int position_Y;
	public String getPrefixCodeIdInitial() {
		return prefixCodeIdInitial;
	}

	public void setPrefixCodeIdInitial(String prefixCodeIdInitial) {
		this.prefixCodeIdInitial = prefixCodeIdInitial;
	}

	public String getPrefixCodeIdFinal() {
		return prefixCodeIdFinal;
	}

	public void setPrefixCodeIdFinal(String prefixCodeIdFinal) {
		this.prefixCodeIdFinal = prefixCodeIdFinal;
	}

	public String getVersionCodeIdInitial() {
		return versionCodeIdInitial;
	}

	public void setVersionCodeIdInitial(String versionCodeIdInitial) {
		this.versionCodeIdInitial = versionCodeIdInitial;
	}

	public String getVersionCodeIdFinal() {
		return versionCodeIdFinal;
	}

	public void setVersionCodeIdFinal(String versionCodeIdFinal) {
		this.versionCodeIdFinal = versionCodeIdFinal;
	}

   	public int getPosition_X() {
   		return position_X;
   	}

   	public void setPosition_X(int position_X) {
   		this.position_X = position_X;
   	}

    public int getCodeIdFinal() {
		return codeIdFinal;
	}

	public void setCodeIdFinal(int codeIdFinal) {
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

    public int getCodeIdInitial() {
        return codeIdInitial;
    }

    public void setCodeIdInitial(int codeIdInitial) {
        this.codeIdInitial = codeIdInitial;
    }

	public  void deleteAllShelvesFromLibrary(int idLibrary) {
		BookshelfMapper shelfMapper = SQLFactory.section.getMapper(BookshelfMapper.class);
		shelfMapper.deleteByLibraryId(idLibrary);
			
		
	}

	public  void insertShelfToBD(Node node) {
		Bookshelf shelf = changeNodeToBookShelf(node);
		
		BookshelfMapper shelfMapper = SQLFactory.section.getMapper(BookshelfMapper.class);
		shelfMapper.insert(shelf);
		SQLFactory.section.commit();
		
	}

	private  Bookshelf changeNodeToBookShelf(Node node) {
		
		setIdBookshelf(node.getContentId());
		setIdLibrary(node.getIdLibrary());
		createInitialShelfIndetification(node.getCodeIdInitialShelf());
		createFinalShelfIndetification(node.getCodeIdFinalShelf());

		
		return this;
	}
	

	

	public  void createInitialShelfIndetification(
			String codeIdInitialShelf) {
		
		int sizeOfcodeId = foundDivisionBetweenPrefixAndVersionId(codeIdInitialShelf);
		
		String prefixCodeId = codeIdInitialShelf.substring(0, sizeOfcodeId);
		Integer numberPosition = returnNumberPosition(prefixCodeId) ;
		setCodeIdInitial(cacthVersionId(prefixCodeId));
		setPrefixCodeIdInitial(prefixCodeId.substring(0, numberPosition));
		
		String versionCodeId = codeIdInitialShelf.substring(sizeOfcodeId+1, codeIdInitialShelf.length());
		setVersionCodeIdInitial(versionCodeId);
		
	}
	private Integer returnNumberPosition(String prefixCodeId) {
		for (int i = 0; i < prefixCodeId.length(); i++) {
			Character character = prefixCodeId.charAt(i);
			if(Character.isDigit(character)){
				
				
				return i;
			}	
		}
		return -1;
	}

	public  void createFinalShelfIndetification(String codeIdFinalShelf) {
		int sizeOfcodeId = foundDivisionBetweenPrefixAndVersionId(codeIdFinalShelf);
		
		String prefixCodeId = codeIdFinalShelf.substring(0, sizeOfcodeId);
		Integer numberPosition = returnNumberPosition(prefixCodeId) ;
		setCodeIdFinal(cacthVersionId(prefixCodeId));
		setPrefixCodeIdFinal(prefixCodeId.substring(0, numberPosition));
		
		String versionCodeId = codeIdFinalShelf.substring(sizeOfcodeId+1, codeIdFinalShelf.length());
		setVersionCodeIdFinal(versionCodeId);
		
	}
	public  int foundDivisionBetweenPrefixAndVersionId(String xml){
		int firstSpace = xml.indexOf(' ');
		int endSpace;
		int firstDigit = -1;
		if(firstSpace>0){
			
			
			for (int i = 0; i < xml.length(); i++) {
				Character character = xml.charAt(i);
				if(Character.isDigit(character)){
					firstDigit = i;
					
					break;
				}	
			}
			if(firstDigit > firstSpace){
				endSpace = xml.indexOf(' ', firstSpace+1);
				
				
			}
			else{
				endSpace = firstSpace;
							
				
			}
		}
		else
			endSpace = firstSpace;
		
		
		return endSpace;
	}
	public  int cacthVersionId(String codeIdShelf) {
		Scanner in = new Scanner(codeIdShelf).useDelimiter("[^0-9]+");
		
		return in.nextInt();
	}

	
	public  int returnTheLastBookShelfID() {
		int lastBookShelfId =-1; 
		BookshelfMapper bookshelfMapper = SQLFactory.section.getMapper(BookshelfMapper.class);
		lastBookShelfId = bookshelfMapper.selectBiggestId();
		
		return lastBookShelfId;
	}
	
	public  void setNewLibraryId(){
		GlobalUtils.idLibrary = returnTheLastBookShelfID() +1;
	}

	public ArrayList<Node> returnNodeOfShelf() {
		ArrayList<Node> nodeList = new ArrayList<Node>();
		BookshelfMapper bookshelfMapper = SQLFactory.section.getMapper(BookshelfMapper.class);
		int cont = 1;
		List<Bookshelf> shelfList = bookshelfMapper.selectByCodeIdShelf(this);
		Node node = new Node();
		node.getBookShelfNodeFromBD(shelfList.get(0));
		Bookshelf lastShelf = shelfList.get(0);
		nodeList.add(node);
		for (Bookshelf bookshelf : shelfList) {
			if(!theyAreTheSameShelf(lastShelf,bookshelf)){
				node = new Node();
				node.getBookShelfNodeFromBD(shelfList.get(cont));
				lastShelf = shelfList.get(cont);
				nodeList.add(node);
				cont++;
			}
		}
		
		return nodeList;
	}

	private boolean theyAreTheSameShelf(Bookshelf lastShelf, Bookshelf bookshelf) {
		if(bookshelf.getCodeIdFinal() == lastShelf.getCodeIdFinal()){
			if(bookshelf.getCodeIdInitial() == lastShelf.getCodeIdInitial())
				if(bookshelf.getPrefixCodeIdInitial() == lastShelf.getPrefixCodeIdInitial())
					if(bookshelf.getPrefixCodeIdFinal() == lastShelf.getPrefixCodeIdFinal())
						return true;
		}
		return false;
	}
}