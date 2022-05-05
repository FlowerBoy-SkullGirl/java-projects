import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Swings{

	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JTextField textField;
	JButton button = new JButton("Click");

	public void go()
	{
		textListener listener = new textListener();

		textField = new JTextField(20); //20 columns wide
		//textField.requestFocus();
		textField.addActionListener(listener);

		//Set the layout of the panel
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	
		panel.setBackground(Color.darkGray);
		//Set the panel as the Frame's content pane
		panel.add(textField);
		panel.add(button);
		frame.getContentPane().add(panel);
	
		frame.setSize(300,300);
		frame.setVisible(true);
		
	}

	public static void main(String[] args)
	{
		Swings gui = new Swings();
		gui.go();	
	}

	//Action listener for text box
	class textListener implements ActionListener{
		public void actionPerformed(ActionEvent event)
		{
			System.out.println(textField.getText());
		}
	}
}
