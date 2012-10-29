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

import library.domain.JsonMap;

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
	String mapItens;
	
	public String getMapItens() {
		return mapItens;
	}

	public void setMapItens(String in) throws IOException{
				  
				 Reader readerString = new StringReader(in);
			      JsonReader reader = new JsonReader(readerString);
			      List<JsonMap> list= readMessagesArray(reader);
			      for (JsonMap mapBean : list) {
					System.out.println("Esse eh o tipo: "+mapBean.getType());
					System.out.println("esse eh o id se for estante: "+ mapBean.getIdShelf());
				}
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
			   }else if(name.equals("shelfId")){	 
				   if(type.equals("shelf")){
					   
				   	 idShelf = reader.nextString();
				   }	
			   }else{
			           reader.skipValue();
			   }
			   
		   }
		  reader.endObject();
		  return new JsonMap(id, type,top,left,heigth,width,idShelf);
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
