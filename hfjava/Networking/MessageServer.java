import java.io.*;
import java.net.*;

public class MessageServer{

	String[] messageList = {"Hello", "It's me, Elroy", "Stop, in the name of jars", "Wow, that's hot"};

	public void go()
	{
		try{
			ServerSocket serverSock = new ServerSocket(4242);

			while (true){
				Socket sock = serverSock.accept();

				PrintWriter writer = new PrintWriter(sock.getOutputStream());
				String message = getMessage();

				writer.println(message);
				writer.close();
				System.out.println(message);
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}

	private String getMessage()
	{
		int random = (int) (Math.random() * messageList.length);
		return messageList[random];
	}

	public static void main(String[] args)
	{
		MessageServer server = new MessageServer();
		server.go();
	}
}
