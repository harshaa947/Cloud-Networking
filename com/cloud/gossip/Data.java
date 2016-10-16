 package com.cloud.gossip;
 import com.cloud.gossip.group.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.*;

 public class Data {
	 public HashMap<String , Node> memberlist;  
	 private static Data Datains ;
	 public  String ip;
	 public String name;
	 public int port;
	 public String id;
	 public ArrayList<String> notifications;
	 private Data(){
		 memberlist = new HashMap<String,Node>();
		 notifications = new ArrayList<String>();
	 }
	 
	 public  synchronized void updateList(ArrayList<Node> nodelist){
		 for(int i=0;i<nodelist.size();i++){
			 String id1 = nodelist.get(i).id;
			 Node temp = memberlist.get(id1);
			 if(temp == null){

				 if(nodelist.get(i).id.equals(this.id))
					 continue;
				 Node check = nodelist.get(i);
				 notifications.add("node added ! "+check.name+" "+check.ip+" "+check.port);
				 memberlist.put(id1,nodelist.get(i));
			 }
			 else{
				 
				 temp.updateHeartBeat(nodelist.get(i).heartbeat);
			 }
		 }
	 }
	 
	 public synchronized void update(Node node){
		 String id1 = node.id;
		 Node temp = memberlist.get(id1);
			 if(temp == null){
				 notifications.add("node added ! "+node.name+" "+node.ip+" "+node.port);
				 memberlist.put(id1,node);
			 }
	 }
	 
	 public synchronized JSONObject getJsonString(){
		 try{
		 JSONObject toreturn = new JSONObject();
		 toreturn.put("name",this.name);
		 toreturn.put("ip",this.ip);
		 toreturn.put("port",this.port);
		 JSONArray arry = new JSONArray();
		 Iterator it = memberlist.entrySet().iterator();
		 while (it.hasNext()) {
        HashMap.Entry pair = ( HashMap.Entry)it.next();
        Node temp =(Node) pair.getValue();
		if(temp==null)
			continue;
		JSONObject tempo = new JSONObject();
		tempo.put("name" , temp.name );
		tempo.put("ip",temp.ip);
		tempo.put("port",temp.port);
		tempo.put("heartbeat",temp.heartbeat);
		arry.put(tempo);
    }
		toreturn.put("Nodes" , arry);
		 return toreturn;}
		 catch(JSONException e){
			 e.printStackTrace();
		 }
		 return null;
	 }
	 
	 public static Data getInstance(){
		 if (Datains == null){
			 Datains = new Data();
			 
		 }
		 return Datains ; 
	 }
	 
 }