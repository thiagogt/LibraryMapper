package library.utils;

import java.io.Reader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;

import org.apache.ibatis.session.SqlSession;

import org.apache.ibatis.session.SqlSessionFactory;

import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SQLFactory {
	private static final SqlSessionFactory sqlSessionFactory;
	public static SqlSession section;
	private static Log log = LogFactory.getLog(SQLFactory.class);
	static {

		try {
	
			Reader reader = Resources.getResourceAsReader("MapperConfig.xml");

			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			
			section = SQLFactory.abrirSessao();
			

		} catch (Exception e) {

//			e.printStackTrace();
			log.error(e,e);
	
			throw new RuntimeException("Error initializing MyAppSqlConfig class. Cause: " + e);
	
		}

	}
	
	public static SqlSessionFactory getSqlSessionFactory() {

		return sqlSessionFactory;

	}

	public static SqlSession abrirSessao(){

		return getSqlSessionFactory().openSession();

	}
}