import java.awt.*; //events
import javax.swing.*; //gui
import javax.sound.midi.*;
import java.util.*; //arraylist
import java.awt.event.*;

public class BeatBox implements MetaEventListener{

	JPanel mainPanel;
	ArrayList checkboxList;
	int bpm = 120;
	Sequencer sequencer;
	Sequence sequence;
	Track track;
	JFrame frame;
	
	String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat",
				"Acoustic Snare", "Crash Cymbal", "Hand Clap", "High Tom",
				"Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell",
				"Vibraslap", "Low-mid Tom", "High Agogo", "Open Hi Conga"};
	int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,68,47,67,63};
	
	public static void main(String[] args)
	{
		new BeatBox().buildGUI();
	}
	
	public void buildGUI()
	{
		frame = new JFrame("BeatBox");
		BorderLayout layout = new BorderLayout(); //default layout NESW
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); //creates margins for panel
		
		checkboxList = new ArrayList();
		Box buttonBox = new Box(BoxLayout.Y_AXIS); //box is some gui container
		
		JButton start = new JButton("Start");
		start.addActionListener(new MyStartListener());
		buttonBox.add(start);

		JButton stop = new JButton("Stop");
		stop.addActionListener(new MyStopListener());
		buttonBox.add(stop);

		JButton upTempo = new JButton("Tempo Up");
		upTempo.addActionListener(new MyUpTempoListener());
		buttonBox.add(upTempo);
		
		JButton downTempo = new JButton("Tempo Down");
		downTempo.addActionListener(new MyDownTempoListener());
		buttonBox.add(downTempo);
		
		Box nameBox = new Box(BoxLayout.Y_AXIS);
		for (int i = 0; i < 16; i++){
			nameBox.add(new Label(instrumentNames[i]));
		}

		background.add(BorderLayout.EAST, buttonBox);
		background.add(BorderLayout.WEST, nameBox);
	
		frame.getContentPane().add(background);
		
		GridLayout grid = new GridLayout(16, 16);
		grid.setVgap(1);
		grid.setHgap(2);
		mainPanel = new JPanel(grid);
		background.add(BorderLayout.CENTER, mainPanel);
	
		for (int i = 0; i < 256; i++){
			JCheckBox c = new JCheckBox();
			c.setSelected(false);
			checkboxList.add(c);
			mainPanel.add(c);
		}
		
		setUpMidi();
	
		frame.setBounds(50,50,300,300);
		frame.pack();
		frame.setVisible(true);

	}
	
	public void setUpMidi() {
		try{
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.addMetaEventListener(this);
			sequence = new Sequence(Sequence.PPQ,4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(bpm);
		}catch(Exception e) { e.printStackTrace(); }
	}

	public void buildTrackAndStart()
	{
		System.out.println("This is working!");
		int[] trackList = null; //make sure initialized to zero
		
		sequence.deleteTrack(track); //always start a new track
		track = sequence.createTrack();
	
		for (int i = 0; i < 16; i++){
			trackList = new int[16]; //16 instruments
			
			int key = instruments[i];
			
			for (int j = 0; j < 16; j++){ //16 beats
				JCheckBox jc = (JCheckBox) checkboxList.get(j + (16 * i)); //For each iteration j over the number of instruments times number of beats
				//If it's meant to play, set it to the instrument #
				if (jc.isSelected()){
					trackList[j] = key;
				}else{
					trackList[j] = 0;
				}
			}
			makeTracks(trackList); //make events for all the tracks
		}
	
		track.add(makeEvent(192,9,1,0,15)); //always have something on beat 16
		try{
			sequencer.setSequence(sequence);
			sequencer.start();
			sequencer.setTempoInBPM(bpm);
		}catch(Exception e){ e.printStackTrace(); }
	}
	
	public class MyStartListener implements ActionListener{
		public void actionPerformed(ActionEvent a)
		{
			buildTrackAndStart();
		}
	}
	
	public class MyStopListener implements ActionListener{
		public void actionPerformed(ActionEvent a)
		{
			sequencer.stop();
		}
	}
	
	public class MyUpTempoListener implements ActionListener{
		public void actionPerformed(ActionEvent a)
		{
			bpm += 3;
		}
	}

	public class MyDownTempoListener implements ActionListener{
		public void actionPerformed(ActionEvent a)
		{
			bpm -= 3;
		}
	}

	//gets passed the array of checkbox states as either a 0 or the instrument # if on
	public void makeTracks(int[] list)
	{
		for (int i = 0; i < 16; i++){
			int key = list[i];
		
			if (key != 0){
				track.add(makeEvent(144,9,key,100,i)); //ON
				track.add(makeEvent(128,9,key,100,i + 1)); //Note OFF
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
	
	//MetaEventListener that listens for end of sequence (signal 47) and starts over
	public void meta(MetaMessage message){
		if (message.getType() == 47){
			sequencer.start();
			sequencer.setTempoInBPM(bpm);
		}
	}

}
