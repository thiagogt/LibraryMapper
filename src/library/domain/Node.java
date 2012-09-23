package library.domain;

import library.mapper.NodeMapper;
import library.utils.SQLFactory;

import org.apache.ibatis.session.SqlSession;

public class Node {
    private Integer idNode;

    private Integer positionX;

    private Integer positionY;

    private Integer contentId;

    private Integer idLibrary;

    private String contentType;//Free,QrCode,Forbidden,Shelf
    
    private Node parentFromBeginNode;
    
    private Node parentFromEndNode;

	public int isInUse;
	
	private String color;
	
	private int pathCost;
	
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

	public boolean estaEmUsoParaleloAgora() {
		// TODO Auto-generated method stub
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
		
		NodeMapper bookshelf = SQLFactory.section.getMapper(NodeMapper.class);
		try {
			Node brotherNode = new Node();
			brotherNode = bookshelf.selectByPositionXAndY(1,brotherPositionX,brotherPositionY);
			System.out.println(brotherNode.getContentType());
			if(brotherNode.getContentType().equals("Empty"))
				return brotherNode;
			else
				return null;
				
		} catch (Exception e) {
			System.out.println("Nao ha esse irmao" + e);
			return null;
		}
	}

	
}