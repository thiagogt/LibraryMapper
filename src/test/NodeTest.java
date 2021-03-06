package test;

import static org.junit.Assert.*;
import library.domain.Node;
import library.mapper.NodeMapper;
import library.utils.SQLFactory;

import org.junit.Test;

public class NodeTest {

	@Test
	public void testCatchBrothers() {
		
		NodeMapper bookshelf = SQLFactory.section.getMapper(NodeMapper.class);
		
		Node realBookShelf = new Node(); 
				realBookShelf = bookshelf.selectByPositionXAndY(1,1,0);
		
		System.out.println(realBookShelf.getContentType());
		
		Node node = realBookShelf.IsDownBrotherReachable(null,realBookShelf);
		node = realBookShelf.IsUpBrotherReachable(null,realBookShelf);
		node = realBookShelf.IsLeftBrotherReachable(null,realBookShelf);
		node = realBookShelf.IsRightBrotherReachable(null,realBookShelf);
		
		
		
	}

	@Test
	public void testDeleteAllNodesFromLibrary() {
		Integer idLibrary = 2;
		
		Node.deleteAllNodesFromLibrary(idLibrary);
		
		//SQLFactory.section.close();
		
	}
	
	@Test
	public void testReturnBiggestNode(){
		int i  =Node.returnTheLastNodeID();
		System.out.println(i);
	}
	
}
