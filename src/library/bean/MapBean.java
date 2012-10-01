package library.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



@ManagedBean(name="mapping")
@SessionScoped
public class MapBean extends HttpServlet{
	//todo : fazer um listener para envios html 
	
	String id;
	int top;
	int left;
	int heigth;
	int width;
	
	
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
	
	public void listenConection(String jsonResponse){
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create(); 
		MapBean mapBean = gson.fromJson(jsonResponse, MapBean.class);
		
		
	}
	
	
}
