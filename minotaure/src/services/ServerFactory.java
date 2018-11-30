package services;

import network.Client;
import network.Server;
import type.AbstractServer;


public class ServerFactory {
	
	public static AbstractServer getServerInstance(String type) {
		if(type.equals("host")) {
			return new Server();
		}
		return new Client();
	}

}
