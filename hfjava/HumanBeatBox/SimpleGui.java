import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleGui implements ActionListener{
	JFrame frame;

	public void actionPerformed(ActionEvent event)
	{
		//button.setText("I've been clicked");
		frame.repaint(); //calls repaint Graphics for drawpanel
	}

	public static void main(String[] args)
	{
		SimpleGui gui = new SimpleGui();
		gui.go();
	}
	public void go()
	{
		//Make a java window
		frame = new JFrame();

		JButton button = new JButton("click me");
		
		button.addActionListener(this); //Adds gui as a listener

		//Makes program quit when window closes
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyDrawPanel drawPanel = new MyDrawPanel();

		//Get the window and the JButton object to it
		frame.getContentPane().add(BorderLayout.SOUTH, button);
		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
		
		frame.setSize(300, 300);
		
		frame.setVisible(true);
	}
}
