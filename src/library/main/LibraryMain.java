package library.main;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

import org.apache.catalina.Session;


import library.domain.Bookshelf;
import library.mapper.BookshelfMapper;
import library.utils.SQLFactory;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSession;

public class LibraryMain {

	public static void main(String[] args) throws SQLException {
		
		SqlSession section = SQLFactory.abrirSessao();
		
		BookshelfMapper bookshelf = section.getMapper(BookshelfMapper.class);
		
		Bookshelf realBookShelf = bookshelf.selectByPrimaryKey(1);
		
		System.out.println(realBookShelf.getIdLibrary());
		
		section.close();
	}

}
