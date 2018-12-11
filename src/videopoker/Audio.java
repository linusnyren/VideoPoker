package videopoker;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.swing.JComponent;

public class Audio {
	Clip clip;
	
//	Denna metod tar emot en sökväg för filen som skall spelas upp
//	Tar även emot en double för att styra volymen, denna skall vara mellan 0->1
	  public void playSound(String filepath, double volume) throws Exception {

	    	File soundFile = new File(filepath); //Lagrar filen i anvigen sökväg i variablen soundFile

	        Line.Info linfo = new Line.Info(Clip.class); //Öppnar en ljudlinje i klassen Line
	        Line line = AudioSystem.getLine(linfo);
	        clip = (Clip) line;
	        AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile); //Väljer filen som skall strömmas
	        clip.open(ais);	//Öppnar ljudströmmen
	        
//	        Volym kontroll som styrs av variabeln volume
	        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
	        gainControl.setValue(20f * (float) Math.log10(volume));
	        
	        clip.start(); //Startar ljudströmmen som spelar upp ljudfilen
	        }
}
