package library.bean;

import java.io.*;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.Reader;
//import java.io.Serializable;
//import java.io.StringReader;
//import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.org.mozilla.javascript.regexp.SubString;

import library.domain.Bookshelf;
import library.domain.CanvasMap;
import library.domain.JsonMap;
import library.domain.Node;
import library.domain.QrCodeMark;
import library.utils.GlobalUtils;
import library.utils.SQLFactory;
import library.domain.Library;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.sun.org.apache.bcel.internal.generic.NEW;



@ManagedBean(name="mapping")
@SessionScoped
public class MapBean extends HttpServlet implements Serializable{
	//todo : fazer um listener para envios html 
	String mapItens;
	Node[][] mapNode;
	int lastShelfId;
	int lastQrCodeId;
	ArrayList<Node> listFree;
	ArrayList<Node> listForbidden;
	ArrayList<Node> listShelf;
	ArrayList<Node> listQrCode;
	
	private static Log log = LogFactory.getLog(MapBean.class);
	
	public ArrayList<Node> getListForbidden() {
		return listForbidden;
	}

	public void setListForbidden(ArrayList<Node> listForbidden) {
		this.listForbidden = listForbidden;
	}

	public ArrayList<Node> getListShelf() {
		return listShelf;
	}

	public void setListShelf(ArrayList<Node> listShelf) {
		this.listShelf = listShelf;
	}

	public ArrayList<Node> getListQrCode() {
		return listQrCode;
	}

	public void setListQrCode(ArrayList<Node> listQrCode) {
		this.listQrCode = listQrCode;
	}

	public ArrayList<Node> getListFree() {
		return listFree;
	}

	public void setListFree(ArrayList<Node> listFree) {
		this.listFree = listFree;
	}

	public String getMapItens() {
		return null;
	}
	public int getLastShelfId() {
		return lastShelfId;
	}

	public void setLastShelfId(int lastShelfId) {
		this.lastShelfId = lastShelfId;
	}

	public int getLastQrCodeId() {
		return lastQrCodeId;
	}

	public void setLastQrCodeId(int lastQrCodeId) {
		this.lastQrCodeId = lastQrCodeId;
	}
	public void setMapItens(String in) throws IOException{
				Bookshelf bookshelf = new Bookshelf();
				listFree = new ArrayList<Node>();
				listShelf = new ArrayList<Node>();
				listQrCode = new ArrayList<Node>();
				listForbidden = new ArrayList<Node>();
				
				lastQrCodeId = QrCodeMark.returnTheLastQrNodeID()+1;
				lastShelfId = bookshelf.returnTheLastBookShelfID()+1;
				
				  Reader readerString = new StringReader(in);
			      JsonReader reader = new JsonReader(readerString);
			      List<JsonMap> list= readMessagesArray(reader);
			      mapNode = new Node[GlobalUtils.MAP_HTML_ROWS][GlobalUtils.MAP_HTML_COLUMNS];

			      			      
//			      //setar primeiro as barrreiras para q nada atravesse as paredes da biblioteca
			      initMatrizFromHtml();
			      
			      putEveryNodeInCorrespondentTypeList(list);
			      
			      CreateNodesInMapNode();
			      printNodesInFile();
			      putMapNodeOnBD();
////			      
   }
	

	public List<JsonMap> readMessagesArray(JsonReader reader) throws IOException {
		ArrayList<JsonMap> messages = new ArrayList<JsonMap>();
		 
		reader.beginArray();
		while (reader.hasNext()) {
		       messages.add(readMessage(reader));
		}
		reader.endArray();
		return messages;
}
		  
	public JsonMap readMessage(JsonReader reader) throws IOException {
	   String id = null;
	   String type = null;
	   String idShelf = null;
	   String idShelfInitial = null;
	   String idShelfFinal = null;
	   int top = 0;
	   int left = 0;
	   int height = 0;
	   int width = 0;
	   
	   
	   reader.beginObject();
	   while (reader.hasNext()) {
		   String name = reader.nextName();
		   if (name.equals("id")) {
		           id = reader.nextString();
		   } else if (name.equals("type")) {
		           type = reader.nextString();
		   } else if (name.equals("top") ) {
			   	  top = reader.nextInt();
		   } else if (name.equals("left") ) {
			   	  left = reader.nextInt();
		   } else if (name.equals("height") ) {
			   	  height = reader.nextInt();
		   }else if (name.equals("width") ) {
			   	  width = reader.nextInt();
		   }else if(name.equals("shelfId")){	 
			   if(type.equals("shelf")){
				   
			   	 idShelf = reader.nextString();
			   	 idShelfInitial = cathInitialPart(idShelf);
			   	 idShelfFinal = catchFinalPart(idShelf);
			   }	
		   }else{
		           reader.skipValue();
		   }
		   
	   }
	  reader.endObject();
	  return new JsonMap(id, type,top,left,height,width,idShelfInitial,idShelfFinal);
   }
	
	
	public String catchFinalPart(String idShelf) {
		int charAsterisc = idShelf.indexOf("**");
		return idShelf.substring(charAsterisc+2,idShelf.length());
	}

	public String cathInitialPart(String idShelf) {
		int charAsterisc = idShelf.indexOf("**");
		return idShelf.substring(0,charAsterisc);
		
	}

	private void initMatrizFromHtml() {
		int id =Node.returnTheLastNodeID()+1;
		for (int i = 0; i < GlobalUtils.MAP_HTML_ROWS; i++) {
			for (int j = 0; j < GlobalUtils.MAP_HTML_COLUMNS; j++) {
				
				Node node = new Node();
				node.setContentType("Forbidden");
				node.setPositionX(j);
				node.setPositionY(i);
				node.setIdNode(id);
				node.setContentId(0);
				id++;
				mapNode[i][j] = node;
			}
	      }
	}



