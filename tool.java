
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
	// please note that spaces are ignored in the encryption process an spaces that
	// are present are there in the final message
	private JFrame frame;
	JTextArea Out = new JTextArea();
	JRadioButton deEncrypt = new JRadioButton("Decrypt");
	char[] temp;
	int level = 5;
	String[] keys = { "f&p", "jaq", "frj", "guh", "il!", "yko", "bmp", "v&s", "wnj", "mvk", "v!?", "!id", "?uh", "zir",
			"q.f", "eey", "z!n", "of?", "ie.", "d!k", "dok", "hvg", "h!z", "dim", "ziy", "pug", "xos", "kep", "tpj",
			"wha", "qs,", "wns", "p,p", "ww,", "&&u", ".!o", "pxj", "l,!", "fjs", "?ne", "jdx", "fin", "exe", "opo",
			"crn", "ipe", "ajo", "abc", "!no", "nz,", "m.h", "wqc", "e&z", "ugp", "phw", "amn", "vcg", "rc%", "w!o",
			"wky", "d&?", "s%b", "qwz", "jn?", "yck", "!ol", "lyt", "aug", "d!v", "yt?", "f,d", "mqd", "zjz", ",ae",
			".!y", "%z,", "p.z", ".t.", "i.g", "us&", "qb?", "v?b", "?tl", "puo", "um.", "%o&", "?hg", "mgs", ".el",
			",bu", "%iy", "rgc", "g&t", "wd,", "w!o", "m!z", "afd", "s&y", "wiu", "ast" };
	char[] alphebet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z', ',', '.', '?', '&', '%', '!' };
	char[][] mixedUps = {
			{ 'q', 'a', 'j', 'k', 'y', 'i', 'w', 'r', 'l', 'u', '?', '&', 'f', 'b', 'h', 'c', 'x', 'p', 'm', 'd', 'z',
					'v', 'n', 't', 's', '.', 'o', '%', 'e', 'g', '!', ',' },
			{ 'g', '?', 'a', 'h', 'v', 'c', 'w', 't', '%', 'n', 'm', 'd', 'i', 'x', 'z', 'o', 's', 'y', 'k', '!', 'p',
					'l', 'u', 'e', 'b', 'f', 'r', '.', '&', 'q', 'j', ',' },
			{ '.', 'o', 'q', 's', 'p', 'u', 'r', 'y', 'f', '?', 'e', 'b', '!', '&', 't', 'g', 'k', 'j', 'i', 'c', 'x',
					'n', 'z', 'm', 'w', 'h', 'd', 'v', '%', 'l', 'a', ',' },
			{ 't', 's', 'h', 'y', '%', 'f', '&', 'i', '!', 'z', '.', 'g', 'm', 'c', 'o', 'j', 'r', 'u', ',', 'x', 'q',
					'e', 'n', 'k', 'w', '?', 'p', 'a', 'd', 'v', 'b', 'l' },
			{ 'm', 'h', 'z', '.', 'x', 'u', 't', 'd', '&', 'k', 'f', '%', 'c', 'w', 'j', 's', 'p', 'q', 'v', 'n', '?',
					'y', '!', 'o', ',', 'i', 'b', 'e', 'g', 'a', 'l', 'r' },
			{ 'q', 'u', '?', 'v', 'y', 'b', '%', 'a', 't', 'c', 'd', 'h', 's', 'm', 'p', 'n', 'f', '!', 'j', 'w', 'z',
					'i', 'l', 'e', 'x', '&', 'o', 'g', ',', '.', 'r', 'k' },
			{ 'f', 'i', '?', 'm', 'j', 'u', 'q', 'g', 'n', 'p', 'z', '!', 'k', 'w', 'a', 'o', '%', 's', 'v', 'c', ',',
					'y', 'd', '.', '&', 'l', 'b', 'r', 't', 'x', 'e', 'h' },
			{ 'n', 'y', 'q', 'g', 's', 'a', 'w', 'p', '!', 'x', 'v', '?', 'i', '&', 't', 'j', 'k', 'z', 'd', 'l', 'e',
					'm', '.', 'c', 'u', 'h', 'f', '%', 'r', 'b', 'o', ',' },
			{ 'w', 'q', 'h', 'd', 'a', 'k', 'l', 'r', 's', 'o', 'n', 'c', 'j', 'y', 'e', 'z', 'u', 'v', '.', 'f', 't',
					'&', 'm', ',', 'i', 'g', 'b', 'x', '?', 'p', '%', '!' },
			{ 'a', 'z', '.', 'j', 'v', 'k', 'h', 'f', 'i', 'o', 'l', 'w', 'd', 'q', '?', 'e', '&', 't', 'c', 'x', 'm',
					'y', 'r', 'p', 'b', '!', 's', 'g', '%', 'u', 'n', ',' },
			{ '&', 's', 'z', 'f', 'x', '%', 'l', 'm', 'd', 'c', 'p', '?', ',', '.', 'y', 'b', 'q', 'g', '!', 't', 'w',
					'u', 'o', 'n', 'v', 'a', 'h', 'e', 'r', 'i', 'k', 'j' },
			{ 'q', 'a', ',', 'b', 's', 't', 'c', 'z', 'p', '%', 'w', 'r', 'f', 'v', 'x', 'u', 'l', 'g', 'y', 'd', '!',
					'.', 'h', 'm', 'k', 'o', 'n', '?', 'e', 'j', '&', 'i' },
			{ 'j', '.', 'l', 'z', 'h', 'f', 's', 'k', 't', 'x', 'e', '&', '?', 'm', 'i', 'w', 'c', 'a', 'o', 'q', 'v',
					'%', 'n', 'r', 'p', ',', 'y', 'g', 'u', 'b', 'd', '!' },
			{ 'f', '.', 'e', 'd', 'q', 'i', 'k', 'a', 'x', 'c', 'r', 'b', 'n', ',', 'm', '&', '?', 'g', 'w', 'p', '%',
					'h', 't', 'v', 'y', 's', 'j', 'o', '!', 'z', 'u', 'l' },
			{ 'j', '.', ',', 'k', 'e', 'm', 'b', 'd', '&', 'n', 'f', 's', '%', 'q', 'a', 'g', '?', 'u', 'v', 'c', 'r',
					't', 'y', 'w', 'l', 'o', 'i', 'x', '!', 'p', 'z', 'h' },
			{ 'x', 'r', 'l', 's', 'p', 'y', 'w', 'o', 'f', 'z', '?', 'i', 't', '%', '.', 'a', '!', 'j', ',', 'e', 'u',
					'h', 'n', 'd', '&', 'c', 'g', 'v', 'b', 'k', 'q', 'm' },
			{ 't', 'h', 'n', 'r', 'e', '.', 'k', 'f', 'i', 'z', 'x', '?', 'c', '&', '!', '%', 'p', ',', 's', 'm', 'v',
					'j', 'g', 'l', 'a', 'd', 'q', 'o', 'b', 'w', 'u', 'y' },
			{ 'n', 'o', 'k', 'e', 'h', 's', 'v', 'p', 'l', 'y', 'd', ',', '%', 't', 'z', '!', 'x', '?', 'b', 'r', 'f',
					'.', 'a', 'w', 'i', 'c', 'j', 'q', 'u', '&', 'g', 'm' },
			{ 'k', '.', 'p', '?', 'y', 'v', '%', 'h', 'u', 'x', 'm', 'z', 'l', 'd', 'o', 's', 'n', '!', 'f', 'j', 'i',
					'r', 'g', 'c', '&', ',', 'b', 't', 'w', 'q', 'e', 'a' },
			{ 'k', 'u', 'q', 'd', 'z', 'v', 'g', 'b', 'i', 's', 'e', 'x', '.', '&', '%', 't', 'h', ',', 'm', 'l', 'y',
					'r', 'p', 'w', 'o', '!', '?', 'j', 'n', 'c', 'f', 'a' },
			{ 'y', 'a', 'm', '.', 'z', 'x', 'd', 'f', 'h', 'l', 'p', '%', 'r', 'e', 'k', 'o', 'i', '&', 'n', 'v', 't',
					'!', ',', 's', 'b', 'c', '?', 'q', 'w', 'g', 'j', 'u' },
			{ 'x', 'k', '%', 'r', 'o', 'q', '&', 't', 'n', 'u', 'd', 'a', 'j', 'v', ',', 'b', 'w', '.', 'p', '?', 's',
					'g', 'y', 'h', 'm', 'c', 'f', 'z', '!', 'i', 'e', 'l' },
			{ 'a', 't', 'v', 'r', 'g', '!', ',', 'i', 'y', '?', 'x', 'z', 'h', 'q', 'c', 'l', 'n', '&', 'u', 'm', 'b',
					'o', 'w', 'e', '.', 'f', 'p', 'j', 's', 'k', '%', 'd' },
			{ '%', 'o', 'n', 'e', 'u', 't', 'k', 'b', 'a', 'z', '?', 'd', 'g', 'h', '&', 'w', 'q', 'x', '!', ',', 'j',
					'r', 'f', 'c', 'p', 'v', 'y', 'i', 'l', '.', 's', 'm' },
			{ 'o', '.', '?', 'n', 't', 'c', 'v', 'b', 'd', '&', 'h', 'm', 'w', 'r', 'y', 'g', '!', 'e', 'i', ',', 'l',
					'%', 'k', 'u', 'p', 'z', 'x', 'q', 's', 'j', 'f', 'a' },
			{ 'a', 'v', 'e', 'w', '%', '!', 'k', 'q', 'f', 'l', 'm', 't', 's', 'u', 'b', '.', 'j', 'r', ',', 'o', 'x',
					'p', 'i', '?', 'h', 'y', '&', 'd', 'c', 'n', 'g', 'z' },
			{ 'g', 'q', '?', 'e', ',', 'j', 'z', '!', '.', '%', 'u', 'k', 'o', 'x', 'f', 'l', 'w', 't', 'a', 'p', 'd',
					'm', 'y', 'r', '&', 'i', 'n', 'v', 'b', 's', 'h', 'c' },
			{ 's', 'x', 'u', '!', 'p', 'f', '.', 'c', 'm', 'i', 'b', 'r', 'l', 'n', 'e', 'g', '%', '?', 'w', 'z', 'y',
					',', 'a', 'h', 'q', 'j', 'k', 'o', '&', 'd', 't', 'v' },
			{ 'o', 'f', ',', 'q', '!', 'k', 'z', 'b', 'v', 'c', 'l', 'd', 'h', '.', 's', 'i', 'g', 'y', '?', '&', 'w',
					'a', '%', 'r', 'x', 'n', 'p', 'e', 'j', 'u', 't', 'm' },
			{ 'b', '!', 'f', 'l', 'c', 'z', 's', 'p', 'r', 'g', 'e', 'i', 'x', '?', 'o', 'y', 'j', 'k', '%', 'n', 't',
					'u', 'a', '.', ',', '&', 'd', 'w', 'q', 'm', 'v', 'h' },
			{ '!', ',', 'e', 'd', 'p', 'b', 'w', 'z', 'c', 'x', 'g', 'o', '?', '.', 'l', 'k', 'f', 'a', 's', 'm', 'i',
					'%', 'j', '&', 'r', 'q', 'n', 'y', 'h', 'u', 'v', 't' },
			{ 'f', 'o', 'w', 'k', 'u', 'q', 'n', 't', 'x', '?', 'y', '&', '!', 's', 'v', 'r', 'j', 'm', 'g', 'l', ',',
					'h', 'i', 'e', '.', '%', 'a', 'p', 'b', 'c', 'd', 'z' },
			{ 'x', 'l', '.', ',', 'w', 'n', 'g', 'o', 'u', 'p', 'a', 'r', 'z', 's', 'y', 'v', '!', 'q', 'm', 'k', '?',
					'b', '%', 'd', 'c', 'e', 'j', 'f', 't', 'h', 'i', '&' },
			{ 'n', '?', 'g', 'z', 'k', 'b', '&', 'r', 't', '.', 'h', 'v', 'o', 'x', 'y', 'e', 'q', 'i', '!', 'f', 'm',
					'd', 'j', 'w', '%', 'c', 'p', 'a', 's', 'l', 'u', ',' },
			{ 'b', 'd', 'g', '?', 'c', 'n', 'm', 'l', 's', 'k', ',', 'f', 'y', 'r', 'j', 'w', '&', 'x', 'q', 'v', '.',
					'p', 'e', 'u', 'h', '%', 'i', '!', 'z', 't', 'o', 'a' },
			{ 'm', 'n', 'y', 'r', '!', 't', 'p', 'g', '?', 'h', 'w', 's', 'x', 'a', ',', 'k', 'v', 'e', 'c', '.', 'b',
					'j', '%', 'f', 'z', 'i', 'o', 'q', 'd', 'l', '&', 'u' },
			{ 'p', 'n', 'w', 'x', 'a', 'i', 'z', 'v', 'h', 's', 'o', 'b', 'c', 'g', 'j', '%', 'r', '&', 'm', 'u', 'k',
					'l', '.', 'y', 'f', ',', 't', 'e', 'd', '?', 'q', '!' },
			{ '?', 'j', 'y', 'n', 'v', 'w', 'b', 'o', 'f', 'i', '&', 'c', 't', 'd', 's', '.', 'p', '%', 'm', 'e', 'r',
					'l', 'x', 'z', 'k', '!', 'h', ',', 'u', 'a', 'g', 'q' },
			{ 'x', 'w', ',', 'f', 'y', 'u', 'i', 'v', 'q', 'o', 'e', 'a', 'l', 'r', 'g', '!', '&', 'h', '?', 'j', 'n',
					'd', 't', '%', 'z', '.', 'c', 'm', 'b', 'k', 'p', 's' },
			{ 'a', 'g', 'f', 'p', '?', 'z', 'o', 'r', 'h', ',', '&', 'i', 'l', 'q', '!', 't', 'e', 'n', '.', 'k', 's',
					'w', 'b', 'y', 'v', 'x', '%', 'j', 'u', 'd', 'm', 'c' },
			{ 'v', 's', 'u', 'w', 'j', ',', '%', 'x', 'n', 'i', 'e', 'b', '!', '&', 'p', 'r', '.', 'a', 'k', 'm', 'g',
					'l', 'd', 'o', 't', 'c', 'h', 'y', 'q', 'z', 'f', '?' },
			{ 'o', '?', 'h', 'p', 'x', 'c', ',', 'e', 'q', 'a', 'f', 's', '.', '%', 'u', '&', 'd', 'l', 'z', 'k', 'g',
					'v', 'y', 'b', 't', 'w', 'n', '!', 'r', 'j', 'm', 'i' },
			{ 's', 'v', 'j', '%', 'f', 'k', 'e', 'a', 'u', ',', 'o', 'd', '?', 'x', 'r', '.', 'n', 'm', 'g', 'i', 'w',
					'!', 'h', 'p', 'b', 'c', 'y', 'l', 't', '&', 'z', 'q' },
			{ 'v', 'e', 'j', '&', '.', 'o', 'n', 'm', 'd', '!', 'f', 'c', 't', 'l', 'x', 'w', 'i', 'u', 'k', 'b', 'r',
					'q', '%', 'z', ',', 'p', '?', 's', 'y', 'h', 'a', 'g' },
			{ '!', 'c', 'b', ',', 'x', 'p', '.', 'n', 'q', 'j', 'e', 'u', 'k', 'm', 'y', 'h', 'd', 'v', '%', 'w', 'o',
					'?', 'z', '&', 'l', 'a', 's', 'i', 'f', 't', 'r', 'g' },
			{ ',', 'v', 'w', '!', 'l', '?', 's', '%', 'b', 'c', 'p', 'e', 'o', 'h', 'z', 'k', 'x', '&', '.', 'y', 'j',
					'q', 'f', 'r', 'm', 't', 'a', 'u', 'd', 'n', 'i', 'g' },
			{ 'h', 'g', 's', '&', ',', 'l', 'f', 'r', 'c', '!', 'k', 'v', 'a', 'e', '%', 'x', 'j', 'q', '.', 't', 'y',
					'o', 'z', 'n', 'w', 'b', 'u', 'p', 'd', '?', 'i', 'm' },
			{ 'i', 'w', 'd', '%', 'z', 'o', 'e', 'a', 'b', 'g', 'q', 'p', 'j', 'c', 'y', 'h', 'l', 'n', ',', 't', 'f',
					'!', 's', 'm', 'u', 'v', 'r', 'k', '.', 'x', '&', '?' },
			{ 'z', 'a', 'd', 'e', 'q', 'v', 'o', 'x', ',', 'p', 'f', 'g', 't', 'u', 'c', 'n', '.', 'k', 's', 'w', 'y',
					'm', '?', 'i', '&', 'j', 'b', '%', 'h', 'l', 'r', '!' },
			{ '?', 'g', ',', 'v', 'c', 'p', 'l', '.', 'f', '&', 'a', 'k', 'r', 's', 'q', 'z', 't', 'o', 'm', 'j', 'x',
					'i', 'u', 'n', '%', 'w', '!', 'b', 'y', 'h', 'e', 'd' },
			{ 'a', 'u', 'g', 'v', 't', 'p', 's', '?', 'd', 'z', ',', 'l', 'o', 'q', '&', 'x', 'i', 'f', 'y', 'h', 'n',
					'j', 'c', 'w', 'e', '.', 'r', '%', 'k', 'b', 'm', '!' },
			{ 'a', 'i', '?', 'm', 'b', 'l', 'o', 'd', 'w', 'x', 't', 'f', 'z', '%', 'c', 'u', '.', '!', 'q', ',', 'v',
					'n', 'e', 'j', 'r', 'g', 'k', 'h', 's', 'p', 'y', '&' },
			{ 'c', 'h', 'a', 'r', 'y', 'm', 'i', '%', 'q', 'e', 'd', 'o', 'x', '!', 'w', 'k', '.', 'l', 'v', 'j', 'g',
					's', 't', 'p', 'n', 'b', 'f', '?', ',', 'u', 'z', '&' },
			{ 't', 'a', 'e', 'g', 'x', 'v', '%', 'y', 'b', '.', 'z', 'd', 'o', 'h', 'q', 's', 'r', 'j', 'u', 'k', 'p',
					'w', ',', '!', 'c', '&', 'f', 'n', 'i', '?', 'm', 'l' },
			{ '&', 'c', 'e', 'j', 't', 'k', 'm', 'p', 'h', '.', 'z', 'd', 'q', 'u', 'r', '?', 'i', 'y', 'x', 'f', 'g',
					'l', '!', 'b', 'v', 'o', ',', 's', 'n', 'w', '%', 'a' },
			{ 'l', 'j', 'y', 'f', 'p', 'r', 'z', 'x', 's', 'n', '&', ',', 'h', 'm', 'i', 'a', 'u', '?', 't', '!', 'c',
					'd', 'g', '.', '%', 'o', 'k', 'v', 'b', 'w', 'q', 'e' },
			{ 'k', ',', 'r', 'b', 'w', 'n', 'a', 'u', 'j', '?', 't', '%', 'c', 'p', 'y', 'v', 'z', 'h', 'o', '&', '.',
					'f', 'i', '!', 'l', 'd', 'm', 'x', 'q', 'e', 's', 'g' },
			{ 'h', 'k', '?', 'd', 's', 'x', 'e', 'u', 'f', 'w', 'v', '.', 'i', 't', 'b', '&', 'n', 'c', '%', 'z', 'y',
					'p', 'g', '!', 'r', 'o', 'm', 'q', 'j', 'a', ',', 'l' },
			{ 'p', 'k', ',', 'z', 'f', '?', 'w', 'e', 'm', 'a', 'v', 'q', '&', 'x', 'i', 'g', 's', 'l', '.', 'c', 'h',
					't', 'o', '%', 'j', 'b', 'd', 'r', 'u', 'n', 'y', '!' },
			{ ',', '%', 'k', 'r', 'a', '!', 'b', 'l', 'y', 'c', 'q', 'x', 'h', 'i', 'g', 's', 'z', 'j', 't', 'v', 'n',
					'p', 'w', 'e', 'm', '&', 'o', 'f', 'u', 'd', '?', '.' },
			{ 'm', 'r', '!', 'i', 'd', 'z', 'v', 'g', ',', '&', 'p', 'q', '.', 'a', 'b', 'h', 'c', '%', '?', 'e', 'f',
					'n', 't', 'k', 'u', 'w', 'y', 's', 'x', 'j', 'o', 'l' },
			{ '.', '&', 'd', 'c', 'i', 'h', 'u', ',', 'n', '!', 's', 'w', 'z', 'b', 'j', 'v', 'p', '%', 'f', 'y', 'm',
					'o', '?', 'q', 't', 'r', 'g', 'k', 'l', 'e', 'x', 'a' },
			{ 's', 'q', 'f', 'p', '&', 'o', 'z', 'd', 'b', 'v', 'l', 'c', 'j', 't', 'm', '.', '%', 'w', 'e', 'n', 'i',
					'a', '!', 'u', ',', 'y', 'x', 'k', '?', 'r', 'h', 'g' },
			{ 'j', 'y', 'c', '.', 't', 'b', 'v', 'd', ',', 'f', 'r', 'e', '&', 'l', 'a', 'i', 'm', 'n', 'u', 'o', 'x',
					'?', '!', 'g', 'h', 'z', 'k', 's', 'q', '%', 'p', 'w' },
			{ 'a', '%', 'c', 'h', 'o', 'y', '!', 'b', 'e', 'z', 'p', 'm', 'n', 'r', 'f', 'g', '?', 'u', 't', 'x', 'k',
					'&', ',', 'l', 's', 'd', 'j', 'q', 'v', 'i', 'w', '.' },
			{ 'l', 'a', 'n', 'y', 'b', 'h', ',', 'm', 'r', '%', '!', '.', '&', '?', 'x', 'c', 't', 'o', 'p', 'i', 'v',
					's', 'd', 'z', 'e', 'j', 'g', 'q', 'k', 'f', 'w', 'u' },
			{ ',', 'f', 'g', 'e', 's', 'l', 'j', 'y', '&', '.', 'u', 'x', 'c', 'i', 'b', 'h', '?', 'k', 't', 'd', 'r',
					'o', 'm', 'w', 'n', 'z', '%', 'v', '!', 'q', 'a', 'p' },
			{ 'w', '&', 'd', 'i', '%', 'b', 'c', 'h', 's', 'q', 'v', ',', 'j', 'n', 'u', 'a', 'y', 'o', 'z', 'g', '!',
					'?', 'r', 'l', 't', '.', 'e', 'p', 'f', 'm', 'x', 'k' },
			{ 'a', 'y', 'v', '%', '.', 'n', 'b', 'k', 'i', 'x', 'z', 'd', 'o', 'e', 'h', 'l', ',', '&', 'g', 's', 'q',
					'!', 'p', 'c', 'm', 'w', 't', 'f', 'u', '?', 'r', 'j' },
			{ 'm', 'o', 'a', 'f', 'j', 'q', 'g', 'l', 'u', 's', 'b', 'n', 'p', ',', 'c', '&', '?', '!', 'k', 'x', 'h',
					'w', '.', 'e', '%', 'r', 'y', 'd', 'z', 'v', 't', 'i' },
			{ 'u', 'k', 'b', '&', '?', '%', 'p', 'r', 'c', 'g', 'i', 'm', ',', 'n', 'd', 'h', 'f', 'z', 'j', 'a', 'e',
					'w', 's', '!', 'v', 'q', 't', 'l', 'o', 'y', 'x', '.' },
			{ 'i', 'b', 'y', 'k', 'z', 'd', 'f', 'c', 'x', 'm', 'j', 'h', 'p', 'l', 'u', 'o', '%', 'a', '&', 's', '?',
					'!', 'v', 'e', 'w', 'r', '.', 'n', 'g', ',', 't', 'q' },
			{ 'b', 'r', ',', 't', 'h', 'y', 'x', '!', 'n', 'e', 'k', 'd', '?', 's', '%', '&', 'j', 'f', 'c', 'm', 'g',
					'a', 'q', 'o', 'p', 'z', 'v', 'i', 'l', 'u', '.', 'w' },
			{ 'a', '!', 'e', 'l', 'c', 'o', 'h', 'b', 'k', 'f', 'g', 's', 'm', 'v', 'q', '&', 'u', 'p', 'd', 'x', 'n',
					'w', 'j', '?', 'i', 'z', '.', 'r', 't', 'y', '%', ',' },
			{ 'h', '!', ',', 'b', 't', 'k', 'i', 'g', 'o', 'x', 'v', 'l', 'd', '.', 'j', 'm', 'z', 'n', 'a', 'u', 'e',
					'%', 'q', '?', 's', 'y', '&', 'p', 'c', 'f', 'r', 'w' },
			{ '?', 'o', 'q', 'a', 'i', 'w', '!', 'k', 'z', 'g', 'j', '&', 's', 'y', 'h', ',', 'u', 'm', 'd', 'n', 'b',
					'x', 'e', 'p', '%', 't', 'f', 'c', 'v', '.', 'l', 'r' },
			{ 'q', 't', 'i', 'v', 'p', 'h', 'y', 'r', 'd', '&', 'o', 'x', 'w', 'n', 'u', 'z', 'l', '.', 'b', 'f', '%',
					'g', 'k', 's', '!', 'm', ',', 'a', 'e', 'j', 'c', '?' },
			{ 'v', 'p', 'b', 'k', 'f', ',', 'x', 'u', 's', 'h', '&', 'd', 'e', 'j', 'a', 'w', '%', 't', 'z', '?', 'l',
					'g', '.', 'n', 'r', '!', 'i', 'q', 'm', 'o', 'c', 'y' },
			{ 'q', '%', 'p', 'e', 'y', 'n', '.', 'f', '&', 'd', 'u', 'o', 't', 'c', ',', 'v', 'z', 'k', '?', 'm', 'l',
					'a', 'w', 'h', '!', 'j', 'x', 'b', 'i', 'g', 'r', 's' },
			{ 'b', '%', 't', '!', 'j', 'd', '?', 'x', 'g', 'c', 'o', 'l', 'e', '.', 'q', 'p', 'a', 'm', 'n', 's', 'z',
					'k', 'w', ',', 'u', 'i', 'r', '&', 'f', 'h', 'y', 'v' },
			{ '?', 'l', 'a', 'h', 'x', '&', ',', 'n', 'z', '%', '.', 'g', 'e', 't', 'o', 'v', 's', 'r', 'd', 'k', 'w',
					'y', 'c', 'u', 'j', 'i', '!', 'b', 'q', 'm', 'f', 'p' },
			{ 'l', 'h', 'a', 'z', '&', 'p', 'f', 'y', 'c', '%', 'x', 'u', 's', 'n', 'd', 'j', 'k', 'w', 'q', 'o', 'g',
					'm', 'e', '!', 'v', 'b', 't', '.', ',', 'r', '?', 'i' },
			{ 'x', 'v', 'y', 'k', 'l', 'g', ',', 'c', 'j', 'r', 'f', '.', 'o', 'a', 'm', 't', 'w', 'h', 'u', 'b', 'q',
					'e', 'i', 'z', '?', '!', '%', 'n', 'p', '&', 's', 'd' },
			{ '&', 'd', 'w', 'j', '!', 'g', 'n', '%', 'f', 'c', '?', 'q', 't', ',', 'r', 'm', '.', 'i', 'v', 'p', 'x',
					'k', 'a', 'z', 'e', 'o', 'h', 'u', 'y', 's', 'l', 'b' },
			{ 'g', '&', 's', 'e', 'w', 'r', 'm', 'n', '?', 'p', 'c', 'v', ',', 'a', 'b', 'k', 'u', 'o', 'l', '%', 'h',
					'q', 't', 'x', 'y', 'i', 'j', 'z', '.', 'f', 'd', '!' },
			{ 'v', 'c', 'r', 'z', 't', 'm', 'g', ',', '&', 's', 'w', 'n', '?', 'p', 'a', 'l', 'b', '!', 'j', 'e', 'x',
					'%', 'u', 'y', 'i', 'o', 'h', 'f', 'd', 'q', '.', 'k' },
			{ 'g', 'r', 's', 'w', 'z', 'j', 'v', 'e', 'q', '.', 'b', 'f', 'o', 't', 'p', '?', 'y', '&', 'h', 'c', 'd',
					'%', 'x', ',', 'n', 'm', 'k', 'l', 'u', 'i', 'a', '!' },
			{ 'b', '&', 'w', 'f', 'h', 'a', 'z', '?', 'n', 'v', 'c', 'e', 'k', 'i', 'g', 'y', 't', 'x', '!', 's', 'o',
					'%', 'd', 'j', 'q', 'r', 'u', '.', 'm', 'p', 'l', ',' },
			{ 'z', 'h', 'w', 'a', '?', 'n', 'm', 'd', 'v', '.', 'f', 't', 'c', 's', '&', 'q', 'g', 'p', '!', '%', 'r',
					'o', 'j', ',', 'u', 'x', 'i', 'l', 'y', 'b', 'e', 'k' },
			{ 'f', 'v', 'l', 'e', 'w', 'p', 'g', 'z', 'a', 'i', '?', ',', 'x', 't', 'd', 'u', '%', 'j', 'y', '!', 'c',
					'q', 's', 'k', 'o', 'b', 'm', 'h', 'r', '&', '.', 'n' },
			{ 't', 'p', 'o', 'j', 'z', 'e', 'i', 'g', 'b', 'c', 'q', 'y', '?', ',', 'r', 'v', 'd', 'm', 'u', 'a', '&',
					'f', 's', 'l', '%', 'x', 'n', 'h', 'k', '!', '.', 'w' },
			{ 'i', 'v', 'l', 'y', 'p', 'k', 'c', 's', 'm', 'q', '?', 'o', '.', '%', 'x', 'h', 'b', 'z', '&', 'a', 'n',
					't', ',', 'j', 'e', 'u', '!', 'w', 'f', 'g', 'd', 'r' },
			{ 'c', '%', 'o', '.', 'h', 'y', ',', 'z', 'v', '?', 'k', 'q', '&', 'l', 'm', 'x', 's', 'e', 'd', 'n', 't',
					'j', 'f', 'i', 'w', '!', 'b', 'r', 'p', 'g', 'u', 'a' },
			{ 'r', '&', 'q', 'h', 'a', 'e', '%', '!', 'f', 'w', 'x', 'g', '.', 'v', 's', 'm', 'z', 'd', 'b', 'j', '?',
					't', 'i', 'u', 'k', 'o', ',', 'p', 'n', 'y', 'l', 'c' },
			{ 'v', 'i', 'a', 'k', 's', '.', 'd', 'w', 'r', 'f', 'e', '?', '&', 'y', 'p', 'z', 'n', 'b', '%', 'q', 'o',
					'h', '!', 'm', 'j', 'x', 'u', 'c', 'g', 'l', 't', ',' },
			{ 'c', 'h', 'i', 'r', 'x', 'n', 'v', 'y', 'z', '%', 'w', 'o', 't', 's', 'g', '&', '.', '!', 'u', 'j', 'b',
					'?', 'd', 'k', 'l', 'm', 'a', ',', 'q', 'f', 'e', 'p' },
			{ 'b', 'y', 'v', 'f', 'j', 'z', 'u', 'o', 'n', 'e', 'l', ',', '&', 'q', 'g', 'm', '%', 'w', 'k', '.', 'd',
					'a', 'x', 't', 'h', 'p', 'i', '?', 's', 'r', 'c', '!' },
			{ 'a', 'j', 'k', 'g', 'v', '?', 'q', 's', 'u', 'e', 'p', 'b', '&', 'z', '%', ',', 'l', 'r', 'f', 'c', '.',
					'i', 'h', 'x', 'y', '!', 't', 'n', 'o', 'm', 'w', 'd' },
			{ 'i', '?', 'l', 'y', 's', 'k', 'a', ',', 'e', 'o', 'q', 'c', 't', 'n', 'w', '&', 'r', 'u', 'b', 'j', 'g',
					'p', 'f', '.', 'z', 'h', 'v', '!', '%', 'd', 'm', 'x' },
			{ 'f', 'b', 's', 'l', '.', 'a', 'k', 'n', 'e', '?', 'x', 'j', 'p', '&', 't', 'z', 'y', 'd', 'w', 'c', 'r',
					',', 'g', 'q', 'u', 'i', '!', '%', 'o', 'h', 'v', 'm' } };

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
		int keyCount = 100;
		for (int y = 0; y < keyCount; y++) {
			System.out.print("{");
			temp = shuffle(alphebet);
			for (int i = 0; i < temp.length; i++) {

				System.out.print("'" + temp[i] + "'");
				if (i != temp.length - 1) {
					System.out.print(",");
				}
			}
			System.out.print("}\n");
			if (y != keyCount - 1) {
				System.out.print(",");
			}
		}
		System.out.print("{");
		for (int i = 0; i < keyCount; i++) {
			String keyString = "";
			Random rnd = new Random();
			for (int j = 0; j < 3; j++) {
				keyString = keyString + mixedUps[rnd.nextInt(mixedUps.length)][rnd.nextInt(alphebet.length)];
			}
			System.out.print("\"" + keyString + "\"");
			if (i != keyCount - 1) {
				System.out.print(",");
			}
		}
		System.out.print("}");
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
				String inString = In.getText().toLowerCase();
				Out.setText("");

				if (deEncrypt.isSelected()) {
					String temp = inString;
					for (int i = 0; i < level; i++) {
						temp = deEnccrypt(temp);
					}
					Out.setText(temp);
				} else {
					String temp = inString;
					for (int i = 0; i < level; i++) {
						temp = encrypt(temp);
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
				outString = outString + Out.getText() + keyToUse[findInArray(alphebet, inString.toCharArray()[i])];
			} else {
				outString = outString + " ";
			}
		}
		return outString;
	}

	String deEnccrypt(String in) {
		String out = "";
		char[] keyUsed = mixedUps[findInArrayString(keys, in.substring(0, 3))];
		for (int i = 3; i < in.toCharArray().length; i++) {
			if (in.toCharArray()[i] != ' ') {
				out = out + alphebet[findInArray(keyUsed, in.toCharArray()[i])];
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
