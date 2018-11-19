package services;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

import model.Coordinates;

public class Byte_translator {
	
	/**
	 * traduit une coordonnée en tableau de byte (octet).
	 * @param coordinates
	 * @return res tableau de byte
	 */
	public static  byte[] toByteArray(Coordinates position) {
		byte[] res, xArray, yArray;
		res= new byte[8];
		int x= position.getX(), y= position.getY();
		ByteBuffer buffer= ByteBuffer.allocate(4);
		buffer.putInt(x);
		xArray= buffer.array();
		
		ByteBuffer buffer2= ByteBuffer.allocate(4);
		buffer2.putInt(y);
		yArray= buffer2.array();
		
		copy(xArray, res, 0, xArray.length);
		int j= 4;
		for(int i=0;i<yArray.length;i++) {
			res[j]= yArray[i];
			j++;
		}
		
		return res;
	}
	
	public static Coordinates toCoordinates(byte[] bytes) {
		int x, y;
		byte[] xArray= new byte[4], yArray= new byte[4];
		copy(bytes, xArray, 0, xArray.length);
		int j= 4;
		for(int i= 0; i< yArray.length; i++) {
			yArray[i]= bytes[j];
			j++;
		}
		ByteBuffer buffer= ByteBuffer.wrap(xArray);
		x= buffer.getInt();
		
		ByteBuffer buffer2= ByteBuffer.wrap(yArray);
		y= buffer2.getInt();
		
		return new Coordinates(x, y);
	}
	
	public static void copy(byte[] src, byte[] dest, int start, int stop) {
		for(int i= start; i < stop; i++) {
			dest[i]= src[i];
		}
	}

}
