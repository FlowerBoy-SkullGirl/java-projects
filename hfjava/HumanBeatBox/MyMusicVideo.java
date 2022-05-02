class MyMusicVideo extends JPanel implements ControllerEventListener{
	boolean msg = false; //set to true when event is captured
	
	public void controlChange(ShortMessage event)
	{
		msg = true;
		repaint();
	}
	
	public void paintComponent(Graphics g)
	{
		if (msg) { //if we receive an event
			Graphics2d g2 = (Graphics2d) g; //cast graphics
			
			int red = (int) (Math.random() * 250);
			int green = (int) (Math.random() * 250);
			int blue = (int) (Math.random() * 250);
			
			g.setColor(new Color(red, green, blue));
			
			int height = (int) ((Math.random() * 120) + 10);
			int width = (int) ((Math.random() * 120) + 10);
			int x = (int) ((Math.random() * 40) + 10);
			int y = (int) ((Math.random() * 40) + 10);
			
			g.fillRect(x,y,height,width);
			msg = false;
		}
	}
}
