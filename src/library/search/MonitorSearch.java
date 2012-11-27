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
		System.out.println("Initial Positions: "+inicialPositionY+","+inicialPositionX);
		System.out.println("Final Positions: "+finalPositionY+","+finalPositionX);
		
		
//		for (int i = 0; i < GlobalUtils.LIBRARY_HEIGHT; i++) {
//			for (int j = 0; j < GlobalUtils.LIBRARY_WIDTH; j++) {
//				node = new Node();
//								
//				node = GlobalUtils.mapLibrary[i][j];
//				
//				library.map[i][j] = node;
//			}
//		}
		
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
//		{
//			System.out.println("to Aqui");
//			
//		}
//		
		System.out.println("Acabou  o monitor");
		buildBFSPath(middleNode);
	}
		public void buildBFSPath(Node middleNode) {
			
			ArrayList<Node> firstHalfList = CreateListFromBeginToMidle(middleNode);
			System.out.println("Comecou a primeira metade.");
			for (Node principalNodes : firstHalfList) {
				System.out.print(principalNodes.getPositionY()+" , "+principalNodes.getPositionX()+" - ");
		}
			ArrayList<Node> secondHalfList = CreateListFromMiddleToEnd(middleNode);
		System.out.println("Comecou a segunda metade.");
			for (Node principalNode : secondHalfList) {
				System.out.print(principalNode.getPositionY()+" , "+principalNode.getPositionX()+" - ");
		}
			firstHalfList.addAll(secondHalfList);
			
//			for (Node principalNode : firstHalfList) {
//				System.out.print(principalNode.getPositionX()+" , "+principalNode.getPositionY()+" - ");
//			}
			System.out.println();
			System.out.println("iniciando Impressao no GlobalUtils");
			
				ArrayList<Node> pathMap=new ArrayList<Node>();
				pathMap.addAll(firstHalfList);
				this.searchBean.setPathNodeSearch(pathMap);
				System.out.println("terminada a Impressao no GlobalUtils");
			
			
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
