import java.sql.Connection;
import java.sql.SQLException;


import library.utils.SQLFactory;

import org.apache.ibatis.session.SqlSession;


public class LibraryMain {

	public static void main(String[] args) throws SQLException {
		
		
		
		 Connection connection = ((SqlSession) new SQLFactory()).getConnection();
	        System.out.println("Conexão aberta!");
	       
	        
	        
	        connection.close();

	}

}
