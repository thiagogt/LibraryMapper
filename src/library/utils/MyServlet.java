package library.utils;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import library.domain.Library;

public class MyServlet implements ServletContextListener {
	private static Log log = LogFactory.getLog(SQLFactory.class);
  public void contextInitialized(ServletContextEvent e) {
    try {
		log.info("Carregando a biblioteca de id: "+GlobalUtils.idLibrary);
		Library library = new Library();
		log.info("Pronto");
		
	} catch (Exception er) {
		log.error("Nao foi possivel carregar a biblioteca na MAIN - ERRO: ",er);
		
	}
  }

  public void contextDestroyed(ServletContextEvent e) {
//    Connection con =
  //     (Connection) e.getServletContext().getAttribute("con");
    //try { con.close(); } 
    //catch (SQLException ignored) { } // close connection
	  log.info("Tomcat derrubado "+e);
  }
}