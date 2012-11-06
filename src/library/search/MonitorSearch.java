package library.search;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import library.utils.GlobalUtils;

public class MonitorSearch {
		public static String wichSearchFoundTheMap;
		public static SearchGrid inicialSearch;
		public static SearchGrid finalSearch;
	
	public void startSearch(int inicialPositionY,int inicialPositionX, int finalPositionY,int finalPositionX) throws InterruptedException{
		inicialSearch = new SearchGrid("BEGIN", inicialPositionY, inicialPositionX, finalPositionY, finalPositionX);
		finalSearch = new SearchGrid("END", finalPositionY, finalPositionX, inicialPositionY, inicialPositionX);
		
		inicialSearch.start();
		finalSearch.start();
////		
		while(!GlobalUtils.stopAllOtherTasks);
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
