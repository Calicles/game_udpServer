package services;

import java.nio.ByteBuffer;

import model.Coordinates;

public class Byte_translator {
	
	/**
	 * traduit une coordonnée en tableau de byte (octet).
	 * @param coordinates
	 * @return res tableau de byte
	 */
	public static  byte[] toByteArray(Coordinates coordinates) {
		byte[] res= new byte[Integer.SIZE * 2];
		ByteBuffer buffer= ByteBuffer.allocate(4);
		
		buffer.putInt(coordinates.getX());
		buffer.flip();
		byte[] bytes= new byte[4];
		buffer.get(bytes);
		
		copy(bytes, res, 0);
		
		ByteBuffer buffer2= ByteBuffer.allocate(4);
		buffer2.putInt(coordinates.getY());
		buffer2.flip();
		buffer2.get(bytes);
		
		copy(bytes, res, bytes.length);
		
		
		return res;
	}
	
	public static Coordinates toCoordinates(byte[] bytes) {
		
		return null;
	}
	
	public static void copy(byte[] src, byte[] dest, int start) {
		for(int i= start; i < src.length; i++) {
			dest[i]= src[i];
		}
	}

}
