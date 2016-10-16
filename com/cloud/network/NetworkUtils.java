 package com.cloud.network;
import java.net.*;
import java.io.*;
import java.util.*;

 public class NetworkUtils {
	 public static String getRandomipv4Address(){
		 Random r = new Random();
		return r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
	 }
	 
	 public static ArrayList<String> getMyself(){
		ArrayList<String> ips = new ArrayList<String>();
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while (interfaces.hasMoreElements()) {
            NetworkInterface iface = interfaces.nextElement();
            // filters out 127.0.0.1 and inactive interfaces
            if (iface.isLoopback() || !iface.isUp())
                continue;

            Enumeration<InetAddress> addresses = iface.getInetAddresses();
            while(addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();
                 ips.add(addr.getHostAddress());
				}
			}
			} catch (SocketException e) {
				throw new RuntimeException(e);
			}
			return ips;
	 }
	 
	 public static  int getfreePort(){
		 int port =0;
		 try{
			         ServerSocket socket = new ServerSocket(port);
					port = socket.getLocalPort();
					socket.close();
		 }
		 catch(IOException e){
			 e.printStackTrace();
		 }
		 return port ;
	 }
	 
 }