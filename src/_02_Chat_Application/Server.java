package _02_Chat_Application;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Server extends JPanel {
	private int port;
	ServerSocket server;
	Socket connection;

	DataInputStream dis;
	DataOutputStream dos;
	JButton button;
	
	public Server(int port) {
		this.port = port;
		button = new JButton("Send");
		JTextField tf = new JTextField();
		tf.setPreferredSize(new Dimension(100, 20));
		add(button);
		add(tf);
		button.addActionListener((e)->{
			sendMessage(tf.getText());
		});
	}
	
	public void start() {
		try {
			server = new ServerSocket(port,100);
			System.out.println("Waiting for client");
			connection = server.accept();
			System.out.println("Connected to client");
			dos = new DataOutputStream(connection.getOutputStream());
			dis = new DataInputStream(connection.getInputStream());
			dos.flush();
			while(connection.isConnected()) {
				JOptionPane.showMessageDialog(null,"Message From Client:"+dis.readUTF());
			}
			connection.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sendMessage(String s) {
		try {
			dos.writeUTF(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getIPAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "ERROR!!!!!";
		}
	}
}