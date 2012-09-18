package library.main;

import java.sql.SQLException;





import library.domain.Bookshelf;
import library.mapper.BookshelfMapper;
import library.utils.SQLFactory;

import org.apache.ibatis.session.SqlSession;

public class LibraryMain {

	public static void main(String[] args) throws SQLException {
		
		SqlSession section = SQLFactory.abrirSessao();
		
		BookshelfMapper bookshelf = section.getMapper(BookshelfMapper.class);
		
		Bookshelf realBookShelf = bookshelf.selectByPrimaryKey(1);
		
		System.out.println(realBookShelf.getIdLibrary());
		
		section.close();
	}

}
