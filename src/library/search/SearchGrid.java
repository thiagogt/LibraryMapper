package library.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;



import library.bean.SearchBean;
import library.domain.Library;
import library.domain.Node;
import library.utils.GlobalUtils;

public class SearchGrid extends Thread{
	
	private int nodeIndexX;
	private int nodeIndexY;
	private int finalIndexX;
	private int finalIndexY;
	private String fromWhoInicialNodeComes;
	private Queue<Node> queueSearch;
	private SearchBean searchBean;
	public MonitorSearch monitorSearch;
	public Node[][] library;
	
	
	public SearchGrid(Node[][] library2,MonitorSearch monitorSearch, String fromNode, int positionY, int posittionX, int finalPositionY, int finalPositionX, SearchBean searchBean2){
		this.setNodeIndexX(posittionX);
		this.setNodeIndexY(positionY);
		this.setFinalIndexX(finalPositionX);
		this.setFinalIndexY(finalPositionY);
		this.setFromWhoInicialNodeComes(fromNode);
		this.setQueueSearch(new LinkedList<Node>());
		this.searchBean = searchBean2;
		this.monitorSearch = monitorSearch;
		this.library = library2;
	}
	
	public SearchBean getSearchBean() {
		return searchBean;
	}

	public void setSearchBean(SearchBean searchBean) {
		this.searchBean = searchBean;
	}

	
	public int getFinalIndexX() {
		return finalIndexX;
	}

	public void setFinalIndexX(int finalIndexX) {
		this.finalIndexX = finalIndexX;
	}

	public int getFinalIndexY() {
		return finalIndexY;
	}

	public void setFinalIndexY(int finalIndexY) {
		this.finalIndexY = finalIndexY;
	}

	public String getFromWhoInicialNodeComes() {
		return fromWhoInicialNodeComes;
	}

	public void setFromWhoInicialNodeComes(String fromWhoInicialNodeComes) {
		this.fromWhoInicialNodeComes = fromWhoInicialNodeComes;
	}

	public int getNodeIndexX() {
		return nodeIndexX;
	}

	public void setNodeIndexX(int nodeIndexX) {
		this.nodeIndexX = nodeIndexX;
	}

	public int getNodeIndexY() {
		return nodeIndexY;
	}

	public void setNodeIndexY(int nodeIndexY) {
		this.nodeIndexY = nodeIndexY;
	}

	public Queue<Node> getQueueSearch() {
		return queueSearch;
	}

	public void setQueueSearch(Queue<Node> queueSearch) {
		this.queueSearch = queueSearch;
	}

	public  void BreadthFirstSearch( Node finalNode){
	
		while(!queueSearch.isEmpty() && monitorSearch.stopAllOtherTasks == false){
			try {
				Node principalNode = queueSearch.remove();
				
				Queue<Node> brothersPrincipalNode = findReachablesBrothers(principalNode);
				
				for (Node adjNode : brothersPrincipalNode) {
					whoIsYourDaddy(adjNode,principalNode);
					if( monitorSearch.stopAllOtherTasks == true){
						
						break;
					}
					//P(adjNode)
					
					adjNode.getSemaphore().acquire();
					if(adjNode.getWhoMarkedThisNode() != fromWhoInicialNodeComes)
						adjNode.isInUse++;
					if(adjNode.estaEmUsoParaleloAgora(adjNode)){//V(adjNode)
						Node middleNode = adjNode;
						if( monitorSearch.stopAllOtherTasks == true){
							break;
						}
						monitorSearch.stopAllOtherTasks = true;
						this.monitorSearch.middleNode = middleNode;
						break;
						
				
						
					}
					else{
						
						if(adjNode.getColor().equals("WHITE")){
							adjNode.setColor("GRAY");
							adjNode.setWhoMarkedThisNode(fromWhoInicialNodeComes);
							adjNode.setPathCost(principalNode.getPathCost()+1);
							if( monitorSearch.stopAllOtherTasks == true){
								break;
							}
							queueSearch.add(adjNode);
						}
						else{
							if(adjNode.getColor().equals("GRAY")){
								if(adjNode.getWhoMarkedThisNode() != fromWhoInicialNodeComes){
									Node middleNode = adjNode;
									if( monitorSearch.stopAllOtherTasks == true){
										break;
									}
									monitorSearch.stopAllOtherTasks = true;
									this.monitorSearch.middleNode = middleNode;
									break;
								}
							}
						}
					}
					principalNode.setColor("BLACK");
				}
			} catch (Exception e) {
				System.out.println("Erro na busca");
				e.printStackTrace();
				
			}
			
		}
		
	}



	public  void whoIsYourDaddy(Node adjNode, Node principalNode) {

		Node parentNode = library[principalNode.getPositionY()][principalNode.getPositionX()];
		if(this.fromWhoInicialNodeComes.equals("BEGIN")){
			library[adjNode.getPositionY()][adjNode.getPositionX()].setParentFromBeginNode(parentNode);
			
		}
		else{
			library[adjNode.getPositionY()][adjNode.getPositionX()].setParentFromEndNode(parentNode);
		}
	}

	public  Queue<Node> findReachablesBrothers(Node principalNode) {
		Queue<Node> reachablesBrothers = new LinkedList<Node>();
		Node upBrotherNode = principalNode.IsUpBrotherReachable(library,principalNode);
		Node leftBrotherNode = principalNode.IsLeftBrotherReachable(library,principalNode);
		Node rightBrotherNode = principalNode.IsRightBrotherReachable(library,principalNode);
		Node downBrotherNode = principalNode.IsDownBrotherReachable(library,principalNode);
		
		if(upBrotherNode!=null){
			 reachablesBrothers.add(upBrotherNode);
		 }
		if(leftBrotherNode!=null){
			reachablesBrothers.add(leftBrotherNode);
		}
		if(rightBrotherNode!=null){
			reachablesBrothers.add(rightBrotherNode);
		}	
		if(downBrotherNode!=null){
			reachablesBrothers.add(downBrotherNode);
		}
		
		return reachablesBrothers;
	}
	
	public void run(){
		Node firstNode = new Node();
		firstNode = library[this.nodeIndexY][this.nodeIndexX];
		queueSearch.add(firstNode);
		monitorSearch.stopAllOtherTasks  = false;
		this.BreadthFirstSearch(firstNode);
		monitorSearch.stopAllOtherTasks = true;	
		
	}
}

