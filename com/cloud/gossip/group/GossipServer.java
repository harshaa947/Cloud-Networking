 package com.cloud.gossip.group;
import com.cloud.network.UDPServer;
import com.cloud.gossip.group.Node;
import com.cloud.gossip.Data;
import java.io.*;
import java.net.*;
import org.json.*;
import java.util.ArrayList;
public class GossipServer extends UDPServer {
	
	public GossipServer(String ip , int port ) throws IOException,UnknownHostException{
			super(ip,port,1024);
		
	}
	
	public void dowithpacket(DatagramPacket receivePacket){
		try{
		String sentence = new String( receivePacket.getData());
		//System.out.println(sentence);
		JSONObject jsonObj = new JSONObject(sentence);
		JSONArray  nodelist = jsonObj.getJSONArray("Nodes");
		ArrayList<Node> nodes = new ArrayList<Node>();
		for (int i=0;i< nodelist.length();i++){
			JSONObject jo = nodelist.getJSONObject(i);
			Node temp = new Node(jo.getString("name"),jo.getString("ip"),jo.getInt("port"));
			temp.updateHeartBeat(jo.getInt("heartbeat"));
			nodes.add(temp);
		}
		Data.getInstance().updateList(nodes);
		Node temp = new Node(jsonObj.getString("name"),jsonObj.getString("ip"),jsonObj.getInt("port"));
		temp.updateHeartBeat(1);
		Data.getInstance().update(temp);
	}catch(Exception e){
		e.printStackTrace();
	}
}

}