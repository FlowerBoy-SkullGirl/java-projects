import java.io.*;
import java.net.*;

public class MessageClient{

	public void go()
	{
		try{
			Socket s = new Socket("127.0.0.1",4242);

			InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
			BufferedReader reader = new BufferedReader(streamReader);

			String message = reader.readLine();
			System.out.println(message);

			reader.close();

		}catch(IOException ex){
			ex.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		MessageClient client = new MessageClient();
		client.go();
	}
}
