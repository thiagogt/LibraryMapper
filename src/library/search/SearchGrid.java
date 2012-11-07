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
	
	public SearchGrid(String fromNode, int positionY, int posittionX, int finalPositionY, int finalPositionX, SearchBean searchBean2){
		this.setNodeIndexX(posittionX);
		this.setNodeIndexY(positionY);
		this.setFinalIndexX(finalPositionX);
		this.setFinalIndexY(finalPositionY);
		this.setFromWhoInicialNodeComes(fromNode);
		this.setQueueSearch(new LinkedList<Node>());
		this.searchBean = searchBean2;
		
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
	
		while(!queueSearch.isEmpty() && GlobalUtils.stopAllOtherTasks == false){
			try {
	 
				Node principalNode = queueSearch.remove();
				
				Queue<Node> brothersPrincipalNode = findReachablesBrothers(principalNode);
				
				for (Node adjNode : brothersPrincipalNode) {
					whoIsYourDaddy(adjNode,principalNode);
					if( GlobalUtils.stopAllOtherTasks == true){
						break;
					}
					//P(adjNode)
					adjNode.getSemaphore().acquire();
					if(adjNode.getWhoMarkedThisNode() != fromWhoInicialNodeComes)
						adjNode.isInUse++;
					System.out.println(fromWhoInicialNodeComes+" On the node: "+adjNode.getPositionY()+","+ adjNode.getPositionX());
					if(adjNode.estaEmUsoParaleloAgora(adjNode)){//V(adjNode)
						Node middleNode = adjNode;
						System.out.println("No final: "+fromWhoInicialNodeComes);
						if( GlobalUtils.stopAllOtherTasks == true){
							break;
						}
						GlobalUtils.stopAllOtherTasks = true;
						buildBFSPath(middleNode);
						
						
						//TODO: resolver  o problema do broadcast de fim
						
					}
					else{
						
						if(adjNode.getColor().equals("WHITE")){
							adjNode.setColor("GRAY");
							adjNode.setWhoMarkedThisNode(fromWhoInicialNodeComes);
							adjNode.setPathCost(principalNode.getPathCost()+1);
							if( GlobalUtils.stopAllOtherTasks == true){
								break;
							}
							queueSearch.add(adjNode);
						}
						else{
							if(adjNode.getColor().equals("GRAY")){
								if(adjNode.getWhoMarkedThisNode() != fromWhoInicialNodeComes){
									Node middleNode = adjNode;
									if( GlobalUtils.stopAllOtherTasks == true){
										break;
									}
									buildBFSPath(middleNode);
									
								}
							}
						}
					}
					principalNode.setColor("BLACK");
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			
		}
		
	}

	public void buildBFSPath(Node middleNode) {
		
		ArrayList<Node> firstHalfList = CreateListFromBeginToMidle(middleNode);
		System.out.println("Comecou a primeira metade.");
		for (Node principalNodes : firstHalfList) {
			System.out.print(principalNodes.getPositionY()+" , "+principalNodes.getPositionX()+" - ");
	}
		ArrayList<Node> secondHalfList = CreateListFromMiddleToEnd(middleNode);
	System.out.println("Comecou a segunda metade.");
		for (Node principalNode : secondHalfList) {
			System.out.print(principalNode.getPositionY()+" , "+principalNode.getPositionX()+" - ");
	}
		firstHalfList.addAll(secondHalfList);
		
//		for (Node principalNode : firstHalfList) {
//			System.out.print(principalNode.getPositionX()+" , "+principalNode.getPositionY()+" - ");
//		}
		System.out.println();
		System.out.println("iniciando Impressao no GlobalUtils");
		
			ArrayList<Node> pathMap=new ArrayList<Node>();
			pathMap.addAll(firstHalfList);
			this.searchBean.setPathNodeSearch(pathMap);
			System.out.println("terminada a Impressao no GlobalUtils");
		
		
	}

	public static ArrayList<Node> CreateListFromMiddleToEnd(Node middleNode) {
		ArrayList<Node> secondHalfList = new ArrayList<Node>();
		Node actualNode = middleNode.getParentFromEndNode(); 
		while(actualNode != null){
			secondHalfList.add(actualNode);
			actualNode = actualNode.getParentFromEndNode();
		}
		return secondHalfList;
		
				
	}
	public  ArrayList<Node> CreateListFromBeginToMidle(Node middleNode) {
		ArrayList<Node> firstHalfList = new ArrayList<Node>();
		Node actualNode = middleNode; 
		while(actualNode!=null){
			firstHalfList.add(actualNode);
			actualNode = actualNode.getParentFromBeginNode();
		}
		return reverseList(firstHalfList);
	}

	public static ArrayList<Node> reverseList(ArrayList<Node> secondHalfList) {
		ArrayList<Node> reverseList = new ArrayList<Node>();
		for (int i = secondHalfList.size() -1; i >= 0; i--) {
			reverseList.add(secondHalfList.get(i));
		}
		return reverseList;
	}


	public  void whoIsYourDaddy(Node adjNode, Node principalNode) {

		Node parentNode = Library.map[principalNode.getPositionY()][principalNode.getPositionX()];
		if(this.fromWhoInicialNodeComes.equals("BEGIN")){
			Library.map[adjNode.getPositionY()][adjNode.getPositionX()].setParentFromBeginNode(parentNode);
			
		}
		else{
			Library.map[adjNode.getPositionY()][adjNode.getPositionX()].setParentFromEndNode(parentNode);
		}
	}

	public static Queue<Node> findReachablesBrothers(Node principalNode) {
		Queue<Node> reachablesBrothers = new LinkedList<Node>();
		Node upBrotherNode = principalNode.IsUpBrotherReachable(principalNode);
		Node leftBrotherNode = principalNode.IsLeftBrotherReachable(principalNode);
		Node rightBrotherNode = principalNode.IsRightBrotherReachable(principalNode);
		Node downBrotherNode = principalNode.IsDownBrotherReachable(principalNode);
		
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
		firstNode = Library.map[this.nodeIndexY][this.nodeIndexX];
		queueSearch.add(firstNode);
		
		this.BreadthFirstSearch(firstNode);
		GlobalUtils.stopAllOtherTasks = true;	
		
	}
}

