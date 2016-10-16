package com.cloud.network;
import java.net.*;
import java.io.*;
import java.util.*;

public class TcpConnection implements Runnable {
	private Socket socket;
	private DataInputStream in ;
	 private DataOutputStream out ;
	 private boolean listening ;
	 private Thread listener =null;
	 private String id ;
	 private boolean exit = false;
	
	public TcpConnection(Socket socket,String signal,boolean start , String id) throws IOException{
		useSignal(signal);
		this.socket = socket;
		this.in = new DataInputStream(socket.getInputStream());
		this.out = new DataOutputStream(socket.getOutputStream());
		this.listening = start;
		this.id = id;
		if(start){
			 listener = new Thread(this , "listener-"+id);
			 listener.start();
		}
	}
	
	public Thread.State getState() {
	   if(listener!=null)
        return listener.getState();
		else return null;
    }
	
	public void  startListening(){
		if(listening){
			if(listener!=null){
				listener.start();
			}
			else{
				listener = new Thread(this , "listener-"+id);
				listener.start();
			}
		}
	}
	
	public void useSignal(String string){
		
	}
	
	public void run() {
		 
		while(!exit){
			try{
				String todo = in.readUTF();
				if(!exit)
				dowithString(todo);
				} catch(Exception e){
					this.listening = false;
                    System.out.println(e.toString());
				}
		}
	
	}
	
	public void t_stop (){
		exit = true;
		listening = false;
	}
	
     public void f_stop(){
		 listening = false;
		 exit = true;
		 if(listener !=null)
		 listener.stop();
	 }
	 
	 public void writeBack(String str) throws IOException{
		 out.writeUTF(str);
                
	 }
	 
	 public void dowithString(String todo){
		 
	 }
}