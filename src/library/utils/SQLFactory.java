package library.utils;

import java.io.Reader;
import java.sql.*;
import org.apache.ibatis.io.Resources;

import org.apache.ibatis.session.SqlSession;

import org.apache.ibatis.session.SqlSessionFactory;

import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SQLFactory {
	private static final SqlSessionFactory sqlSessionFactory;

	static {

	try {

	Reader reader = Resources.getResourceAsReader("MapperConfig.xml");

	sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

	} catch (Exception e) {

	e.printStackTrace();

	throw new RuntimeException("Error initializing MyAppSqlConfig class. Cause: " + e);

	}

	}

	public static SqlSessionFactory getSqlSessionFactory() {

	return sqlSessionFactory;

	}

	public static SqlSession abrirSessao(){

	return getSqlSessionFactory().openSession();

	}


	
	
	
	
	
	
	
	//	 public Connection getConnection() {
//	        try {
//	            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/libraryMapper","postgres","postgres");
//	        }
//	        catch(SQLException excecao) {
//	            throw new RuntimeException(excecao);
//	        }
//	    }
}