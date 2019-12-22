package _02_Chat_Application;

import java.awt.Dimension;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Client extends JPanel {
	String ip;
	int port;
	
	DataOutputStream dos;
	DataInputStream dis;
	JButton button;
	Client(String ip,int port) {
		this.ip = ip;
		this.port = port;
		JTextField tf = new JTextField();
		tf.setPreferredSize(new Dimension(100, 20));
		button = new JButton("Send");
		button.addActionListener((e)->{
			sendMessage(tf.getText());
		});
		add(tf);
		add(button);
	}
	public void Start() {
		try {
			Socket connection = new Socket(ip,port);
			dos = new DataOutputStream(connection.getOutputStream());
			dis = new DataInputStream(connection.getInputStream());
			System.out.println("Connected");
			while(connection.isConnected()) {
				JOptionPane.showMessageDialog(null,"Message From Server:"+dis.readUTF());
				System.out.println("Closing");
			}
			System.out.println("hi");
			connection.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void sendMessage(String s) {
		try {
			dos.writeUTF(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}