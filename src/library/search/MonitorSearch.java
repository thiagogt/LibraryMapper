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
		private static SearchGrid inicialSearch;
		private static SearchGrid finalSearch;
		public volatile Boolean stopAllOtherTasks = false;
		private SearchBean searchBean;
		private volatile Library library;
		public volatile Node middleNode;
		
	public MonitorSearch(SearchBean searchBean2) {
			this.searchBean = searchBean2;
			if(this.searchBean.getPathNodeSearch()!= null){
				this.searchBean.setPathNodeSearch(new ArrayList<Node>());
			}
			this.library  = new Library();
	}
	public void startSearch(int inicialPositionY,int inicialPositionX, int finalPositionY,int finalPositionX) throws InterruptedException{
		Node node;
		stopAllOtherTasks = false;
		inicialSearch = new SearchGrid(library.map,this,"BEGIN", inicialPositionY, inicialPositionX, finalPositionY, finalPositionX,searchBean);
		finalSearch = new SearchGrid(library.map,this,"END", finalPositionY, finalPositionX, inicialPositionY, inicialPositionX,searchBean);
		
		inicialSearch.start();
		finalSearch.start();
////		
//		while(!stopAllOtherTasks);
		inicialSearch.join();
		finalSearch.join();
		if(inicialSearch.isAlive() || finalSearch.isAlive()){

			inicialSearch.interrupt();
			finalSearch.interrupt();
		}
		buildBFSPath(middleNode);
	}
		public void buildBFSPath(Node middleNode) {
			
			ArrayList<Node> firstHalfList = CreateListFromBeginToMidle(middleNode);
			ArrayList<Node> secondHalfList = CreateListFromMiddleToEnd(middleNode);
			firstHalfList.addAll(secondHalfList);
			
			
				ArrayList<Node> pathMap=new ArrayList<Node>();
				pathMap.addAll(firstHalfList);
				this.searchBean.setPathNodeSearch(pathMap);
			
			
		}

		public static ArrayList<Node> CreateListFromMiddleToEnd(Node middleNode) {
			ArrayList<Node> secondHalfList = new ArrayList<Node>();
			Node actualNode = middleNode.getParentFromEndNode(); 
			while(actualNode != null){
				secondHalfList.add(actualNode);
				actualNode = actualNode.getParentFromEndNode();
			}
			return secondHalfList;
			
					
		}
		public  ArrayList<Node> CreateListFromBeginToMidle(Node middleNode) {
			ArrayList<Node> firstHalfList = new ArrayList<Node>();
			Node actualNode = middleNode; 
			while(actualNode!=null){
				firstHalfList.add(actualNode);
				actualNode = actualNode.getParentFromBeginNode();
			}
			return reverseList(firstHalfList);
		}
//		

		public static ArrayList<Node> reverseList(ArrayList<Node> secondHalfList) {
			ArrayList<Node> reverseList = new ArrayList<Node>();
			for (int i = secondHalfList.size() -1; i >= 0; i--) {
				reverseList.add(secondHalfList.get(i));
			}
			return reverseList;
		}
	public Library getLibrary() {
		return library;
	}
	public void setLibrary(Library library) {
		this.library = library;
	}
	
	
}
