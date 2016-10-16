package com.cloud.gossip.gui;
import javax.swing.*;  

import com.cloud.gossip.group.*;

import com.cloud.gossip.Data;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class mainScreen extends JFrame{
	JFrame screen; 
	 private JPanel controlPanel;
	private ArrayList<String> seednodes = new ArrayList<String>();
	private int seednodecount =0;
	public mainScreen(){  
			
	prepareGui();
	} 
 
	private void  prepareGui(){
		
			addWindowListener(new WindowAdapter() {
					 public void windowClosing(WindowEvent windowEvent){
						System.exit(0);
							}        
						});    
			controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		add(controlPanel);
		addComponents();
			setSize(400,500);  
			 
			setVisible(true);  
	}
	
	private void addComponents(){
		JTextField name = new JTextField(15);
		JTextField textField = new JTextField(15);
		JButton b=new JButton("start");//create button  
		ArrayList<JTextField> seeds = new ArrayList<JTextField>();
		b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {     
					String ip = textField.getText();
					System.out.println(ip);	
					GossipPanel goosiptable = new GossipPanel();
					ArrayList<Node> memberlist = new ArrayList<Node>();
					String ip_server = textField.getText();
					try{
					GossipServer gs = new GossipServer(ip_server,8000);
					for(int i=0;i<seeds.size();i++){
						JTextField temp = seeds.get(i);
						String str[] = temp.getText().split(":");
						Node tempn = new Node("seed_"+i,str[0],Integer.valueOf(str[1]));
						memberlist.add(tempn);
						
					}
					Data data = Data.getInstance();
					data.updateList(memberlist);
					data.ip=ip_server;
					data.port=8000;
					data.name= name.getText();
					data.id=ip_server+"::"+8000;
					GossipClient gc = new GossipClient();
					}catch(Exception ex){
						ex.printStackTrace();
					}
					dispose();
			}});
		JButton plus = new JButton("+");
		JButton minus = new JButton("-");
		controlPanel.add(name);
		controlPanel.add(textField);
		
		JLabel  addseedlabel= new JLabel("Add seed nodes :", JLabel.RIGHT);
		controlPanel.add(addseedlabel);
		
			
		plus.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JTextField temp = new JTextField(15);
				seeds.add(temp);
				controlPanel.add(temp);
				System.out.println(temp);
				validate();
			}
		});
		minus.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(seeds.size()>0){
				JTextField temp = seeds.remove(seeds.size()-1);
				seeds.remove(temp);
				controlPanel.remove(temp);
				validate();
				}
			}
		});
		
		controlPanel.add(plus);
		controlPanel.add(minus);
		controlPanel.add(b);
		
	
	}
	
	public static void main(String[] args) {  
			new mainScreen();  
		}

	}