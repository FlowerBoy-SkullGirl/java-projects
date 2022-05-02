import java.awt.*;
import javax.swing.*;

class MyDrawPanel extends JPanel{
	public void paintComponent(Graphics g)
	{
		//If you want to cast Graphics as Graphics2d
		//Graphics2d g2d = (Graphics2d) g;
		int red = (int) (Math.random() * 255);
		int green = (int) (Math.random() * 255);
		int blue = (int) (Math.random() * 255);

		Color randCol = new Color(red, green, blue);

		g.setColor(randCol);
		g.fillRect(20,50,100,100);
		/*
		Image image = new ImageIcon("Filen.ext").getImage();
		g.drawImage(image,3,4,this); //x and y coords 3,4
		
		this.getWidth(), this.getHeight(); //gets size of Panel
		Color color = new Color(r, g, b); //int values rgb
		*/
	}
}
