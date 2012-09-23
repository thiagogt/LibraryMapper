package library.main;

import java.sql.SQLException;





import library.domain.Bookshelf;
import library.domain.Node;
import library.mapper.BookshelfMapper;
import library.mapper.NodeMapper;
import library.utils.SQLFactory;

import org.apache.ibatis.session.SqlSession;

public class LibraryMain {

	public static void main(String[] args) throws SQLException {
		
		
		NodeMapper bookshelf = SQLFactory.section.getMapper(NodeMapper.class);
		
		Node realBookShelf = new Node(); 
				realBookShelf = bookshelf.selectByPositionXAndY(1,1,1);
		
		System.out.println(realBookShelf.getContentType());
		
		Node node = realBookShelf.IsDownBrotherReachable(realBookShelf);
		node = realBookShelf.IsUpBrotherReachable(realBookShelf);
		node = realBookShelf.IsLeftBrotherReachable(realBookShelf);
		node = realBookShelf.IsRightBrotherReachable(realBookShelf);
		
		SQLFactory.section.close();
	}

}
