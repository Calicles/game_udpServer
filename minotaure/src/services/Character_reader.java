package services;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import javax.imageio.ImageIO;

import model.Coordinates;

public class Character_reader {
	
	public static HashMap<Integer, BufferedImage[]> readCharactereAnimation(String url, Coordinates position){
		HashMap<Integer, BufferedImage[]> map= new HashMap<>();
		
		try(BufferedReader reader= new BufferedReader(
				new FileReader(new File(url)))) {
					
			String[] bounds= reader.readLine().split(" ");
			String line;
			int directionNumber= Integer.parseInt(bounds[0]);
			int imagePerDirection= Integer.parseInt(bounds[1]);
			BufferedImage[] tab= null;
			
			for(int i= 0; i < directionNumber; i++) {
				
				tab= new BufferedImage[imagePerDirection];
				
				for(int j= 0; j < imagePerDirection; j++) {
					line= reader.readLine();
					tab[j]= ImageIO.read(new File(line));
				}
				
				map.put(i, tab);
			}
			line= reader.readLine();
			position.setCoordinates(readCoordinates(line));
			
		}catch(Throwable t) {System.out.print(t);}
		return map;
	}
	
	public static Coordinates readCoordinates(String line) {
		String[] buff= line.split(" ");
		int x= Integer.parseInt(buff[0]);
		int y= Integer.parseInt(buff[1]);
		return new Coordinates(x, y);
	}

}
