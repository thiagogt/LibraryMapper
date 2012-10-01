package library.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.commons.digester.xmlrules.FromXmlRuleSet;
import org.apache.tomcat.jni.Global;

import library.domain.Node;
import library.utils.GlobalUtils;

public class SearchGrid extends Thread{
	
	private int nodeIndexX;
	private int nodeIndexY;
	private String fromWhoInicialNodeComes;
	private Queue<Node> queueSearch;
	
	public SearchGrid(String fromNode, int positionX, int posittionY){
		this.setNodeIndexX(positionX);
		this.setNodeIndexY(posittionY);
		this.setFromWhoInicialNodeComes(fromNode);
		this.setQueueSearch(new LinkedList<Node>());
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

	public ArrayList<Node> BreadthFirstSearch( Node inicial){
		
		while(!queueSearch.isEmpty()){
			try {
				Node principalNode = queueSearch.remove();
				
				Queue<Node> brothersPrincipalNode = findReachablesBrothers(principalNode);
				
				for (Node adjNode : brothersPrincipalNode) {
					whoIsYourDaddy(adjNode,principalNode);
				
					//P(adjNode)
					adjNode.getSemaphore().acquire();
					adjNode.isInUse++;
					if(adjNode.estaEmUsoParaleloAgora(adjNode)){//V(adjNode)
						Node middleNode = adjNode;
						System.out.println(fromWhoInicialNodeComes);
						return buildBFSPath(middleNode);
						
						//TODO: resolver  o problema do broadcast de fim
						
					}
					else{
						
						if(adjNode.getColor().equals("WHITE")){
							adjNode.setColor("GRAY");
							adjNode.setPathCost(principalNode.getPathCost()+1);
							queueSearch.add(adjNode);
						}
						else{
							if(adjNode.getColor().equals("GRAY")){
								Node middleNode = adjNode;
								return buildBFSPath(middleNode);
							}
						}
					}
					principalNode.setColor("BLACK");
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			
		}
		return null;
	}

	public ArrayList<Node> buildBFSPath(Node middleNode) {
		GlobalUtils.stopAllOtherTasks = true;
		ArrayList<Node> firstHalfList = CreateListFromBeginToMidle(middleNode);
		System.out.println("Comecou a primeira metade.");
		for (Node principalNodes : firstHalfList) {
			System.out.print(principalNodes.getPositionX()+" , "+principalNodes.getPositionY()+" - ");
	}
		ArrayList<Node> secondHalfList = CreateListFromMiddleToEnd(middleNode);
	System.out.println("Comecou a segunda metade.");
		for (Node principalNode : secondHalfList) {
			System.out.print(principalNode.getPositionX()+" , "+principalNode.getPositionY()+" - ");
	}
		firstHalfList.addAll(secondHalfList);
		
//		for (Node principalNode : firstHalfList) {
//			System.out.print(principalNode.getPositionX()+" , "+principalNode.getPositionY()+" - ");
//		}
		System.out.println("terminou");
		return firstHalfList;
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
	public static ArrayList<Node> CreateListFromBeginToMidle(Node middleNode) {
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
		if(this.fromWhoInicialNodeComes.equals("BEGIN")){
			adjNode.setParentFromBeginNode(principalNode);
		}
		else{
			adjNode.setParentFromEndNode(principalNode);
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
		Node firstNode = Node.getNodeByPosition(this.nodeIndexX, this.nodeIndexY);
		queueSearch.add(firstNode);
		GlobalUtils.pathMap = new ArrayList<Node> (); 
		GlobalUtils.pathMap = this.BreadthFirstSearch(firstNode);
			
		GlobalUtils.stopAllOtherTasks = true;
	}
}

