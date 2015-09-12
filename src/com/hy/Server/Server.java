package com.hy.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
public static void main(String[] args) throws IOException {
	@SuppressWarnings("resource")
	ServerSocket ss = new ServerSocket(8687);
	System.out.println("***The Server is Starting***");
	while(true){
		Socket socket = ss.accept();
		ServerThread st = new ServerThread(socket);
		st.start();
	}
}
}
