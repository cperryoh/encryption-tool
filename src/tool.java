
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
	int level = 50;
	String[] keys = { "lu>", "lmm", "hfw", ":y:", "m2:", ",ur", "fq9", ":<9", "bs8", "<,k", "wr!", "qe?", "nyq", "cp3",
			"#yo", "f%2", "wh6", "rg&", "hea", "&&&", "ne=", "5z2", "dcn", "y=p", "oan", "wnc", ">qi", "o:e", ":a%",
			".zy", "n%g", "kg6", "1j9", "3/p", "bc0", "f#n", "t7#", "eg&", "otj", "6<:", "97s", ",f#", ";hw", "egb",
			"66l", "d1!", "gc/", "s%4", "lti", "g3u", "=k?", "#l;", "2c,", "=&p", "#hz", "t:q", "p#?", "?4,", "%,:",
			"w1p", ":?z", "c.n", "dd1", "o#5", "z:a", "?#4", "p0j", "12b", "34#", "s<9", "1x3", "?2n", "j;;", "j:q",
			"8nd", ":u:", ".4k", "22k", "y0,", "1e?", "t//", "7y9", ";x5", ":/j", "q%:", "5ih", "h!h", "<wz", "h.x",
			"/#3", "wpo", "k/,", "p/8", "tp#", "b3#", ",;;", "s:y", "x3p", "52r", "qin" };
	char[][] mixedUps = {
			{ 'd', '0', 'a', '<', '%', '/', 'w', 'e', 'v', 'n', 'l', '&', 'm', 's', 'y', 'q', 'j', '.', '2', 'c', '4',
					':', '#', 'g', ',', '6', '8', 'p', 'h', '3', 'u', '?', '5', ';', '7', 'b', 'f', 'z', 't', 'x', '>',
					'1', 'o', '!', 'i', 'k', '9', 'r', '=' },
			{ 's', 'p', '5', 'o', '2', '/', '3', '8', '6', 'a', '9', 'q', 'z', 't', 'i', 'w', 'v', ':', 'r', '<', 'c',
					'k', '&', 'y', 'l', 'u', 'n', '>', 'm', 'h', '0', '1', '#', '?', ';', 'f', '4', '%', 'g', 'b', 'd',
					'x', 'j', '7', ',', 'e', '!', '=', '.' },
			{ '?', '9', '&', 'r', '#', 't', '6', 'p', '>', 'u', 'z', '2', 'm', 'b', 'q', 'f', '<', 'v', 'j', ':', '.',
					'a', 'x', '5', 'c', '1', 's', 'n', 'k', '7', '0', 'd', '/', ';', '8', '%', ',', 'o', '3', 'h', 'i',
					'4', 'e', '=', 'w', '!', 'y', 'g', 'l' },
			{ '8', 'a', '=', ',', 't', '!', '6', 'f', 'q', 'c', 'v', 'p', 'x', '>', 'd', 'j', 'z', '1', '/', '0', '#',
					'7', 'r', 'e', '5', '<', 'o', 'i', 'g', 'y', 'b', '2', 's', 'h', 'l', '%', '4', 'u', 'm', '?', ';',
					':', 'k', '9', 'n', '&', '.', '3', 'w' },
			{ '/', '!', 'z', 'g', '4', '6', 'o', '%', 'x', ':', 'v', '5', '0', 'm', 'q', '8', 'h', 'e', 'j', ',', 'c',
					'n', '3', '<', '#', ';', '&', '=', '.', 'l', 'b', 'k', 'y', 't', '1', 'w', 'i', '2', 'u', 'r', 'f',
					's', 'p', '9', '7', '>', '?', 'd', 'a' },
			{ '=', '4', '0', '%', '6', '3', '9', '!', '&', ';', 'd', '1', '<', '?', 's', 'l', 'v', 'z', 'm', 'e', 'u',
					'c', 'k', '7', 'a', ',', 't', '/', 'h', 'j', 'y', 'g', 'b', ':', 'n', '5', '.', 'i', '2', 'p', 'q',
					'r', 'o', 'f', '#', 'x', '8', '>', 'w' },
			{ ';', '>', 't', 'f', 'v', '<', 'a', 'i', 'q', '4', '.', 'n', '6', 'e', '0', '7', 'm', 'k', 's', 'x', '=',
					'2', ':', 'z', ',', '!', '5', 'w', 'j', '1', 'g', '3', '%', 'h', '/', 'b', '9', 'c', 'p', 'r', '&',
					'#', 'u', '8', 'l', '?', 'd', 'o', 'y' },
			{ 'e', 'y', 'm', '8', '6', '?', '/', 'i', 'l', 'v', 'k', 'q', 'g', ':', '=', 'x', 'c', '&', '.', 'o', '4',
					's', '!', '>', ',', 'h', '<', 'a', ';', '5', 'u', 'd', '2', 'f', '3', 'j', 't', '#', 'w', 'b', '%',
					'9', 'r', '0', 'p', '7', '1', 'n', 'z' },
			{ '3', '&', 'i', '9', '>', 'z', 'o', '?', 'u', 'x', '4', ';', '%', 'b', 'y', 'k', 'h', '7', 'e', 'f', '=',
					'2', 'g', 't', '/', ':', '8', 's', 'c', 'j', 'w', 'p', 'a', 'm', 'n', '1', '<', '!', '6', '.', '5',
					'0', 'v', 'r', '#', 'q', ',', 'l', 'd' },
			{ ';', '9', '8', '>', 'z', 'f', '?', 'e', 'x', 'w', '0', 'p', '#', '1', '/', '7', 'j', 'k', '!', 'u', 'o',
					'm', 'a', ',', 'r', '6', 'g', '.', '&', 's', 't', '<', 'v', 'y', 'h', '3', '2', 'q', 'n', '4', 'l',
					':', '=', '5', 'b', 'c', 'i', '%', 'd' },
			{ '!', 'o', 'v', 'h', 'r', '8', '9', 'k', '1', 'd', 'j', 'i', '?', 'a', 's', 'u', '%', '2', '#', '7', 't',
					'3', ',', 'e', '5', '&', ':', '0', '/', ';', '<', 'l', 'b', '=', 'p', 'n', 'w', 'f', 'm', 'g', '>',
					'.', '4', 'c', 'x', 'y', 'q', 'z', '6' },
			{ 'd', 'g', '0', 'j', 'a', '1', '/', 'r', 's', '#', '2', ',', 'p', '%', ':', 'l', 'e', '7', '.', 'o', 'i',
					'4', '5', 't', 'w', 'm', 'z', 'c', 'v', 'q', '<', '9', 'b', 'y', '3', '?', '>', '&', '!', 'n', '=',
					'x', ';', 'k', '8', 'h', 'f', '6', 'u' },
			{ 'b', 'n', ';', 'o', ',', 'x', 'm', '7', '8', '%', 'l', '0', 'a', '!', 'e', 'w', 'c', 'z', '4', '6', 'g',
					'<', 'p', 'u', '&', ':', '=', '>', '#', '5', '1', 'v', 'j', 't', 's', 'd', '3', 'i', 'f', '2', '9',
					'y', 'h', '?', '.', 'k', 'q', '/', 'r' },
			{ ';', 'w', '8', '0', 'c', 'u', '&', 'k', 'j', 'y', 'e', 't', '=', '2', ':', '5', '9', 'b', '6', 'a', 'g',
					'1', 'f', 'o', '.', 'v', '!', 's', 'n', '#', 'i', ',', 'q', 'z', '/', '?', '3', '>', '7', '%', '4',
					'<', 'x', 'r', 'd', 'h', 'l', 'p', 'm' },
			{ 'd', 'v', '7', '=', ',', '&', 'e', '?', '8', '>', '!', 'o', 'i', 'k', '#', 'm', 'l', '9', 'q', 'y', 'b',
					'/', 'r', 't', 'f', '4', '%', '<', 'c', 'n', 'x', '1', ':', 'w', '.', 'u', ';', 'h', '6', 'g', '5',
					'p', 's', 'z', '2', '3', 'a', 'j', '0' },
			{ 'v', '.', 'e', 'k', '=', 'm', 'w', '#', '<', '8', 'n', '0', '?', 'c', '!', '/', '>', '4', 'b', '2', '1',
					'j', 'z', 'q', '9', '6', 'h', 'r', ';', 'u', 'p', '7', 'f', 'x', 'y', '3', '&', 'o', 'd', 'a', ',',
					'i', 't', 'g', '5', ':', '%', 's', 'l' },
			{ '>', '=', '/', 'o', '6', '3', '.', '5', 'b', 'l', 'g', 'y', 't', 's', '8', '#', 'k', 'v', 'e', 'u', 'j',
					'&', '0', ',', '9', ':', 'f', 'q', '7', 'p', 'r', 'x', '!', 'w', 'c', 'a', '<', 'z', 'h', 'm', 'n',
					'2', 'i', ';', '%', 'd', '?', '4', '1' },
			{ '0', '6', 'm', 'p', 'l', '.', '8', 'a', 'f', 'y', '9', 'u', 'z', 'w', ';', 'v', 'j', ':', 'o', '>', '%',
					't', '/', 'c', '2', 's', 'k', '&', '#', '!', '<', 'e', 'h', 'r', '?', 'b', 'n', 'q', '=', '1', 'd',
					'i', 'g', 'x', '5', '7', '4', ',', '3' },
			{ ';', ',', 'c', 'o', 'j', 'l', 'h', 'w', '/', '0', 'k', 'r', '5', 'n', 'm', 'f', 'u', '<', 'd', '3', 'p',
					'q', '4', '2', 'y', '?', '6', 'i', 's', 'v', '#', '.', 'e', 't', 'g', 'x', '!', '>', '8', '7', '=',
					'&', 'z', '1', 'b', '9', '%', ':', 'a' },
			{ 'y', '6', 't', '7', '3', '%', '2', '>', 'b', 'f', '=', '4', '8', ';', '9', 'p', '!', 'q', 'r', 'o', 'h',
					'0', 'm', 'z', 'c', 'w', '<', '/', '?', '.', 'u', '5', 'x', 'g', 'a', 'l', ':', 'k', 'j', ',', '&',
					'#', 'd', 'v', '1', 'e', 's', 'i', 'n' },
			{ 'b', 'o', 'c', 'k', 'u', '0', 'q', '=', '?', ':', '3', 'h', '6', 'g', 'a', 'r', '#', '>', 'j', 'l', '2',
					'!', ',', 's', 'm', 'x', '.', 'p', 'z', 'n', '7', 'd', 'e', '9', '4', '%', '5', '<', 'w', '1', '&',
					'v', '8', 't', '/', 'y', ';', 'i', 'f' },
			{ ':', '?', 'd', '0', '&', '4', '2', 'e', 'j', '8', 'o', '#', 'q', 'b', '6', 'x', 'i', 'r', 'n', '>', 'm',
					's', 'u', '5', 'w', 'k', '9', '=', '!', ',', '.', 'h', '/', 'c', '3', 'a', 'y', '1', 'v', 'p', '<',
					'f', ';', 'z', 'l', 'g', '%', 't', '7' },
			{ '%', 'u', 'x', 'w', '2', 'f', 'e', '5', 'q', 'z', '#', '&', 'h', '/', 'y', '1', 'j', '9', 'c', '!', 'p',
					'?', 'a', 'o', 'k', 'g', '7', 'b', ':', '>', 'r', 'i', 'm', '3', 'd', '<', 'v', ',', '4', '0', '6',
					'l', 's', '=', '8', '.', ';', 't', 'n' },
			{ 'w', '!', '<', 'd', '&', '%', 'q', '/', '4', 'e', 'f', 'h', 'y', 'j', '1', '0', '.', 'u', 'a', 'l', '9',
					'o', ';', 'i', '7', '6', '5', 'g', '?', '8', 'b', ',', 'm', 'n', 'p', '2', 'z', 's', 'x', '>', '3',
					't', 'c', 'r', 'k', '=', 'v', '#', ':' },
			{ 's', '0', '4', 'f', 'v', '<', 'h', 'd', '2', '7', 'u', 'b', '8', ',', 'g', '%', '3', 'w', 'l', 'r', 'q',
					'?', 'e', '>', 'j', 'z', '&', 'y', '1', 'o', 't', 'x', 'm', 'n', '#', 'i', '6', '!', 'c', ':', '/',
					'5', ';', '=', 'p', '.', '9', 'k', 'a' },
			{ '5', '2', 'o', '?', 's', 'd', '#', '1', '%', 'v', 'j', 'c', '!', 'x', 'i', 'f', ':', 'a', 'g', '0', '9',
					'>', 'h', 'l', ',', '8', 'z', '.', 'm', 't', ';', '<', 'n', '6', 'u', 'b', 'e', 'y', '3', '&', '/',
					'4', 'k', 'q', 'p', 'w', '7', '=', 'r' },
			{ '!', 'l', 'q', 't', 'w', '1', 'e', 'x', '5', 'h', '8', 'p', 'f', 'u', 'k', 'z', '9', '&', '#', 'a', 'm',
					'o', '0', '>', 's', 'i', '6', '3', '7', '4', '<', 'n', '%', ':', 'y', 'c', 'r', '/', 'j', '?', ',',
					'd', 'v', '2', ';', '=', 'b', 'g', '.' },
			{ 'e', ';', 'l', 'x', 'j', 's', '6', 'w', '5', 'c', '1', '9', 'p', '0', '=', '8', 'y', '7', 'h', '%', 'n',
					'd', '>', '<', 'r', '#', 'a', '3', '2', '?', 'o', 'k', 'v', '/', ',', 'i', 'z', '!', '4', 'm', '&',
					'g', 'b', 'q', 't', '.', 'f', ':', 'u' },
			{ 's', 'm', '2', 'k', 'z', ',', 'q', 'a', 'v', '1', '3', 'r', '.', 'g', 'p', '<', '=', 'i', '0', 'e', ':',
					'w', '#', 'b', '/', 'y', '5', 'o', 'd', 'h', 'x', '4', '&', '!', 'c', 'n', 'j', 't', 'l', 'u', '>',
					'8', '9', '7', '?', 'f', '6', '%', ';' },
			{ 'u', 'l', 'b', '1', '!', 'd', 'o', '4', '>', '6', 'v', 'y', 'm', '.', 'c', 'i', '<', ';', 'n', '2', 'e',
					'=', 'p', '#', 'w', '%', '3', ',', ':', 'z', 's', 't', 'q', '7', 'f', 'j', 'a', 'k', '&', '0', '5',
					'?', '/', 'h', 'g', 'r', '9', 'x', '8' },
			{ 'c', ':', 'k', 'z', 'r', ',', '!', 'n', '4', 'j', 'p', '&', '5', '>', 't', 'l', '?', '%', 'e', 'v', ';',
					'i', 'u', 's', 'w', '1', '/', 'b', '6', '.', '7', 'd', '2', '3', '0', 'a', '8', 'y', 'f', 'm', '9',
					'q', '#', 'h', 'x', 'g', 'o', '=', '<' },
			{ 'z', 'r', 'y', '.', 'u', 'i', '>', 'p', 'l', 'x', 'f', 'e', ',', 'g', '8', 'n', 's', '2', ':', 'a', ';',
					'5', '/', '0', '=', '1', '7', 'q', 'm', '%', 'k', 'w', '#', '3', 'v', 'o', '4', 'b', '<', 'c', '9',
					'6', '?', 'd', '&', 'h', '!', 'j', 't' },
			{ 'c', ':', 'y', 'w', '5', 't', 'o', '1', 'q', '=', '6', '3', '!', '&', 'r', '%', 'i', 'e', '.', 'u', ',',
					'9', 'v', 'd', 'l', '2', 'k', 'g', 'p', 's', 'n', '<', ';', '?', 'z', 'm', 'j', 'a', 'h', 'b', '#',
					'/', '0', 'x', '>', '8', '7', 'f', '4' },
			{ ',', ':', 'e', '5', 'f', 'h', 'q', 'z', 'y', '?', '>', 'i', 'o', '.', '!', '=', 'n', 's', '%', 'l', '4',
					'/', 'g', 'k', '1', '6', 'b', 't', ';', '3', '<', 'w', 'v', '9', 'u', 'a', 'p', '&', 'c', '2', 'x',
					'0', 'd', 'r', '8', '7', '#', 'm', 'j' },
			{ '4', ',', 'c', '.', 'g', 'd', 'k', '8', '3', '%', 'v', 'z', 'w', ';', '9', '2', 'r', 'n', ':', 'u', '/',
					'm', 'q', 'o', '7', 'h', '=', 'e', 'j', '6', 'f', '>', 'p', '0', 'b', 'x', 'y', 'l', '<', '1', '&',
					's', 'a', '#', '!', '?', 't', 'i', '5' },
			{ 'z', 'd', '=', ',', 'p', ';', 'k', 'l', '/', '8', '6', 'o', '?', ':', 'v', 'x', 'a', '.', 'f', '4', '%',
					'u', 'r', 'g', 't', '7', 'c', 'b', '1', 'i', '9', 'h', '5', '<', 's', 'q', '!', '0', 'm', 'e', 'y',
					'w', '&', '>', 'n', '#', 'j', '3', '2' },
			{ 'v', '%', 'g', '#', 'j', 'n', 'd', 'p', '6', '4', 'f', 'h', '5', 'x', 'e', 'k', '9', '8', '.', 'l', '7',
					'a', '!', ':', '0', 'r', '>', '<', '/', 't', 'o', 'u', 'z', 'y', '&', '3', '1', 'm', 'i', '?', 's',
					'q', ';', 'b', 'c', 'w', '2', ',', '=' },
			{ 'r', 'u', 'l', '>', '7', '<', ';', 'a', 'i', '9', 'p', '1', 't', '?', 'q', '3', 'j', '=', 'o', '6', ',',
					'4', 'z', 's', '#', '5', 'h', 'm', 'e', 'k', '/', 'n', 'x', 'c', '2', '8', 'f', 'y', '!', '%', 'v',
					'w', '&', ':', 'b', '.', 'd', '0', 'g' },
			{ '!', 'g', '3', 'a', 'y', 'd', 'j', '%', '=', 't', '1', '/', 'v', 'x', 'f', 'z', 'p', '4', 'q', 'n', 'c',
					'&', '8', '?', 'r', 's', '9', '2', 'u', 'b', '.', ',', '>', '7', 'o', 'k', 'w', ':', '5', ';', '6',
					'e', 'i', 'h', '#', '<', 'l', '0', 'm' },
			{ 'a', 'y', 'l', '!', '>', 'd', 'c', '5', 'f', 'i', 'm', '4', '8', '<', '&', '%', '=', ':', 'h', 'o', '1',
					'j', 'u', '/', '#', 'n', 'w', '7', 'k', '2', 'g', 'z', '.', ',', '6', 'q', '9', 't', '0', '3', 'x',
					';', 'v', 'b', '?', 'r', 'e', 'p', 's' },
			{ 'c', '!', '%', '/', 'l', '#', 'y', '9', '4', 'j', '6', ',', 'b', '=', ':', 'o', 'h', '2', 'q', '1', 'g',
					'i', '5', 'v', 'r', '7', '<', '8', 'n', 'k', '?', '>', 't', '&', 'p', 'a', 'w', 'z', 'x', 'e', ';',
					'3', 'f', 's', 'u', '.', 'd', '0', 'm' },
			{ 'x', '1', '9', 'o', '7', 'r', '>', ';', 'e', '=', 'y', '4', 'd', 'f', '2', '&', '?', 'q', 'k', 'g', 'a',
					'u', '3', 't', '0', 'n', '%', 'h', '<', 'i', '5', '8', '!', '.', 'b', '#', 'c', ':', 'p', '6', ',',
					's', 'z', 'm', 'j', 'w', 'l', 'v', '/' },
			{ '0', '<', 'g', 'y', 'f', 'b', 'l', 'j', '1', '.', '&', 'i', '=', 't', 'n', '3', '8', '?', 'a', '#', ',',
					'%', 'd', 'u', 'h', 'w', '!', ':', '9', 's', '6', '5', 'q', 'o', '7', 'x', 'r', ';', '4', 'z', 'm',
					'c', '>', '2', 'p', 'e', '/', 'v', 'k' },
			{ '5', '3', 'l', '0', '.', '<', '&', 'm', 'z', '%', 'k', '7', 'w', '2', '9', 'n', 't', 's', 'd', 'a', 'v',
					'h', 'u', 'r', '6', 'j', '4', ',', ';', '=', '!', ':', 'i', 'g', '/', 'b', '>', 'x', 'e', 'y', 'o',
					'c', '1', '8', 'f', 'p', '#', '?', 'q' },
			{ '&', 'n', '5', 'a', '=', 'o', 'm', 'k', '<', '7', 'j', '!', ',', ':', '#', 'q', ';', 'z', 'r', 's', 'e',
					'6', 'c', 'g', 'l', 'w', '4', 'd', 'i', '3', 't', 'v', 'u', '?', '/', '1', 'b', 'p', '8', '0', 'h',
					'f', 'x', '>', '.', 'y', '2', '9', '%' },
			{ 'z', 'a', '>', ',', '1', 'd', 'p', '&', ':', '=', 'j', 'k', '%', ';', 'i', '9', 'c', 'n', '4', 'q', 'u',
					'r', '3', 'e', '7', 'm', 'y', '5', 'v', 'g', 'b', '#', 'l', 'f', '6', '?', 'o', 's', '!', '2', 'w',
					't', 'x', '0', '/', '<', '8', 'h', '.' },
			{ '<', 's', '&', '?', 'n', '6', 'w', 'r', 'p', 'y', 'x', 'c', 'i', '2', 'f', 'b', 'q', ',', '3', '/', 'z',
					'8', 'a', ';', 'l', 't', 'm', 'o', '!', 'h', '9', '.', '=', '>', 'v', 'j', '#', '4', 'u', '5', 'g',
					'7', 'e', '%', 'd', '1', 'k', ':', '0' },
			{ 'w', 'f', 'v', '9', 'c', 's', '0', 'm', 'e', '!', '8', '7', '/', 'y', 'q', '2', 'd', 'n', '%', 'j', 'l',
					',', 'a', ';', 'o', 'i', '3', '&', '5', '=', 'h', 'k', '4', 'p', '.', '>', '#', 'u', 'g', 'x', 't',
					'6', ':', '<', 'z', 'r', '1', '?', 'b' },
			{ 's', 'd', '=', 'c', '&', ',', '!', '9', 'a', '%', '#', 'u', 'm', 'i', 'z', 'h', '4', 't', 'b', '5', 'e',
					'f', 'g', '1', '0', '.', 'w', 'v', '?', '/', 'y', '7', '<', 'q', '6', '8', 'n', ':', ';', 'r', '3',
					'o', '2', 'k', '>', 'p', 'x', 'l', 'j' },
			{ '3', 't', 'k', 'r', 'h', 'a', '>', '%', 'w', 'j', '7', '/', 'i', '6', 'n', 'b', ';', '9', '<', '?', 'f',
					'x', '=', '!', '1', ':', 'q', 'v', 'c', '.', '&', '#', 'o', 'g', '4', 'm', 'u', 's', 'y', '2', 'l',
					'8', 'z', 'd', 'p', '5', '0', ',', 'e' },
			{ 'r', 'z', 'd', 'v', 'e', '3', '<', '=', '8', 'i', '9', 't', '7', '!', '0', '/', 'p', '4', 'k', '%', 'n',
					'h', '5', '?', 'j', 'o', '2', 'q', 'f', 'x', ';', 'a', '>', 'l', 'y', ',', 's', 'm', ':', '&', 'u',
					'.', 'g', '6', 'w', 'b', 'c', '#', '1' },
			{ '/', '?', '7', 'k', 'm', 'j', '6', 'o', 'h', 'c', ':', 'r', 'x', '<', '=', 'n', 'y', '4', '&', '9', 'z',
					'1', '!', 'a', '.', 'f', ',', 's', '5', '3', 'g', 'd', 'q', 'l', '0', 'v', 'e', '#', 'w', ';', 't',
					'b', 'p', '%', '2', '8', 'i', 'u', '>' },
			{ 'o', 'z', 'k', '>', '.', 'j', 'n', 'q', '7', '6', 'm', 'h', '9', '&', '/', '!', '?', 'w', '8', '3', ';',
					'2', 'v', ',', 'p', 'e', 'd', ':', 'x', 't', 'y', 'r', 'f', '=', '%', 'i', 'c', '4', '5', 's', '#',
					'<', 'l', '0', 'g', 'b', 'a', '1', 'u' },
			{ ';', 'i', ',', ':', 'z', '#', '3', 'c', '>', '5', 'd', '7', 'r', '4', '6', 't', 'j', '2', 'f', '8', 'h',
					'l', '.', 'x', '?', 'w', '&', 'k', '9', 'g', '/', 'e', 'v', '1', 'm', 'y', '%', 's', 'u', 'o', '0',
					'n', '!', '<', 'b', 'a', 'q', 'p', '=' },
			{ '%', 'm', '7', 'b', '#', ':', 'z', 'c', '<', 't', '5', '8', 'x', '.', 'a', 'r', ',', 'y', '>', '0', '3',
					'g', '1', 'v', ';', '9', 'j', '/', 'p', 'i', 'n', '=', '4', 'd', '&', '!', 'l', '2', 'f', 'q', 's',
					'u', 'e', '6', 'h', '?', 'o', 'w', 'k' },
			{ '7', 'g', '/', '5', 'o', 'b', '9', 'd', ',', 'a', 'k', 'v', 'j', '1', '?', 'p', '0', '!', 'i', 'y', 'f',
					'm', 'z', '&', 's', 'q', '%', '<', '.', '=', 'u', '8', 'r', '>', 'x', 'w', '#', 'l', ';', 'n', ':',
					'4', '2', 't', 'e', '3', 'h', '6', 'c' },
			{ '&', 'b', 'f', 'h', ';', '<', '1', 'a', 'q', 's', '#', '/', 'g', '8', 'j', 'u', 'x', 'c', 'o', '!', '4',
					',', 'd', '%', '=', '>', '7', '.', '?', 't', ':', 'y', '0', 'r', 'k', 'i', '3', '2', '6', 'm', 'e',
					'z', '5', 'l', 'p', 'w', '9', 'v', 'n' },
			{ 'd', '%', '8', 'h', '3', 's', '6', 'j', '>', 'k', ':', '7', 'a', '.', '=', '<', 'w', ',', 'i', '/', 'u',
					'0', 'z', 'n', 'f', 'p', 'c', 'q', '!', 't', ';', 'r', '9', '5', '&', '#', '1', 'm', '4', '2', 'y',
					'g', 'l', 'b', 'e', '?', 'o', 'v', 'x' },
			{ '6', 't', '/', 'e', 'a', 'f', 'l', '#', 'j', '!', 'p', '2', '=', 'c', 'm', 'b', '?', '8', 's', 'u', 'd',
					'g', 'r', 'y', '7', 'i', '.', 'v', '%', '1', 'k', 'z', ':', '<', 'h', ';', 'w', 'n', '5', 'q', '&',
					'9', ',', '0', '3', '>', '4', 'x', 'o' },
			{ 'v', 'e', 'i', '.', 'x', '8', '3', 'y', '=', 'k', '&', 'a', '?', 'b', '#', 'z', '!', '1', '/', 'f', ':',
					'7', '4', '%', 'n', '5', 'p', 't', 'r', 'l', '<', ',', ';', 'u', 'm', 'q', '0', 'o', '6', 's', 'w',
					'g', 'j', '2', 'h', '>', 'c', 'd', '9' },
			{ 'p', '8', 'f', 'a', '%', '0', '.', '&', '/', '#', 'e', '1', 'n', 'i', 'w', '9', '4', '!', '3', 'o', 'v',
					',', 'r', ';', '6', 'b', '5', 's', ':', 'x', 'd', '=', 't', 'u', '<', 'c', 'z', 'g', 'y', 'k', 'h',
					'l', 'q', '2', '>', '?', 'm', 'j', '7' },
			{ 'd', ':', '.', '>', '<', 'h', '0', 'u', 'p', '3', 'b', '7', '6', ',', '!', 'w', '8', 't', '9', 'k', ';',
					'o', '1', 'z', 'a', '2', '?', '&', '=', 'c', 'm', '%', 'i', 'v', 's', '#', 'f', 'x', '/', '5', 'l',
					'r', 'e', '4', 'g', 'y', 'q', 'j', 'n' },
			{ 'f', 'i', 'h', 'k', '9', 'p', 'e', '&', '4', 'n', '=', '!', '5', 'd', '/', 'l', 'u', ';', '2', ':', '.',
					'b', '?', ',', 'g', '%', 'o', '6', 't', '3', 'a', '1', 'y', '0', 'j', '>', '#', 'c', 'v', 'r', '8',
					'x', 'w', '7', 'm', 's', 'q', '<', 'z' },
			{ '3', 'h', '.', '>', 'u', 'o', 'w', ',', 't', 'z', '?', 'g', ':', '1', 'q', 'e', 'j', 'y', '/', '5', 's',
					'k', '0', 'r', 'd', 'b', '<', 'x', 'c', '8', '!', 'n', 'l', '7', 'f', 'a', ';', '=', '%', 'm', '2',
					'#', 'p', '&', 'v', 'i', '9', '6', '4' },
			{ 'h', '&', 'u', ';', 'b', '4', 'z', 'f', '3', ',', 'm', 'q', '/', '9', 'y', 'l', '<', 's', 'w', 'o', 'c',
					'!', '%', 'j', '7', 'x', '0', '?', 't', '=', '6', 'a', '8', '>', 'n', 'g', 'i', 'p', 'd', '#', '1',
					'v', 'r', ':', '.', '2', 'k', 'e', '5' },
			{ 'q', 'f', '<', '%', 'l', 'z', ',', '/', 't', '9', 'x', 'a', 's', '8', '2', 'i', 'n', '0', '.', '3', 'g',
					'p', '5', 'c', ':', 'w', '!', '1', '=', '?', 'v', '6', 'j', '&', 'e', 'u', 'm', '4', 'k', '#', 'b',
					'y', '7', '>', 'h', ';', 'o', 'r', 'd' },
			{ 'y', '=', '7', ':', '!', 'k', '0', 'g', 'n', '4', 'i', 'u', '8', 's', 'd', 'o', 'q', 'w', '%', 'x', '6',
					'2', ',', 'p', '?', 'a', 't', '/', 'm', 'l', 'e', '3', 'h', 'c', '>', 'b', 'f', ';', '1', 'z', '<',
					'.', 'r', '#', '5', 'j', 'v', '9', '&' },
			{ '3', '.', '7', '5', 'a', 's', 'e', 'i', ';', 'n', 'w', '!', 'z', '6', 'q', ',', 'y', 'h', 'f', 'v', 'm',
					'/', 'o', 'k', '2', 'p', 'g', 'c', '0', 'd', '<', 'b', '4', '>', 't', '%', '9', 'u', '&', '8', 'r',
					'#', 'j', 'l', 'x', ':', '=', '1', '?' },
			{ 'i', 'f', ',', 'l', 'b', '0', '5', ';', '#', 'n', '3', '%', '/', 'j', 'd', 'e', '.', 'w', 'y', 's', 'p',
					'v', '4', '1', 't', 'q', '=', ':', 'r', '7', '<', 'g', 'm', 'c', 'z', '9', '2', '&', '?', '6', 'a',
					'o', 'k', 'h', '>', 'x', '8', '!', 'u' },
			{ 'j', 'v', 'm', '/', '4', 't', '<', 'l', '%', '8', 'i', '!', 'x', '=', 'q', '5', '3', '>', 'd', 'k', 'a',
					'y', 'p', '0', ',', '?', 'o', 'w', 'h', 'e', 'b', 'z', '9', 'r', ';', 'f', '.', 'u', '#', 's', 'g',
					'2', 'n', '&', ':', 'c', '1', '6', '7' },
			{ 't', 's', '?', 'u', 'h', '=', '8', ',', 'f', 'j', 'n', 'v', 'o', 'z', 'a', 'p', '#', 'x', '.', 'b', 'm',
					'e', '0', 'w', 'y', '&', '4', 'g', 'l', '%', 'd', '6', 'k', '7', '3', 'q', ';', 'c', '/', '!', '<',
					'>', '5', 'i', '1', ':', '2', 'r', '9' },
			{ ';', '4', 'd', '1', '6', 'm', 'y', 's', 'z', '<', 'a', '!', 'e', 'h', 'n', 'b', '5', 'u', '=', '&', '.',
					'%', 'j', ',', 'g', 'f', 'v', '3', 'x', '>', '#', 't', 'p', '7', '2', ':', 'o', 'q', 'w', '9', 'l',
					'?', 'c', 'r', '0', '8', 'k', 'i', '/' },
			{ 'j', '0', ';', 'u', '.', 'c', '>', 'a', 'd', '6', '7', 'l', 'k', '!', '?', 'e', 'x', 'g', '=', '2', 'y',
					'8', 'm', '#', 'i', ',', '3', '/', 'w', '4', '<', 'n', '&', ':', '5', 's', '1', 'z', 'q', 'b', 't',
					'f', 'p', '9', '%', 'r', 'o', 'v', 'h' },
			{ 'o', '=', 'd', '&', 'u', 'l', ':', '3', 'c', '!', 'k', '.', 'w', '<', '>', '2', '5', 'g', '0', 's', 'q',
					'#', ';', 'n', ',', 'e', '9', 'j', 'r', '8', 't', '?', 'z', '6', '/', 'h', 'y', 'i', 'm', 'b', 'x',
					'p', '1', '7', '4', '%', 'v', 'f', 'a' },
			{ 'n', '4', 'r', 'i', '?', '3', 's', '7', 't', 'y', ',', 'q', '.', 'l', '#', 'g', ';', 'j', 'u', 'c', '5',
					'9', '6', 'z', 'e', '0', 'k', 'b', '=', '>', 'o', '!', '8', '2', 'm', ':', 'p', '<', 'f', 'a', '%',
					'/', 'w', 'x', 'd', 'v', '1', '&', 'h' },
			{ 'j', 'w', 'q', 'g', '0', '%', 'e', '=', '.', 't', '3', 'l', 'm', 'z', '5', '2', 'c', '7', 'k', 'a', '9',
					';', '6', ':', 'v', 'n', 'h', '1', 'x', 'f', 'b', '&', 'o', '>', '/', 'p', 'u', '<', 'r', ',', '8',
					'?', 'd', '#', '4', '!', 'y', 'i', 's' },
			{ 'x', '.', 'g', '5', 'l', 'm', '9', '#', '1', 'n', 'y', 'u', 'z', ';', '3', 'p', 'f', 'o', '6', '8', '<',
					'4', 'i', 'a', 'e', 'k', 'b', 'q', '%', '&', '>', 'v', 'w', '2', '=', 'd', '7', '0', '?', ':', 'c',
					'!', 'r', '/', 't', ',', 'h', 's', 'j' },
			{ 'a', '0', '2', 'i', 'h', '3', 'u', 'e', 'y', 'f', '=', 'w', '&', '5', '7', '!', 'c', 'o', 't', '#', 'x',
					'6', '1', 'q', 'k', '>', 'p', '8', '%', 'r', ';', 'l', ',', '4', '?', 'b', ':', 'n', '/', 'g', 'z',
					'v', 's', 'm', '<', 'j', '.', 'd', '9' },
			{ '1', '>', 'x', 'g', 'a', '2', 'c', 'm', '7', '=', ',', 'w', '5', 'd', 'j', '4', 'l', '<', '6', 'e', '%',
					'y', 'h', 'u', '.', 'i', '3', 'b', 't', ':', '#', '0', 'k', 'o', 'z', '&', '/', '!', 'q', 'f', 'n',
					's', '?', 'p', '9', ';', 'v', 'r', '8' },
			{ 'c', '/', '6', 'm', '1', 's', 'e', 'f', 'x', 'v', 'l', 'z', '>', '5', 'd', ',', 'y', ';', '!', 'i', 'n',
					'3', '0', '4', '=', 'j', '.', 'o', 'h', 'k', 'w', '2', '?', '#', '&', 'r', 'g', 'p', 'q', '%', 'a',
					'7', 'b', '<', '9', 't', '8', 'u', ':' },
			{ '&', 's', 'o', 't', 'y', '>', '/', ';', '1', 'z', 'p', 'i', 'g', '4', 'l', 'k', '5', 'f', '6', '!', 'a',
					'#', '=', '2', '9', '3', 'h', 'r', 'q', '?', 'w', 'j', ':', 'd', '7', 'e', 'c', 'b', '<', 'n', '%',
					'v', '.', 'm', 'u', '8', '0', ',', 'x' },
			{ '3', '1', '7', 'l', ':', '/', 'w', 'i', 'k', 'd', 'y', '8', '6', 'a', 'b', 'v', '=', 'q', 'h', 'j', 'p',
					'c', '#', 'o', ',', 's', '?', 'n', '0', 'g', '&', '4', '5', 'r', '2', '9', '.', '>', 't', ';', 'u',
					'f', '!', '<', 'x', '%', 'm', 'z', 'e' },
			{ '.', 'v', 'k', '8', 'n', '7', 't', 'y', '?', 'p', '%', 'u', '/', 'z', 'q', '#', '9', 'h', '3', 'o', '6',
					'2', '=', 'j', 'a', 's', 'c', '0', '!', 'w', 'r', ';', '5', 'e', '<', '&', '4', 'i', 'd', 'm', '>',
					'1', 'f', 'x', 'b', 'l', ',', 'g', ':' },
			{ 'l', '5', '#', 'm', '2', 'a', 'u', 'y', 'z', '&', '4', 'i', '1', '.', '%', '?', 'n', 'j', '<', '3', '=',
					'6', 'v', 'k', ':', 'b', ',', '0', 'p', '8', '!', 'w', 's', 'f', ';', 'c', '>', 'h', 'g', 'q', 'r',
					'9', 't', 'd', '7', 'x', 'o', '/', 'e' },
			{ '?', 'd', '%', '9', ':', '#', 'p', '>', 'm', 'o', 'g', '4', '8', 'a', '7', 'c', ';', 'r', 'k', 'f', 't',
					'6', 'l', '5', '0', '1', 'b', 'q', 'u', 'w', '/', 'h', '3', 'e', 'v', 'y', 'j', 'x', 'i', '2', '=',
					'&', ',', 's', '<', 'n', 'z', '!', '.' },
			{ ',', 'q', ';', 'b', 'w', '2', 'h', 'g', 'r', 'l', '7', 'd', 'm', '6', '5', 'o', '0', '/', '=', 'v', 'u',
					'9', '#', 'e', 'y', '>', 'f', 's', 'n', '!', 'c', 'j', 'x', 't', '%', '8', '&', '<', 'p', '4', ':',
					'i', 'a', '?', '1', 'z', '.', 'k', '3' },
			{ '7', 'o', 'x', 'b', 'h', 'l', '#', ':', '/', ',', 't', '=', 's', '6', '.', 'z', 'n', '9', '>', '?', 'f',
					'y', '8', '%', '0', ';', '&', 'a', 'u', '<', 'k', 'm', '!', 'v', 'j', 'e', 'i', 'c', 'r', '5', 'g',
					'd', '1', 'w', 'p', '3', '4', 'q', '2' },
			{ 'u', 'l', ';', 'h', '0', 'z', 'f', '=', ':', 'i', 'm', '.', '#', '?', 'y', '6', '4', 'g', 'a', '7', 'o',
					's', 'v', '<', 'k', 'b', '>', '1', '/', 'x', 'w', '3', 'c', 'e', 'n', '8', 'p', '2', 'r', '!', 'd',
					'9', 'j', 'q', '&', '5', ',', 't', '%' },
			{ '<', 'g', '5', '>', '&', 'l', 'j', 'y', '6', 'n', 'c', 'x', 'i', 'v', 'b', '8', 's', 't', '3', 'd', '/',
					'0', '!', '1', '.', 'o', 'u', 'a', 'k', ':', '?', '2', '4', 'p', 'q', ',', 'f', '%', '7', ';', 'w',
					'z', 'h', 'm', '=', 'e', '9', 'r', '#' },
			{ '%', 'i', 'c', 'r', '.', 'd', 'o', 'p', 's', 'h', '0', '>', 'l', '<', '2', 't', '?', '4', 'm', '5', 'g',
					',', 'u', '1', 'x', 'q', '6', 'k', 'f', ':', '9', 'v', '/', 'j', 'z', '=', 'y', '7', '#', '8', '&',
					'b', 'a', '!', 'e', '3', 'n', 'w', ';' },
			{ 'y', '7', 'p', 'i', '3', '>', 'q', '5', 'k', 'c', '<', ',', '.', '!', 'f', 'w', 'd', 'x', 'e', ';', '8',
					'?', 'v', 'l', ':', '&', 'r', 'g', '/', 'b', 'z', 'n', '%', '#', 't', 'm', 'u', 'h', '4', 'a', 's',
					'j', 'o', '1', '2', '9', '6', '=', '0' },
			{ '3', ':', '2', 'u', 'q', ',', 'x', '0', 'o', '%', 'l', 'h', 'w', '?', 't', '!', 'i', 'j', 'b', 'a', 'g',
					';', 'd', '=', '&', '1', '5', 'z', '9', 's', 'p', 'n', 'v', 'm', '4', '/', '7', '6', '8', 'y', '#',
					'>', 'e', '.', 'k', 'r', 'c', '<', 'f' },
			{ 'a', '%', '.', 'v', 'u', '4', 'c', 'b', 'r', '7', '>', ',', 'k', 'h', '/', '0', '?', 'e', '3', ':', 'f',
					'n', 'd', 'p', '9', 'z', 'i', 't', '&', '2', '8', '#', '!', 'l', ';', 'y', 'j', 'w', 'q', '1', '<',
					'o', 'g', 'x', '5', 'm', 's', '6', '=' },
			{ 'v', '.', '#', 'm', 'w', '0', 'p', '9', 'k', 'h', '5', 'f', '/', '4', 't', '?', 'u', 'x', 'q', '6', 'n',
					'j', '>', '!', 'y', 'e', 'i', 'a', 'c', ',', '2', '&', 'd', ';', 'g', 'o', '3', 'l', '<', '=', 's',
					'8', '%', 'b', '7', ':', '1', 'z', 'r' },
			{ '5', 'y', 'm', 'g', ':', '0', 'p', 'r', ';', '!', 'h', 't', '1', '/', 'j', ',', 'z', '3', '9', '8', '<',
					'o', '6', 'q', '.', 'u', 's', 'w', 'n', 'a', 'd', '7', 'f', '#', 'x', 'v', 'b', '2', 'k', '>', 'c',
					'4', 'e', 'i', '&', '?', '=', '%', 'l' },
			{ 'd', 'a', '#', ';', 'i', 'k', 'q', 'c', '9', '5', 'y', '%', ':', '2', 'p', 'r', 't', '<', 'b', '/', '0',
					'w', 's', '8', 'x', 'u', 'h', 'e', 'z', '.', 'g', '4', '7', '>', 'm', '=', '6', 'v', 'o', '&', 'n',
					'f', '3', 'l', '!', '1', 'j', '?', ',' },
			{ '=', 'p', '.', 'v', 'z', '0', ',', '#', 'k', '3', 'r', 'e', 'c', 't', 'f', 'd', 'x', 'i', '9', 'o', 'm',
					'6', '8', '?', 'g', ';', '>', 'h', 's', ':', 'q', '4', 'u', 'w', 'b', '%', '&', 'l', '!', 'j', 'a',
					'n', '/', '1', '2', '<', '5', 'y', '7' },
			{ '5', '0', '1', 'c', 'y', 'x', '>', 'p', '3', '=', '%', '&', 'g', 'i', '4', 'm', 'f', 'r', 'd', 'z', 't',
					'8', 'j', 'e', '!', 'q', 'a', 'u', 'o', '/', '2', '9', 'l', '7', 'n', '6', 'v', '#', 'w', 'b', ';',
					'?', ':', 'h', ',', 'k', '.', 's', '<' },
			{ 'e', '4', '=', '9', 'x', 'w', '!', 'f', 'z', 'u', '#', 't', '5', 'i', '1', '>', 'j', 'm', 'k', 'y', ':',
					'h', '6', '7', 'p', 'v', 'd', 'b', ',', '3', 'l', 'a', '%', 'c', 's', '.', 'g', '0', 'n', 'o', '?',
					';', 'q', '&', '<', '2', '8', '/', 'r' },
			{ 'j', '4', 'd', 'i', '7', '5', 'p', 'o', 'v', 'q', 'b', '9', 't', '/', 'k', 'm', 'w', '8', 'c', ',', 's',
					'?', '.', 'z', '&', '#', '%', 'f', '<', '6', 'e', 'y', '>', 'x', 'a', 'u', '3', 'r', 'n', '!', 'h',
					'1', ':', 'l', ';', 'g', '2', '0', '=' } };
	int[] randomLevels = { 71, 51, 26, 66, 50, 20, 49, 34 };
	String[] levelKeys = { "k5", ";k", "/p", "&l", "!u", "ff", "!6", "5=" };
	char[] charsToUse = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z', ',', '.', '?', '&', '%', '!', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', '0', '/', ':', ';', '=', '<', '>', '#' };

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

	// shuffle char array
	char[] shuffle(char[] array) {
		Random rand = new Random();

		for (int i = 0; i < array.length; i++) {

			// get random index
			int randomIndexToSwap = rand.nextInt(array.length);

			// move chars to new positions
			char temp = array[randomIndexToSwap];
			array[randomIndexToSwap] = array[i];
			array[i] = temp;
		}
		return array;
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
				String out = "String[] keys ={";
				for (int i = 0; i < mixUpKeyCount; i++) {
					String keyString = "";
					Random rnd = new Random();
					for (int j = 0; j < 3; j++) {
						keyString = keyString + charsToUse[rnd.nextInt(charsToUse.length)];
					}
					out += "\"" + keyString + "\"";
					if (i != mixUpKeyCount - 1) {
						out += ",";
					}
				}
				out += "};";

				// make mix ups
				out += "char[][] mixedUps = {";
				char[] temp;
				for (int y = 0; y < mixUpKeyCount; y++) {
					out += "{";
					// shuffles the alphabet
					temp = shuffle(charsToUse);

					// prints to console with correct formating in case you would like to use new
					// keys
					for (int i = 0; i < temp.length; i++) {
						out += "'" + temp[i] + "'";
						if (i != temp.length - 1) {
							out += ",";
						}
					}
					out += "}";
					if (y != mixUpKeyCount - 1) {
						out += ",";
					}
				}
				// make random levels
				out += "};";
				out += "int[] randomLevels = {";
				for (int i = 0; i < randomLevels.length; i++) {
					String keyString = "";
					Random rnd = new Random();
					keyString = keyString + Integer.toString(rnd.nextInt(100 - 10) + 10);
					out += keyString;
					if (i != randomLevels.length - 1) {
						out += ",";
					}
				}
				out += "};";
				// make random level keys
				out += "String[] levelKeys ={";
				for (int i = 0; i < randomLevels.length; i++) {
					String keyString = "";
					Random rnd = new Random();
					for (int j = 0; j < 2; j++) {
						keyString = keyString + charsToUse[rnd.nextInt(charsToUse.length)];
					}
					out += "\"" + keyString + "\"";
					if (i != randomLevels.length - 1) {
						out += ",";
					}
				}
				out += "};";
				copyToClipBoard(out);
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

				/* decryption logic */
				if (deEncrypt.isSelected()) {
					String temp = inString;

					// find out how many times the message was ran through the algorithm using our
					// level keys and the first two characters of our input
					int ecyrtptionLevelIndex = findInArray(levelKeys, temp.substring(0, 2));
					temp = temp.substring(2);

					// loop till we have a string that has no more keys left and just the message
					for (int i = 0; i < randomLevels[ecyrtptionLevelIndex]; i++) {
						try {
							temp = deEnccrypt(temp);
						} catch (IndexOutOfBoundsException e) {
							encrypt.setSelected(true);
							deEncrypt.setSelected(false);
						}
					}
					// copyToClipBoard(temp);
					// clipboard.setContents(stringSelection, null);
					Out.setText(temp);
				} else {
					Random rnd = new Random();

					// select random lvl key(how many times encrypt() will be ran on our temp
					// string)
					int levelSelectedIndex = rnd.nextInt(levelKeys.length);

					// value to store our out put after encryption to be dumped back into encrypt()
					String temp = inString;

					// run our message through the encrypt algorithm for our chosen count
					for (int i = 0; i < randomLevels[levelSelectedIndex]; i++) {
						temp = encrypt(temp);
					}
					// copyToClipBoard(levelKeys[levelSelectedIndex] + temp);
					// clipboard.setContents(stringSelection, null);

					// add our chosen level key to our encrypted string
					// level key is a signature that tells the dencryptor how many times we ran
					// encrypt() on our string
					Out.setText(levelKeys[levelSelectedIndex] + temp);
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

	// encryption algorithm
	String encrypt(String message) {
		String inString = message;
		Random rndRandom = new Random();

		// chooses mix up index
		int mixUpIndex = rndRandom.nextInt(keys.length);

		// add our selected key to the beginning of our string
		String outString = keys[mixUpIndex];

		// chooses the mix up that will be used for cipher
		char[] keyToUse = mixedUps[mixUpIndex];

		// encrypts the string with the chosen key each iteration
		for (int i = 0; i < inString.toCharArray().length; i++) {

			// if the current char is a space, skip it
			if (inString.toCharArray()[i] != ' ') {

				// Correlate our current char, like 'h' in hello to a new char in our mix up,
				// like '?'
				outString += keyToUse[findInArray(charsToUse, inString.toCharArray()[i])];
			} else {

				// adds a space to our out put if there is one in our input
				outString += " ";
			}
		}
		return outString;
	}

	String deEnccrypt(String in) {
		String out = "";
		// finds which key was used for this encryption using the keys array and the
		// first three chars of our input
		char[] keyUsed = mixedUps[findInArray(keys, in.substring(0, 3))];

		// We start off at 3 because we don't want to include the first 3 chars to our
		// output, so we start af the 4th character in our string
		for (int i = 3; i < in.toCharArray().length; i++) {
			if (in.toCharArray()[i] != ' ') {

				// go from cipher -> actual characters
				out += charsToUse[findInArray(keyUsed, in.toCharArray()[i])];
			} else {

				// if there is a space, add it to our output
				out += " ";
			}
		}
		return out;
	}

	// finds a string in an array and returns the index
	int findInArray(String[] array, String value) {

		// loop through each index looking for a string that matches the value varibe
		for (int i = 0; i < array.length; i++) {
			// if the item in the current index matches our input, return i(our current
			// index)
			if (array[i].equals(value)) {
				return i;
			}
		}
		// if we can't find it, return -1
		return -1;
	}

	// the same thing as the string version, but with chars, Ex: 'H'
	int findInArray(char[] array, char value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}
}
