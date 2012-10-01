package library.search;

import library.utils.GlobalUtils;

public class MonitorSearch {
		public static String wichSearchFoundTheMap;
		public static SearchGrid inicialSearch;
		public static SearchGrid finalSearch;
	
	public void startSearch(int inicialPositionX,int inicialPositionY, int finalPositionX,int finalPositionY){
		inicialSearch = new SearchGrid("BEGIN", inicialPositionX, inicialPositionY);
		finalSearch = new SearchGrid("END", finalPositionX, finalPositionY);
		
		inicialSearch.start();
		finalSearch.start();
		
		while(!GlobalUtils.stopAllOtherTasks)System.out.println(".");
		
		if(inicialSearch.isAlive()){System.out.println("teve q matar a BEGIN");inicialSearch.stop();}
		if(finalSearch.isAlive()){System.out.println("teve q matar a END");finalSearch.stop();}
		

	}
	public static void stopAllTasks(){
		if(inicialSearch.isAlive()){System.out.println("teve q matar a BEGIN");inicialSearch.stop();}
		if(finalSearch.isAlive()){System.out.println("teve q matar a END");finalSearch.stop();}
	}
	
}
