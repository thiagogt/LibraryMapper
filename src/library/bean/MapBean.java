package library.bean;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;



@ManagedBean(name="mapping")
@SessionScoped
public class MapBean extends HttpServlet{
	//todo : fazer um listener para envios html 
	
	String id;
	String type;
	int top;
	int left;
	int heigth;
	int width;
	String mapItens;
	
	public MapBean() {
		
	}
	
	public MapBean(String id2, String type2, int top2, int left2, int heigth2,int width2) {
		this.id = id2;
		this.type = type2;
		this.top = top2;
		this.left = left2;
		this.heigth = heigth2;
		this.width = width2;
		this.mapItens = null;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getHeigth() {
		return heigth;
	}
	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
	public String getMapItens(){
		System.out.println("olaaaaa get");
		return null;
	}
	
	public void setMapItens(String in) throws IOException{
				  
				 Reader readerString = new StringReader(in);
			      JsonReader reader = new JsonReader(readerString);
			      List<MapBean> list= readMessagesArray(reader);
			      for (MapBean mapBean : list) {
					System.out.println("Esse eh o tipo: "+mapBean.type);
				}
   }
			  
	public List<MapBean> readMessagesArray(JsonReader reader) throws IOException {
			List<MapBean> messages = new ArrayList<MapBean>();
			 
			reader.beginArray();
			while (reader.hasNext()) {
			       messages.add(readMessage(reader));
			}
			reader.endArray();
			return messages;
	}
			  
	public MapBean readMessage(JsonReader reader) throws IOException {
		   String id = null;
		   String type = null;
		   int top = 0;
		   int left = 0;
		   int heigth = 0;
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
			   } else if (name.equals("heigth") ) {
				   	  heigth = reader.nextInt();
			   }else if (name.equals("width") ) {
				   	  width = reader.nextInt();
			   }else {
			           reader.skipValue();
			   }
		   }
		  reader.endObject();
		  return new MapBean(id, type,top,left,heigth,width);
	   }
//		JsonParser parser = new JsonParser();
//		JsonElement element = parser.parse(mapa);
//		JsonArray array = element.getAsJsonArray();
//		
//		System.out.println("olaaaa set");
//		System.out.println(mapa);
//		for (JsonElement jsonElement : array) {
//			
//			System.out.println(jsonElement.getAsString());
//				
//		}
		
	
	
}