	private void putEveryNodeInCorrespondentTypeList( List<JsonMap> list) {
		for (JsonMap mapBean : list) {
			 
			if (mapBean.getType().equals("bloco") ) {
	    		  listFree.addAll(typeOfNode(mapBean,"Free"));
	    		  
	    	  }
	    	  if (mapBean.getType().equals("forbidden")) {
	    		  listForbidden.addAll(typeOfNode(mapBean,"Forbidden"));
	    	  }
	    	  if(mapBean.getType().equals("shelf")){

	    		  listShelf.addAll(typeOfNode(mapBean,"Shelf"));
	    	  }
	    	  //Free,QrCode,Forbidden,Shelf
	    	  if(mapBean.getType().equals("qrCode")){
	    		   listQrCode.addAll(typeOfNode(mapBean,"QrCode"));
	    		   
	    	  }
	    	  
		}
		
	}

	
	private ArrayList<Node> typeOfNode(JsonMap mapBean,String nodeType) {
		
			int positionX = mapBean.getLeft()/GlobalUtils.SIZE_EQUIVALENT_ONE_POINT_HTML_MAP_CREATION;
			int positionY = mapBean.getTop()/GlobalUtils.SIZE_EQUIVALENT_ONE_POINT_HTML_MAP_CREATION;
			ArrayList<Node> list = new ArrayList<Node>();
			//POR CAUSA Q O MENOR V ALOR EH 6 NA MATRIZ, O VALOR ZERO EH UM VALOR VALIDO PARA O TAMANHO
			int limitX = mapBean.getWidth()/GlobalUtils.SIZE_EQUIVALENT_ONE_POINT_HTML_MAP_CREATION;
			int limitY = mapBean.getHeight()/GlobalUtils.SIZE_EQUIVALENT_ONE_POINT_HTML_MAP_CREATION;
			int contId = 1;
			Node node;
			if(nodeType.equals("QrCode"))
				contId = ++lastQrCodeId;
			if(nodeType.equals("Shelf"))
				contId = ++lastShelfId;
			
			for (int i = 0; i <= limitY; i++) {
				for (int j = 0; j <= limitX ;j++) {
					node = new Node();
					node.setContentType(nodeType);
					node.setContentId(contId);
					//System.out.println("Esse eh o id : "+contId);
					if(nodeType.equals("QrCode"))
						 lastQrCodeId =contId;
					if(nodeType.equals("Shelf"))
						lastShelfId = contId;
					
					contId++;
					
					if(nodeType.equals("Shelf")){
						node.setCodeIdInitialShelf(mapBean.getIdInitialShelf());
						node.setCodeIdFinalShelf(mapBean.getIdFinalShelf());
					}
					node.setPositionX(positionX+j);
					node.setPositionY(positionY+i);
					node.setIdNode(mapNode[node.getPositionY()][node.getPositionX()].getIdNode());
					list.add(node);
					
				}
			}
			return list;
	}
		  

	private void CreateNodesInMapNode() {
		
		for (Node listNodeFree : listFree) {
			mapNode[listNodeFree.getPositionY()][listNodeFree.getPositionX()]  = listNodeFree;
		}
		for (Node listNodeShelf : listShelf) {
			mapNode[listNodeShelf.getPositionY()][listNodeShelf.getPositionX()]  = listNodeShelf;
		}
		for (Node listNodeForbidden : listForbidden) {
			mapNode[listNodeForbidden.getPositionY()][listNodeForbidden.getPositionX()]  = listNodeForbidden;
		} 
		for (Node listNodeQrCode : listQrCode) {
			mapNode[listNodeQrCode.getPositionY()][listNodeQrCode.getPositionX()]  = listNodeQrCode;
			
		}
		
		
	}

	private void printNodesInFile() throws IOException {
		StringBuilder out = new StringBuilder();  
		catchAllTheTypesfrom(out);
				  
		String path = GlobalUtils.PRINT_MATRIX_FILE;  
		File f = new File(path);
		if (!f.exists()) {
			f.createNewFile();
		}
		
		try {
			FileWriter fw = new FileWriter(f);  
			fw.write(out.toString());  
			fw.close();	
		} catch (Exception e) {
			log.error("Erro ao carregar arquivo para impressao da Biblioteca:ERRO ",e);
			e.printStackTrace();
		}  
		  		
	}

	private void catchAllTheTypesfrom( StringBuilder out) {
		String stringType = null;
		for (int i = 0; i < GlobalUtils.MAP_HTML_ROWS; i++) {
			for (int j = 0; j < GlobalUtils.MAP_HTML_COLUMNS; j++) {
				
				if(mapNode[i][j].getContentType().equals("Forbidden")){
					stringType = "x";
				}
				if(mapNode[i][j].getContentType().equals("Free")){
					stringType = " ";
				} 
				if(mapNode[i][j].getContentType().equals("Shelf")){
					stringType = "s";
				}
				if(mapNode[i][j].getContentType().equals("QrCode")){
					stringType = "q";
				}
				out.append(stringType +"|");
			}
			out.append("\n");
	      }		
	}
	private void putMapNodeOnBD() {
		
		Library.setNewLibrary();
		for (int i = 0; i < GlobalUtils.MAP_HTML_ROWS; i++) {
			for (int j = 0; j < GlobalUtils.MAP_HTML_COLUMNS; j++) {
				mapNode[i][j].setIdLibrary(GlobalUtils.idLibrary);
				Library.insertNodesToLibrary(mapNode[i][j]);
			}
		}
		
	}

}
