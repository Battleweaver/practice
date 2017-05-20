package practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	
	private static Socket socket;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			String host = "localhost";
			int port = 25000;
			InetAddress address = InetAddress.getByName(host);
			socket = new Socket(address, port);
			
			//Send message to server
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);
			
			String number = "2";
			
			String sendMessage = number + "\n";
			bw.write(sendMessage);
			bw.flush();
			System.out.println("Sent to server" + sendMessage);
			
			//Get return message
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String message = br.readLine();
			System.out.println("Message recieved from the server:" + message);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			//Closing socket
			try
			{
				socket.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
