

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.ObjectOutputStream;

public class tool {

	private JFrame frame;
	JTextArea Out = new JTextArea();
	JRadioButton deEncrypt = new JRadioButton("De encrypt");
	char[] temp;
	String[] keys = { "awr", "qwe", "xcv","foq" };
	char[] alphebet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z' };
	char[][] mixedUps = {
			{ 'd', 'l', 'u', 't', 'f', 'g', 'j', 'k', 'c', 'y', 'z', 'm', 's', 'n', 'q', 'r', 'o', 'i', 'p', 'v', 'a',
					'w', 'b', 'e', 'h', 'x' },
			{ 'h', 'r', 'l', 'p', 'd', 'm', 'v', 'e', 'g', 'k', 'c', 'f', 'o', 'y', 'a', 'q', 'j', 'i', 'b', 'u', 'x',
					's', 'w', 'z', 't', 'n' },
			{ 'b', 'v', 'k', 'a', 'x', 'g', 'w', 'e', 't', 'f', 'l', 'q', 'p', 'm', 'o', 'u', 'n', 'c', 'z', 'j', 'h',
					's', 'y', 'i', 'r', 'd' },
			{ '3', 'm', 'w', 'n', 'o', 'l', '1', 'y', 'i', 'u', 'd', 'c', 'j', 'p', 'q', 'g', 'v', 'f', 't', 'h', 'b',
					'a', 'z', 'x', 'r', 'e' }, };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tool window = new tool();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public tool() {
		initialize();
		temp = shuffle(alphebet);
		for (int i = 0; i < temp.length; i++) {
			System.out.print("'" + temp[i] + "'" + ",");
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	char[] shuffle(char[] array) {
		Random rand = new Random();

		for (int i = 0; i < array.length; i++) {
			int randomIndexToSwap = rand.nextInt(array.length);
			char temp = array[randomIndexToSwap];
			array[randomIndexToSwap] = array[i];
			array[i] = temp;
		}

		return array;
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 857, 528);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JRadioButton encrypt = new JRadioButton("Encrypt");
		encrypt.setSelected(true);
		encrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (encrypt.isSelected()) {
					deEncrypt.setSelected(false);
				}
			}
		});
		encrypt.setBounds(250, 12, 155, 29);
		frame.getContentPane().add(encrypt);

		deEncrypt.setBounds(250, 49, 155, 29);
		deEncrypt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (deEncrypt.isSelected()) {
					encrypt.setSelected(false);
				}
			}
		});
		frame.getContentPane().add(deEncrypt);
		Out.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Out.setLineWrap(true);
		
		
		Out.setEditable(false);
		Out.setBounds(422, 85, 388, 393);
		frame.getContentPane().add(Out);
		
		JTextArea In = new JTextArea();
		In.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String inString = In.getText();
				Out.setText("");
				int level=100;
				if (deEncrypt.isSelected()) {
					String temp=inString;
					for(int i=0;i<level;i++) {
						temp=deEnccrypt(temp);
					}
					Out.setText(temp);
				} else {
					String temp=inString;
					for(int i=0;i<level;i++) {
						temp=encrypt(temp);
					}
					Out.setText(temp);
				}
			}
		});
		In.setLineWrap(true);
		In.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		In.setBounds(15, 85, 388, 393);
		frame.getContentPane().add(In);
	}
	String encrypt(String message) {
		String inString = message;
		Random rndRandom = new Random();
		int choosen = rndRandom.nextInt(3);
		String outString = keys[choosen];
		char[] keyToUse = mixedUps[choosen];
		for (int i = 0; i < inString.toCharArray().length; i++) {
			if (inString.toCharArray()[i] != ' ') {
				outString=outString+Out.getText() + keyToUse[findInArray(alphebet, inString.toCharArray()[i])];
			} else {
				outString=outString+ " ";
			}
		}
		return outString;
	}
	String deEnccrypt(String in) {
		String out="";
		char[] keyUsed = mixedUps[findInArrayString(keys, in.substring(0, 3))];
		for (int i = 3; i < in.toCharArray().length; i++) {
			if (in.toCharArray()[i] != ' ') {
				out=out+alphebet[findInArray(keyUsed, in.toCharArray()[i])];
			} else {
				out=out+ " ";
			}
		}
		return out;
	}
	int findInArrayString(String[] array, String value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(value)) {
				return i;
			}
		}
		return -1;
	}

	int findInArray(char[] array, char value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}
}
