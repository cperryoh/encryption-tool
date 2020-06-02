
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
	String[] keys = { "y5.", "hvt", "3ju", "g4o", "ubg", ",0q", "w8j", "as.", "lsx", "s1&", "xye", "%1l", "wbn", "7q9",
			".!t", ",,r", "ujc", "!gc", "jid", "vi7", "q!4", "g?k", "a,h", "g4d", "!k0", "2rw", "ii3", "5v7", "h!?",
			"0og", "bx3", "6od", "5o!", "a%v", "2yq", "pdi", ".9a", "mfs", "c83", "k06", "p3h", "dvh", "yrj", "nu0",
			"3gb", "f&5", "pif", "!9v", "rhv", "rp0", "n9g", "b6f", "%tt", "md6", "bm.", "ufj", ",!j", "6gu", "ozf",
			"mta", "&fc", "vsa", "k7z", "m,5", "uxx", "yqv", "kgs", "1b2", "!5k", "7a4", "8z1", "0cn", "z!t", "hrj",
			"33i", "?&n", "3,g", "04w", "w5h", "%xt", "862", "3np", "9ps", "qmy", "c3!", "8wc", "rc1", "slr", "&md",
			"?4q", "7e%", "2?%", "4ih", "3dd", "7s!", "1b.", "&89", "q95", "05,", "!2r" };
	char[][] mixedUps = {
			{ '2', '1', 'b', '&', 'v', 's', 'a', 'p', '%', '4', 'k', 'r', '?', '9', 'c', 'j', 'y', '3', '6', 'w', 'd',
					'0', ',', 'e', 'm', '8', '!', 'u', 'x', 'g', 'n', 'z', '7', 'h', 'l', '5', 'q', 'f', 'i', 't', 'o',
					'.' },
			{ 'k', '5', 'm', 'p', 'o', 'u', '4', 'c', 's', 'v', 'a', ',', 'b', 'g', '&', 'h', 'e', 'z', 'f', 'd', '1',
					'y', '3', '6', 'i', '0', 'l', 'q', 'r', 'w', '8', 'n', '.', '9', 'j', '%', 't', '7', '?', '2', 'x',
					'!' },
			{ 'a', 's', 'r', 'g', 'f', 'v', 'i', 'e', 'z', '0', 'p', 'b', '7', 'q', 'y', 'n', 'w', 'k', '3', '4', '%',
					'1', '&', '8', '.', '2', 'j', 't', 'o', 'm', 'c', 'l', '?', '6', 'x', '9', 'h', ',', '5', 'u', '!',
					'd' },
			{ '5', 'k', 'q', 'p', 'm', 'e', 'x', 'f', 'v', 'o', 'y', 'w', '6', '.', 'd', 'c', 's', '!', 'b', '2', '7',
					'g', 'l', 'u', '1', 'a', '8', 'n', 'j', '0', 'i', '3', 'h', '%', ',', '&', 'r', '?', 'z', 't', '9',
					'4' },
			{ 'j', '3', 'u', 'a', '9', 'e', 'y', '.', 'o', '4', '6', 'b', 'm', 'd', '%', '0', 'q', 'h', 'i', '7', ',',
					'f', '!', 't', '8', 'l', '?', 'z', 'w', 'g', '1', 'r', '&', '2', 's', 'k', 'x', '5', 'v', 'c', 'n',
					'p' },
			{ 'u', '8', ',', 'p', '4', 'y', 'i', 't', 'g', '5', 'j', '.', 'l', '&', '6', '!', '1', 'q', '9', 'c', 's',
					'r', 'm', 'z', 'b', 'o', '?', 'x', 'v', 'a', 'w', 'h', 'n', '7', 'k', '3', 'd', '2', 'f', '0', '%',
					'e' },
			{ '6', 'k', 's', 'g', '8', 'e', 'n', '?', 'q', 'i', 'c', '0', '3', 'z', '5', 'a', '7', 'j', '9', 'd', 'u',
					't', 'h', 'x', 'm', '!', 'w', '%', 'y', '.', '&', 'r', '2', 'f', 'l', 'o', 'b', 'v', ',', 'p', '4',
					'1' },
			{ 'r', '!', '0', '5', 'q', 'a', 'k', '2', '4', '&', '?', 'm', 'n', 'w', 'h', 't', 's', 'x', 'd', 'i', 'p',
					'3', 'v', 'z', '7', '9', 'o', 'g', '8', '6', 'c', 'u', 'j', '.', 'b', '1', 'y', '%', 'l', 'f', 'e',
					',' },
			{ ',', 'd', 'n', 'm', '%', '.', '1', '0', '3', 'f', 'x', 'b', 'w', '9', '2', 's', 'j', 'v', '6', '5', 'c',
					'z', 'p', 'u', 'y', '!', 't', 'l', 'q', 'h', 'a', 'g', 'i', '4', '&', 'o', 'r', 'k', 'e', '8', '?',
					'7' },
			{ 'b', 'x', 'm', 'e', 'u', 'w', 'j', 's', '8', '.', 'l', ',', 'p', 'y', '%', '4', '9', 'a', 't', 'k', 'h',
					'i', '&', 'c', 'q', '!', 'd', '2', '3', '?', 'o', '0', 'n', 'r', '7', 'g', '1', 'v', '5', 'f', 'z',
					'6' },
			{ 'u', 'o', 'l', 'c', 'd', '?', '4', 'h', 'z', 'j', '5', '2', 'w', 'x', '7', '&', '%', 't', 'b', 'e', 'm',
					'n', '.', '0', 'i', '3', '1', 'g', 'y', 'v', 'k', 'q', 'a', 's', 'r', '!', '6', 'p', 'f', ',', '8',
					'9' },
			{ 'n', 'r', 's', 'l', 'o', '4', '5', 'e', '2', '?', 't', '6', '7', 'x', '3', '&', 'q', '.', 'i', 'a', '1',
					'v', 'u', '8', 'd', 'w', 'p', 'z', 'h', 'y', '0', 'j', ',', 'm', 'k', 'c', '%', '9', 'f', 'g', 'b',
					'!' },
			{ ',', 'u', 'i', 'c', 'x', 'j', 't', 'w', '?', '0', '3', 'n', 'f', '6', 'p', '.', 'z', 's', 'o', '&', 'b',
					'v', 'h', 'l', 'y', '4', '!', 'd', '%', '7', 'q', '8', 'a', 'm', 'e', '1', '2', '5', 'r', '9', 'g',
					'k' },
			{ '0', 'x', '5', 'k', 'z', 'q', 't', 'f', 'l', '&', ',', '8', '4', 'n', 'a', 'e', 'h', '6', '2', '9', '1',
					'r', 'w', '!', 'u', 'g', '3', '?', 'p', '%', 'v', 'c', 'i', '7', 'o', 'b', 'j', 'm', 's', 'd', 'y',
					'.' },
			{ 'f', 'i', 'l', 's', 'c', 'k', '2', 'q', 'm', '1', 'r', 'h', '3', 'd', 't', '6', 'b', 'j', 'a', 'o', 'g',
					'n', 'w', '!', 'e', 'p', '.', '5', '?', '9', 'x', ',', 'u', 'z', '4', 'v', '0', 'y', '%', '&', '8',
					'7' },
			{ 'h', 'o', '!', '0', '4', 'd', 'c', 'x', 'l', 'k', '6', 'a', 'v', 'g', '7', 'i', 'q', '5', '9', 'p', '1',
					'.', '&', 't', '3', 'f', 'j', '?', 'u', ',', 'w', 'm', '%', 'n', 'b', 'e', '8', 's', '2', 'z', 'r',
					'y' },
			{ 'g', 'h', '9', '3', 'k', 'd', 'c', 'w', 'q', '5', ',', 'a', 'i', '!', 'u', 'n', 'p', '6', '&', '2', 'z',
					's', 't', 'l', '4', 'o', 'v', 'r', 'e', '8', '1', 'b', '%', '7', '0', 'm', '?', 'f', '.', 'y', 'x',
					'j' },
			{ 'a', 's', 'r', 'h', '3', '7', '.', 'n', 'z', 'x', '5', 'g', '8', 'u', '0', 'e', 'i', '2', 'f', 'l', 'v',
					'o', 'd', 'q', '6', 'k', '1', 'b', 'y', '9', 't', '!', 'p', 'w', 'm', 'j', '%', '?', 'c', ',', '&',
					'4' },
			{ 'w', '&', '3', ',', 'x', 'o', 'm', 'g', 'n', 'v', '1', '9', 'e', '!', 'l', 's', '5', 'k', 'y', 'b', 'q',
					'f', 'r', '.', 'i', '2', 'c', 'h', 'z', 'p', '7', 'd', '8', 'u', 'a', '6', 'j', '?', '4', 't', '0',
					'%' },
			{ 'z', '%', '7', 'x', 'a', '8', '.', ',', 't', 'r', 'd', '4', 'm', 's', 'o', 'p', 'y', '0', '2', 'h', 'i',
					'f', '5', 'e', 'w', 'n', '6', 'v', '1', 'j', '9', '3', 'b', '!', 'k', '?', 'g', '&', 'l', 'u', 'q',
					'c' },
			{ '7', '.', 'r', 'm', '?', '!', '3', 't', '5', 'p', 'g', 's', '0', '2', '4', 'z', 'a', 'k', 'q', 'd', '9',
					'e', 'o', '1', 'n', 'h', 'j', '6', '&', 'f', 'v', 'b', '8', ',', 'c', 'u', 'i', 'w', 'x', 'y', 'l',
					'%' },
			{ '!', 'k', '.', '0', 'p', 'c', '1', 'e', '8', 'q', 'z', 'i', 'd', 'a', 'v', 'b', '4', ',', 'n', '9', 'y',
					'j', '3', 'u', 'g', '2', 'x', '7', 'o', '&', '%', 'm', '6', 't', '5', '?', 'h', 'l', 'w', 'r', 's',
					'f' },
			{ 'y', 'l', '2', 'j', 'h', 'd', '&', 'a', 'g', 'z', 's', '6', 'u', ',', 'i', '7', '5', 'f', '?', 'n', 'q',
					'o', 'v', '%', '0', '4', 'c', 'k', '9', 't', 'm', 'b', '3', '!', 'e', 'p', 'w', '.', '1', 'r', 'x',
					'8' },
			{ 'v', 's', '&', '4', 'm', '%', 'l', 'n', '0', 'x', 'c', '.', 'z', 'k', ',', '1', '9', 'h', 'b', '!', 'f',
					'p', 'j', '2', 'i', '6', 'u', '8', 'a', 'e', 'g', '?', 'w', 'o', 'd', 'y', '3', 'q', '5', 't', '7',
					'r' },
			{ 'f', 'r', '%', 'v', '2', '9', 'p', '.', 'x', 's', 'j', '4', 'y', 'o', 'u', '!', '?', 't', 'n', 'l', 'd',
					'3', 'z', '7', 'w', 'g', 'q', 'i', '5', 'a', 'k', 'c', '0', '8', '1', 'e', 'h', '6', '&', 'm', 'b',
					',' },
			{ '%', 'y', 'z', 'o', '4', 'd', 'q', 'i', 'c', 'm', 't', 'u', 'f', '9', '!', 's', '7', '2', 'g', 'b', 'x',
					'r', '&', '?', '0', '.', '1', 'w', '8', 'h', 'l', 'e', 'j', '6', '3', 'v', '5', 'p', 'n', 'k', ',',
					'a' },
			{ 'e', '0', 'q', 'y', 'd', 'g', ',', '4', 'v', '8', 'f', 'u', 'b', 'p', '.', 's', '6', 'a', 'x', 't', '%',
					'o', '1', '5', 'n', 'l', '3', '9', '!', 'm', 'j', 'h', 'k', 'i', 'w', 'c', '&', '?', '7', 'r', '2',
					'z' },
			{ 'c', 'p', '?', 'b', '4', 'w', 'y', '6', 'q', '7', '0', 's', '2', 'k', '.', 'd', '5', 'u', '3', 'o', 'x',
					'!', 't', 'r', '&', 'z', 'v', 'j', 'n', '1', 'm', ',', '8', 'e', 'i', 'l', 'h', '9', 'a', '%', 'f',
					'g' },
			{ 'z', 'x', '3', 'w', 'd', 's', '9', '&', 'j', ',', 't', '!', 'h', 'i', '5', '6', 'v', 'a', 'o', 'n', 'u',
					'7', '8', 'g', '%', 'y', 'q', 'm', 'p', '0', 'e', 'b', 'c', '.', '?', '1', 'k', 'l', 'r', '4', 'f',
					'2' },
			{ 'n', 't', '9', 'p', 's', 'x', 'a', '5', '8', 'u', 'h', 'i', '!', '.', 'e', 'g', '3', '7', 'b', 'd', '%',
					'r', 'v', 'l', 'q', 'z', 'c', '2', '6', 'y', 'w', 'k', '&', ',', 'm', 'j', '1', 'f', '0', '4', 'o',
					'?' },
			{ '?', 's', 'b', 'x', 'p', '.', 'd', 'g', '5', 'w', 'u', 'y', '%', 'e', '3', 'n', 'c', ',', 'r', 't', '8',
					'q', 'h', 'k', 'v', '2', 'o', '6', '!', '9', '7', 'f', '4', 'i', '1', '&', '0', 'l', 'j', 'a', 'z',
					'm' },
			{ 'c', 'k', 'n', '7', 'x', 't', '5', 'j', ',', '3', 'u', '8', 'y', '1', 'm', 'b', 'w', '9', 'e', '0', 'h',
					'd', 's', '!', '&', 'r', '4', '2', 'v', '?', 'p', '%', '6', 'i', 'a', 'f', 'l', 'q', 'g', 'z', '.',
					'o' },
			{ '5', 'u', '?', 'd', 't', 'g', 'h', '0', '1', 'f', '9', 'c', 'n', 'o', '&', 'p', '!', '6', 'k', '4', 'q',
					'j', '.', '8', 'l', 'v', ',', 'y', 'e', '2', '3', 'i', 'm', '%', 'a', '7', 'x', 'b', 'w', 'z', 'r',
					's' },
			{ '6', '7', 'r', '%', '3', '9', 'e', 't', '&', '8', '.', 'v', '5', 'd', '?', 's', 'm', 'h', 'n', ',', 'j',
					'u', '2', '!', 'p', 'w', 'o', 'x', 'z', 'f', 'c', 'l', '1', 'b', '0', 'g', 'k', 'q', 'y', 'a', 'i',
					'4' },
			{ 'b', ',', '3', 'm', 'f', '7', '9', 'e', '?', 'x', 'h', '4', '0', 'w', 'v', 'o', 'p', 'q', 'c', 'z', '8',
					'u', '%', '1', 'k', 't', 'i', 'y', '!', 'j', 'd', '6', '5', 'n', '2', 'a', 'g', 's', '.', 'r', '&',
					'l' },
			{ 'v', '4', 'i', 'f', 'm', 'w', 'j', '0', '.', 'd', 'y', 'u', '%', ',', 'n', '!', 'o', '9', 't', '2', '6',
					'?', '3', 'g', '1', 'r', 's', 'c', '8', '7', 'e', 'p', 'a', 'x', 'z', '&', 'b', 'k', 'l', 'h', 'q',
					'5' },
			{ '6', 'n', 'p', 'y', '?', 'w', 'd', 'c', 'u', '1', 'g', 'v', 'f', '5', '%', '4', 'l', '3', '7', '0', 'b',
					'q', ',', 'o', 'a', '2', 'h', 'i', '&', 'r', '.', '8', 't', '!', 'j', 'x', 'e', 'm', '9', 'z', 'k',
					's' },
			{ '7', 'y', '0', '?', 'h', 'b', 'f', '.', '6', 'q', 'o', 't', 'u', 'c', 'a', ',', 'i', 'w', 's', '!', 'm',
					'd', '4', '9', 'r', '8', 'x', '1', 'z', '2', 'k', 'n', 'e', '%', 'g', '&', 'j', '5', 'p', '3', 'l',
					'v' },
			{ 'd', '9', '%', '?', 'y', 't', 'j', 'e', 'u', 'p', 'a', '2', 'r', 'i', '!', 'b', '8', '3', 'q', 'x', '1',
					's', 'l', '7', 'n', 'h', '0', '&', '6', 'm', 'w', '5', 'g', '4', 'k', 'f', 'z', 'o', 'v', 'c', ',',
					'.' },
			{ '6', 'i', 't', '1', '&', 'b', '2', 'd', 'r', '3', 'p', 'o', 'l', '?', '.', 'e', 'f', 'c', 'm', '9', 'z',
					'g', 'h', '0', 'v', 'u', '5', 'a', 'q', 'j', 'w', 's', '4', '%', ',', '!', 'k', 'y', 'x', '8', '7',
					'n' },
			{ 'v', 'q', 'g', 'i', 'l', '2', 'p', '6', 'y', 'd', 'j', 'o', '5', 'f', '1', 'u', 't', '&', '3', '0', '8',
					'a', 'k', 'w', 'b', '9', ',', '%', 's', 'h', '?', '7', 'm', '4', 'r', 'c', 'n', 'z', '!', 'e', 'x',
					'.' },
			{ 'o', '!', 'r', 'p', '%', 'v', 'u', '5', '&', 'q', 'a', '0', '8', '2', 'x', '1', 'z', 'k', '?', 'w', 'd',
					'f', 'n', '3', 't', 'i', 'l', '.', 'c', '4', 'y', ',', '6', '9', 's', 'j', '7', 'b', 'g', 'e', 'h',
					'm' },
			{ '9', 'x', '.', '1', 'w', 'l', 'm', 'y', '&', 'e', 'v', '0', 'a', 't', '3', 'f', 'n', 'c', 's', '6', 'd',
					'7', 'p', 'g', '!', ',', '5', 'u', '%', 'h', 'o', 'b', '2', '8', 'q', 'j', 'k', 'i', '?', '4', 'z',
					'r' },
			{ '5', 'b', '6', 'r', 'x', 'm', '&', 'n', '?', 'e', 'c', 'f', 'q', 'd', 'g', 'o', '9', ',', 'h', '3', '1',
					'k', '2', 'i', 'v', 'u', 't', '7', '0', 's', 'z', 'l', 'j', 'p', '8', 'y', '.', '%', '!', 'w', '4',
					'a' },
			{ '%', '4', 'c', 'f', 'z', '?', 'm', '3', 'h', '!', '9', '2', 'r', '7', 'q', 's', 'v', '&', 'k', 'g', 'j',
					'o', 'y', '0', 'l', 't', 'n', 'a', 'e', 'w', '8', 'u', '1', '6', 'x', 'b', 'd', 'i', 'p', '.', '5',
					',' },
			{ 's', 'y', 'f', '?', 'l', '4', 'n', '8', 'c', '9', '6', 'e', 'b', '%', 'p', 'g', 'x', '&', 'i', ',', 'v',
					'5', '1', 'z', '7', 'q', 'm', '0', 'd', '!', 'r', 'o', 't', 'w', 'j', '2', 'u', '.', '3', 'k', 'h',
					'a' },
			{ 'l', 'w', 'o', '0', 'y', 'j', 'd', '&', 'a', 'q', '.', 'i', '5', '9', 'p', '7', ',', 'm', 'z', 's', 'b',
					'r', 'n', 'e', 't', '?', '!', '4', 'c', '1', '%', 'g', 'h', 'v', 'f', '2', 'u', 'x', '8', 'k', '3',
					'6' },
			{ '?', 'a', 'p', 'd', 'm', 'q', 'w', '8', 'h', '2', 'o', 'e', '1', '3', 's', 'u', 'b', '9', '.', '!', '&',
					'j', 'g', 'f', '6', 'y', 'v', 'z', 'k', 'n', '0', 'x', '4', '%', 't', 'r', 'c', ',', '5', '7', 'i',
					'l' },
			{ 'h', 'u', '&', 't', '1', 'm', '%', 'c', 'o', '?', '!', '.', 's', 'q', 'k', '2', 'e', 'g', 'l', 'z', ',',
					'i', '7', 'p', 'd', '5', 'x', '0', '8', 'j', 'a', '9', 'b', '3', 'r', '6', 'f', 'w', '4', 'v', 'n',
					'y' },
			{ 'o', '1', '5', '0', '!', 'i', 'w', ',', '?', 'q', 'p', 'z', 'c', '2', 'v', 'e', '7', '4', 'f', '6', 'y',
					'b', 'j', 'd', 'x', 'a', 'm', '&', 'g', '%', 'n', 'r', 't', 'l', '8', '9', '.', 'k', 's', 'h', '3',
					'u' },
			{ 'm', 'v', '&', 'l', 'e', 'o', 'c', '1', 'b', 'w', 'y', ',', '2', 'p', 'r', 'f', '4', 'k', 'j', 's', 'g',
					'a', 'q', '3', '5', '8', 'n', '?', 'x', '0', '6', 'i', 't', 'h', 'd', 'u', '!', '%', '9', 'z', '.',
					'7' },
			{ 'r', 'k', 'c', 'i', 'm', '1', 'f', 'n', '?', '6', 'g', '2', ',', 'v', '.', 's', 'u', 'a', 'x', '7', 'q',
					'3', 'y', 'l', 'h', 'p', '&', 'w', '!', '%', '8', 'b', 'e', '4', 'j', 'd', 'o', 't', '9', 'z', '0',
					'5' },
			{ 'c', '1', 'l', '&', '?', 'y', '0', 'v', 'o', 'x', '7', '!', 'z', 'b', 'm', 'g', '.', 'u', '6', '4', 'n',
					'k', ',', 'w', 'r', 'j', '%', '2', 'a', '3', 'q', '9', 'h', 'f', '8', 'e', 't', 'p', 'i', 'd', '5',
					's' },
			{ 'j', 'l', 'm', 'p', 's', 'h', 'o', 'i', '%', '8', '.', 'w', 'n', 'b', 'v', 'd', 't', '9', 'y', 'q', '5',
					'7', '2', 'f', 'z', '0', 'c', 'g', '&', 'u', 'r', 'x', '4', '!', '3', '6', 'e', '1', 'k', 'a', '?',
					',' },
			{ 'p', 'x', '6', 'o', 'm', '8', 'y', 'v', 'b', 'w', '.', 'a', '9', '%', '4', 'u', '5', 'n', '0', 'f', '1',
					'i', 'h', '!', 'r', 'g', '3', '7', 'd', '&', 'j', 'k', '2', 't', 'e', 'l', '?', 'c', 's', 'q', 'z',
					',' },
			{ 'v', '?', '0', 'n', 'a', '6', '9', '3', 'h', 'e', 'l', 'b', '7', ',', 'm', '2', 'u', 'g', 'y', 's', 'k',
					'q', 'j', '%', 'f', '8', 'd', '&', 'i', 't', 'p', '4', 'c', 'o', '1', 'w', '!', 'z', 'x', '.', 'r',
					'5' },
			{ 's', ',', 'j', '2', '&', 'l', 'e', 'a', '4', 'c', 'p', '7', '6', 'f', '0', 'h', 'r', 'm', 'q', 'y', 'g',
					'%', 'i', '!', '5', '8', 'v', 't', 'u', '.', 'n', '9', '1', 'x', 'b', 'z', 'd', 'o', '3', 'w', 'k',
					'?' },
			{ 'r', 'p', 'e', 'z', 'x', 'm', '5', 'i', '0', 'c', 'y', '6', 'g', '4', 'w', 'l', 'n', '?', '&', 't', 'j',
					's', '.', '1', 'k', ',', '9', 'h', '!', '8', 'd', '7', 'a', 'o', '%', 'q', '2', 'u', 'b', 'f', 'v',
					'3' },
			{ '3', 'p', 'u', 'q', 'f', 'b', '0', 'h', 'r', '5', '2', 'x', '?', '4', '1', '!', '8', '&', 'o', 'j', 'v',
					'a', ',', '9', 't', '6', '7', '%', 'd', 'k', 'c', 'y', '.', 'i', 'n', 'g', 'e', 'l', 'w', 'm', 'z',
					's' },
			{ 'y', 'e', 'g', 'q', 's', 'n', '1', '%', 'm', 't', '!', 'l', 'z', 'w', 'u', '4', 'd', 'j', 'f', 'v', 'k',
					',', '7', '3', 'a', 'p', '?', '9', '0', 'o', '2', '8', 'h', '5', 'c', '6', '&', 'i', '.', 'r', 'b',
					'x' },
			{ 'c', '.', 'x', 'j', 'g', 'h', 'p', 'o', 'f', 'b', '!', '4', '3', '7', 'e', '5', '?', 'd', 'w', 's', 'k',
					'z', 'm', 'q', ',', 'v', 'y', 't', 'i', 'a', '%', 'u', '0', '9', 'n', 'r', '8', '&', '6', '2', '1',
					'l' },
			{ 'l', 'd', 'q', '!', 'm', '7', 'u', '%', 'o', ',', '5', '8', 'j', 'e', 'h', 'x', 'g', '6', 't', 'b', 's',
					'0', 'c', 'z', '3', '2', 'k', '1', '.', 'y', 'w', '?', '&', 'a', '4', 'v', 'f', '9', 'n', 'p', 'i',
					'r' },
			{ 'i', 'd', '?', 'r', '0', 't', '1', '4', 'z', '8', '6', 'q', 'f', '.', 'u', ',', 'n', 'e', 'j', '3', 'c',
					'h', '7', 'k', '9', 'y', 'm', 'l', 's', 'b', 'a', 'p', '%', '5', 'o', 'g', '!', '&', 'v', 'x', 'w',
					'2' },
			{ 'f', '6', '&', 't', '%', 'i', 'z', 's', 'w', 'n', 'm', '9', '2', 'r', '1', '8', 'b', 'g', 'l', 'u', 'q',
					'a', 'v', 'd', 'p', 'y', '!', 'o', '.', 'h', '5', 'c', ',', 'e', '3', '4', '?', '7', 'x', 'k', '0',
					'j' },
			{ 'l', 't', 'x', 'm', 'd', '1', 'i', 'a', '&', ',', '!', '6', 'q', 'y', '.', '2', '%', '0', 'u', 'k', '5',
					'8', 'w', '4', 'r', 'c', 'j', '7', '9', '?', 'v', 'o', 'b', 'e', 'z', '3', 'g', 's', 'h', 'p', 'f',
					'n' },
			{ '7', 'f', 'm', '0', '%', '3', ',', '6', 'w', '.', 'v', '&', 'n', 'h', 'k', 'q', '4', '8', 'l', 't', 'b',
					'z', '5', '!', 's', 'o', 'a', '9', '?', 'p', 'e', 'u', 'g', 'd', 'i', 'r', 'c', 'j', 'x', '1', 'y',
					'2' },
			{ 'c', 'e', 'b', 'n', 'a', 'u', '7', '%', 'z', '!', '9', '6', 'd', 'i', 's', '?', 't', 'j', '4', '5', 'm',
					'y', 'x', 'f', ',', '0', 'k', '2', 'p', 'o', 'g', '.', 'v', 'h', '8', '&', '1', 'q', 'l', 'w', '3',
					'r' },
			{ 'u', 'c', '0', 'y', ',', 'h', 'a', '6', 'v', 'k', 'i', 'f', 's', '8', '%', 'w', 'o', '2', 'z', 'x', 't',
					'?', '9', '!', 'g', '1', '3', '.', 'n', '&', 'j', '4', 'q', 'b', '7', '5', 'p', 'd', 'e', 'm', 'l',
					'r' },
			{ '9', '.', '6', 'h', 'g', 'u', '4', '&', '5', '8', 't', 'x', 'c', '2', 'b', 'y', 'z', '!', 'p', 'w', '?',
					'j', 'f', 'r', 'k', '1', 'm', ',', 'n', 'q', '%', 'e', 'd', 'v', 'a', '7', 's', 'l', 'o', '0', 'i',
					'3' },
			{ 'a', 'e', 'j', 'k', '0', '4', 'w', 't', '2', 'r', '3', '%', 'o', 'l', '7', 'h', 'y', '5', 'i', '6', 'q',
					'z', 'm', '1', 'b', 'c', ',', '&', 'n', 'u', 'v', '!', '?', 'x', '9', '.', '8', 's', 'f', 'g', 'p',
					'd' },
			{ '?', 'e', '&', 'r', 'a', 'f', '4', ',', '2', 'i', 'g', 'd', 'l', 'h', 'u', 'o', '9', '5', '1', 't', '!',
					'w', 'v', '6', 'j', 'x', 's', 'c', 'p', '.', 'q', 'b', '8', '0', 'z', '7', 'y', '%', '3', 'm', 'k',
					'n' },
			{ 'u', ',', '6', 'f', '%', '4', 'n', '1', 'i', 'q', '7', 'g', 'r', 'o', 'd', 'k', 'b', 'a', 'e', 's', 't',
					'v', 'x', '5', '.', 'c', 'h', 'l', '2', '!', '0', '8', '3', '?', 'm', '&', 'z', 'j', 'p', '9', 'y',
					'w' },
			{ '!', 'k', '.', 'h', ',', '?', 'u', '0', '2', 'r', 't', 'b', 'y', '5', 'x', '7', '3', '1', 'a', 'l', '%',
					'd', 'n', 'w', '&', '4', 'g', 'j', 'v', 'm', 's', '6', 'q', '8', 'z', 'f', 'c', 'p', 'o', 'i', 'e',
					'9' },
			{ '3', '5', 'a', 'p', 'l', '6', '8', 'n', '0', 'j', 'w', '4', '7', 'g', '.', 'k', 'c', 'q', 's', '!', 'd',
					'?', '9', '%', 'i', 'v', 't', ',', 'u', 'f', 'y', '&', 'x', 'r', '1', 'm', 'o', 'z', '2', 'e', 'b',
					'h' },
			{ '7', 'g', 'z', 'd', 'l', 'k', 'c', 'n', 'q', '5', 'j', 'm', '?', 'y', '.', 'a', 'r', 'f', 'v', '%', 'u',
					'8', 's', 'e', '9', '&', 'b', 't', 'i', 'x', '3', '1', '6', 'o', 'w', 'p', 'h', '0', '2', '4', ',',
					'!' },
			{ 'f', 'k', 's', 'q', '%', '7', '8', 'b', 'm', '.', '3', '2', 'x', '4', 'o', 'h', '1', 'i', 'z', '!', 'r',
					'a', 'u', 'n', 'p', ',', '0', '&', 'l', 'j', 'c', '9', 'y', 'd', '5', 'v', 't', '?', 'e', '6', 'w',
					'g' },
			{ 'b', '?', 'p', 'n', 'h', 'e', '1', '4', '5', 'u', '%', '7', 'r', '2', 'a', 't', '&', 'q', 'm', 'x', 'y',
					'8', 'j', 'i', 'z', 'c', '.', '6', ',', 'o', '9', 'v', 'k', 'w', 'l', 'g', 'd', '3', '!', '0', 's',
					'f' },
			{ 'u', 'k', 'b', '1', 't', 'j', '2', 'n', '0', 'h', '7', ',', '%', 'y', '?', 'm', '6', 'v', '8', 'e', 'i',
					'x', 'c', 'q', '.', '9', 'l', 'w', '3', 'z', '5', 'r', 's', 'o', '&', 'p', 'd', '!', 'f', '4', 'g',
					'a' },
			{ '1', 'g', '3', 'f', '5', 'm', 'i', 'q', '9', 'e', 'h', 'n', 'l', '&', '0', '8', 'v', '.', 'x', 's', '!',
					'c', 'u', 'a', 'b', ',', '6', 'w', 'k', 'r', 'p', '4', '2', 'd', '?', '%', 'o', 'y', 'j', 't', '7',
					'z' },
			{ 'v', '2', 'z', 'e', 'j', '9', '7', 'm', 'k', '1', '0', 'y', '8', '4', 's', 'd', '.', 'n', '?', 'f', '%',
					'b', 'a', 'c', 'i', 'p', '5', 'x', '&', 'l', 'q', '3', 'o', 't', 'u', 'h', 'r', '6', 'g', '!', ',',
					'w' },
			{ 'x', 'v', '1', 'n', 'm', 'w', '?', 'l', 'a', 'z', '4', 'j', '0', '6', '2', 'r', '5', 'e', ',', 'g', 'i',
					'8', 'y', '.', 'c', 'o', 'f', 't', '3', 'u', 'q', 'd', 'k', 's', 'p', '!', 'h', '9', '7', '&', 'b',
					'%' },
			{ 'f', 'h', '.', 'l', '%', '6', '5', '1', 'y', '7', 'z', '0', '2', 'e', 'b', 's', 'g', ',', 'c', 'r', 'u',
					'w', '4', '9', 'k', 'p', 'q', 'm', 'o', 'n', '!', 'i', 'j', 'a', 'd', 'x', '&', 't', '8', 'v', '?',
					'3' },
			{ 'j', 't', 'r', '3', 'f', '5', '.', ',', 'l', 'u', 'p', '7', 'o', '%', '8', 'm', 's', '6', 'e', '9', 'v',
					'n', '?', 'z', 'h', 'k', 'b', '1', 'g', '0', 'q', '&', 'i', 'd', 'w', '4', '2', '!', 'x', 'y', 'a',
					'c' },
			{ '3', '%', '!', 'm', 'a', 'n', ',', 'h', 'o', 'v', 'p', 'k', 'f', '7', '?', '1', 't', 'i', 'r', '5', 'j',
					'6', 's', 'x', 'e', 'w', 'g', 'l', 'c', 'y', '2', '.', 'u', '0', '8', 'z', 'd', 'b', '&', '9', 'q',
					'4' },
			{ '7', '3', 'h', 'v', 'p', '!', 'm', '1', 'b', 'g', 'w', 'z', 'f', 's', '5', 'k', 'x', '2', 'r', '9', '&',
					'c', '%', 'e', 'o', 'q', 'n', ',', '6', '0', 'u', 't', 'j', '4', '8', '?', '.', 'a', 'd', 'i', 'l',
					'y' },
			{ 'g', 'k', 'x', 'o', 'q', '%', '1', 'j', 'b', '&', '?', 'h', '7', '5', '0', 'e', '9', '2', 't', 'c', '8',
					'f', '6', 'a', '!', ',', '3', 'v', 'u', 'n', 'm', 'z', '4', 'r', '.', 'l', 'y', 'p', 'w', 'i', 's',
					'd' },
			{ '4', '&', 'g', '?', '0', 'i', '8', 'd', '1', 'e', 't', 'v', ',', 'm', 'x', 'n', 's', 'q', '6', 'l', '9',
					'j', '.', 'r', '7', '%', 'u', 'h', '!', 'o', 'c', 'b', 'a', '5', 'z', 'f', '2', 'y', '3', 'p', 'k',
					'w' },
			{ 'g', 's', 'r', '1', 'b', 'd', '8', 'i', 'o', 'l', '!', 'x', '3', 'z', ',', 'w', '9', 'h', '7', 't', 'j',
					'p', 'n', '2', 'q', 'f', '?', '&', 'c', 'a', '5', 'e', '6', '%', 'y', 'm', '0', 'v', '.', 'u', 'k',
					'4' },
			{ 'v', 'h', '7', 'a', 'r', '1', 'o', 'b', '2', 'd', 'm', 's', 'n', 't', '?', 'f', '%', 'x', '4', 'j', '&',
					'e', '9', '!', 'y', 'u', '8', ',', 'k', 'p', 'l', '.', 'q', '6', 'i', 'z', 'w', '5', 'c', 'g', '3',
					'0' },
			{ 'n', '5', 'm', 'f', 't', 'k', '!', '1', 'c', '9', 'v', 'r', '4', 'i', '7', 'b', 'z', '8', '2', 'p', ',',
					'?', 's', 'u', 'h', 'a', 'e', 'w', '3', 'l', '6', 'x', '.', 'o', 'g', 'd', 'y', 'j', 'q', '0', '%',
					'&' },
			{ '7', 'x', '0', 'u', '8', 'w', 's', '6', '9', '&', '?', 'm', '1', ',', '2', '%', 'p', 'c', 'b', 'j', 'o',
					'v', '!', 't', '3', '.', 'n', 'd', 'y', 'z', 'i', 'r', 'g', 'a', 'q', '4', 'k', 'l', 'e', '5', 'h',
					'f' },
			{ 'i', 'e', 's', '1', 'm', '6', '4', '9', 'h', 'r', 'd', 'z', '7', 'p', 'k', '3', 't', '5', '%', 'f', '&',
					'c', 'b', '0', '2', 'q', 'u', 'w', 'g', 'l', '8', '.', 'v', 'j', 'a', '!', 'n', ',', '?', 'y', 'x',
					'o' },
			{ 'p', 'z', '4', 'm', '9', '6', '.', 'h', 'r', 'e', 'b', '?', '!', 'u', '1', 'o', 'y', '&', 't', '8', 'k',
					'g', 'a', '5', 'w', 'd', 's', 'x', 'f', 'l', '7', 'c', 'i', '2', ',', '%', '0', 'n', '3', 'v', 'j',
					'q' },
			{ 'x', '9', 'n', 'z', 'o', '%', 'v', '3', 'd', '4', '!', 'm', 'u', '.', 'a', 'p', 'f', 'w', 'r', ',', '2',
					'c', '0', 'e', 'b', 's', 'y', '6', 'h', '1', 'l', '8', 'g', 'i', 'k', '7', 't', '5', '&', 'j', '?',
					'q' },
			{ 'd', ',', '&', 'e', 's', '!', '3', 'y', 'v', 'p', '?', '%', '1', 'i', '.', 'l', '2', '6', '8', 'x', 'c',
					'w', 'q', 'n', 'g', '4', 'm', 'k', '9', 'o', 'h', 'f', 't', 'j', '7', '5', 'a', '0', 'r', 'b', 'z',
					'u' },
			{ '3', 'd', 'h', 'm', 'j', 'k', 'o', 'g', '%', 'e', '.', '4', 'u', 'n', 'v', 'i', '8', '2', 'z', 'x', 'p',
					'!', 'y', 'f', 'q', '9', '&', 'b', '5', 't', 'a', 'l', '1', '?', '7', '6', 's', '0', 'w', ',', 'r',
					'c' },
			{ '.', 'r', '1', '3', 'o', 'e', 'd', 'u', 'i', 't', '5', 'g', '4', ',', 'p', 'l', '2', '?', 'j', 'b', 'q',
					'n', 'v', 'w', '0', 'a', 'c', 'k', 'x', 'h', 'y', 'f', '6', '&', 's', 'z', '8', '!', '%', 'm', '9',
					'7' },
			{ '3', '1', '?', ',', '9', 'j', 'p', '%', 'e', 'v', 'h', '4', 'b', 'g', 'z', '.', 'c', '!', 'n', 's', 'o',
					'5', 'r', 'w', '&', '2', '0', 'i', '6', 'm', 'f', 'x', '7', 'q', 'k', '8', 'l', 'd', 't', 'y', 'u',
					'a' },
			{ 'p', '&', '7', 'm', 'u', 'c', '1', 'i', 'z', '2', ',', 'w', 's', '.', 'f', '!', 'h', 'a', 'v', 'x', 'r',
					'0', 'd', 'g', '5', '?', 'b', 't', 'k', 'j', 'l', '8', '4', 'n', '3', '9', 'q', 'y', 'e', '6', 'o',
					'%' },
			{ '2', 'q', 'w', 'v', 'j', 'g', '0', 'p', 't', 'd', 'm', 'a', 'z', '6', '7', '%', 's', 'c', '9', '!', 'k',
					'y', '4', 'r', 'u', 'i', 'f', 'l', '3', 'b', '8', 'x', '&', 'h', '5', '1', '?', 'o', '.', 'n', ',',
					'e' } };
	int[] randomLevels = { 80, 67, 74, 79, 97, 31, 17, 64 };
	String[] levelKeys = { "q1", "v6", "b?", ".a", ",o", "!1", "c%", "l1" };
	char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z', ',', '.', '?', '&', '%', '!', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', '0', };

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
			int randomIndexToSwap = rand.nextInt(array.length);
			char temp = array[randomIndexToSwap];
			array[randomIndexToSwap] = array[i];
			array[i] = temp;
		}

		return array;
	}

	public String setLevel(String textIn) {
		level = randomLevels[findInArrayString(levelKeys, textIn.substring(0, 2))];
		return textIn.substring(2, textIn.length());
	}

	private void initialize() {
		/* start of frame initialization */
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
		//Generates new keys to be copied back into script for use
		makeNewKeys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// make mix up keys
				String out = "String[] keys ={";
				for (int i = 0; i < mixUpKeyCount; i++) {
					String keyString = "";
					Random rnd = new Random();
					for (int j = 0; j < 3; j++) {
						keyString = keyString + mixedUps[rnd.nextInt(mixedUps.length)][rnd.nextInt(alphabet.length)];
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
					temp = shuffle(alphabet);

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
						keyString = keyString + mixedUps[rnd.nextInt(mixedUps.length)][rnd.nextInt(alphabet.length)];
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
					int ecyrtptionLevelIndex = findInArrayString(levelKeys, temp.substring(0, 2));
					temp = temp.substring(2);

					for (int i = 0; i < randomLevels[ecyrtptionLevelIndex]; i++) {
						try {
							temp = deEnccrypt(temp);
						} catch (IndexOutOfBoundsException e) {
							encrypt.setSelected(true);
							deEncrypt.setSelected(false);
						}
					}
					copyToClipBoard(temp);
					// clipboard.setContents(stringSelection, null);
					Out.setText(temp);
				} else {
					Random rnd = new Random();

					// select random lvl key
					int levelSelectedIndex = rnd.nextInt(levelKeys.length);

					String temp = inString;
					for (int i = 0; i < randomLevels[levelSelectedIndex]; i++) {
						temp = encrypt(temp);
					}
					copyToClipBoard(levelKeys[levelSelectedIndex] + temp);
					// clipboard.setContents(stringSelection, null);
					Out.setText(levelKeys[levelSelectedIndex] + temp);
				}
			}
		});
	}

	void copyToClipBoard(String str) {
		StringSelection stringSelection = new StringSelection(str);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}

	String encrypt(String message) {
		String inString = message;
		Random rndRandom = new Random();
		// chooses mix up index
		int mixUpIndex = rndRandom.nextInt(keys.length);
		String outString = keys[mixUpIndex];
		// chooses the key that will be used for this level of encryption
		char[] keyToUse = mixedUps[mixUpIndex];
		// encrypts the string with the chosen key
		for (int i = 0; i < inString.toCharArray().length; i++) {
			if (inString.toCharArray()[i] != ' ') {
				outString = outString + Out.getText() + keyToUse[findInArray(alphabet, inString.toCharArray()[i])];
			} else {
				outString = outString + " ";
			}
		}
		return outString;
	}

	String deEnccrypt(String in) {
		String out = "";
		// finds which key was used for this encryption
		char[] keyUsed = mixedUps[findInArrayString(keys, in.substring(0, 3))];
		for (int i = 3; i < in.toCharArray().length; i++) {
			if (in.toCharArray()[i] != ' ') {
				out = out + alphabet[findInArray(keyUsed, in.toCharArray()[i])];
			} else {
				out = out + " ";
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
