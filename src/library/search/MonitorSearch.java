package library.search;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import library.utils.GlobalUtils;

public class MonitorSearch {
		public static String wichSearchFoundTheMap;
		public static SearchGrid inicialSearch;
		public static SearchGrid finalSearch;
	
	public void startSearch(int inicialPositionX,int inicialPositionY, int finalPositionX,int finalPositionY){
		inicialSearch = new SearchGrid("BEGIN", inicialPositionX, inicialPositionY, finalPositionX, finalPositionY);
		finalSearch = new SearchGrid("END", finalPositionX, finalPositionY, inicialPositionX, inicialPositionY);
		
		inicialSearch.start();
		finalSearch.start();
		
		while(!GlobalUtils.stopAllOtherTasks);
		
		if(inicialSearch.isAlive()){System.out.println("teve q matar a BEGIN");inicialSearch.interrupt();}
		if(finalSearch.isAlive()){System.out.println("teve q matar a END");finalSearch.interrupt();}
		

	}
	public static void stopAllTasks(){
		if(inicialSearch.isAlive()){System.out.println("teve q matar a BEGIN");inicialSearch.stop();}
		if(finalSearch.isAlive()){System.out.println("teve q matar a END");finalSearch.stop();}
	}
	
}
