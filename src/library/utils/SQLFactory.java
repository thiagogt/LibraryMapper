package library.utils;

import java.sql.*;

public class SQLFactory {
	 public Connection getConnection() {
	        try {
	            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/libraryMapper","postgres","postgres");
	        }
	        catch(SQLException excecao) {
	            throw new RuntimeException(excecao);
	        }
	    }
}