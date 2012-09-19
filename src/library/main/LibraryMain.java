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
		
		SqlSession section = SQLFactory.abrirSessao();
		
		NodeMapper bookshelf = section.getMapper(NodeMapper.class);
		
		Node realBookShelf = bookshelf.selectByPositionXAndY(1,0,1);
		
		System.out.println(realBookShelf.getContentType());
		
		section.close();
	}

}
