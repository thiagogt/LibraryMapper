package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.management.Query;

import library.domain.Node;
import library.mapper.NodeMapper;
import library.search.SearchGrid;
import library.utils.SQLFactory;

import org.junit.Test;

public class SearchTests {
	
	static Node principalNode;
	static Queue<Node> queueNode;
	static NodeMapper nodeMapper;
	
	static{
		nodeMapper = SQLFactory.section.getMapper(NodeMapper.class);
		queueNode = new LinkedList<Node>();
		principalNode = new Node(); 
				principalNode = nodeMapper.selectByPositionXAndY(principalNode.getIdLibrary(),3,2);
		
		System.out.println(principalNode.getContentType());
	}
//Para testar esse metodo mudar o retorno do metodo Node.ReachablesBrothers para nao null	
//	@Test
//	public void testReverseList() {
//
//	
//		
//		queueNode.add(principalNode.IsDownBrotherReachable(principalNode));
//		queueNode.add(principalNode.IsUpBrotherReachable(principalNode));
//		queueNode.add(principalNode.IsLeftBrotherReachable(principalNode));
//		queueNode.add(principalNode.IsRightBrotherReachable(principalNode));
//		ArrayList<Node> correctNode = new ArrayList<Node>();
//		for (Node node : queueNode) {
//			correctNode.add(node);
//		} 
//		ArrayList<Node> reverseList = SearchGrid.reverseList(correctNode);
//		System.out.println("Agora a lista invertida: ");
//		
//		for (Node node : reverseList) {
//			System.out.println(node.getContentType());
//		}
//	}
	
	@Test
	public void testfindReachablesBrothers(){
		Queue<Node> queue = SearchGrid.findReachablesBrothers(principalNode);
		System.out.println("Esses sao os irmaos: ");
		for (Node node : queue) {
			System.out.println(node.getContentType() + ", at position : " + node.getPositionX()+","+node.getPositionY() );
		}
	}
	@Test
	public void testBuidPath(){
		Node s = new Node();
		Node node13 = new Node(); 
		Node node23 = new Node();
		Node node33 = new Node();
		Node c = new Node();
		
		s = nodeMapper.selectByPositionXAndY(s.getIdLibrary(),0,3);
		
		c = nodeMapper.selectByPositionXAndY(c.getIdLibrary(),3,2);
		
		node13 = nodeMapper.selectByPositionXAndY(node13.getIdLibrary(),1,3);
		node13.setParentFromBeginNode(s);
		
		node33 = nodeMapper.selectByPositionXAndY(node33.getIdLibrary(),3,3);
		node33.setParentFromEndNode(c);
		
		
		node23 = nodeMapper.selectByPositionXAndY(node23.getIdLibrary(),2,3);
		node23.setParentFromBeginNode(node13);
		node23.setParentFromEndNode(node33);
		
		
		
		
		Node middleNode = node23;
		ArrayList<Node> pathMap = SearchGrid.buildBFSPath(middleNode);
		for (Node node : pathMap) {
			System.out.print(node.getPositionX()+","+node.getPositionY() +" - ");
		}
	}
	@Test
	public void testBfs() throws Throwable{
		
		Thread inicialSearch = new Thread();
		Thread finalSearch = new Thread();
		
		
	}

	
	
	
}
