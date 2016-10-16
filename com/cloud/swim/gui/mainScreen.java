package gossip.gui;
import javax.swing.*;  
import gossip.group.Node;
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
		
		JTextField textField = new JTextField(20);
		JButton b=new JButton("start");//create button  
		//b.setBounds(130,100,100, 40);
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {     
					String ip = textField.getText();
					System.out.println(ip);	
					GossipPanel goosiptable = new GossipPanel();
					dispose();
			}});
		JButton plus = new JButton("+");
		JButton minus = new JButton("-");
			controlPanel.add(textField);
			controlPanel.add(b);
			
	}
	
	public static void main(String[] args) {  
			new mainScreen();  
		}

	}