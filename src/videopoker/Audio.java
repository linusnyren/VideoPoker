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
	  public void playSound(String filepath, double volume) throws Exception {

		    Clip clip;
	    	File soundFile = new File(filepath);

	        Line.Info linfo = new Line.Info(Clip.class);
	        Line line = AudioSystem.getLine(linfo);
	        clip = (Clip) line;
	        AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
	        clip.open(ais);
	        
//	        Volym kontroll som styrs av variabeln volume
	        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
	        gainControl.setValue(20f * (float) Math.log10(volume));
	        
	        clip.start();
	        }
	  

	    public void update(LineEvent le) {
	        LineEvent.Type type = le.getType();
	        JComponent playingDialog = null;
			if (type == LineEvent.Type.OPEN) {
	            System.out.println("OPEN");
	        } else if (type == LineEvent.Type.CLOSE) {
	            System.out.println("CLOSE");
	            System.exit(0);
	        } else if (type == LineEvent.Type.START) {
	            System.out.println("START");
	            playingDialog.setVisible(true);
	        } else if (type == LineEvent.Type.STOP) {
	            System.out.println("STOP");
	            playingDialog.setVisible(false);
	            clip.close();
	        }
	    }

}
