package library.search;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.faces.bean.ManagedProperty;

import library.bean.SearchBean;
import library.domain.Node;
import library.utils.GlobalUtils;

public class MonitorSearch {
		public static String wichSearchFoundTheMap;
		public static SearchGrid inicialSearch;
		public static SearchGrid finalSearch;
		public volatile Boolean stopAllOtherTasks = false;
		private SearchBean searchBean;
		
	public MonitorSearch(SearchBean searchBean2) {
			this.searchBean = searchBean2;
	}
	public void startSearch(int inicialPositionY,int inicialPositionX, int finalPositionY,int finalPositionX) throws InterruptedException{
		inicialSearch = new SearchGrid(this,"BEGIN", inicialPositionY, inicialPositionX, finalPositionY, finalPositionX,searchBean);
		finalSearch = new SearchGrid(this,"END", finalPositionY, finalPositionX, inicialPositionY, inicialPositionX,searchBean);
		
		inicialSearch.start();
		finalSearch.start();
////		
//		while(!stopAllOtherTasks);
		inicialSearch.join();
		finalSearch.join();
//		{
//			System.out.println("to Aqui");
//			
//		}
//		
//		System.out.println("Acabou  o monitor");
		
//		

	}
	public static void stopAllTasks(){
		if(inicialSearch.isAlive()){System.out.println("teve q matar a BEGIN");inicialSearch.stop();}
		if(finalSearch.isAlive()){System.out.println("teve q matar a END");finalSearch.stop();}
	}
	
}
