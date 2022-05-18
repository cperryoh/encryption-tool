import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Random;

public class CipherData implements Serializable{
	public String[] keys =new String[100];
	public char[][] mixedUps;
	public int[] randomLevels;
	public String[] levelKeys;
	public char[] charsToUse = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
			'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ',', '.', '?', '&', '%', '!', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', '0', '/', ':', ';', '=', '<', '>', '#' };
	
	public CipherData () {
		String jarPath = CipherData.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		mixedUps = new char[100][charsToUse.length];
		randomLevels=new int[10];
		levelKeys=new String[10];
		try {
			File f = new File("data.dat");
			if(f.exists()) {
				FileInputStream fis = new FileInputStream("data.dat");
			    ObjectInputStream ois = new ObjectInputStream(fis);
			    CipherData readData = (CipherData)ois.readObject();
			    keys=readData.keys;
			    mixedUps=readData.mixedUps;
			    randomLevels=readData.randomLevels;
			    levelKeys=readData.levelKeys;
			    return;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < 100; i++) {
			mixedUps[i]=Encryption.shuffle(charsToUse);
		}
		Random rnd = new Random();
		for(int i=0; i<100;i++) {
			String keyString="";
			for (int j = 0; j < 3; j++) {
				 keyString+= charsToUse[rnd.nextInt(charsToUse.length)];
			}
			keys[i]=keyString;
		}
		for(int i =0;i<10;i++) {
			randomLevels[i]=rnd.nextInt(100 - 10) + 10;
			levelKeys[i]=Character.toString(charsToUse[rnd.nextInt(charsToUse.length)])+charsToUse[rnd.nextInt(charsToUse.length)];
		}
		makeCopyInFiles();
	}
	void makeCopyInFiles() {
		CipherData obj = this;
	    try {
			FileOutputStream fos = new FileOutputStream("data.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
		    oos.writeObject(obj);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
