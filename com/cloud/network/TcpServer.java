package com.cloud.network;
import java.net.*;
import java.io.*;
import java.util.*;

 public class TcpServer implements Runnable{
	 private ServerSocket serverSocket;
	 private Thread server = null;
	 private boolean exit = false;
	 public TcpServer(int port  , int backlog,InetAddress ip) throws IOException
	 
   {
      serverSocket = new ServerSocket(port,backlog,ip);
      server = new Thread(this,"server");
	  server.start();
   }
   
   public Thread.State getState() {
	   if(server!=null)
        return server.getState();
		else return null;
    }
	
   public void t_stop(){
	   if(server !=null)
			exit = true;
   }
   
   public void t_start(){
	   if(server !=null)
		 server.start();
   }
   
   public void f_stop(){
	   if(server !=null)
			server.stop();
   }
   
	public void run()
   {
	   while(!exit){
	   try
         {
           // System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
			//System.out.println("Just connected to " + server.getRemoteSocketAddress());
			DataInputStream in = new DataInputStream(server.getInputStream());
			String signal = in.readUTF();
			new TcpConnection(server,signal,true,UUID.randomUUID().toString());
            
         }catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e)
         {
            e.printStackTrace();
            break;
         }
   }}
   
 }