import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyAnimationFrame{
	int x = 0;
	int y = 0;
	public static void main(String[] args)
	{
		MyAnimationFrame gui = new MyAnimationFrame();
		gui.go();
	}
	//So we can use non static variables in non-static method
	public void go()
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyAnimationPanel aPanel = new MyAnimationPanel();
		frame.getContentPane().add(BorderLayout.CENTER, aPanel);
		
		frame.setSize(300, 300);
		frame.setVisible(true);
		
		x = 0;
		y = frame.getHeight();

		//Loop that moves x and y coords until border is hit
		while (true){
			try{
			Thread.sleep(50);
			}catch (Exception ex){}

			if (x < frame.getWidth()){
				x++;
				y--;
				frame.repaint();
			}else{
				while (x > 0){
					try{
					Thread.sleep(50);
					}catch (Exception ex){}
					x--;
					y++;
					frame.repaint();
				}
			} 
		}
	}

	//Inner class so that it can share x and y variables without being passed in a method
	public class MyAnimationPanel extends JPanel{

	public void paintComponent(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(0,0,this.getWidth(),this.getHeight());

		g.setColor(Color.orange);
		g.fillOval(x,y,100,100);
	}
	}
}
