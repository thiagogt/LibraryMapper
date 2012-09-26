package library.domain;

import java.util.concurrent.Semaphore;

import library.mapper.NodeMapper;
import library.utils.GlobalUtils;
import library.utils.SQLFactory;

import org.apache.ibatis.session.SqlSession;

public class Node extends Thread{
    
	private Integer idNode;
    private Integer positionX;
    private Integer positionY;
    private Integer contentId;
    private Integer idLibrary;
    private String contentType;//Free,QrCode,Forbidden,Shelf
    private Node parentFromBeginNode;
    private Node parentFromEndNode;
	public int isInUse;
	private Semaphore semaphore;
	private String color;
	private int pathCost;

	public Node(){
		this.semaphore = new Semaphore(1);
		this.color = "WHITE";
		this.idLibrary = GlobalUtils.idLibrary;
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
		if(adjNode.getIsInUse() == 2){
			adjNode.semaphore.release();
			return true;
		}
		adjNode.semaphore.release();
		return false;
	}

	public Node IsUpBrotherReachable(Node principalNode) {
		return brotherReachable(principalNode,principalNode.getPositionX() -1,principalNode.getPositionY());
	}

	public Node IsLeftBrotherReachable(Node principalNode) {
		 return brotherReachable(principalNode,principalNode.getPositionX(),principalNode.getPositionY() -1);
	}
	public Node IsRightBrotherReachable(Node principalNode) {
		return brotherReachable(principalNode,principalNode.getPositionX(),principalNode.getPositionY()+1);
	}
	public Node IsDownBrotherReachable(Node principalNode) {
		return brotherReachable(principalNode,principalNode.getPositionX()+1,principalNode.getPositionY());
	}
	private Node brotherReachable(Node principalNode, Integer brotherPositionX, Integer brotherPositionY) {
		
		
		try {
			Node brotherNode  = getNodeByPosition(brotherPositionX,brotherPositionY);
			System.out.println(brotherNode.getContentType());
			if(brotherNode.getContentType().equals("Empty"))
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
			System.out.println("Nao existe");
			return null;
		}
	}

	public Semaphore getSemaphore() {
		return semaphore;
	}

	public void setSemaphore(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

	public static Node getNodeByPosition( int positionX, int positionY) {
		
		NodeMapper nodeMapper = SQLFactory.section.getMapper(NodeMapper.class);
		return nodeMapper.selectByPositionXAndY(GlobalUtils.idLibrary, positionX, positionY);
		
	}

	
}