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
	
	static{
		NodeMapper bookshelf = SQLFactory.section.getMapper(NodeMapper.class);
		queueNode = new LinkedList<Node>();
		principalNode = new Node(); 
				principalNode = bookshelf.selectByPositionXAndY(1,3,2);
		
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
//				
//		SQLFactory.section.close();
//				
//	}
//	
	@Test
	public void testfindReachablesBrothers(){
		Queue<Node> queue = SearchGrid.findReachablesBrothers(principalNode);
		System.out.println("Esses sao os irmaos: ");
		for (Node node : queue) {
			System.out.println(node.getContentType() + ", at position : " + node.getPositionX()+","+node.getPositionY() );
		}
	}

}