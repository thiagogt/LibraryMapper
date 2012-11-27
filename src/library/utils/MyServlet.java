package library.utils;

import javax.servlet.*;
import javax.servlet.http.*;

import library.domain.Library;

public class MyServlet implements ServletContextListener {

  public void contextInitialized(ServletContextEvent e) {
    try {
		System.out.println("Carregando a biblioteca de id: "+GlobalUtils.idLibrary);
		Library library = new Library();
		System.out.println("Pronto");
		
	} catch (Exception er) {
		System.out.println("Nao foi possivel carregar a biblioteca na MAIN - ERRO: "+er);
	}
  }

  public void contextDestroyed(ServletContextEvent e) {
//    Connection con =
  //     (Connection) e.getServletContext().getAttribute("con");
    //try { con.close(); } 
    //catch (SQLException ignored) { } // close connection
    System.out.println("contextDestroyed(ServletContextEvent e)");
  }
}