package com.cloud.gossip.gui;
import javax.swing.*;  

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GossipPanel extends JFrame{
	   private JPanel controlPanel;

	public GossipPanel(){
		prepareGui();
	}
	
	private void prepareGui(){
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
		JLabel  notificationlabel= new JLabel("Notifications :", JLabel.RIGHT);
		JTextField textField = new JTextField(20);
		JButton b=new JButton("Add to cluster");//create button  
		b.setBounds(130,100,100, 40);
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {     
					String ip = textField.getText();
					System.out.println(ip);	
					
			}});
			
		JTextField notification = new JTextField(400);
		notification.setText("java2s.com");
		notification.setEditable(false);
		controlPanel.add(textField);
		controlPanel.add(b);
		controlPanel.add(notificationlabel);
		controlPanel.add(notification);
	}
}