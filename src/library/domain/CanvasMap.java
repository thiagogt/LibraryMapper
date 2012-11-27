package library.domain;

import java.util.ArrayList;

import javax.faces.bean.ManagedProperty;

import library.bean.SearchBean;
import library.search.MonitorSearch;
import library.utils.GlobalUtils;

public class CanvasMap {
	
	private SearchBean searchBean;
	private ArrayList<Node> pathNodeSearch;
	
	public SearchBean getSearchBean() {
		return searchBean;
	}
	public void setSearchBean(SearchBean searchBean) {
		this.searchBean = searchBean;
	}
	
	public  void init(StringBuilder out, Book selectedBook, SearchBean searchBean2) throws InterruptedException {
//		out.append("<html>\n");  
//		out.append("<head><title>Map</title></head>\n");  
//		out.append("<body onload=\"init();\">\n"+
//					"<canvas id=\"canvas\" width=\"1200\" height=\"600\"></canvas><br/>\n");  
//		out.append("<script type=\"text/javascript\">function init() {\n"+
//				"\tvar canvas = document.getElementById(\"canvas\");\n"+
//				"\tvar ctx = canvas.getContext(\"2d\");\n");
		searchBean = searchBean2;
		loadNodesFromBD(out);		
		loadSearch(out,selectedBook);
//		out.append(	"}</script>\n");
//		out.append("</body>\n</html>");  

		
	}
	private  void loadSearch(StringBuilder out, Book selectedBook) throws InterruptedException {
		MonitorSearch monitorSearch ;
		searchBean.naoExisteAEstante = false;
		int finalY;
		int finalX;
		//Fazer METODO Q BUSCA POR UM INTERVALO DE ESTANTES
		
		ArrayList<Node> pathMap;
		int idQrCode = Integer.parseInt(searchBean.getIdDoQr());
	    
		
		int initialY = returnPositionYFrom(idQrCode);
		int initialX = returnPositionXFrom(idQrCode);
		Bookshelf bookshelf = new Bookshelf();
		
		bookshelf.createInitialShelfIndetification(searchBean.getSelectedBook().getBookShelf());
		bookshelf.createFinalShelfIndetification(searchBean.getSelectedBook().getBookShelf());
		ArrayList<Node> nodeShelfList = bookshelf.returnNodeOfShelf();
		if(nodeShelfList.size()>0){
			for (Node node : nodeShelfList) {
				createJavaScriptForSearchImpression(node, out, node.getPositionY(), node.getPositionX());
			}
			monitorSearch =  new MonitorSearch(searchBean);
			 finalY = nodeShelfList.get(0).getPositionY();
			 finalX=nodeShelfList.get(0).getPositionX();
			 monitorSearch.startSearch(initialY,initialX , finalY, finalX);
				
			int i,j;
			try{
				
				this.pathNodeSearch = this.searchBean.getPathNodeSearch();
				
				for (Node node : this.pathNodeSearch) {
					
					i = node.getPositionY();
					j = node.getPositionX();
					createJavaScriptForSearchImpression(node,out,i,j);
				}
			}
			catch(Exception e){
				System.out.println("Load Search book on html ERROR: "+e);
				e.printStackTrace();
				searchBean.naoExisteAEstante = true;
			}
			
		}
		else{
			searchBean.naoExisteAEstante = true;
		}
	}
	
	
	
	private int returnPositionXFrom(int idQrCode) {
		int positionX = idQrCode/GlobalUtils.CASA_DE_GRANDEZA_X_QRCODE;
		return positionX;
	}
	private int returnPositionYFrom(int idQrCode) {
		int positionY = idQrCode%GlobalUtils.CASA_DE_GRANDEZA_X_QRCODE;
		return positionY;
	}
	public ArrayList<Node> getPathNodeSearch() {
		return pathNodeSearch;
	}
	public void setPathNodeSearch(ArrayList<Node> pathNodeSearch) {
		this.pathNodeSearch = pathNodeSearch;
	}
	private  void createJavaScriptForSearchImpression(Node node,
			StringBuilder out, int i, int j) {
		beginPath(out);
		drawSearchOnMap(node,out,i,j);

		closePath(out);
		
	}
	private  void drawSearchOnMap(Node node, StringBuilder out, int i,
			int j) {
		if(node.getContentType().equals("QrCode")){
			out.append("\t\tctx.fillStyle = \"yellow\";\n");
		}else if(node.getContentType().equals("SelectedShelf")){
			out.append("\t\tctx.fillStyle = \"blue\";\n");
		}else
			out.append("\t\tctx.fillStyle = \"red\";\n");
		
		int size = GlobalUtils.NODE_SIZE;
		//A matriz impressa eh transposta por causa do esquema top left do html
		out.append("\t\tctx.fillRect("+j*size+","+i*size+","+size+","+size+");\n");
				
	}
	private  void loadNodesFromBD(StringBuilder out) {
		
		try {
			Library library = new Library();
			
			for (int i = 0; i < GlobalUtils.LIBRARY_HEIGHT; i++) {
				for (int j = 0; j < GlobalUtils.LIBRARY_WIDTH; j++) {
					
					createJavaScriptForImpression(GlobalUtils.mapLibrary[i][j],out,i,j);
					
				}
			}
		} catch (Exception e) {
			System.out.println("Nao foi possivel carregar a biblioteca: ERRO : "+e);
			e.printStackTrace();
		}
		
		
	}
	public  void createJavaScriptForImpression(Node node,StringBuilder out,int i,int j) {
		beginPath(out);
		drawPositionFromMap(node,out,i,j);

		closePath(out);
	}

	public  void drawPositionFromMap(Node node, StringBuilder out,int i,int j) {
		
		if(node.getContentType().equals("Free")){
			out.append("\t\tctx.fillStyle = \"white\";\n");
		}
		else if(node.getContentType().equals("Shelf")){
			out.append("\t\tctx.fillStyle = \"brown\";\n");
		}else{
			out.append("\t\tctx.fillStyle = \"black\";\n");
		}
		int size = GlobalUtils.NODE_SIZE;
		//A matriz impressa eh transposta por causa do esquema top left do html
		out.append("\t\tctx.fillRect("+j*size+","+i*size+","+size+","+size+");\n");
		

	}

	public  void closePath(StringBuilder out) {

		out.append("\tctx.closePath();\n");		
		
	}

	static void beginPath(StringBuilder out) {
		out.append("\tctx.beginPath();\n");
	}


}
