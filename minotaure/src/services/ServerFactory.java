package services;

import network.Server;
import type.AbstractServer;


public class ServerFactory {
	
	public static AbstractServer getServerInstance(int type) {
		if(type == 1) {
			return new Server();
		}
		return null;
	}

}
