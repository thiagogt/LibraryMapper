package library.search;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.faces.bean.ManagedProperty;

import library.bean.SearchBean;
import library.domain.Library;
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
			if(this.searchBean.getPathNodeSearch()!= null){
				this.searchBean.setPathNodeSearch(null);
			}
	}
	public void startSearch(int inicialPositionY,int inicialPositionX, int finalPositionY,int finalPositionX) throws InterruptedException{
		Node node;
		System.out.println("Initial Positions: "+inicialPositionY+inicialPositionX);
		System.out.println("Final Positions: "+finalPositionY+","+finalPositionX);
		Library lybrary  = new Library();
		lybrary.map = new Node[GlobalUtils.LIBRARY_HEIGHT][GlobalUtils.LIBRARY_WIDTH];
		for (int i = 0; i < GlobalUtils.LIBRARY_HEIGHT; i++) {
			for (int j = 0; j < GlobalUtils.LIBRARY_WIDTH; j++) {
				node = new Node();
				node = GlobalUtils.mapLibrary[i][j];
				lybrary.map[i][j] = node;
			}
		}
		inicialSearch = new SearchGrid(lybrary.map,this,"BEGIN", inicialPositionY, inicialPositionX, finalPositionY, finalPositionX,searchBean);
		finalSearch = new SearchGrid(lybrary.map,this,"END", finalPositionY, finalPositionX, inicialPositionY, inicialPositionX,searchBean);
		
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
		System.out.println("Acabou  o monitor");
		
//		

	}
	public static void stopAllTasks(){
		if(inicialSearch.isAlive()){System.out.println("teve q matar a BEGIN");inicialSearch.stop();}
		if(finalSearch.isAlive()){System.out.println("teve q matar a END");finalSearch.stop();}
	}
	
}
