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
		int test= 5;
		ByteBuffer buffer= ByteBuffer.allocate(4);
		buffer.putInt(test);
		return buffer.array();
	}
	
	public static int toCoordinates(byte[] bytes) {
		ByteBuffer buffer= ByteBuffer.wrap(bytes);
		return buffer.getInt();
	}
	
	public static void copy(byte[] src, byte[] dest, int start) {
		for(int i= start; i < src.length; i++) {
			dest[i]= src[i];
		}
	}

}
