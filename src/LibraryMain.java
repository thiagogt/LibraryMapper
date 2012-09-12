import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

import org.apache.catalina.Session;


import library.mapper.BookshelfMapper;
import library.utils.SQLFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSession;

public class LibraryMain {

	public static void main(String[] args) throws SQLException {
		
		
		
		 Connection connection = new SQLFactory().getConnection();
	        System.out.println("Conexão aberta!");
	       
	        
	        
	        connection.close();

	}

}
