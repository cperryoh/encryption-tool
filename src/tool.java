
/*
     * Quick run-down on how this works(READ IF CONFUSED): So we have a few arrays
	 * of keys and arrays that correlate to them 
	 * keys->mixups 
	 * levelkeys->random
	 * levels
	 * 
	 * Mixups: array of ciphers or mixups of the array, charsToUse (Cipher replaces one character for another) 
	 * Keys: Array of strings, each 3 letters long. Each string correlates to a mixup
	 * 
	 * randomLevels: Array of random numbers, this gives the encryptor multiple
	 * options of how many times to encrypt our string 
	 * levelkeys: an array of two character long strings that correlate to a number in random levels
	 * 
	 * CharsToUse: Valid characters that can be encrypted and be used in decryption
	 * 
	 * Methodology: All encryption does is it first decides how many times to
	 * encrypt our string(using randomLevels). After that is takes the string the
	 * user provided, runs it through the encrypt() function a certain number of
	 * times, and store the output, it takes that output and does that process over
	 * and over again, constantly building upon it's self. Once we have a final
	 * string that has been ran a number of times(decided by a random index
	 * selection from randomLevels) through encrypt(), we tack on our key that
	 * correlates to our encryption level to the beginning and spit out a final
	 * output. 
	 * encrypt(): in this method we chose a cipher from mixups, run our
	 * input through it, then tack on our key for our chosen mixup to the beginning
	 * and return.
	 * 
	 * For decryption, it is a bit more complicated. First, we find out how many
	 * times we ran our original string through encrypt(). After we figure that out,
	 * we remove the first two characters(the key that told us how many times our
	 * string was encrypted over). Then we loop our string through decrypt() that
	 * many times. 
	 * decrypt(): this method takes note of the first three characters(a
	 * mixup key) and connects it to a mix up. Then starting from the fourth
	 * character(as to not include the key). We reverse the cipher from our mixup we
	 * found using the key to CharsToUse and build a new string that we then output
	 * for either further decryption or final output to the user.
	 * 
	 * 
	 * This is powerful because it uses 100 ciphers to make a countless number of
	 * others. This is because each output from encrypt(), including the key, is ran
	 * back through, re-encrypting the whole string using a new(or possibly the
	 * same) cipher compared to the previous one.
	 */
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import java.awt.Component;
import javax.swing.Box;

public class tool {
	// please note that spaces are ignored in the encryption process an spaces that
	// are present are there in the final message
	private JFrame frame;
	JTextArea Out = new JTextArea();
	JRadioButton deEncrypt = new JRadioButton("Decrypt");
	boolean consumed;
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tool window = new tool();
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

	}

	private void initialize() {
		/* start of frame initialization */
		// No need to pay attention unless you want to change the look of the app
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
		encrypt.setBounds(366, 49, 155, 29);
		frame.getContentPane().add(encrypt);

		deEncrypt.setBounds(250, 49, 114, 29);
		deEncrypt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// toggle between the two radio buttons
				if (deEncrypt.isSelected()) {
					encrypt.setSelected(false);
				}
			}
		});
		frame.getContentPane().add(deEncrypt);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 85, 365, 369);
		frame.getContentPane().add(scrollPane);

		JTextArea In = new JTextArea();
		scrollPane.setViewportView(In);
		In.setLineWrap(true);
		In.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(409, 85, 405, 369);
		frame.getContentPane().add(scrollPane_1);
		scrollPane_1.setViewportView(Out);
		Out.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Out.setLineWrap(true);
		Out.setEditable(false);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		int mixUpKeyCount = 100;

		JMenuItem makeNewKeys = new JMenuItem("Make New Keys");

		// Generates new keys to be copied back into script for use
		makeNewKeys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// make mix up keys
				copyToClipBoard(Encryption.reprogram());
			}
		});
		menuBar.add(makeNewKeys);

		Component horizontalStrut = Box.createHorizontalStrut(575);
		menuBar.add(horizontalStrut);
		frame.setVisible(true);
		/* end of frame initialization */

		/* start of decryption/encryption logic */
		In.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				/* pulls text from input and clears output */
				String inString = In.getText().toLowerCase();
				Out.setText(""); 
				
				if (deEncrypt.isSelected()) {
					try {
						Out.setText(Encryption.de_encrypt(inString));
					} catch (IndexOutOfBoundsException e) {
						encrypt.setSelected(true);
						deEncrypt.setSelected(false);
					}
				} else {
					Out.setText(Encryption.encrypt(inString.toLowerCase()));
				}
			}
		});
	}

	// copy text to clipboard
	void copyToClipBoard(String str) {
		StringSelection stringSelection = new StringSelection(str);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}
}
