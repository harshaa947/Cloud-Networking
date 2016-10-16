 package com.cloud.gossip.group;
import com.cloud.network.*;
import com.cloud.gossip.group.Node;
import com.cloud.gossip.Data;
import java.net.*;
import java.io.*;
import java.util.Random;
import org.json.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GossipClient implements Runnable {
	public Thread client ;
	private boolean exit=false;
	public UDPClient clientd;
	public GossipClient(){
		try{
		clientd = new UDPClient(1024);
		}
		catch (SocketException e){
			e.printStackTrace();
		}
		client = new Thread(this,"client");
		client.start();
	}
	
	
	public Thread.State getState() {
	   if(client!=null)
        return client.getState();
		else return null;
    }
	
   public void t_stop(){
	   if(client !=null)
			exit = true;
   }
   
   public void t_start(){
	   if(client !=null)
		 client.start();
   }
   
   public void f_stop(){
	   if(client !=null)
			client.stop();
   }
   
   public void run(){
	   while(!exit){
		   JSONObject jo = Data.getInstance().getJsonString();
		   System.out.println(jo.toString());
		   Random r = new Random();
		   HashMap<String,Node> memberlist = Data.getInstance().memberlist;
		   
		   System.out.println(memberlist.size());
		   if(memberlist.size() >0){
		   int j = r.nextInt(memberlist.size());
		   int k = r.nextInt(memberlist.size());
		  Iterator it = memberlist.entrySet().iterator();
		  int i=0;
		 while (it.hasNext()) {
			 
			 if(i==j || i==k){
				 HashMap.Entry pair = ( HashMap.Entry)it.next();
				 Node temp =(Node) pair.getValue();
				 System.out.println(temp);
				 System.out.println(pair.getKey());
				 if(temp!=null)
				 clientd.sentData(jo.toString(),temp.ip,temp.port);
			 }
			 if(i>j && i>k){
				 break;
			 }
		   }}
		   try{
		   client.sleep(5000);
		   }
		   catch (InterruptedException e){
			   e.printStackTrace();
		   }
	   }
   }
}