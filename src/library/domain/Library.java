package library.domain;

import java.util.ArrayList;

public class Library {
    public static Integer idLibrary;

    public static Integer sizeX;
    public static Integer sizeY;

    public static ArrayList<Bookshelf> bookShelves;
    public static ArrayList<QrCodeMark> qrCodesMarks;
	
    public static Node[][] map;
	
	public static void Mapping(int newSize_X, int newSize_Y){
		sizeX = newSize_X;
		sizeY = newSize_Y;
		map = new Node[sizeX][sizeY];
		
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				map[i][j] = Node.getNodeByPosition(i,j);
							
			}
			
		}
	}
		
 }