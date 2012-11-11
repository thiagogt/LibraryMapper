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
	
	public Bookshelf(){
		this.idLibrary = GlobalUtils.idLibrary;
	}
	
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
		String versionCodeId = null;
		
		//IMPORTANTE: caso nao haja versao, ou seja, vem apenas a identificacao sem espaco depois(EX: QA1**QA999) Ele retorna -1.
		//DEVE-SE LEMBRAR O USUARIO DE COLOCAR A VERSAO DO LIVRO OU ESTANTE NA IDENTIFICACAO
		int sizeOfcodeId = foundDivisionBetweenPrefixAndVersionId(codeIdInitialShelf);
		
		String prefixCodeId = codeIdInitialShelf.substring(0, sizeOfcodeId);
		Integer numberPosition = returnNumberPosition(prefixCodeId) ;
		setCodeIdInitial(cacthVersionId(prefixCodeId));
		setPrefixCodeIdInitial(prefixCodeId.substring(0, numberPosition));
		
		//para o caso de nao haver versao do livro
		if(sizeOfcodeId < codeIdInitialShelf.length())
			versionCodeId = codeIdInitialShelf.substring(sizeOfcodeId+1, codeIdInitialShelf.length());
		setVersionCodeIdInitial(versionCodeId);
		verifySpecialCases();
		
		
		
	}
	private void verifySpecialCases() {
		if(prefixCodeIdInitial.contains("IME-RAE-CEA")){
			setPrefixCodeIdInitial("IME-RAE-CEA");
			setPrefixCodeIdFinal("IME-RAE-CEA");
			setCodeIdInitial(0);
			setCodeIdFinal(0);
		}

		if(prefixCodeIdInitial.contains("BBC")){
			setPrefixCodeIdInitial("BBC");
			setPrefixCodeIdFinal("BBC");
			setCodeIdInitial(0);
			setCodeIdFinal(0);
		}
		if(prefixCodeIdInitial.contains("BDBH")){
			setPrefixCodeIdInitial("BDBH");
			setPrefixCodeIdFinal("BDBH");
			setCodeIdInitial(0);
			setCodeIdFinal(0);
		}

		if(prefixCodeIdInitial.contains("REF.")){
			setPrefixCodeIdInitial("REF.");
			setPrefixCodeIdFinal("REF.");
			setCodeIdInitial(0);
			setCodeIdFinal(0);
		}

		if(prefixCodeIdInitial.contains("OB/ESP") || prefixCodeIdInitial.contains("RARE")){
			setPrefixCodeIdInitial("OB/ESP");
			setPrefixCodeIdFinal("OB/ESP");
			setCodeIdInitial(0);
			setCodeIdFinal(0);
		}
		
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
		String versionCodeId = null;
		
		String prefixCodeId = codeIdFinalShelf.substring(0, sizeOfcodeId);
		Integer numberPosition = returnNumberPosition(prefixCodeId) ;
		setCodeIdFinal(cacthVersionId(prefixCodeId));
		setPrefixCodeIdFinal(prefixCodeId.substring(0, numberPosition));
		//para o caso de nao haver versao do livro
		if(sizeOfcodeId < codeIdFinalShelf.length())
			versionCodeId = codeIdFinalShelf.substring(sizeOfcodeId+1, codeIdFinalShelf.length());
		setVersionCodeIdFinal(versionCodeId);
		verifySpecialCases();
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
				System.out.println("aquiii");
				endSpace = firstSpace;
							
				
			}
		}
		else
			endSpace = xml.length();
		
		System.out.println("esse eh o size da separacao: "+endSpace);
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
		System.out.println("Esse eh o prefixo: "+getPrefixCodeIdInitial());
		System.out.println("Esse eh code id: "+getCodeIdInitial());
		System.out.println("Essa eh a versao: "+getVersionCodeIdInitial());
		System.out.println("Esse eh o prefixo Final: "+getPrefixCodeIdFinal());
		System.out.println("Esse eh code id Final: "+getCodeIdFinal());
		System.out.println("Essa eh a versao Final: "+getVersionCodeIdFinal());
		
		ArrayList<Node> nodeList = new ArrayList<Node>();
		BookshelfMapper bookshelfMapper = SQLFactory.section.getMapper(BookshelfMapper.class);
		
		List<Bookshelf> shelfList = bookshelfMapper.selectByCodeIdShelf(this);
		System.out.println("Esse eh o tamanho da lista shelf: "+shelfList.size());
		Node node;
		
		for (Bookshelf bookshelf2 : shelfList) {
			
			node = new Node();
			node.getBookShelfNodeFromBD(bookshelf2);
			node.setContentType("SelectedShelf");
			nodeList.add(node);
			
		}
		
		return nodeList;
	}

	private boolean theyAreTheSameShelf(Bookshelf lastShelf, Bookshelf bookshelf) {
		if(bookshelf.getCodeIdFinal() == lastShelf.getCodeIdFinal()){
			if(bookshelf.getCodeIdInitial() == lastShelf.getCodeIdInitial())
				if(bookshelf.getPrefixCodeIdInitial().equals(lastShelf.getPrefixCodeIdInitial()))
					if(bookshelf.getPrefixCodeIdFinal().equals(lastShelf.getPrefixCodeIdFinal()))
						if(bookshelf.getVersionCodeIdFinal().equals(lastShelf.getVersionCodeIdFinal()))
							if(bookshelf.getVersionCodeIdInitial().equals(lastShelf.getVersionCodeIdInitial()))
								return true;
		}
		return false;
	}
}