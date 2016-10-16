package gossip.group;

import java.net.*;
import java.io.*;
import java.util.*;

 public class UDPServer implements Runnable{
	 private DatagramSocket serverSocket;
	 private Thread server;
	 private boolean exit ;
	 byte[] recieveData;
	 byte[] sentData;
	 public UDPServer(String ip , int port , int packet_size){
		 InetAddress IPAddress = InetAddress.getByName(ip);
		  serverSocket = new DatagramSocket(port , IPAddress);
		  recieveData = new byte[packet_size];
		  sentData = new byte[packet_size];
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
   
   public void run(){
	    while(!exit){
		   
			DatagramPacket	receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			if(!exit)
			dowithpacket(receivePacket);	
			}
		}
		
	public void dowithpacket(DatagramPacket receivePacket){
		
	}
 
 }