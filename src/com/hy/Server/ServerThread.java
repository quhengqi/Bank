package com.hy.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	private Socket socket;
	public ServerThread(Socket socket){
		this.socket = socket;
		System.out.print("IP :"+socket.getInetAddress()+"  Port :"+socket.getPort()+" STATE :");
	}
	public void run(){
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader  bf =null;
		OutputStream os = null;
		PrintWriter pw = null;
		try{
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			bf = new BufferedReader(isr);
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
			String info =bf.readLine();
			String [] data = info.split(";");
			String[] name = data[0].split(":");
			String[] password = data[1].split(":");
			if(name[1].equals("mike")&&password[1].equals("123")){
				System.out.println("success");
				pw.write("Login Success!");
				pw.flush();
			}
			else{
				System.out.println("failed");
				pw.write("Login Failed!");
				pw.flush();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try {
				if(bf != null)bf.close();
				if(isr != null)isr.close();
				if(is != null)is.close();
				if(pw != null)pw.close();
				if(os != null)os.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
