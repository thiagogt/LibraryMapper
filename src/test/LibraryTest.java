package test;

import static org.junit.Assert.*;
import library.domain.Library;
import library.domain.Node;
import library.utils.GlobalUtils;

import org.junit.Test;



public class LibraryTest {

//	@Test
//	public void testLibraryConstructor() {
//		
//		Library library = new Library();
//		library.Mapping(GlobalUtils.LIBRARY_WIDTH,GlobalUtils.LIBRARY_HEIGHT);
//		GlobalUtils.mapLibrary = library.map;
//		String type;
//		for (int i = 0; i < GlobalUtils.LIBRARY_HEIGHT; i++) {
//				System.out.println("");
//			for (int j = 0; j < GlobalUtils.LIBRARY_WIDTH; j++) {
//				type = GlobalUtils.mapLibrary[i][j].getContentType();
//				
//				if(type.equals("Free"))
//					System.out.print("0"+" | ");
//				if(type.equals("Forbidden"))
//					System.out.print("x"+" | ");
//				if(type.equals("QrCode"))
//					System.out.print("qr"+" | ");
//				if(type.equals("Shelf"))
//					System.out.print("s"+" | ");
//			}
//			
//		}
//	}
	@Test
	public void testDeleteLibrary() {
		Library.deleteAllLibrary(12);
		
	}
	@Test
	public void testSelectLastId(){
		int id = Library.returnTheLastLibraryID();
		System.out.println("Esse ehe o id: "+id);
	}
	
}
