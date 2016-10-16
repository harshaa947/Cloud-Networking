 package gossip.group;

import java.net.*;
import java.io.*;
import java.util.*;

 public class UDPClient {
	 private DatagramSocket clientSocket ;
	 int sentPackets;
	 byte[] sendData;
	 public UDPClient(int packetSize){
	  clientSocket = new DatagramSocket();
	  clientSocket.setSoTimeout(100);
	  sendData = new byte[packetSize];
	  sentPackets=0;
	 }
	 public boolean sentData(String payload , String ip , int port){
	  InetAddress IPAddress = InetAddress.getByName(ip);
	  sendData = payload.getBytes();
	  DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
      clientSocket.send(sendPacket);
	  sentPackets++;
	  return true;
	 }
	 
	 public closeSocket(){
		 if(clientSocket!=null){
			clientSocket.close(); 
		 }
	 }
	 
	 
 }