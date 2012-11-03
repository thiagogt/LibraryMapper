package test;

import static org.junit.Assert.*;
import library.domain.Library;
import library.domain.Node;

import org.junit.Test;



public class LibraryTest {

	@Test
	public void testLibraryConstructor() {
		Library.Mapping(5,4);
		
		String type;
		for (int i = 0; i < 5; i++) {
				System.out.println("");
			for (int j = 0; j < 4; j++) {
				type = Library.map[i][j].getContentType();
				
				if(type.equals("Empty"))
					System.out.print("0"+" | ");
				if(type.equals("Forbidden"))
					System.out.print("x"+" | ");
				if(type.equals("QrCode"))
					System.out.print("qr"+" | ");
				if(type.equals("Shelf"))
					System.out.print("s"+" | ");
			}
			
		}
	}
	@Test
	public void testDeleteLibrary() {
		Library.deleteAllLibrary(2);
	}
	@Test
	public void testSelectLastId(){
		int id = Library.returnTheLastLibraryID();
		System.out.println("Esse ehe o id: "+id);
	}
	
}
