package _02_Chat_Application;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import _01_Intro_To_Sockets.server.ServerGreeter;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame {
	public static void main(String[] args) {
		new ChatApp();
	}
	ChatApp() {
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION) {
			setTitle("Server");
			Server s = new Server(8080);
			JOptionPane.showMessageDialog(null, "Server started at: "+s.getIPAddress()+"Port: 8080");
			add(s);
			this.setVisible(true);
			this.pack();
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			s.start();
		} else {
			setTitle("Client");
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtStr);
			Client c = new Client(ipStr,port);
			add(c);
			this.setVisible(true);
			this.pack();
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			c.Start();
		}
	}
}