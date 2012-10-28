package library.utils;

import java.util.ArrayList;

import library.domain.Library;
import library.domain.Node;



public class GlobalUtils {

	public static final String COLMEIA_SERVICE = 	"http://biblioteca.ime.usp.br/colmeia/buscaObra.do";
	public static final String ROOT_PATH = "/home/thiago/workspace/";
	public static final String WEB_CONTENT_PATH ="LibraryMapper/WebContent/";
	public static final String WEB_LIBRARY_PATH = "WebLibraryMapper/";
	public static final String PRINT_MAP_FILE = "printMap.xhtml";
	public static final String PRINT_ERRO_FILE = "printErro.xhtml";
	
	public static final String DEFAULT_HOST = null;
	public static final String DEFAULT_PORT = null;
	public static final String APPLICATION_NAME = null;
	
	
	
	public static int QRMARK_VALUE = 2;
	public static int BOOKSHELf_VALUE = 1;
	public static int EMPTY_VALUE = 0;
	public static Integer idLibrary = 1;
	public static boolean stopAllOtherTasks = false;
	public static ArrayList<Node> pathMap;
	
	public static int LIBRARY_HEIGHT = 5;
	public static int LIBRARY_WIDTH = 4;
	public static final int NODE_SIZE = 50;
}
