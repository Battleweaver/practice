package practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
	
	private static Socket socket;
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		try 
		{
			int port = 25000;
			ServerSocket serverSocket = new ServerSocket(port);
			
			while (true) 
			{
				//Reading message from client
				socket = serverSocket.accept();
				InputStream is = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String number = br.readLine();
				System.out.println("Message recieved from client is" + number);
				
				//Multiplying number by 2 and forming return message
				
				String returnMessage;
				
				try 
				{
					int numberInIntFormat = Integer.parseInt(number);
					int returnValue = numberInIntFormat * 2;
					returnMessage = String.valueOf(returnValue) + "\n";
				}
				catch (NumberFormatException e) {
					returnMessage = "Please send a proper number\n";
					
				}
				
				//Response back to client
				OutputStream os = socket.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os);
				BufferedWriter bw = new BufferedWriter(osw);
				bw.write(returnMessage);
				System.out.println("sent to the client is " + returnMessage);
				bw.flush();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				socket.close();
			}
			catch(Exception e) {}
		}
	}

}
