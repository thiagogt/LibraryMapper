package library.domain;

import library.utils.GlobalUtils;

public class CanvasMap {
	
	public static void init(StringBuilder out) {
		out.append("<html>\n");  
		out.append("<head><title>Map</title></head>\n");  
		out.append("<body onload=\"init();\">\n"+
					"<canvas id=\"canvas\" width=\"1200\" height=\"600\"></canvas><br/>\n");  
		out.append("<script type=\"text/javascript\">function init() {\n"+
				"\tvar canvas = document.getElementById(\"canvas\");\n"+
				"\tvar ctx = canvas.getContext(\"2d\");\n");

		loadNodesFromBD(out);		

		out.append(	"}</script>\n");
		out.append("</body>\n</html>");  

		
	}
	private static void loadNodesFromBD(StringBuilder out) {
		
		try {
			if(Library.map == null)
				Library.Mapping(GlobalUtils.LIBRARY_HEIGHT, GlobalUtils.LIBRARY_WIDTH);
			for (int i = 0; i < GlobalUtils.LIBRARY_HEIGHT; i++) {
				for (int j = 0; j < GlobalUtils.LIBRARY_WIDTH; j++) {
					CanvasMap.createJavaScriptForImpression(Library.map[i][j],out,i,j);
					
				}
			}
		} catch (Exception e) {
			System.out.println("Nao foi possivel carregar a biblioteca: ERRO : "+e);
		}
		
		
	}
	public static void createJavaScriptForImpression(Node node,StringBuilder out,int i,int j) {
		beginPath(out);
		drawPositionFromMap(node,out,i,j);

		closePath(out);
	}

	public static void drawPositionFromMap(Node node, StringBuilder out,int i,int j) {
		// TODO Auto-generated method stub
		System.out.println(node.getContentType()+i+j);
		if(node.getContentType().equals("Free")){
			System.out.println("entro");
			out.append("\t\tctx.fillStyle = \"white\";\n");
		}
		else if(node.getContentType().equals("Shelf")){
			out.append("\t\tctx.fillStyle = \"brown\";\n");
		}else{
			out.append("\t\tctx.fillStyle = \"black\";\n");
		}
		int size = GlobalUtils.NODE_SIZE;
		//A matriz impressa eh transposta por causa do esquema top left do html
		out.append("\t\tctx.fillRect("+i*size+","+j*size+","+size+","+size+");\n");
		

	}

	public static void closePath(StringBuilder out) {

		out.append("\tctx.closePath();\n");		
		
	}

	static void beginPath(StringBuilder out) {
		out.append("\tctx.beginPath();\n");
	}


}
