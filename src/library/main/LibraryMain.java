package library.main;

import java.sql.SQLException;





import library.domain.Bookshelf;
import library.domain.Library;
import library.domain.Node;
import library.mapper.BookshelfMapper;
import library.mapper.NodeMapper;
import library.utils.GlobalUtils;
import library.utils.SQLFactory;

import org.apache.ibatis.session.SqlSession;

public class LibraryMain {

	public static void main(String[] args) throws SQLException {
		try {
			System.out.println("Carregando a biblioteca de id: "+GlobalUtils.idLibrary);
			Library.Mapping(GlobalUtils.LIBRARY_WIDTH, GlobalUtils.LIBRARY_HEIGHT);
			System.out.println("Pronto");
			
		} catch (Exception e) {
			System.out.println("Nao foi possivel carregar a biblioteca na MAIN - ERRO: "+e);
		}
	}

}
