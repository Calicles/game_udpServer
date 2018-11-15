package services;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Map_reader {
	
	public static int[][] readMap(){
		int[][] map= null;
		
		try(BufferedReader reader= 
				new BufferedReader(new FileReader(new File("ressources/map/map.txt")))){
			
			String[] line;
			String[] bounds= reader.readLine().split(" ");
			int iMax= Integer.parseInt(bounds[0]);
			int jMax= Integer.parseInt(bounds[1]);
			map= new int[iMax][jMax];
			
			for(int i= 0; i < iMax; i++) {
				
				line= reader.readLine().split(" ");
				for(int j= 0; j < jMax; j++) {
					map[i][j]= Integer.parseInt(line[j]);
				}
			}
			reader.close();
			
		}catch(Throwable t) {System.out.println(t);}
		
		return map;
	}
	
	public static HashMap<Integer, BufferedImage> readTileSet(){
		HashMap<Integer, BufferedImage> tileSet= new HashMap<>();
		
		try(BufferedReader reader= 
				new BufferedReader(new FileReader(new File("ressources/map/tileSet.txt")))){
			
			BufferedImage image;
			String[] line;
			
			int bounds=  Integer.parseInt(reader.readLine());
			int number;
			
			for(int i= 0; i < bounds; i++) {
				
				line= reader.readLine().split(" ");
					number= Integer.parseInt(line[0]);
					image= ImageIO.read(new File(line[1]));
					tileSet.put(number, image);
					
			}
			reader.close();
			
		}catch(Throwable t) {System.out.println(t);}
		
		return tileSet;
	}

}
