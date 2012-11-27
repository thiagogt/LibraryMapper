package library.domain;

import java.util.concurrent.Semaphore;

import library.mapper.LibraryMapper;
import library.mapper.NodeMapper;
import library.utils.GlobalUtils;
import library.utils.SQLFactory;

import org.apache.ibatis.session.SqlSession;

public class Node extends Thread implements Cloneable{
    
	private Integer idNode;
    private Integer positionX;
    private Integer positionY;
    private Integer contentId;
    private Integer idLibrary;
    private String codeIdInitialShelf;
    private String codeIdFinalShelf;
	private String contentType;//Free,QrCode,Forbidden,Shelf
    private Node parentFromBeginNode;
    private Node parentFromEndNode;
	public int isInUse;
	private Semaphore semaphore;
	private String color;
	private String whoMarkedThisNode;
	public String getWhoMarkedThisNode() {
		return whoMarkedThisNode;
	}
	@Override
	protected Node clone() throws CloneNotSupportedException {
		
		Node node = (Node) super.clone();
		node.idLibrary = this.idLibrary;
		node.positionX = this.positionX;
		node.positionY = this.positionY;
		node.codeIdFinalShelf = this.codeIdFinalShelf;
		node.codeIdInitialShelf = this.codeIdInitialShelf;
		node.contentId = this.contentId;
		node.contentType = this.contentType;
		node.idNode = this.idNode;
		node.parentFromBeginNode = this.parentFromBeginNode;
		node.parentFromEndNode = this.parentFromEndNode;
		node.isInUse = this.isInUse;
		node.whoMarkedThisNode = this.whoMarkedThisNode;
		return node;
	}

	public void setWhoMarkedThisNode(String whoMarkedThisNode) {
		this.whoMarkedThisNode = whoMarkedThisNode;
	}

	private int pathCost;

	public Node(){
		this.semaphore = new Semaphore(1);
		this.color = "WHITE";
		this.idLibrary = GlobalUtils.idLibrary;
	}

   	public String getCodeIdFinalShelf() {
		return codeIdFinalShelf;
	}

	public void setCodeIdFinalShelf(String codeIdFinalShelf) {
		this.codeIdFinalShelf = codeIdFinalShelf;
	}

	public int getPathCost() {
		return pathCost;
	}

	public void setPathCost(int pathCost) {
		this.pathCost = pathCost;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	 public String getCodeIdInitialShelf() {
		return codeIdInitialShelf;
	}

	public void setCodeIdInitialShelf(String codeIdInitialShelf) {
		this.codeIdInitialShelf = codeIdInitialShelf;
	}
 
    public int getIsInUse() {
		return isInUse;
	}

	public void setIsInUse(int isInUse) {
		this.isInUse = isInUse;
	}

	public Node getParentFromBeginNode() {
		return parentFromBeginNode;
	}

	public void setParentFromBeginNode(Node parentFromBeginNode) {
		this.parentFromBeginNode = parentFromBeginNode;
	}

	public Node getParentFromEndNode() {
		return parentFromEndNode;
	}

	public void setParentFromEndNode(Node parentFromEndNode) {
		this.parentFromEndNode = parentFromEndNode;
	}

	
    

    public Integer getIdNode() {
        return idNode;
    }

    public void setIdNode(Integer idNode) {
        this.idNode = idNode;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }

    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getIdLibrary() {
        return idLibrary;
    }

    public void setIdLibrary(Integer idLibrary) {
        this.idLibrary = idLibrary;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

	public boolean estaEmUsoParaleloAgora(Node adjNode) {
		boolean returnUse = false;
		if(adjNode.getIsInUse() >= 2){
			
			returnUse =  true;
		}
		adjNode.semaphore.release();
		return returnUse;
	}

	public Node IsUpBrotherReachable(Node[][] library,Node principalNode) {
		return brotherReachable(library,principalNode,principalNode.getPositionY() -1,principalNode.getPositionX());
	}

	public Node IsLeftBrotherReachable(Node[][] library,Node principalNode) {
		 return brotherReachable(library,principalNode,principalNode.getPositionY(),principalNode.getPositionX() -1);
	}
	public Node IsRightBrotherReachable(Node[][] library,Node principalNode) {
		return brotherReachable(library,principalNode,principalNode.getPositionY(),principalNode.getPositionX()+1);
	}
	public Node IsDownBrotherReachable(Node[][] library,Node principalNode) {
		return brotherReachable(library,principalNode,principalNode.getPositionY()+1,principalNode.getPositionX());
	}
	private Node brotherReachable(Node[][] library,Node principalNode, Integer brotherPositionY, Integer brotherPositionX) {
		
		
		try {
			Node brotherNode  = getNodeByPosition(library,brotherPositionY,brotherPositionX);
			
			if(brotherNode.getContentType().equals("Free"))
				try{
					if(brotherNode.getColor().equals("BLACK"))
						brotherNode = null;
					return brotherNode;
				}	
				catch (Exception e) {
					System.out.println("Ainda nao tem cor");
					return brotherNode;
				}
		
		
			else
				return null;
				
			
				
		} catch (Exception e) {
//			System.out.println("Nao existe");
			return null;
		}
	}

	public Semaphore getSemaphore() {
		return semaphore;
	}

	public void setSemaphore(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

	public static Node getNodeByPosition(Node[][] library, int positionY, int positionX) {
		Node node;
		if(library == null){
			NodeMapper nodeMapper = SQLFactory.section.getMapper(NodeMapper.class);
			 node = new Node();
			node = nodeMapper.selectByPositionXAndY(GlobalUtils.idLibrary, positionY, positionX);
		}else
			node = library[positionY][positionX];
	return node;
		
	}
	public static void deleteAllNodesFromLibrary(int idLibrary) {
		
		NodeMapper nodeMapper = SQLFactory.section.getMapper(NodeMapper.class);
		nodeMapper.deleteByLibraryId(idLibrary);
		
		
	}
	
	public static void insertNodesToBD(Node node) {
		
		NodeMapper nodeMapper = SQLFactory.section.getMapper(NodeMapper.class);
		nodeMapper.insert(node);
		SQLFactory.section.commit();
		
	}

	public static int returnTheLastNodeID() {
		int lastNodeId =-1; 
		NodeMapper nodeMapper = SQLFactory.section.getMapper(NodeMapper.class);
		lastNodeId = nodeMapper.selectBiggestId();
		
		return lastNodeId;
	}
	
	public static void setNewLibraryId(){
		GlobalUtils.idLibrary = returnTheLastNodeID() +1;
	}

	public void getBookShelfNodeFromBD(Bookshelf bookshelf) {
		NodeMapper nodeMapper = SQLFactory.section.getMapper(NodeMapper.class);
		
		System.out.println(bookshelf.getIdBookshelf());
		Node node = nodeMapper.selectNodeByContentIdAndType(GlobalUtils.idLibrary,bookshelf.getIdBookshelf(),"Shelf");
		System.out.println("Sao as posicoes yx da estante: "+node.getPositionY()+","+node.getPositionX());
		setPositionX(node.getPositionX());
		setPositionY(node.getPositionY());
	}
	

	
}