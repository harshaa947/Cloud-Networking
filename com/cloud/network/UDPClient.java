package com.cloud.network;
import java.net.*;
import java.io.*;
import java.util.*;

 public class UDPClient {
	 private DatagramSocket clientSocket ;
	 int sentPackets;
	 byte[] sendData;
	 public UDPClient(int packetSize) throws SocketException{
	  clientSocket = new DatagramSocket();
	  clientSocket.setSoTimeout(100);
	  sendData = new byte[packetSize];
	  sentPackets=0;
	 }
	 public boolean sentData(String payload , String ip , int port){
		 try{
	  InetAddress IPAddress = InetAddress.getByName(ip);
	  sendData = payload.getBytes();
	  DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
      clientSocket.send(sendPacket);
		 sentPackets++;}
		 catch (Exception e){
			 e.printStackTrace();
			 return false;
		 }
	  return true;
	 }
	 
	 public void closeSocket(){
		 if(clientSocket!=null){
			clientSocket.close(); 
		 }
	 }
	 
	 
 }