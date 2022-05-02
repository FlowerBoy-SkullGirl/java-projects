import javax.sound.midi.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class MiniMusicPlayer {
	
	static JFrame frame = new JFrame("My First Music Video");
	static MyMusicVideo musicVid;	

	public static void main(String[] args){
		MiniMusicPlayer mini = new MiniMusicPlayer();
		mini.go();
	}
	
	public void setUpGui(){
		musicVid = new MyMusicVideo();
		frame.setContentPane(musicVid);
		frame.setBounds(30,30,300,300);
		frame.setVisible(true);
	}

	public void go(){
		setUpGui();

		try{
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			
			int[] eventsToListen = {127}; //This is an array of events, only event 127 to listen
			sequencer.addControllerEventListener(musicVid, eventsToListen);

			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();
			
			int r = 0;
			for (int i = 0; i < 61; i += 4){
				r = (int) ((Math.random() * 50) + 30);

				track.add(makeEvent(144,1,r,100,i)); //Note on
				track.add(makeEvent(176,1,127,0,i)); //Nothing event of 176(Controller listener) with event number 127
				track.add(makeEvent(128,1,r,100,(i+2))); //note off
			}
				sequencer.setSequence(seq);
				sequencer.setTempoInBPM(120);
				sequencer.start();
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}

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
				Graphics2D g2 = (Graphics2D) g; //cast graphics
			
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

	public static MidiEvent makeEvent(int command, int chan, int one, int two, int tick)
	{
		MidiEvent event = null;
		try{
			ShortMessage a = new ShortMessage();
			a.setMessage(command, chan, one, two);
			event = new MidiEvent(a, tick);
		}catch (Exception e) {}
		return event;
	}
}
