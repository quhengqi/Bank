package com.hy.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	public static void main(String[] args) throws IOException {
		InetAddress  address =InetAddress.getByName("localhost");
		int port = 8687;
		Socket socket = new Socket(address,port);
		OutputStream os =socket.getOutputStream();
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		byte[] data = "name:mike;password:123".getBytes();
		try{
			os.write(data);
			os.flush();
			socket.shutdownOutput();
			System.out.println("Client message :"+br.readLine());
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(os != null)os.close();
			if(br != null)br.close();
			if(isr != null)isr.close();
			if(is != null)is.close();	
			socket.close();
		}
	}
}
