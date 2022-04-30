import javax.swing.*;

public class SimpleGui{
	public void changeButtonText()
	{
		button.setText("I've been clicked");
	}
	public static void main(String[] args)
	{
		//Make a java window
		JFrame frame = new JFrame();
		JButton button = new JButton("click me");
		
		//Makes program quit when window closes
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Get the window and the JButton object to it
		frame.getContentPane().add(button);
		
		frame.setSize(300, 300);
		
		frame.setVisible(true);
	}
}
