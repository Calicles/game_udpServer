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
		
		copy(xArray, yArray, res, 0);
		
		return res;
	}
	
	public static Coordinates toCoordinates(byte[] bytes) {
		int x, y;
		byte[] xArray= new byte[4], yArray= new byte[4];
		copyOut(bytes, xArray, yArray);

		ByteBuffer buffer= ByteBuffer.wrap(xArray);
		x= buffer.getInt();
		
		ByteBuffer buffer2= ByteBuffer.wrap(yArray);
		y= buffer2.getInt();
		
		return new Coordinates(x, y);
	}
	
	public static void copy(byte[] src1, byte[] src2, byte[] dest, int start) {
		for(int i= start; i < dest.length; i++) {
			if(i < src1.length) {
				dest[i]= src1[i];
			}else {
				dest[i]= src2[i - src1.length];
			}
		}
		
	}
	
	public static void copyOut(byte[] src, byte[] dest1, byte[] dest2) {
		for(int i= 0; i< src.length; i++) {
			if(i < dest1.length) {
				dest1[i]= src[i];
			}else {
				dest2[i - dest1.length]= src[i];
			}
		}
	}
	

}
