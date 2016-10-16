package com.cloud.gossip.group;
 import java.sql.Timestamp;
public class Node{
	public String name ="";
	public String ip ="";
	public int port =0;
	public int heartbeat=0;
	public Timestamp timestamp;
	public String id;
	public Node(String name , String ip , int port){
		this.name = name ;
		this.ip = ip ;
		this.port = port;
		this.id = ip+"::"+port;
	}
	
	public void updateHeartBeat(int beat){
		if(beat > heartbeat){
			heartbeat = beat ;
			timestamp = new Timestamp(System.currentTimeMillis());
		}
		else heartbeat ++;
	}
	public String toString(){
		return id;
	}
}