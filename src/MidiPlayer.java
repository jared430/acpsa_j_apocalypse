/* 
 * Date: 6/5/2018
 * Static class that can play a .mid file
 * based off of https://gist.github.com/indy/360540
 * 
 * You can convert an .mp3 audio file to a .midi audio file by using:
 * 		https://www.bearaudiotool.com/mp3-to-midi
 *
 * If you place the .mid file in your "src" folder,
 * remember to name the file using the "src/fileName.mid"
 */


import javax.sound.midi.*;
import java.io.*;

/** Plays a midi file provided on command line */
public class MidiPlayer implements Runnable {

	private String midiString;
	private static File midiFile;

	public static void play(String midiString) {

		midiString = midiString;
		midiFile = new File(midiString);	
		(new Thread(new MidiPlayer())).start();
	}

	public void run() {
		System.out.println("Your .mid file started playing!");

		if(!midiFile.exists()) {
			System.out.println("Cannot find .mid file.  Check your path.");
			helpAndExit();
		} else if(midiFile.isDirectory()) {
			System.out.println("Can't find .mid file in the chosen directory.");
			helpAndExit();
		} else if (!midiFile.canRead() ) {
			System.out.println("Can't read .mid file.  Are you sure you have the right file type?");
			helpAndExit();
		}

		// Play once
		try {
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.setSequence(MidiSystem.getSequence(midiFile));
			sequencer.open();
			sequencer.start();
			while(true) {
				if(sequencer.isRunning()) {
					try {
						Thread.sleep(1000); // Check every second
					} catch(InterruptedException ignore) {
						break;
					}
				} else {
					break;
				}
			}
			// Close the MidiDevice & free resources
			sequencer.stop();
			sequencer.close();
		} catch(MidiUnavailableException mue) {
			System.out.println("Midi device unavailable!");
		} catch(InvalidMidiDataException imde) {
			System.out.println("Invalid Midi data!");
		} catch(IOException ioe) {
			System.out.println("I/O Error!");
		} 

	}  


	/** Provides help message and exits the program */
	private static void helpAndExit() {
		System.out.println("Usage: java MidiPlayer midifile.mid");
		System.exit(1);
	}
}