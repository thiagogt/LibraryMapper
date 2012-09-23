package library.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import library.domain.Node;

public class SearchGrid {

	
	
	public static ArrayList<Node> BreadthFirstSearch(String fromWhoInicialNodeComes, Node inicial, Queue<Node> queue){
		
		while(!queue.isEmpty()){
			try {
				Node principalNode = queue.remove();
				Queue<Node> brothersPrincipalNode = findReachablesBrothers(principalNode);
				
				for (Node adjNode : brothersPrincipalNode) {
					whoIsYourDaddy(adjNode,principalNode,fromWhoInicialNodeComes);
					//tem q ser atomico
					adjNode.isInUse++;
					if(adjNode.estaEmUsoParaleloAgora()){//ate aqui tem q ser atomico)
						Node middleNode = adjNode;
						return buildBFSPath(middleNode);
					}
					else{
						if(adjNode.getColor().equals("WHITE")){
							adjNode.setColor("GRAY");
							adjNode.setPathCost(principalNode.getPathCost()+1);
							queue.add(adjNode);
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

	private static ArrayList<Node> buildBFSPath(Node middleNode) {
		// stopAllOtherTasks();
		ArrayList<Node> firstHalfList = CreateListFromBeginToMidle(middleNode);
		ArrayList<Node> secondHalfList = CreateListFromMiddleToEnd(middleNode);
		ArrayList<Node> completeList = new ArrayList<Node>();
		System.arraycopy(firstHalfList, 0, completeList, 0, firstHalfList.size());
		System.arraycopy(secondHalfList, 0, completeList, firstHalfList.size(), secondHalfList.size());
		return completeList;
	}

	private static ArrayList<Node> CreateListFromMiddleToEnd(Node middleNode) {
		ArrayList<Node> secondHalfList = new ArrayList<Node>();
		Node actualNode = middleNode.getParentFromEndNode(); 
		while(actualNode.getParentFromEndNode()!=null){
			secondHalfList.add(actualNode);
			actualNode = actualNode.getParentFromEndNode();
		}
		return secondHalfList;
		
				
	}
	private static ArrayList<Node> CreateListFromBeginToMidle(Node middleNode) {
		ArrayList<Node> firstHalfList = new ArrayList<Node>();
		Node actualNode = middleNode; 
		while(actualNode.getParentFromBeginNode()!=null){
			firstHalfList.add(actualNode);
			actualNode = actualNode.getParentFromBeginNode();
		}
		return reverseList(firstHalfList);
	}

	private static ArrayList<Node> reverseList(ArrayList<Node> secondHalfList) {
		ArrayList<Node> reverseList = new ArrayList<Node>();
		for (int i = secondHalfList.size(); i > 0; i--) {
			reverseList.add(secondHalfList.get(i));
		}
		return null;
	}


	private static void whoIsYourDaddy(Node adjNode, Node principalNode,
			String fromWhoInicialNodeComes) {
		if(fromWhoInicialNodeComes.equals("BEGIN")){
			adjNode.setParentFromBeginNode(principalNode);
		}
		else{
			adjNode.setParentFromEndNode(principalNode);
		}
	}

	private static Queue<Node> findReachablesBrothers(Node principalNode) {
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
}

