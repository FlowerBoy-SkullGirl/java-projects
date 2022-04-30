import javax.sound.midi.*;

public class MusicTest{
	public void play(int instr, int note_arg) //throws MidiUnavailableException (put this to pass the responsibility of the exception to main)
	{
		try{
			//Get a sequencer object from the midi library
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();

			//Create a sequence
			Sequence seq = new Sequence(Sequence.PPQ, 4);
		
			//Tell the sequence to make a track
			Track t = seq.createTrack();

			/*Fill the track with midi
			t.add(MidiEvent);
			*/
		
			//Message type, channel, note, velocity	
			//Message 144 is on, 128 is off, 192 is change instrument
			
			ShortMessage z = new ShortMessage();
			z.setMessage(192, 1, instr, 0);
			MidiEvent instrument = new MidiEvent(z, 1);
			t.add(instrument);
		
			ShortMessage a = new ShortMessage();
			a.setMessage(144, 1, note_arg, 100);
			MidiEvent noteOn = new MidiEvent(a, 2);
			t.add(noteOn);
			
			ShortMessage b = new ShortMessage();
			b.setMessage(128, 1, note_arg, 100);
			MidiEvent noteOff = new MidiEvent(b, 4);
			t.add(noteOff);

			sequencer.setSequence(seq);
			sequencer.start();
				
			Thread.sleep(2000);
			sequencer.close();

			System.out.println("Sequencer sequestered");
		}catch (Exception ex){
			System.out.println("Midi exception");
			ex.printStackTrace(); //strace for exception
		}//finally {
			//Do nothing, this runs after EITHER try success or catch
		//}
		System.out.println("Finished playing");
	}
		
	public static void main(String[] args)
	{
		if (args.length < 2) {
			System.out.println("Put two numbers");
			return;
		}else {
			int instrument = Integer.parseInt(args[0]);
			int note = Integer.parseInt(args[1]);
			//Create a music test object and play it
			MusicTest mt = new MusicTest();
			mt.play(instrument, note);
		}
	}
}
